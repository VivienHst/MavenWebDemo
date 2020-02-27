package dev.vivienhuang.mavenwebdemo.linebot.message.flex;

public class TextComponent extends FlexContent{

	
	public TextComponent() {
		super();
		type = "text";
	}
	
	// xxs, xs, sm, md, lg, xl, xxl, 3xl, 4xl, or 5xl.
	private String text;
	
	private String size;
	
	private String weight;
	
	private int flex;
	
	private String color;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public int getFlex() {
		return flex;
	}

	public void setFlex(int flex) {
		this.flex = flex;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
	
}
