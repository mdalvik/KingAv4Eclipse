package com.wzb.kingav.converter;

import java.io.IOException;
import java.util.List;

import com.wzb.kingav.parse.Engine;
import com.wzb.kingav.parse.TaoBaoParseEngine;
import com.wzn.kingav.bean.VideoBean;
import com.wzn.kingav.bean.VideoPlayBean;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class ConverterVideoPlay implements Converter<ResponseBody, VideoPlayBean> {

	@Override
	public VideoPlayBean convert(ResponseBody value) throws IOException {
	
		return Engine.getEngine().ParseVedioPlayAddress(value.string());
	}

}
