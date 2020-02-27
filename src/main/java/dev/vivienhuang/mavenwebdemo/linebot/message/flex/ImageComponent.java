package dev.vivienhuang.mavenwebdemo.linebot.message.flex;

import dev.vivienhuang.mavenwebdemo.linebot.message.action.ActionObject;

public class ImageComponent extends FlexContent{
	public ImageComponent() {
		super();
		type = "image";
	}
	
	private String url;
	
	private String size;
	
	private String aspectRatio;
	
	private String aspectMode;
	
	private ActionObject action;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getAspectRatio() {
		return aspectRatio;
	}

	public void setAspectRatio(String aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	public String getAspectMode() {
		return aspectMode;
	}

	public void setAspectMode(String aspectMode) {
		this.aspectMode = aspectMode;
	}

	public ActionObject getAction() {
		return action;
	}

	public void setAction(ActionObject action) {
		this.action = action;
	}
	
}
