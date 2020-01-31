package dev.vivienhuang.mavenwebdemo.linebot.skill;

import java.util.List;

import dev.vivienhuang.mavenwebdemo.entity.linemessage.LineMessage;

public interface IMessageSkill {
	public void sendTextMessage(String userId, String message, String channelAccessToken);
	public void sendLineMessage(String userId, List<LineMessage> messages, String channelAccessToken);
}
