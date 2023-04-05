package com.servlet.study.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.servlet.study.dto.ResponseDto;
import com.servlet.study.entity.User;
import com.servlet.study.service.UserService;
import com.servlet.study.service.UserServiceImpl;


@WebServlet("/auth/signin")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private UserService userService;
	private Gson gson;
    
	// login인증은 post로 주소창에 노출을 막기위함.
    public SignIn() {
        userService = UserServiceImpl.getInstance();
        gson = new Gson();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = userService.getUser(username);
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if (user == null) {
			//로그인 실패 1(아이디 찾을 수 없음)
			ResponseDto<Boolean> responseDto =
					new ResponseDto<Boolean>(400, "사용자 인증 실패", false);
			out.println(gson.toJson(responseDto));
		}
		
		if (!user.getPassword().equals(password)) {
			// 로그인 실패 2 (비밀번호 틀림)
			ResponseDto<Boolean> responseDto =
					new ResponseDto<Boolean>(400, "사용자 인증 실패", false);
			out.println(gson.toJson(responseDto));
		}
		// 로그인 성공
		HttpSession session = request.getSession();
		session.setAttribute("AuthenticationPrincipal", user.getUserId());
		// session?? 
		// 인증시 2가지 방법 유, 로그인때 인증을된 사용자를 기억을 위해 why? 요청이 새로일어나면 서버에 다시들어간다는것 인증된 사용자만 들어오게 할대 그인증 가능케 하는 기능을 세션이 보유.
		// AuthenticationPrincipal 저장된 사용자의 아이디를 가져와 인증절차를 밟음.
		
		ResponseDto<Boolean> responseDto =
				new ResponseDto<Boolean>(200, "사용자 인증 성공", true);
		out.println(gson.toJson(responseDto));
	}

}
