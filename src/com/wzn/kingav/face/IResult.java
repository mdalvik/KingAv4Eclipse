package com.wzn.kingav.face;

import java.util.List;

public interface IResult<T> {
	public void onSucess(List<T> list);
	public void onError(Throwable t);

}
