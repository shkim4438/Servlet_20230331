<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%!
	// 선언문(클래스 정의, 함수 정의 즉, 클래스 안이라고 보면됨.)
	
%>

<%
	// 스크립틀릿란? java 소스코드 작성가능 (메소드안)
	String wc = (String) request.getAttribute("welcome");
	out.println(wc);
	int[] numbers = (int[]) request.getAttribute("numbers");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1><%=wc %></h1>
	<%
		for(int i = 0; i < numbers.length; i++){
	%>
		<div><%=numbers[i] %></div>
	<%
		}
	%>
	<form action="/auth/signin" method="post">
		<input type="text" name="username" placeholder="username">
		<input type="password" name="password" placeholder="password">
		<button type="submit">로그인</button>
	</form>
</body>
</html>