package dev.vivienhuang.mavenwebdemo.service.chat;

import java.util.List;

import dev.vivienhuang.mavenwebdemo.entity.ChatKeyWordVO;

public interface IChatKeyWordService {
	public void createChatKeyWord(ChatKeyWordVO chatKeyWordVO);
	public ChatKeyWordVO getChatKeyWord(int cId);
	public List<ChatKeyWordVO> getChatKeyWords();
	public void updateChatKeyWord(ChatKeyWordVO chatKeyWordVO);
	public void deleteChatKeyWord(int cId);
}
