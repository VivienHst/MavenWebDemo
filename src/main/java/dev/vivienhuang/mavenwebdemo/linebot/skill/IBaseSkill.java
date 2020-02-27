package dev.vivienhuang.mavenwebdemo.linebot.skill;

import java.util.List;

import dev.vivienhuang.mavenwebdemo.entity.LineBotVO;
import dev.vivienhuang.mavenwebdemo.entity.LineMemberVO;
import dev.vivienhuang.mavenwebdemo.entity.linemessage.LineMessage;
import dev.vivienhuang.mavenwebdemo.linebot.message.ReplyMessageModel;
import dev.vivienhuang.mavenwebdemo.linebot.webhook.EventModel;

public interface IBaseSkill {
	public boolean isBotExist(String destination);
	public LineBotVO getLineBotInfo(String  destination);
	public boolean dealMessage(LineBotVO lineBotVO, EventModel lineEvent);
	public boolean isTextMessage(EventModel lineEvent);
	
	public void pushTextMessage(String userId, String message, String channelAccessToken);
	public void pushImageMessage(String userId, String url, String channelAccessToken);

	public void replyTextMessage(String replyToken, String message, String channelAccessToken);

	public void sendPushMessage(String userId, List<LineMessage> messages, String channelAccessToken);
	public void sendReplyMessage(ReplyMessageModel replyMessageModel, String channelAccessToken);
}
