package co.min.board.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.min.board.common.Action;
import co.min.board.dao.MemberDao;
import co.min.board.vo.MemberVO;

public class MemberListAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 회원 리스트 보기 구현
		
		MemberDao dao = new MemberDao(); // 인스턴스 이름 권장사항은 => memberDao
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		list = dao.selectAll();
		
		request.setAttribute("members", list); // members => jsp에서 items와 같음
		
		
		return "jsp/member/memberList.jsp";
	}

}
