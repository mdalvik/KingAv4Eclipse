package com.wzb.kingav.netservice;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface IDownService {
	//http://www.avtaobao.cc/templates/defboot/images/logo.png
	@Streaming
	//@Headers("Range:bytes=0-499")
	@GET()
	Call<ResponseBody> downVideo(@Url String url,@Header("Range") String lang);


}
