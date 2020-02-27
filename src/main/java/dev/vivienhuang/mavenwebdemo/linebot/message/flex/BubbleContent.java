package dev.vivienhuang.mavenwebdemo.linebot.message.flex;

import dev.vivienhuang.mavenwebdemo.linebot.message.action.ActionObject;

public class BubbleContent extends FlexContent{

	
	public BubbleContent() {
		super();
		type = "bubble";
	}
	
	// optional
	private String size;
	
	// optional
	private String direction;
	
	// optional
	private FlexContent header;
	
	// optional
	private FlexContent hero;
	
	// optional
	private FlexContent body;
	
	// optional
	private FlexContent footer;
	
	// optional
	private BubbleStyle styles;
	
	// optional
	private ActionObject action;

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public FlexContent getHeader() {
		return header;
	}

	public void setHeader(FlexContent header) {
		this.header = header;
	}

	public FlexContent getHero() {
		return hero;
	}

	public void setHero(FlexContent hero) {
		this.hero = hero;
	}

	public FlexContent getBody() {
		return body;
	}

	public void setBody(FlexContent body) {
		this.body = body;
	}

	public FlexContent getFooter() {
		return footer;
	}

	public void setFooter(FlexContent footer) {
		this.footer = footer;
	}

	public BubbleStyle getStyles() {
		return styles;
	}

	public void setStyles(BubbleStyle styles) {
		this.styles = styles;
	}

	public ActionObject getAction() {
		return action;
	}

	public void setAction(ActionObject action) {
		this.action = action;
	}
}
