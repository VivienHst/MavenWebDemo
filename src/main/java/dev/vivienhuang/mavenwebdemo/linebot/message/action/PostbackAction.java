package dev.vivienhuang.mavenwebdemo.linebot.message.action;

public class PostbackAction extends ActionObject{

	public PostbackAction() {
		type = "postback";
	}
	
	
	
	public PostbackAction(String label, String data) {
		super();
		type = "postback";
		this.label = label;
		this.data = data;
	}



	private String label;
	
	// Required
	private String data;
	
	// displayText and text properties cannot both be used at the same time.
	private String displayText;
	
	// Optional
	private String text;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	
}
