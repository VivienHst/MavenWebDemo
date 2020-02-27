package dev.vivienhuang.mavenwebdemo.linebot.message.flex;

import dev.vivienhuang.mavenwebdemo.entity.linemessage.LineMessage;

public class FlexMessage extends LineMessage {

	public FlexMessage() {
		super();
		type = "flex";
	}
	
	private String altText;
	
	private FlexContent contents;

	public String getAltText() {
		return altText;
	}

	public void setAltText(String altText) {
		this.altText = altText;
	}

	public FlexContent getContents() {
		return contents;
	}

	public void setContents(FlexContent contents) {
		this.contents = contents;
	}
}
