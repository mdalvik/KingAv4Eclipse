package com.wzn.kingav.bean;

import java.util.ArrayList;
import java.util.List;

public class VideoPlayBean {
	private String poster;
	private List<Source> srcList ;
	
	
	public String getPoster() {
		return poster;
	}


	public void setPoster(String poster) {
		this.poster = poster;
	}


	public List<Source> getSrcList() {
		return srcList;
	}


	public void setSrcList(List<Source> srcList) {
		this.srcList = srcList;
	}


	@Override
	public String toString() {
		return "VideoPlayBean [poster=" + poster + ", srcList=" + srcList + "]";
	}


public	static class Source{
		private String src;
		private String type;
		private String label;
		private String res;
		public String getSrc() {
			return src;
		}
		public void setSrc(String src) {
			this.src = src;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getLabel() {
			return label;
		}
		public void setLabel(String label) {
			this.label = label;
		}
		public String getRes() {
			return res;
		}
		public void setRes(String res) {
			this.res = res;
		}
		@Override
		public String toString() {
			return "Source [src=" + src + ", type=" + type + ", label=" + label + ", res=" + res + "]";
		}
		
		
	}

}
