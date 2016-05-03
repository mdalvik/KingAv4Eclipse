package com.wzn.kingav.presenter;

import com.wzn.kingav.bean.MenuBean;
import com.wzn.kingav.bean.VideoBean;
import com.wzn.kingav.face.IResult;


public interface  HomePresenter {
	public interface view 
	{
		
		public void reviceVideo(IResult<VideoBean> listener);
		
		public void reviceInitDate(IResult<MenuBean> listenerMenu,IResult<VideoBean> listenerVideo);
	
	    
	}
	public interface model
	{
		 public void getListMenu(String url,IResult<MenuBean> listener);
	     
		 public void getListVideo(String path,String recent,String page,IResult<VideoBean> listener);
		 
	     public void getMenuAndVideo(String url,IResult<MenuBean> listenerMenu,IResult<VideoBean> listenerVideo);
	     
	     
	}

	

}
