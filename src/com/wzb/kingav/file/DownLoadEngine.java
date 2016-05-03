package com.wzb.kingav.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.net.Proxy.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.wzb.kingav.file.IFile.onDownFileCallback;
import com.wzb.kingav.utils.UtilFile;
import com.wzn.kingav.bean.FileDownBean;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DownLoadEngine implements IDownLoadEngine {
	private OkHttpClient okHttpclient;
	private Executor executor;
	/**
	 * 文件总大小
	 */
	private long fileSize;
	/**
	 * 已经下载完成进度
	 */
	private long downloadingSize;
	/**
	 * 文件监听回调
	 */
	private onDownFileCallback downFileCallback;
	/**
	 * 下载地址
	 */
	private String downUrl;
	
	//更新回调时间
	private static final  int UPDATE_INTERVAL= 1*1000;
	/**
	 * 线程个数
	 */
	private int threadCount = 5;
	// 完成线程个数
	private int completeThread;

	private String filePath;
	
	private FileModel fileModel;
	
	private List<Call<ResponseBody>> mListCall = new ArrayList<>();

	public DownLoadEngine() {

	}
	enum DownState{
		CANCEL,DOWNING,COMPLETE
		
	}
	private boolean isCancel = false;

	@Override
	public void startDownloadingTask(FileModel model, onDownFileCallback callback) {
		this.downUrl = model.getUrl();
		this.downFileCallback = callback;
		this.threadCount = model.getDownThreadCount();
	    this.filePath=model.getFilePath();
	    this.fileModel = model;
		Retrofit retrofit = new Retrofit.Builder().baseUrl("http://www.baidu.com/").build();
        IDownService service = retrofit.create(IDownService.class);
		Call<ResponseBody> downFileSize = service.getDownFileSize(downUrl);
		downFileSize.enqueue(new Callback<ResponseBody>() {

			@Override
			public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

				// 获取文件大小
				long fSize = response.body().contentLength();
				try {
					startDownload(downUrl, threadCount, fSize, filePath);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					downFileCallback.onFail(new Exception("下载过程出现错误"),fileModel);
				}
			}

			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {

				downFileCallback.onFail(new Exception("文件大小获取失败"),fileModel);
			}
		});

	}
	
 private  long upDateTime  = 0; 
	private void startDownload(String url, int threadCount, long fileSize, final String filePath) throws Exception {
	
		
		
		ThreadPoolExecutor executor = new ThreadPoolExecutor(threadCount, Integer.MAX_VALUE, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());

		Retrofit retrofit = new Retrofit.Builder().callbackExecutor(executor).baseUrl("http://www.baidu.com/").build();

		IDownService service = retrofit.create(IDownService.class);
		// 创建文件
		RandomAccessFile ra = new RandomAccessFile(filePath, "rw");
		ra.setLength(fileSize);
		ra.close();
		// 分割文件请求头
		List<FileDownBean> ranges = UtilFile.getRanges(fileSize, threadCount);
		System.out.println(ranges);

		mListCall.clear();
		for (int i = 0; i < threadCount; i++) {
			final FileDownBean fileDownBean = ranges.get(i);
			
			
			Call<ResponseBody> downVideo = service.downFile(url, fileDownBean.getRange());
			mListCall.add(downVideo);
            downVideo.enqueue(new Callback<ResponseBody>() {
				@Override
				public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
					Headers headers = response.headers();
					System.out.println(headers.get("Content-Range"));
					RandomAccessFile ra = null;
					try {
						ra = new RandomAccessFile(filePath, "rw");
						ra.seek(fileDownBean.getStart());
						InputStream is = response.body().byteStream();
						byte buf[] = new byte[2048];
						int len = -1;
						while ((len = is.read(buf)) != -1) {

							ra.write(buf, 0, len);
							synchronized (DownLoadEngine.this) {
								downloadingSize = downloadingSize + len;
								// 更新下载进度  1秒
								long  current =	 System.currentTimeMillis() ;
								 if((current- upDateTime) > UPDATE_INTERVAL)
								 {
									 upDateTime=current;
									 DownLoadEngine.this.downFileCallback.onDownload(fileSize, downloadingSize);
								 }
							}

						}
				

					} catch (IOException e) {

						e.printStackTrace();
					} finally {
						try {
							ra.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					synchronized (this) {
						completeThread++;
						
						if (downloadingSize == fileSize) {
							//下载完成
							DownLoadEngine.this.downFileCallback.onComplete(fileModel);

						}
					}

				}

				@Override
				public void onFailure(Call<ResponseBody> call, Throwable t) {

					// 下载失败
					synchronized (this) {
						if (!isCancel) {
							isCancel = true;
							for (int i = 0; i < mListCall.size(); i++) {
								Call<ResponseBody> call2 = mListCall.get(i);
								call2.cancel();
								mListCall.remove(call2);
							}
							DownLoadEngine.this.downFileCallback.onFail(new Exception("下载失败"),fileModel);
						}

					}

				}
			});
		}

	}

	/**
	 * 取消所有请求
	 */
	public void cancelAll() {
		for (Call call : mListCall) {
			call.cancel();
		}
	}

}
