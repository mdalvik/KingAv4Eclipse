package com.wzb.kingav.file;

public interface IFile {
	
	interface OnNetFileSizeListener{
		/**
		 * 成功回调文件大小
		 * @param size
		 */
		void onSucess(long size);
		/**
		 * 失败回调错误信息
		 * @param t
		 */
		void onFail(Throwable t);
	}
	
	/**
	 * 下载数据回调接口
	 * 
	 *
	 */
	interface onDownFileCallback{
		/**
		 * 下载中回调
		 * @param fileSize 文件总大小
		 * @param downloadprogress 当前下载进度
		 */
		void onDownload(long fileSize,long downloadprogress);
		/**
		 * 下载完成回调
		 */
		void onComplete(FileModel fileModel);
		
		
		/**
		 * 下载失败回调
		 * @param t
		 */
		void onFail(Throwable t,FileModel fileModel);
		
		
	}

}
