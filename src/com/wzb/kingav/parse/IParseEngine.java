package com.wzb.kingav.parse;

import java.util.List;

import com.wzn.kingav.bean.MenuBean;
import com.wzn.kingav.bean.VideoBean;
import com.wzn.kingav.bean.VideoPlayBean;

public interface IParseEngine {
	/**
	 * 解析菜单
	 * @param html
	 * @return
	 */
	 List<MenuBean> ParseMenu(String html);
	 /**
	  * 解析页面中的vedio
	  * @param html
	  * @return
	  */
	 List<VideoBean> ParseVedio(String html);
	 
	 
	 /**
	  * 解析视频中的播放地址
	  * @param html
	  * @return
	  */
	 VideoPlayBean  ParseVedioPlayAddress(String html);
}
