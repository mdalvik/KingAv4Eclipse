package com.wzb.kingav.work;

import com.wzb.kingav.global.GlobalVariable;

import okhttp3.HttpUrl;
import retrofit2.Retrofit;

public class NetWrok {
	
	public <T> T create(final Class<T> service) {
	
		Retrofit retrofit =new Retrofit.Builder()
				.baseUrl(GlobalVariable.NET_ADDRESS)
				.build();
		
		return retrofit.create(service);
	}
	
	public Retrofit.Builder getBaseBuilder()
	{
	   return new Retrofit.Builder().baseUrl(GlobalVariable.NET_ADDRESS);
	}
	
	class Bulder{
		
		
	}

}
