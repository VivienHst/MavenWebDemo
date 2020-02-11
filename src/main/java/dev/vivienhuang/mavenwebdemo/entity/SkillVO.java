package dev.vivienhuang.mavenwebdemo.entity;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name="Skill")
public class SkillVO implements Serializable{
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
	@JsonIgnore
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((skillDesc == null) ? 0 : skillDesc.hashCode());
		result = prime * result + ((skillId == null) ? 0 : skillId.hashCode());
		result = prime * result + ((skillName == null) ? 0 : skillName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SkillVO other = (SkillVO) obj;
	
		if (skillDesc == null) {
			if (other.skillDesc != null)
				return false;
		} else if (!skillDesc.equals(other.skillDesc))
			return false;
		if (skillId == null) {
			if (other.skillId != null)
				return false;
		} else if (!skillId.equals(other.skillId))
			return false;
		if (skillName == null) {
			if (other.skillName != null)
				return false;
		} else if (!skillName.equals(other.skillName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String jsonString = "";
		ObjectMapper mapper = new ObjectMapper();		
		// Java object to JSON string
		try {
			jsonString = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return jsonString;
		
	}

	
	
}
