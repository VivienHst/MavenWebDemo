package dev.vivienhuang.mavenwebdemo.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LineWebhookLog")
public class LineWebhookLogVO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private Integer id;

	@Column(name="Destination")
	private String destination;
	
	@Column(name="Data")
	private String data;
	
	@Column(name="CreateDate")
	private Timestamp createDate;

	public LineWebhookLogVO() {
		super();
	}

	public LineWebhookLogVO(String destination, String data, Timestamp createDate) {
		super();
		this.destination = destination;
		this.data = data;
		this.createDate = createDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "LineWebhookLogVO [id=" + id + ", destination=" + destination + ", data=" + data + ", createDate="
				+ createDate + "]";
	}
	
}
