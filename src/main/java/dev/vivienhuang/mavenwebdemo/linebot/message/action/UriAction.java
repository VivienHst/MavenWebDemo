package dev.vivienhuang.mavenwebdemo.linebot.message.action;

public class UriAction extends ActionObject {
	public UriAction() {
		super();
		type = "uri";
	}
	
	private String label;
	
	private String uri;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
