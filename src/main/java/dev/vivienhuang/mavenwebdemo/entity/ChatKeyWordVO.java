package dev.vivienhuang.mavenwebdemo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ChatKeyWord")
public class ChatKeyWordVO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CId")
	private Integer cId;
	
	@Column(name="ChatKey")
	private String chatKey;
	
	@Column(name="ChatValue")
	private String chatValue;
	
	@Column(name="CreateDate")
	private Timestamp createDate;
	
	public ChatKeyWordVO() {
		super();
	}

	public ChatKeyWordVO(String chatKey, String chatValue, Timestamp createDate) {
		super();
		this.chatKey = chatKey;
		this.chatValue = chatValue;
		this.createDate = createDate;
	}

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
	}

	public String getChatKey() {
		return chatKey;
	}

	public void setChatKey(String chatKey) {
		this.chatKey = chatKey;
	}

	public String getChatValue() {
		return chatValue;
	}

	public void setChatValue(String chatValue) {
		this.chatValue = chatValue;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "ChatKeyWordVO [cId=" + cId + ", chatKey=" + chatKey + ", chatValue=" + chatValue + ", createDate="
				+ createDate + "]";
	}
}
