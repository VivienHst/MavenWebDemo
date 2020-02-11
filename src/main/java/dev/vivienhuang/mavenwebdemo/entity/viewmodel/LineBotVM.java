package dev.vivienhuang.mavenwebdemo.entity.viewmodel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.vivienhuang.mavenwebdemo.entity.LineBotVO;
import dev.vivienhuang.mavenwebdemo.entity.SkillVO;

public class LineBotVM {
	private LineBotVO lineBotVO;
	private Set<SkillVO> unUsedSkills;
	private Integer botSkills[];
	
	public LineBotVO getLineBotVO() {
		return lineBotVO;
	}
	public void setLineBotVO(LineBotVO lineBotVO) {
		this.lineBotVO = lineBotVO;
	}
	
	public Set<SkillVO> getUnUsedSkills() {
		return unUsedSkills;
	}
	public void setUnUsedSkills(Set<SkillVO> unUsedSkills) {
		this.unUsedSkills = unUsedSkills;
	}
	public Integer[] getBotSkills() {
		return botSkills;
	}
	public void setBotSkills(Integer[] botSkills) {
		this.botSkills = botSkills;
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
