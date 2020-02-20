package dev.vivienhuang.mavenwebdemo.linebot.message;

import java.util.List;

import dev.vivienhuang.mavenwebdemo.entity.linemessage.LineMessage;

public class ReplyMessageModel {
	/*
	 * 
	 * "replyToken":"nHuyWiB7yP5Zw52FIkcQobQuGDXCTA",
	    "messages":[
	        {
	            "type":"text",
	            "text":"Hello, user"
	        },
	        {
	            "type":"text",
	            "text":"May I help you?"
	        }
	    ]
	}'*/
	
	private String replyToken;
	
	private List<LineMessage> messages;
	
	public ReplyMessageModel(String replyToken, List<LineMessage> messages) {
		super();
		this.replyToken = replyToken;
		this.messages = messages;
	}
	public String getReplyToken() {
		return replyToken;
	}
	public void setReplyToken(String replyToken) {
		this.replyToken = replyToken;
	}
	public List<LineMessage> getMessages() {
		return messages;
	}
	public void setMessages(List<LineMessage> messages) {
		this.messages = messages;
	}
}
