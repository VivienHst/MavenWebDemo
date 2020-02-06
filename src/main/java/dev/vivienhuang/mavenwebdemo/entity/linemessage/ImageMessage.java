package dev.vivienhuang.mavenwebdemo.entity.linemessage;

public class ImageMessage extends LineMessage {
	
	public ImageMessage(String text) {
		super();
		type = "image";
	}
	
    public ImageMessage(String originalContentUrl, String previewImageUrl) {
		super();
		type = "image";
		this.originalContentUrl = originalContentUrl;
		this.previewImageUrl = previewImageUrl;
	}

	public String originalContentUrl;

	public String previewImageUrl;

	public String getOriginalContentUrl() {
		return originalContentUrl;
	}

	public void setOriginalContentUrl(String originalContentUrl) {
		this.originalContentUrl = originalContentUrl;
	}

	public String getPreviewImageUrl() {
		return previewImageUrl;
	}

	public void setPreviewImageUrl(String previewImageUrl) {
		this.previewImageUrl = previewImageUrl;
	}

}
