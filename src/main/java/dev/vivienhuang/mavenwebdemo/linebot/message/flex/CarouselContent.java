package dev.vivienhuang.mavenwebdemo.linebot.message.flex;

import java.util.List;

public class CarouselContent extends FlexContent {
	public CarouselContent() {
		super();
		type = "carousel";
	}
	
	// max:10
	private List<FlexContent> contents;

	public List<FlexContent> getContents() {
		return contents;
	}

	public void setContents(List<FlexContent> contents) {
		this.contents = contents;
	}

}
