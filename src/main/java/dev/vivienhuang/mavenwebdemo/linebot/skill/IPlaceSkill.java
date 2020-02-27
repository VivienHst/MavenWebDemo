package dev.vivienhuang.mavenwebdemo.linebot.skill;

import dev.vivienhuang.mavenwebdemo.entity.LineBotVO;
import dev.vivienhuang.mavenwebdemo.linebot.webhook.EventModel;

public interface IPlaceSkill {
	public boolean replyEatPlaceSkill(EventModel lineEvent, String channelAccessToken);

	public boolean addFavoriteFoodPlace(LineBotVO lineBotVO, EventModel lineEvent);
}
