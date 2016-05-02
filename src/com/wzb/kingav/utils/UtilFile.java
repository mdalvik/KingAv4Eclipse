package com.wzb.kingav.utils;

import java.util.ArrayList;
import java.util.List;

import com.wzn.kingav.bean.FileDownBean;

public class UtilFile {
	public static synchronized List<FileDownBean> getRanges(final long fileSize, final int threadCount) {
		List<FileDownBean> list = new ArrayList<>();
		long singleSize = fileSize / threadCount;// 每条线程需要负责
		final String flag = "bytes=";
		for (int i = 1, j = 0; i <= threadCount; i++, j++) {
			FileDownBean fBean = new FileDownBean();
			String range = "";
			if (i == threadCount)// 最后一条线程处理
			{
				
			    range = flag + ((singleSize * j) + "-" + (fileSize));
				fBean.setRange(range);
				fBean.setEnd(fileSize);
				fBean.setStart(singleSize * j);
				fBean.setSize(fileSize-singleSize * j);
			} else {
				range = flag + (singleSize * j) + "-" + (singleSize * i);
				fBean.setRange(range);
				fBean.setEnd(fileSize);
				fBean.setStart(singleSize * j);
				fBean.setSize(singleSize * i);
			}
			list.add(fBean);
		}
		return list;
	}

}
