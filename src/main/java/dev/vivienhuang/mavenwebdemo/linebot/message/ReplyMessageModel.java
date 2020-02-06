package dev.vivienhuang.mavenwebdemo.linebot.message;

import java.util.List;

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
	
	private List<MessageModel> messages;
	
	public ReplyMessageModel(String replyToken, List<MessageModel> messages) {
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
	public List<MessageModel> getMessages() {
		return messages;
	}
	public void setMessages(List<MessageModel> messages) {
		this.messages = messages;
	}
}
