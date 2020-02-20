package dev.vivienhuang.mavenwebdemo.linebot.message.action;

public class MessageAction extends ActionObject {
	
	public MessageAction() {
		super();
		type = "message";
	}
	
	public MessageAction(String label, String text) {
		super();
		type = "message";
		this.label = label;
		this.text = text;
	}
	
	private String label;
	private String text;
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
