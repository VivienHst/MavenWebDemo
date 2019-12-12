package dev.vivienhuang.mavenwebdemo.entity.linemessage;

public class TextMessage extends LineMessage {
	
	public TextMessage() {
		super();
		type = "text";
	}
	
    public TextMessage(String text) {
		super();
		type = "text";
		this.text = text;
	}

	public String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
