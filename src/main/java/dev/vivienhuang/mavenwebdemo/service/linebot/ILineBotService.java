package dev.vivienhuang.mavenwebdemo.service.linebot;

import java.util.List;

import dev.vivienhuang.mavenwebdemo.entity.LineBotVO;

public interface ILineBotService {
	public void createLineBot(LineBotVO lineBotVO);
	public LineBotVO getLineBot(int botId);
	public LineBotVO getLineBot(String destination);
	public List<LineBotVO> getLineBots();
	public void updateLineBot(LineBotVO lineBotVO);
	public void deleteLineBot(int botId);
}
