package dev.vivienhuang.mavenwebdemo.linebot.skill;

import dev.vivienhuang.mavenwebdemo.entity.LineBotVO;
import dev.vivienhuang.mavenwebdemo.linebot.webhook.EventModel;

public interface IBaseSkill {
	public boolean isBotExist(String destination);
	public LineBotVO getLineBotInfo(String  destination);
	public boolean dealMessage(EventModel lineEvent);
}
