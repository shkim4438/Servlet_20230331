package com.servlet.study.dto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

public class RequestDto<T> {
	
	
	public static <T> T convertRequestBody(HttpServletRequest request, Class<?> c) throws IOException {
		ServletInputStream inputStream = request.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String requestBody = bufferedReader.lines().collect(Collectors.joining());
		
		System.out.println(requestBody);
		
		Gson gson = new Gson();
		T object = (T) gson.fromJson(requestBody, c);
		
		return object; // 매번 달라지는 리턴 자료형 그로인해 제네릭을 활용.
	}
}
