package dev.vivienhuang.mavenwebdemo.linebot.message.template;

import java.util.List;

import dev.vivienhuang.mavenwebdemo.entity.linemessage.LineMessage;

public class TemplateMessage extends LineMessage {
	
	public TemplateMessage() {
		type = "template";
	}
	
	public String altText;
	
	public LineMessage template;

	public String getAltText() {
		return altText;
	}

	public void setAltText(String altText) {
		this.altText = altText;
	}

	public LineMessage getTemplate() {
		return template;
	}

	public void setTemplate(LineMessage template) {
		this.template = template;
	}

	
}
