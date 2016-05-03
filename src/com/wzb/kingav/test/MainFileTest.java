package com.wzb.kingav.test;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import retrofit2.Retrofit;

public class MainFileTest {

	public static void main(String[] args) throws Exception {
		
	//Retrofit retrofit =new Retrofit.Builder().callFactory(factory)
		List<String> ranges = getRanges(10000, 10);
		System.out.println(ranges);
		
	
	}

	public static synchronized List<String> getRanges(final long fileSize, final int threadCount) {
		List<String> list = new ArrayList<>();
		long singleSize = fileSize / threadCount;
		final String flag = "bytes=";
		for (int i = 1, j = 0; i <= threadCount; i++, j++) {
			String range = "";
			if (i == threadCount)
			{
				range = flag + ((singleSize * j+1) + "-" + (fileSize));
			} 
			else if(i==1)
			{
				range = flag + (singleSize * j) + "-" + (singleSize * i);
			}
			else {
				range = flag + (singleSize * j+1) + "-" + (singleSize * i);
			}
			list.add(range);
		}
		return list;
	}

}
