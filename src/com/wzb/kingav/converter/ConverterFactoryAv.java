package com.wzb.kingav.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.wzn.kingav.bean.MenuBean;
import com.wzn.kingav.bean.VideoBean;
import com.wzn.kingav.bean.VideoPlayBean;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Converter.Factory;
import retrofit2.Retrofit;

public class ConverterFactoryAv extends Factory {

	@Override
	public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit)
	{
	    // TODO Auto-generated method stub
		// class sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
		if (type instanceof ParameterizedType) {

			ParameterizedType parameterizedType = (ParameterizedType) type;
			Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
  //判断用哪个Converter
			if (actualTypeArguments.length > 0) {
				if (actualTypeArguments[0] == VideoBean.class) {
					return new ConverterVideo();
				} else if (actualTypeArguments[0] == MenuBean.class) {
					return new ConverterMenu();
				} else if (actualTypeArguments[0] == VideoPlayBean.class) {
					return new ConverterVideoPlay();
				}
			}

		}
		return null;
	
	}

}
