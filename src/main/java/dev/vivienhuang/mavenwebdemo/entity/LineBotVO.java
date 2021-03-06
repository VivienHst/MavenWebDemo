package dev.vivienhuang.mavenwebdemo.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="LineBot")
public class LineBotVO {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="BotId")
	private Integer botId;

	@Column(name="Destination")
	private String destination;
	
	@Column(name="Token")
	@NotEmpty(message = "請填入Token")
	private String token;
	
	@Column(name="Secret")
	@NotEmpty(message = "請填入Secret")
	private String secret;
	
	@Column(name="BotUid")
	@NotEmpty(message = "請填入BotUid")
	private String botUid;
	
	@Column(name="DisplayName")
	@NotEmpty(message = "請填入DisplayName")
	private String displayName ;
	
	@Column(name="Type")
	@NotEmpty(message = "請填入Type")
	private String type;
	
	@Column(name="CreateDate")
	private Timestamp createDate;
	
	@Column(name="UpdateDate")
	private Timestamp updateDate;
	
	@ManyToMany(cascade= {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinTable(
			name="LineBot_Skill",
			joinColumns= {@JoinColumn(name="BotId")},
			inverseJoinColumns= {@JoinColumn(name="SkillId")}
			)
	private Set<SkillVO> skills = new HashSet<>();
	
	public LineBotVO() {
		super();
	}

	public LineBotVO(String destination, String type, Timestamp createDate) {
		super();
		this.destination = destination;
		this.type = type;
		this.createDate = createDate;
	}

	public LineBotVO(String token, String secret, String displayName, String type, String botUid,Timestamp createDate) {
		super();
		this.token = token;
		this.secret = secret;
		this.displayName = displayName;
		this.type = type;
		this.botUid = botUid;
		this.createDate = createDate;
	}

	public Integer getBotId() {
		return botId;
	}

	public void setBotId(Integer botId) {
		this.botId = botId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getBotUid() {
		return botUid;
	}

	public void setBotUid(String botUid) {
		this.botUid = botUid;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	public Set<SkillVO> getSkills() {
		return skills;
	}

	public void setSkills(Set<SkillVO> skillList) {
		this.skills = skillList;
	}

	@Override
	public String toString() {
		return "LineBotVO [botId=" + botId + ", token=" + token + ", secret=" + secret + ", displayName=" + displayName
				+ ", type=" + type + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}

}
