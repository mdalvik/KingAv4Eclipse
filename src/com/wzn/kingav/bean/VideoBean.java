package com.wzn.kingav.bean;

public class VideoBean {
	private String img;
	private String link;
	private String title;
	private String playCount;
	private String pullTime;
	private String playTime;
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPlayCount() {
		return playCount;
	}
	public void setPlayCount(String playCount) {
		this.playCount = playCount;
	}
	public String getPullTime() {
		return pullTime;
	}
	public void setPullTime(String pullTime) {
		this.pullTime = pullTime;
	}
	public String getPlayTime() {
		return playTime;
	}
	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}
	@Override
	public String toString() {
		return "VideoBean [img=" + img + ", link=" + link + ", title=" + title + ", playCount=" + playCount
				+ ", pullTime=" + pullTime + ", playTime=" + playTime + "]";
	}
	
}
