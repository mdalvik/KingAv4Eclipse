package com.wzb.kingav.file;

import java.util.HashMap;
import java.util.Map;

import com.wzb.kingav.file.IFile.onDownFileCallback;
import com.wzb.kingav.utils.UtilFile;

public  final class DownLoadManager {
	static  final DownLoadManager downLoadManager = new DownLoadManager();
	static final int DownThreadCount = 5;
	private Map<String,String>  fileMap=  new HashMap<>();
	private DownLoadManager()
	{
		
	}
 public static  final DownLoadManager Manager()
 {
	 return downLoadManager;
 }

	private String diskPath ="d:\\";
	
//	/"http://dlsw.baidu.com/sw-search-sp/soft/ca/13442/Thunder_dl_7.9.43.5054.1456898740.exe"
	public boolean addDownTask(String url,onDownFileCallback callback)
	{

		IDownLoadEngine downLoadEngine = new DownLoadEngine();
		String filename = UtilFile.Url2FileName(url);
		if(filename.equals(""))
		{
			System.out.println("文件名获取失败");
			return  false;
		}
		if(fileMap.containsKey(url))
		{
			System.out.println("改链接正在下载");
			return false;
			
		}
		fileMap.put(url, filename);
		FileModel fileModel = new FileModel
				(
				url, 
				diskPath+filename, 
				DownThreadCount);
		
		downLoadEngine.startDownloadingTask(fileModel, new onDownFileCallback() {
			
			@Override
			public void onFail(Throwable t,FileModel fileModel) {
				// TODO Auto-generated method stub
				fileMap.remove(fileModel.getUrl());
				if(callback!=null)
				{
					callback.onFail(t, fileModel);
				}
				System.out.println("---"+t.getMessage());
				
			}
			
			@Override
			public void onDownload(long fileSize, long downloadprogress) {
			System.out.println("文件大小"+fileSize+",下载进度"+downloadprogress);
			if(callback!=null)
			{
				callback.onDownload(fileSize, downloadprogress);
			}
			}
			
			@Override
			public void onComplete(FileModel fileModel) {
				System.out.println("下载完成了"+fileModel.getUrl());
				fileMap.remove(fileModel.getUrl());
				if(callback!=null)
				{
					callback.onComplete(fileModel);
				}
				System.out.println(fileMap.size());
			}
		});
	 return true;
	}
	
}
