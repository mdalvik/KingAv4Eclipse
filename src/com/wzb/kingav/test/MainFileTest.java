package com.wzb.kingav.test;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public class MainFileTest {

	public static void main(String[] args) throws Exception {
		
	//Retrofit retrofit =new Retrofit.Builder().callFactory(factory)
	
	}

	public static synchronized List<String> getRanges(final long fileSize, final int threadCount) {
		List<String> list = new ArrayList<>();
		long singleSize = fileSize / threadCount;// 每条线程需要负责
		final String flag = "bytes=";
		for (int i = 1, j = 0; i <= threadCount; i++, j++) {
			String range = "";
			if (i == threadCount)// 最后一条线程处理
			{
				range = flag + ((singleSize * j) + "-" + (fileSize));
			} else {
				range = flag + (singleSize * j) + "-" + (singleSize * i);
			}
			list.add(range);
		}
		return list;
	}

}
