package dev.vivienhuang.mavenwebdemo.linebot.message.template;

import java.util.List;

import dev.vivienhuang.mavenwebdemo.entity.linemessage.LineMessage;
import dev.vivienhuang.mavenwebdemo.linebot.message.action.ActionObject;

public class ButtonsTemplate extends LineMessage {
	
	public ButtonsTemplate () {
		type = "buttons";
	}
	
	// Optional
	private String thumbnailImageUrl;
	
	// Optional
	private String imageAspectRatio;
	
	// Optional
	private String imageSize;
	
	// Optional
	private String imageBackgroundColor;
		
	// Optional
	private String title;
	
	// Required
	private String text;
	
	// Optional
	private ActionObject defaultAction;
	
	// Required max:4
	private List<ActionObject> actions;

	public String getThumbnailImageUrl() {
		return thumbnailImageUrl;
	}

	public void setThumbnailImageUrl(String thumbnailImageUrl) {
		this.thumbnailImageUrl = thumbnailImageUrl;
	}

	public String getImageAspectRatio() {
		return imageAspectRatio;
	}

	public void setImageAspectRatio(String imageAspectRatio) {
		this.imageAspectRatio = imageAspectRatio;
	}

	public String getImageSize() {
		return imageSize;
	}

	public void setImageSize(String imageSize) {
		this.imageSize = imageSize;
	}

	public String getImageBackgroundColor() {
		return imageBackgroundColor;
	}

	public void setImageBackgroundColor(String imageBackgroundColor) {
		this.imageBackgroundColor = imageBackgroundColor;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ActionObject getDefaultAction() {
		return defaultAction;
	}

	public void setDefaultAction(ActionObject defaultAction) {
		this.defaultAction = defaultAction;
	}

	public List<ActionObject> getActions() {
		return actions;
	}

	public void setActions(List<ActionObject> actions) {
		this.actions = actions;
	}
	
}
