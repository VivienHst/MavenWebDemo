package dev.vivienhuang.mavenwebdemo.dao.log;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dev.vivienhuang.mavenwebdemo.entity.LineWebhookLogVO;

@Repository
public class LineWebhookLogDAO  implements ILineWebhookLogDAO{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void createLineWebhookLog(LineWebhookLogVO lineWebhookLogVO) {
		Session session = sessionFactory.getCurrentSession();
		session.save(lineWebhookLogVO);
	}

}
