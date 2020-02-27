package dev.vivienhuang.mavenwebdemo.entity.member;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LineMember")
public class LineMemberVO {
	/*
	 * 
	 *  Create table LineMember(
     	LineId varchar(50) not null,
	     LineName nvarchar(50) null,
	     LinePicture nvarchar(200)  null,
	     BotId int not null,
	     MemberStatus [int] not null,
	     CreateDate datetime not null,
	     UpdateDate datetime null,
	    constraint LINE_MEMBER_UID_PK PRIMARY KEY(LineId)
	    CONSTRAINT LINE_MEMBER_BOTID_FK FOREIGN KEY (BotId) REFERENCES LineBot (BotId),
	    
	 )*/
	
	@Id
	@Column(name="LineId")
	private String lineId;
	
	@Column(name="LineName")
	private String lineName;
	
	@Column(name="LinePicture")
	private String linePicture;
	
	@Column(name="BotId")
	private Integer botId;
	
	@Column(name="MemberStatus")
	private Integer memberStatus;

	@Column(name="CreateDate")
	private Timestamp createDate;
	
	@Column(name="UpdateDate")
	private Timestamp updateDate;

	public LineMemberVO() {
		super();
	}
	
	public LineMemberVO(String lineId, Integer botId, Integer memberStatus, Timestamp createDate) {
		super();
		this.lineId = lineId;
		this.botId = botId;
		this.memberStatus = memberStatus;
		this.createDate = createDate;
	}

	public LineMemberVO(String lineId, String lineName, String linePicture, Integer botId, Integer memberStatus,
			Timestamp createDate, Timestamp updateDate) {
		super();
		this.lineId = lineId;
		this.lineName = lineName;
		this.linePicture = linePicture;
		this.botId = botId;
		this.memberStatus = memberStatus;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getLinePicture() {
		return linePicture;
	}

	public void setLinePicture(String linePicture) {
		this.linePicture = linePicture;
	}

	public Integer getBotId() {
		return botId;
	}

	public void setBotId(Integer botId) {
		this.botId = botId;
	}

	public Integer getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(Integer memberStatus) {
		this.memberStatus = memberStatus;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "LineMemberVO [lineId=" + lineId + ", lineName=" + lineName + ", linePicture=" + linePicture + ", botId="
				+ botId + ", memberStatus=" + memberStatus + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ "]";
	}
	
}
