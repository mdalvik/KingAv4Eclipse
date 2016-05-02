package com.wzb.kingav.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wzb.kingav.parse.Engine;
import com.wzn.kingav.bean.MenuBean;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public  final class ConverterMenu implements Converter<ResponseBody, List<MenuBean>> {

	@Override
	public List<MenuBean> convert(ResponseBody value) throws IOException {
	     
	
		return Engine.getEngine().ParseMenu(value.string());
	
	}

}
