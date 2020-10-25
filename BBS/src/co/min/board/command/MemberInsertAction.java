package co.min.board.command;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.min.board.common.Action;
import co.min.board.dao.MemberDao;
import co.min.board.vo.MemberVO;

public class MemberInsertAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
		// 회원 정보를 db에 입력
		
		MemberDao dao = new MemberDao();
		MemberVO vo = new MemberVO();
		
		vo.setId(request.getParameter("id"));
		vo.setName(request.getParameter("name"));
		vo.setPassword(request.getParameter("password"));
		vo.setAddress(request.getParameter("address"));
		vo.setTel(request.getParameter("tel"));
		vo.setEnterdate(Date.valueOf(request.getParameter("enterdate")));
		
		int n = dao.insert(vo);
		
		String page;
		
		if(n !=0) {
			page = "jsp/member/insertSuccess.jsp"; // 성공하면 리스트 화면 보여주기
		} else { 
			page = "jsp/member/insertFail.jsp"; // 실패하면 fail 페이지 보여주기
		}
		
		
//		// request에 n을 실어 보냄 (0이면 실패 0이 아니면 성공)
//		request.setAttribute("check", n);
		
		return page;
	}

}
