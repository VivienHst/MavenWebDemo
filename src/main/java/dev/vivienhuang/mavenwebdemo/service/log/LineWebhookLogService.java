package dev.vivienhuang.mavenwebdemo.service.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.vivienhuang.mavenwebdemo.dao.log.ILineWebhookLogDAO;
import dev.vivienhuang.mavenwebdemo.entity.LineWebhookLogVO;

@Service
public class LineWebhookLogService implements ILineWebhookLogService {

	@Autowired
	ILineWebhookLogDAO lineWebhookLogDAO;
	
	@Override
	@Transactional
	public void createLineWebhookLog(LineWebhookLogVO lineWebhookLogVO) {
		lineWebhookLogDAO.createLineWebhookLog(lineWebhookLogVO);
	}

}
