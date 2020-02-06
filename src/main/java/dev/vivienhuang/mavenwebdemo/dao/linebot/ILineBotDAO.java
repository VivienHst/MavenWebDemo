package dev.vivienhuang.mavenwebdemo.dao.linebot;

import java.util.List;

import dev.vivienhuang.mavenwebdemo.entity.LineBotVO;


public interface ILineBotDAO {
	public void createLineBot(LineBotVO lineBotVO);
	public LineBotVO getLineBot(int botId);
	public LineBotVO getLineBot(String destination);
	public List<LineBotVO> getLineBots();
	public void updateLineBot(LineBotVO lineBotVO);
	public void deleteLineBot(int botId);
	public LineBotVO getLineBotByMemberLineId(String lineId);

}
