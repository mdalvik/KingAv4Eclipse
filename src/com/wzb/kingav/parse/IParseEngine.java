package com.wzb.kingav.parse;

import java.util.List;

import com.wzn.kingav.bean.MenuBean;
import com.wzn.kingav.bean.VideoBean;
import com.wzn.kingav.bean.VideoPlayBean;

public interface IParseEngine {
	/**
	 * �����˵�
	 * @param html
	 * @return
	 */
	 List<MenuBean> ParseMenu(String html);
	 /**
	  * ����ҳ���е�vedio
	  * @param html
	  * @return
	  */
	 List<VideoBean> ParseVedio(String html);
	 
	 
	 /**
	  * ������Ƶ�еĲ��ŵ�ַ
	  * @param html
	  * @return
	  */
	 VideoPlayBean  ParseVedioPlayAddress(String html);
}
