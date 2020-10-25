package co.min.board.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 인터페이스 => 메소드 원형만 갖고 있음
public interface Action {
	public String exec(HttpServletRequest request, HttpServletResponse response);
}

