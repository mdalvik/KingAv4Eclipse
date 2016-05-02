package com.wzb.kingav.netservice;

import java.util.List;

import com.wzn.kingav.bean.MenuBean;
import com.wzn.kingav.bean.VideoBean;
import com.wzn.kingav.bean.VideoPlayBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface IService {
	/**
	 * 获取左侧菜单条目
	 * @return
	 */
  @GET("/")
  Call<List<MenuBean>> getListMenu();
  @GET("/{path}/{recent}/{page}")
  /**
   * 获取页面播放数据
   * @param path
   * @param recent
   * @param page
   * @return
   */
   Call<List<VideoBean>> getListVideo(@Path("path")String path,@Path("recent")String recent,@Path("page")String page);
  
  /**
   * 获取播放地址
   * @param path
   * @return
   */
  @GET("/{path}")
   Call<VideoPlayBean> getVideoPlay(@Path("path")String path);

}
