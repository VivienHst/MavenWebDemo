package dev.vivienhuang.mavenwebdemo.linebot.skill;

import java.util.List;

import dev.vivienhuang.mavenwebdemo.entity.linemessage.LineMessage;
import dev.vivienhuang.mavenwebdemo.linebot.message.ReplyMessageModel;
import dev.vivienhuang.mavenwebdemo.linebot.webhook.EventModel;

public interface IMessageSkill {
	public boolean replyKeyWordMessage(EventModel lineEvent, String channelAccessToken);
	public boolean replyEchoMessage(EventModel lineEvent, String channelAccessToken, String lineName);
}
