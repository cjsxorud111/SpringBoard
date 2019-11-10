package com.example.dto;

public class DefineSubVO {

	private String num;
	private int groupnum;
	private String connum;
	private String space;
	private String content;
	private String pw;
	private String id;
	
	public DefineSubVO() {
	
	}
	
	public int getGroupnum() {
		return groupnum;
	}
	public void setGroupnum(int groupnum) {
		this.groupnum = groupnum;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getConnum() {
		return connum;
	}
	public void setConnum(String connum) {
		this.connum = connum;
	}
	public String getSpace() {
		return space;
	}
	public void setSpace(String space) {
		this.space = space;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
    public static class Builder {
    	private String num;
    	private int groupnum;
    	private String connum;
    	private String space;
    	private String content;
    	private String pw;
    	private String id;
    
    	public Builder num(String val) {
    		num = val;
    		return this;
    	}
    	public Builder groupnum(int val) {
    		groupnum = val;
    		return this;
    	}
    	public Builder connum(String val) {
    		connum = val;
    		return this;
    	}
    	public Builder space(String val) {
    		space = val;
    		return this;
    	}
    	public Builder content(String val) {
    		content = val;
    		return this;
    	}
    	public Builder pw(String val) {
    		pw = val;
    		return this;
    	}
    	public Builder id(String val) {
    		id = val;
    		return this;
    	}
    	public DefineSubVO build() {
    		return new DefineSubVO(this);
    	}
    }
    
    private DefineSubVO(Builder builder) {
		this.num = builder.num;
		this.groupnum = builder.groupnum;
		this.connum = builder.connum;
		this.space = builder.space;
		this.content = builder.content;
		this.pw = builder.pw;
		this.id = builder.id;
	}
}
