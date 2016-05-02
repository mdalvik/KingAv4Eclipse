package com.wzn.kingav.bean;

public class FileDownBean {
private String range;
private long start;
private long end;
private long size;


public String getRange() {
	return range;
}
public void setRange(String range) {
	this.range = range;
}
public long getStart() {
	return start;
}
public void setStart(long start) {
	this.start = start;
}
public long getEnd() {
	return end;
}
public void setEnd(long end) {
	this.end = end;
}
public long getSize() {
	return size;
}
public void setSize(long size) {
	this.size = size;
}
@Override
public String toString() {
	return "FileDownBean [range=" + range + ", start=" + start + ", end=" + end + ", size=" + size + "]";
}

	
}
