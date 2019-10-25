package dev.vivienhuang.mavenwebdemo.dao.chat;

import java.util.List;

import dev.vivienhuang.mavenwebdemo.entity.ChatKeyWordVO;

public interface IChatKeyWordDAO {
	public void createChatKeyWord(ChatKeyWordVO chatKeyWordVO);
	public ChatKeyWordVO getChatKeyWord(int cId);
	public List<ChatKeyWordVO> getChatKeyWords();
	public void updateChatKeyWord(ChatKeyWordVO chatKeyWordVO);
	public void deleteChatKeyWord(int cId);
}
