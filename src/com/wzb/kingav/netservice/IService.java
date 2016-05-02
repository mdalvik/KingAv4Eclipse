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
	 * ��ȡ���˵���Ŀ
	 * @return
	 */
  @GET("/")
  Call<List<MenuBean>> getListMenu();
  @GET("/{path}/{recent}/{page}")
  /**
   * ��ȡҳ�沥������
   * @param path
   * @param recent
   * @param page
   * @return
   */
   Call<List<VideoBean>> getListVideo(@Path("path")String path,@Path("recent")String recent,@Path("page")String page);
  
  /**
   * ��ȡ���ŵ�ַ
   * @param path
   * @return
   */
  @GET("/{path}")
   Call<VideoPlayBean> getVideoPlay(@Path("path")String path);

}
