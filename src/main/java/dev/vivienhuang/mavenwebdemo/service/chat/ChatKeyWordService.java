package dev.vivienhuang.mavenwebdemo.service.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.vivienhuang.mavenwebdemo.dao.chat.IChatKeyWordDAO;
import dev.vivienhuang.mavenwebdemo.entity.ChatKeyWordVO;

@Service
public class ChatKeyWordService implements IChatKeyWordService{

	@Autowired
	IChatKeyWordDAO chatKeyWordDAO;
	
	@Override
	@Transactional
	public void createChatKeyWord(ChatKeyWordVO chatKeyWordVO) {
		chatKeyWordDAO.createChatKeyWord(chatKeyWordVO);		
	}

	@Override
	@Transactional
	public ChatKeyWordVO getChatKeyWord(int cId) {
		return chatKeyWordDAO.getChatKeyWord(cId);
	}

	@Override
	@Transactional
	public List<ChatKeyWordVO> getChatKeyWords() {
		return chatKeyWordDAO.getChatKeyWords();
	}

	@Override
	@Transactional
	public void updateChatKeyWord(ChatKeyWordVO chatKeyWordVO) {
		chatKeyWordDAO.updateChatKeyWord(chatKeyWordVO);
	}

	@Override
	@Transactional
	public void deleteChatKeyWord(int cId) {
		chatKeyWordDAO.deleteChatKeyWord(cId);
	}

}
