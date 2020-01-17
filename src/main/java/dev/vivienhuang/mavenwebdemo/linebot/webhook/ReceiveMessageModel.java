package dev.vivienhuang.mavenwebdemo.linebot.webhook;

public class ReceiveMessageModel {

	private String id;
	private String type;
	private String text;
	private String stickerId;
	private String packageId;
	private String stickerResourceType;
	private ContentProvider contentProvider;

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getStickerId() {
		return stickerId;
	}
	public void setStickerId(String stickerId) {
		this.stickerId = stickerId;
	}
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public String getStickerResourceType() {
		return stickerResourceType;
	}
	public void setStickerResourceType(String stickerResourceType) {
		this.stickerResourceType = stickerResourceType;
	}
	public ContentProvider getContentProvider() {
		return contentProvider;
	}
	public void setContentProvider(ContentProvider contentProvider) {
		this.contentProvider = contentProvider;
	}
	
}
