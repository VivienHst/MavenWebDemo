package dev.vivienhuang.mavenwebdemo.entity.linemessage;

import java.util.List;

public class MessagePayload {
	private String to;

	private List<LineMessage> messages;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public List<LineMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<LineMessage> messages) {
		this.messages = messages;
	}
	
	
}
