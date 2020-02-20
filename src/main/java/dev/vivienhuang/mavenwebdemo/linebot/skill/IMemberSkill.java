package dev.vivienhuang.mavenwebdemo.linebot.skill;

import dev.vivienhuang.mavenwebdemo.entity.LineBotVO;
import dev.vivienhuang.mavenwebdemo.entity.LineMemberVO;
import dev.vivienhuang.mavenwebdemo.linebot.webhook.EventModel;

public interface IMemberSkill {
	public LineMemberVO registerMember(LineBotVO lineBotVO, EventModel lineEvent);
	public String getLineMemberProfile(String lineId, String channelAccessToken);
}
