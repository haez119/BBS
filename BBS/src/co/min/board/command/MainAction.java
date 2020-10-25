package co.min.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.min.board.common.Action;

public class MainAction implements Action {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 메인액션은 페이지만 보여주면 됨
		return "jsp/main/main.jsp";
	}
	

}
