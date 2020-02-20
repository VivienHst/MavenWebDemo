package dev.vivienhuang.mavenwebdemo.linebot.skill;

import dev.vivienhuang.mavenwebdemo.linebot.webhook.EventModel;

public interface IWeatherSkill {
	
	public boolean replyWeatherSkill(EventModel lineEvent, String channelAccessToken);

	public String getObserveRadarDataImage();
}
