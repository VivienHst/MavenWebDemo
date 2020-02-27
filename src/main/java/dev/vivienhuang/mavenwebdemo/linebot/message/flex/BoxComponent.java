package dev.vivienhuang.mavenwebdemo.linebot.message.flex;

import java.util.List;

public class BoxComponent extends FlexContent{

	
	public BoxComponent() {
		super();
		type = "box";
	}
	
	// Required
	private String layout;
	
	// Required
	private List<FlexContent> contents;
	
	// Optional 
	// The default value is #00000000.
	private String backgroundColor;
	
	// Optional
	private String borderColor;
		
	// Optional
	// You can specify a value in pixels 
	// or any one of none, light, normal, medium, semi-bold, or bold.
	private String borderWidth;
		
	// Optional
	// You can specify a value in pixels or any one of none, xs, sm, md, lg, xl, or xxl
	private String cornerRadius;
		
	// Optional
	// width of box
	private String width;
	
	// Optional
	// height of box
	private String height;
		
	// Optional
	// Minimum space between components in this box. The default value is none
	private String spacing;

	
	private String margin;
	
	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public List<FlexContent> getContents() {
		return contents;
	}

	public void setContents(List<FlexContent> contents) {
		this.contents = contents;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public String getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(String borderWidth) {
		this.borderWidth = borderWidth;
	}

	public String getCornerRadius() {
		return cornerRadius;
	}

	public void setCornerRadius(String cornerRadius) {
		this.cornerRadius = cornerRadius;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getSpacing() {
		return spacing;
	}

	public void setSpacing(String spacing) {
		this.spacing = spacing;
	}

	public String getMargin() {
		return margin;
	}

	public void setMargin(String margin) {
		this.margin = margin;
	}

	
	
}
