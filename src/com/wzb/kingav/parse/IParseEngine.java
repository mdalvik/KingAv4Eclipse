package com.wzb.kingav.parse;

import java.util.List;

import com.wzn.kingav.bean.MenuBean;
import com.wzn.kingav.bean.VideoBean;
import com.wzn.kingav.bean.VideoPlayBean;

public interface IParseEngine {
	
	 List<MenuBean> ParseMenu(String html);
	
	 List<VideoBean> ParseVedio(String html);
	 
	
	 VideoPlayBean  ParseVedioPlayAddress(String html);
}
