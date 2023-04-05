package com.servlet.study.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.servlet.study.dto.RequestDto;
import com.servlet.study.dto.ResponseDto;
import com.servlet.study.entity.User;
import com.servlet.study.service.UserService;
import com.servlet.study.service.UserServiceImpl;



@WebServlet("/auth")
public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserServiceImpl userService;
	private Gson gson;
    
    public Authentication() {
        userService = UserServiceImpl.getInstance();
        gson = new Gson();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		
				
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = RequestDto.<User>convertRequestBody(request,User.class);
		// 명확한 매개변수에 따라 리턴 타입이 결정됨.
//		Map<String, String> map = RequestDto.<Map<String, String>>convertRequestBody(request, Map.class); 
		System.out.println(user);
		
		boolean duplictaedFlag = userService.duplicatedUsername(user.getUsername());
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(duplictaedFlag) {
			// true == 중복, false == 가입가능
			ResponseDto<Boolean> responseDto = new ResponseDto<Boolean>(400, "duplicated username", duplictaedFlag);
			out.println(gson.toJson(responseDto));
			return;
		}
		
		ResponseDto<Integer> responseDto =
				new ResponseDto<Integer>(201, "signup", userService.addUser(user));
		out.println(gson.toJson(responseDto));
		
	}

}
