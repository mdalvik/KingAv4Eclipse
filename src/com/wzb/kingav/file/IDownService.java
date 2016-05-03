package com.wzb.kingav.file;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface IDownService {
	//http://www.avtaobao.cc/templates/defboot/images/logo.png
	@Streaming
	//@Headers("Range:bytes=0-499")
	@GET()
	Call<ResponseBody> downFile(@Url String url,@Header("Range") String lang);

	/**
	 * 获取文件大小
	 * @param url
	 * @return
	 */
   @Streaming
   @GET
   Call<ResponseBody> getDownFileSize(@Url String url);
}
