package com.wzn.kingav.model;

import com.wzn.kingav.bean.MenuBean;
import com.wzn.kingav.face.IResult;

public interface IListMenu {
   public void getListMenu(String url,IResult<MenuBean> list);
}
