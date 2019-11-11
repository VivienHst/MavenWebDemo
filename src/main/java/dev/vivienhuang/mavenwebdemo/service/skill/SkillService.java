package dev.vivienhuang.mavenwebdemo.service.skill;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.vivienhuang.mavenwebdemo.dao.skill.ISkillDAO;
import dev.vivienhuang.mavenwebdemo.entity.SkillVO;

@Service
public class SkillService implements ISkillService {

	@Autowired
	ISkillDAO skillDAO;
	
	
	@Override
	@Transactional
	public void createSkill(SkillVO skillVO) {
		skillDAO.createSkill(skillVO);
	}

	@Override
	@Transactional
	public List<SkillVO> getSkills() {
		return skillDAO.getSkills();
	}

	@Override
	@Transactional
	public SkillVO getSkill(int skillId) {
		return skillDAO.getSkill(skillId);
	}

	@Override	
	@Transactional
	public void upadteSkill(SkillVO skillVO) {
		skillDAO.upadteSkill(skillVO);
	}

	@Override
	@Transactional
	public void deleteSkill(int skillId) {
		skillDAO.deleteSkill(skillId);
	}

}
