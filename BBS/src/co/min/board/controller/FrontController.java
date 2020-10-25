package co.min.board.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.min.board.command.LoginAction;
import co.min.board.command.LoginForm;
import co.min.board.command.LogoutAction;
import co.min.board.command.MainAction;
import co.min.board.command.MemberForm;
import co.min.board.command.MemberInsertAction;
import co.min.board.command.MemberListAction;
import co.min.board.common.Action;

@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, Action> map = new HashMap<>(); 

    public FrontController() {
        super();
        
    }

	
	public void init(ServletConfig config) throws ServletException {
		// 들어오는 요청들을 정의
		// login.do가 들어오면 loginAction()을 실행 => 키,값
		
		// 폼 호출할 때는 action x  결과를 리턴하면 action 붙이기
		map.put("/main.do", new MainAction()); // 처음 들어오는 페이지 처리
		map.put("/login.do", new LoginAction()); // 로그인 할 때 페이지 처리
		map.put("/loginForm.do", new LoginForm());
		
		map.put("/memberList.do", new MemberListAction()); // 회원리스트 전체보기
		
		map.put("/memberForm.do", new MemberForm()); //회원가입 화면 보여줌
		map.put("/memberInsert.do", new MemberInsertAction()); // insert하는 기능
		
		map.put("/logout.do", new LogoutAction()); // 세션 remove 시킬거야
//		map.put("/loginForm.do", new LoginForm());
//		map.put("/loginForm.do", new LoginForm());
		
		
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 실제 수행할 명령들을 정의
		
		request.setCharacterEncoding("utf-8"); 
		
		String uri = request.getRequestURI(); // /BBS/main.do
		String contextPath = request.getContextPath(); // /BBS
		
		// 실제 들어오는 요청 페이지를 찾음?
		String path = uri.substring(contextPath.length()); 
		//  /BBS/main.do 여기서 /BBS 만큼 빼면 main.do가 저장됨 => 실제 페이지
		
		
		Action command = map.get(path); // MainAction() 이 실행됨
		String viewPage = command.exec(request, response);  // 명령이 수행되고 나서 보여줄 페이지를 선택
		// Action이 갖고 있는 메소드? exec의 매개값 => request, response 에 현재 request, response를 넘겨줌
		
		// viewPage가 가지고 있는 객체를 유지하면서 전달(forward)
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage); // 선택한 페이지로 가기
		dispatcher.forward(request, response); // 전달
		

		
		
		
		
		
	}

}








