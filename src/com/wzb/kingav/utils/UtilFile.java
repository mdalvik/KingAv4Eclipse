package com.wzb.kingav.utils;

import java.util.ArrayList;
import java.util.List;

import com.wzn.kingav.bean.FileDownBean;

public class UtilFile {
	public static synchronized List<FileDownBean> getRanges(final long fileSize, final int threadCount) {
		List<FileDownBean> list = new ArrayList<>();
		long singleSize = fileSize / threadCount;
		final String flag = "bytes=";
		for (int i = 1, j = 0; i <= threadCount; i++, j++) {
			FileDownBean fBean = new FileDownBean();
			String range = "";
			if (i == threadCount)
			{
				
			    range = flag + ((singleSize * j+1) + "-" + (fileSize));
				fBean.setRange(range);
				fBean.setEnd(fileSize);
				fBean.setStart(singleSize * j+1);
				fBean.setSize(fileSize-(singleSize * j+1));
			} 
			else if(i==1)
			{
				range = flag + (singleSize * j) + "-" + (singleSize * i);
				fBean.setRange(range);
				fBean.setEnd(fileSize);
				fBean.setStart(singleSize * j);
				fBean.setSize(singleSize * i);
			}
			else {
				range = flag + (singleSize * j+1) + "-" + (singleSize * i);
				fBean.setRange(range);
				fBean.setEnd(fileSize);
				fBean.setStart(singleSize * j+1);
				fBean.setSize(singleSize * i-(singleSize * j+1));
			}
			list.add(fBean);
		}
		return list;
	}
  public static synchronized String Url2FileName(String str)
  {
	  
	  String[] split = str.split("/");
	  if(split!=null&&split.length>1)
	  {
		  return split[split.length-1];
	  }
	  return "";
  }
}
