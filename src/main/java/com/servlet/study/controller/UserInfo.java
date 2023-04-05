package com.servlet.study.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


@WebServlet({"/user" })
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setCharacterEncoding("UTF-8"); //response.getWriter()가 일어나기전에 인코딩
		
		/*====================<<Json형식>>========================*/
		Gson gson = new Gson();
		
		System.out.println("GET 요청");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		
		Map<String, String> userMap = new HashMap<>();
		userMap.put("name", name);
		userMap.put("phone", phone);
		// map.put: key : value 에서 기존의 value를 수정 
		// 즉, key값을 통해여 value값을 새로 수정하면 본래의 값에 덮어쓰여지는 형식.
		
		String userJson = gson.toJson(userMap);
		System.out.println(userJson);
		/*====================<<>>========================*/
		/**
		 * GET요청의 특징. 데이터를 조회한다는 개념
		 * 1. 주소창, src, href, replace 등으로 요청할 수 있음.
		 * 2. 데이터를 전달(요청)하는 방법(Query String), 
		 * 		http(s)://서버주소(로컬호스트): 포트/요청메세지?key=value&key=value
		 * 
		 */
		System.out.println(response.getCharacterEncoding());
		
//		response.addHeader("Content-Type", "text/html;charset=UTF-8");
//		response.addHeader("test", "test data"); // 응답으로 key, value를 보내었음.
//		response.setContentType("text/html;charset=UTF-8");			HTML 응답
		response.setContentType("application/json;charset=UTF-8"); // json으로 응답함. 또한 charset 인코딩 할필요없음.
		
		PrintWriter out = response.getWriter();
//		out.println("이름: " + name);
//		out.println("연락처: " + phone);
		out.println(userJson);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST 요청");
		request.setCharacterEncoding("UTF-8");
//		System.out.println(request.getParameter("address"));
		
		Gson gson = new Gson();
		
		ServletInputStream inputStream = request.getInputStream();
		System.out.println(inputStream);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		System.out.println(bufferedReader);
		String json = "";
		String line = null;
		while((line = bufferedReader.readLine()) != null) {
			json += line;
			System.out.println(line);
		}
		json.replaceAll(" ", "");
		System.out.println(json);
		
//		AtomicReference<String> jsonAtomic = new AtomicReference<>("");
//		bufferedReader.lines().forEach(line -> {
//			jsonAtomic.getAndUpdate(t -> t + line);
//		});
//		String json = jsonAtomic.get().replace(" ", "");
//		System.out.println(json);
		
//		String json = bufferedReader.lines().collect(Collectors.joining();
		
		Map<String, String> jsonMap = gson.fromJson(json, Map.class);
		
		System.out.println(jsonMap);
		
		/**
		 * post요청. 데이터를 수정 또는 값을 집어넣는 목적.
		 * 1. 
		 * <form action="요청메세지(http://localhost:8080/Servlet_Study_20230331/user)" method="post" >
		 * 		<input name="key" value="value">
		 * 		<button type="submit">요청</button>
		 * </form>
		 * 
		 * 요청에대한 응답, post요청, put(수정)요청만 json형식으로 보냄.
		 */
	}

}
