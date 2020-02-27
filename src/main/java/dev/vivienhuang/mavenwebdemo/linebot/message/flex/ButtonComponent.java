package dev.vivienhuang.mavenwebdemo.linebot.message.flex;

import dev.vivienhuang.mavenwebdemo.linebot.message.action.ActionObject;

public class ButtonComponent extends FlexContent {

	
	public ButtonComponent() {
		super();
		type = "button";
	}
	
	private ActionObject action;
	
	private int flex;
	
	private String margin;
	private String position;
	private String style;
	private String height;
	public ActionObject getAction() {
		return action;
	}
	public void setAction(ActionObject action) {
		this.action = action;
	}
	public int getFlex() {
		return flex;
	}
	public void setFlex(int flex) {
		this.flex = flex;
	}
	public String getMargin() {
		return margin;
	}
	public void setMargin(String margin) {
		this.margin = margin;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
}
