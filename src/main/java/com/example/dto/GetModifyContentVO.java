package com.example.dto;

import com.example.dto.GetContentVO.Builder;

public class GetModifyContentVO {
	private String num;
	private String word;
	private String info;
	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public static class Builder {
		private String num;
		private String word;
		private String info;
    
		public Builder num(String val) {
			num = val;
    		return this;
    	}
		public Builder word(String val) {
			word = val;
    		return this;
    	}
		public Builder info(String val) {
			info = val;
    		return this;
    	}
    	public GetModifyContentVO build() {
    		return new GetModifyContentVO(this);
    	}
    }
    
    private GetModifyContentVO(Builder builder) {
		this.num = builder.num;
		this.word = builder.word;
		this.info = builder.info;
	}
}
