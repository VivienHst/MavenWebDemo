package dev.vivienhuang.mavenwebdemo.dao.chat;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.vivienhuang.mavenwebdemo.entity.ChatKeyWordVO;

@Repository
public class ChatKeyWordDAO implements IChatKeyWordDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void createChatKeyWord(ChatKeyWordVO chatKeyWordVO) {
		Session session = sessionFactory.getCurrentSession();
		session.save(chatKeyWordVO);
	}

	@Override
	public ChatKeyWordVO getChatKeyWord(int cId) {
		Session session = sessionFactory.getCurrentSession();
		ChatKeyWordVO chatKeyWordVO = session.get(ChatKeyWordVO.class, cId);
		return chatKeyWordVO;
	}

	@Override
	public List<ChatKeyWordVO> getChatKeyWords() {
		Session session = sessionFactory.getCurrentSession();
		Query<ChatKeyWordVO> query = session.createQuery(
				"from ChatKeyWordVO order by cId", ChatKeyWordVO.class);
		List<ChatKeyWordVO> keyWordVOs = query.getResultList();
		return keyWordVOs;
	}

	@Override
	public void updateChatKeyWord(ChatKeyWordVO chatKeyWordVO) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(chatKeyWordVO);
	}

	@Override
	public void deleteChatKeyWord(int cId) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(session.get(ChatKeyWordVO.class, cId));
		
	}

}
