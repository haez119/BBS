package co.min.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.min.board.common.Action;
import co.min.board.dao.MemberDao;
import co.min.board.vo.MemberVO;

public class LoginAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 로그인 인증 과정을 처리
		
		MemberDao dao = new MemberDao();
		MemberVO vo = new MemberVO();
		// String msg;
		
		vo.setId(request.getParameter("id"));
		vo.setPassword(request.getParameter("password"));
		
		vo = dao.select(vo); // select 할 vo를 request를 전달한 vo로 보내고 반환값을 다시 vo에 받음?
		
		// dao.select()는 매개값으로 받은 vo의 id와 pw를 where절에 사용함 => 입력받은 id, pw를 vo에 넣어서 매개값으로 주고
		// 반환값을 다시 vo에 담음 => 검색된 행이 없으면 null을 반환 하니까
		// vo가 null이면 id, pw가 맞지 않는 것
		
		
		
		// id랑 password를 비교해서 없으면 name이 null이 되니가 name로 비교
//		if(vo.getName() == null) {
//			msg = "존재하지 않는 ID 입니다.";
//			request.setAttribute("msg", msg); // request에  msg라는  attr를 붙임 (메시지 싣고)
//			// request.setAttribute("msg", "존재하지 않는 ID 입니다.");
//			
//		} 
		
		//존재 하든 안하든 /  request한테 vo객체만들어서 select의 반환값 vo를 전달
		
		// 세션객체로 만들어서 넘길거야
		HttpSession session = request.getSession(false); //false 안넣어도 됨 , false는 없으면 새로 만들어라??
		
		// 세션에 id, 권한 담기
		session.setAttribute("id", vo.getId());
		session.setAttribute("name", vo.getName());
		session.setAttribute("author", vo.getAuthor());
		
		
		request.setAttribute("vo", vo); 
		return "jsp/main/loginResult.jsp";
	}

}
