package dev.vivienhuang.mavenwebdemo.entity;


public class BasicDBMessageVO {
	private Integer code;
	private String message;
	
	public BasicDBMessageVO() {
		super();
	}
	public BasicDBMessageVO(Integer code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
