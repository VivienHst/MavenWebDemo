package dev.vivienhuang.mavenwebdemo.service.linebot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.vivienhuang.mavenwebdemo.dao.linebot.ILineBotDAO;
import dev.vivienhuang.mavenwebdemo.entity.LineBotVO;

@Service
public class LineBotService implements ILineBotService {

	@Autowired
	ILineBotDAO lineBotDAO;
	
	@Override
	@Transactional
	public void createLineBot(LineBotVO lineBotVO) {
		lineBotDAO.createLineBot(lineBotVO);
	}

	@Override
	@Transactional
	public LineBotVO getLineBot(int botId) {
		return lineBotDAO.getLineBot(botId);
	}
	
	@Override
	@Transactional
	public LineBotVO getLineBot(String destination) {
		return lineBotDAO.getLineBot(destination);
	}

	@Override
	@Transactional
	public List<LineBotVO> getLineBots() {
		return lineBotDAO.getLineBots();
	}

	@Override
	@Transactional
	public void updateLineBot(LineBotVO lineBotVO) {
		lineBotDAO.updateLineBot(lineBotVO);
	}

	@Override
	@Transactional
	public void deleteLineBot(int botId) {
		lineBotDAO.deleteLineBot(botId);
	}

	@Override
	@Transactional
	public LineBotVO getLineBotByMemberLineId(String lineId) {
		return lineBotDAO.getLineBotByMemberLineId(lineId);
	}

}
