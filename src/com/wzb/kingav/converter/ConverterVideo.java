package com.wzb.kingav.converter;

import java.io.IOException;
import java.util.List;

import com.wzb.kingav.parse.Engine;
import com.wzb.kingav.parse.TaoBaoParseEngine;
import com.wzn.kingav.bean.VideoBean;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class ConverterVideo implements Converter<ResponseBody, List<VideoBean>> {

	@Override
	public List<VideoBean> convert(ResponseBody value) throws IOException {
	
		return Engine.getEngine().ParseVedio(value.string());
	}

}
