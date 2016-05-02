package com.wzn.kingav.bean;

public class MenuBean {
	
	private String name;
	private String url;
	private String count;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "MenuBean [name=" + name + ", url=" + url + ", count=" + count + "]";
	}


}
