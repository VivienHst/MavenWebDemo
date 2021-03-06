package dev.vivienhuang.mavenwebdemo.linebot.webhook;

import dev.vivienhuang.mavenwebdemo.linebot.webhook.source.SourceModel;

public class EventModel {
	
	private String replyToken;
	private String type;
	private String mode;
	private String timestamp;
	private SourceModel source;
	private ReceiveMessageModel message;
	private ReceivePostbackModel postback;
	
	public String getReplyToken() {
		return replyToken;
	}
	public void setReplyToken(String replyToken) {
		this.replyToken = replyToken;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public SourceModel getSource() {
		return source;
	}
	public void setSource(SourceModel source) {
		this.source = source;
	}
	public ReceiveMessageModel getMessage() {
		return message;
	}
	public void setMessage(ReceiveMessageModel message) {
		this.message = message;
	}
	public ReceivePostbackModel getPostback() {
		return postback;
	}
	public void setPostback(ReceivePostbackModel postback) {
		this.postback = postback;
	}
}
