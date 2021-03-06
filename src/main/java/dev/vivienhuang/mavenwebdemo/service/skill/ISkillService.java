package dev.vivienhuang.mavenwebdemo.service.skill;

import java.util.List;

import dev.vivienhuang.mavenwebdemo.entity.SkillVO;

public interface ISkillService {
	public void createSkill(SkillVO skillVO);
	public List<SkillVO> getSkills();
	public SkillVO getSkill(int skillId);
	public void upadteSkill(SkillVO skillVO);
	public void deleteSkill(int skillId);
}
