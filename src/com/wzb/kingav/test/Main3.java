package com.wzb.kingav.test;

import com.wzb.kingav.file.DownLoadEngine;
import com.wzb.kingav.file.DownLoadManager;
import com.wzb.kingav.file.FileModel;
import com.wzb.kingav.file.IDownLoadEngine;
import com.wzb.kingav.file.IFile.onDownFileCallback;

public class Main3 {
	
	public static void main(String[] args) {
//		/http://dlsw.baidu.com/sw-search-sp/soft/ca/13442/Thunder_dl_7.9.43.5054.1456898740.exe
		
		
		DownLoadManager.Manager().addDownTask("http://dlsw.baidu.com/sw-search-sp/soft/ca/13442/Thunder_dl_7.9.43.5054.1456898740.exe",null);
		DownLoadManager.Manager().addDownTask("http://dlsw.baidu.com/sw-search-sp/soft/47/10963/fiddler4_4.6.2.0_setup.1453708442.exe",null);
	}

}
