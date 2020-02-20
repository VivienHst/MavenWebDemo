package dev.vivienhuang.mavenwebdemo.linebot.message;

import dev.vivienhuang.mavenwebdemo.entity.linemessage.LineMessage;

public class MessageModel extends LineMessage {
	
	private String text;
	
	
	
	public MessageModel(String type, String text) {
		super();
		this.type = type;
		this.text = text;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
