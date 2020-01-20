package dev.vivienhuang.mavenwebdemo.service.line_member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.vivienhuang.mavenwebdemo.dao.line_member.ILineMemberDAO;
import dev.vivienhuang.mavenwebdemo.entity.LineMemberVO;

@Service
public class LineMemberService implements ILineMemberService {
	
	@Autowired
	ILineMemberDAO lineMemberDAO;

	@Override
	@Transactional
	public void createLineMember(LineMemberVO lineMemberVO) {
		lineMemberDAO.createLineMember(lineMemberVO);
	}

	@Override
	@Transactional
	public LineMemberVO getLineMember(String lineId) {
		return lineMemberDAO.getLineMember(lineId);
	}

	@Override
	@Transactional
	public List<LineMemberVO> getLineMembers() {
		return lineMemberDAO.getLineMembers();
	}

	@Override
	@Transactional
	public void updateLineMember(LineMemberVO lineMemberVO) {
		lineMemberDAO.updateLineMember(lineMemberVO);
	}

	@Override
	@Transactional
	public void deleteLineMember(String lineId) {
		lineMemberDAO.deleteLineMember(lineId);
	}

}
