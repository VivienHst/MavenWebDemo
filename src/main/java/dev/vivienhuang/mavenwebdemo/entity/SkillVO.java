package dev.vivienhuang.mavenwebdemo.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Skill")
public class SkillVO {
	/*
	 * create table Skill (
	    SkillId               int NOT NULL  IDENTITY(1, 1),
	    SkillName             nvarchar(40) not null,
	    SkillDesc              nvarchar(100),
	    CONSTRAINT SKILL_SKILLID_PK PRIMARY KEY (SKILLID));

	 * 
	 * */

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SkillId")
	private Integer skillId;
	
	@Column(name="SkillName")
	private String skillName;
	
	@Column(name="SkillDesc")
	private String skillDesc;
	
	@ManyToMany(mappedBy = "skills", fetch = FetchType.EAGER)
	private Set<LineBotVO> linebots = new HashSet<>();
	
	public SkillVO() {
		super();
	}

	public SkillVO(String skillName, String skillDesc) {
		super();
		this.skillName = skillName;
		this.skillDesc = skillDesc;
	}

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getSkillDesc() {
		return skillDesc;
	}

	public void setSkillDesc(String skillDesc) {
		this.skillDesc = skillDesc;
	}

	public Set<LineBotVO> getLinebots() {
		return linebots;
	}

	public void setLinebots(Set<LineBotVO> linebots) {
		this.linebots = linebots;
	}

	@Override
	public String toString() {
		return "SkillVO [skillId=" + skillId + ", skillName=" + skillName + ", skillDesc=" + skillDesc + "]";
	}
}
