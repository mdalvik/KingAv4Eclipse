package com.wzb.kingav.file;

public class FileModel {
	
	private String url;
	
	private long fileSize;
	
	private String filePath;
	
	private int downThreadCount;

	public FileModel(String url, String filePath, int downThreadCount) {
		super();
		this.url = url;
	    this.filePath = filePath;
		this.downThreadCount = downThreadCount;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getDownThreadCount() {
		return downThreadCount;
	}

	public void setDownThreadCount(int downThreadCount) {
		this.downThreadCount = downThreadCount;
	}
	
	

}
