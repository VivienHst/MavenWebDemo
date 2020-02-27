package dev.vivienhuang.mavenwebdemo.linebot.skill;

import dev.vivienhuang.mavenwebdemo.linebot.webhook.EventModel;

public interface IPlaceSkill {
	public boolean replyEatPlaceSkill(EventModel lineEvent, String channelAccessToken);
}
