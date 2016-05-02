package com.wzb.kingav.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Converter.Factory;
import retrofit2.Retrofit;

public class ConverterFactoryVideoPlay extends Factory {

	@Override
	public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
		// TODO Auto-generated method stub
		return new ConverterVideoPlay();
	}

	
}
