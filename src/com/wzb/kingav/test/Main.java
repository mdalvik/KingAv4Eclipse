package com.wzb.kingav.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import com.wzb.kingav.converter.ConverterFactoryMenu;
import com.wzb.kingav.converter.ConverterFactoryVideo;
import com.wzb.kingav.converter.ConverterFactoryVideoPlay;
import com.wzb.kingav.global.GlobalVariable;
import com.wzb.kingav.netservice.IDownService;
import com.wzb.kingav.netservice.IService;
import com.wzb.kingav.utils.UtilFile;
import com.wzn.kingav.bean.FileDownBean;
import com.wzn.kingav.bean.MenuBean;
import com.wzn.kingav.bean.VideoBean;
import com.wzn.kingav.bean.VideoPlayBean;

import okhttp3.ConnectionPool;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.Interceptor.Chain;
import okhttp3.internal.Util;
import retrofit2.Call;import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;import retrofit2.http.Path;

public class Main {
	//http://img4.imgtn.bdimg.com/it/u=4236942158,2307642402&fm=21&gp=0.jpg
	static String teString ="http://www.avtaobao.cc/2309/%E5%92%8C%E9%81%B5%E4%B9%89%E7%9A%84%E5%89%8D%E5%A5%B3%E5%8F%8B%E4%B9%8B%E5%89%8D%E7%9A%84%E6%93%8D%E9%80%BC%E8%A7%86%E9%A2%91-%E6%98%AF%E6%97%B6%E5%80%99%E5%8F%91%E5%87%BA%E6%9D%A5%E4%BA%86/";
	public static void main(String[] args) throws Exception{
	     //http://dlsw.baidu.com/sw-search-sp%2Fsoft%2Fca%2F13442%2FThunder_dl_7.9.43.5054.1456898740.exe
		 //http://dlsw.baidu.com/sw-search-sp/soft/ca/13442/Thunder_dl_7.9.43.5054.1456898740.exe
		Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress("192.168.1.106", 8888));

	

		OkHttpClient client = new OkHttpClient.Builder().build();

	
		  Executor executor  = new ThreadPoolExecutor(10, Integer.MAX_VALUE,  
                  0L, TimeUnit.MILLISECONDS,  
                  new LinkedBlockingQueue<Runnable>());
		Retrofit retrofit = new Retrofit.Builder().callbackExecutor(executor).client(client).baseUrl("http://dlsw.baidu.com/").build();
	
		IDownService service = retrofit.create(IDownService.class);
		long fileSize=19995914;
		int threadCount=10;
		RandomAccessFile ra =new RandomAccessFile("D:/11.exe", "rw");
		ra.setLength(fileSize);
		List<FileDownBean> ranges = UtilFile.getRanges(fileSize, threadCount);
	
		System.out.println(ranges);
	
		for(int i=0;i<threadCount;i++)
		{
			final FileDownBean fileDownBean = ranges.get(i);
			//http://www.avtaobao.cc/file/2302/1/d395857a0ddef9db4f69/1462235278/mp4/2302.mp4
			//http://dlsw.baidu.com/sw-search-sp/soft/47/10963/fiddler4_4.6.2.0_setup.1453708442.exe
			Call<ResponseBody> downVideo = service.downVideo("http://www.avtaobao.cc/file/2302/1/d395857a0ddef9db4f69/1462235278/mp4/2302.mp4",fileDownBean.getRange());
		      downVideo.enqueue(new Callback<ResponseBody>() {

					@Override
					public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
					 Headers headers = response.headers();
						System.out.println("开始下载了"+fileDownBean.getRange()+Thread.currentThread().getName());
							//System.out.println(headers.name(i));
							System.out.println(headers.get("Content-Range"));
							RandomAccessFile ra=null;
							
							try {
								ra=new RandomAccessFile("D:/11.exe", "rw");
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						
							try {
							
								ra.seek(fileDownBean.getStart());
								InputStream is = response.body().byteStream();
							   byte buf [] = new byte[2048];
							   int len =-1;
							   long size =0;
							   while((len=is.read(buf))!=-1)
							   {
								
								   ra.write(buf, 0, len); 
								 
							   }
							   System.out.println("第"+fileDownBean.getStart()+"线程执行完毕");
							   
						  
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
						//System.out.println(response.body().contentLength());
					}

					@Override
					public void onFailure(Call<ResponseBody> call, Throwable t) {
						// TODO Auto-generated method stub
					
						System.out.println("erro"+	fileDownBean.getStart());
					}
				});
		}
		
		
		System.out.println("--");
		
		//Retrofit retrofit = new Retrofit.Builder().baseUrl(GlobalVariable.NET_ADDRESS).addConverterFactory(new ConverterFactoryVideoPlay()).build();
		//IService create = retrofit.create(IService.class);
		//Call<VideoPlayBean> videoPlay = create.getVideoPlay("2309/%E5%92%8C%E9%81%B5%E4%B9%89%E7%9A%84%E5%89%8D%E5%A5%B3%E5%8F%8B%E4%B9%8B%E5%89%8D%E7%9A%84%E6%93%8D%E9%80%BC%E8%A7%86%E9%A2%91-%E6%98%AF%E6%97%B6%E5%80%99%E5%8F%91%E5%87%BA%E6%9D%A5%E4%BA%86/");
		//Response<VideoPlayBean> execute = videoPlay.execute();
		//System.out.println(execute.body());
	}

	private static Interceptor getInterceptor() {
		return new Interceptor() {
			
			@Override
			public okhttp3.Response intercept(Chain chain) throws IOException {
			
				okhttp3.Response proceed = chain.proceed(chain.request());
				
				System.out.println(proceed.body().contentLength());
				return proceed.newBuilder().body(new MyResponseBody(proceed.body())).build();
			}
		};
	}

	private static void test4() throws IOException {
		Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress("192.168.1.106", 8888));
		
		OkHttpClient client = new OkHttpClient.Builder().proxy(proxy).build();
		
		Retrofit retrofit = new Retrofit.Builder().baseUrl(GlobalVariable.NET_ADDRESS).addConverterFactory(new ConverterFactoryVideo()).build();
		
		IService service = retrofit.create(IService.class);
		//首页的话直接用/代替 path参数
		Call<List<VideoBean>> listVedio = service.getListVideo("/","recent","2");
		
		System.out.println(listVedio.execute().body());
	}

	private static void tests() throws IOException {
		Document document = Jsoup.connect(GlobalVariable.NET_ADDRESS).get();
		   //nav nav-stacked navigation
		   Elements ul = document.select(".nav.nav-stacked.navigation").select("li");
		    List<MenuBean> list = new ArrayList<>();
		   for(int i=0;i<ul.size();i++)
		   {
			   Element li = ul.get(i);
			   Elements elementA = li.select("a");
			   MenuBean menuBean = new MenuBean();
			   menuBean.setName(elementA.first().ownText());
			   menuBean.setUrl( elementA.attr("href"));
			   if(elementA.first().childNodeSize()>1)
		   	   {
				  menuBean.setCount(elementA.first().child(0).text());
			   }
			  System.out.println(menuBean);
		   }
	}

}
