package com.wzb.kingav.file;

public interface IDownLoadEngine {
	
	
	
	/**
	 * 
	 * @param url 下载地址
	 * @param threadCount 下载线程管理
	 * @param callback 下载进度回调
	 */
	void startDownloadingTask(FileModel model,IFile.onDownFileCallback callback);

}
