package co.min.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.min.board.common.Action;

public class LoginForm implements Action{

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
		return "jsp/main/loginForm.jsp";
	}

}
