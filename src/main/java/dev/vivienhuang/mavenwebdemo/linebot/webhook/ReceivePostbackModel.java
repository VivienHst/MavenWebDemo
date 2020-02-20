package dev.vivienhuang.mavenwebdemo.linebot.webhook;

public class ReceivePostbackModel {
	private String data;
	
	private PostbackParamsModel params;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public PostbackParamsModel getParams() {
		return params;
	}

	public void setParams(PostbackParamsModel params) {
		this.params = params;
	}
	
	

}
