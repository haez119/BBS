package co.min.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.min.board.common.Action;

public class LogoutAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 세션 리무브
		
		HttpSession session = request.getSession();
		// session.removeAttribute();??
		String name = (String) session.getAttribute("name"); // 세션 종료 전 name을 담고 
		
		session.invalidate(); // 세선 전체 삭제
		
		request.setAttribute("name", name);
		
		return "jsp/main/logout.jsp"; // 로그아웃 하면 로그인 페이지로 돌아감
	}

}
