package com.example.dto;

import com.example.dto.DefineSubVO.Builder;

public class GetContentVO {
	private String title;
	private String content;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public static class Builder {
		private String title;
		private String content;
    
		public Builder title(String val) {
			title = val;
    		return this;
    	}
		public Builder content(String val) {
			content = val;
    		return this;
    	}
    	public GetContentVO build() {
    		return new GetContentVO(this);
    	}
    }
    
    private GetContentVO(Builder builder) {
		this.title = builder.title;
		this.content = builder.content;
	}
}
