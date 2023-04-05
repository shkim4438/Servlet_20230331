package com.servlet.study.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.servlet.study.dto.ResponseDto;
import com.servlet.study.entity.Role;
import com.servlet.study.service.RoleService;
import com.servlet.study.service.RoleServiceImpl;

/**
 * 
 * 데이터베이스에서 파라미터로 넘어온 RoleName이 존재하는지 여부확인
 * 	존재한다면 ResponseDto Json(200, success, true)
 * 	존재하지 않으면 ResponseDto Json(400, error, false)
 * 	
 * 	RoleService
 * 	RoleRepository
 *
 */


@WebServlet("/role")
public class RoleInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private RoleService roleService;
	private Gson gson;
    
    public RoleInfo() {
    	roleService = RoleServiceImpl.getInstance();
    	gson = new Gson();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roleName = request.getParameter("roleName");
		System.out.println("roleName: " + roleName);
		
		Role role = (roleService.getRole(roleName));
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if (role == null) {
			out.println(new ResponseDto<>(400, "error", false));
			return;
		}
		
		out.println(new ResponseDto<>(200, "error", true));
	}

	/**
	 * <<error 종류>>
	 * 400
	 * 403
	 * 405
	 * 415
	 * 
	 * 클라이언트 -> : GET요청 /role ? key=value로 요청을 날려 서블릿의 getPrameter()를 가져옴. 요청때 header body(value임)가 같이 날라옴.
	 * 				   POST요청 Json으로 보내어 getPrameter()로 들어옴.
	 * 왓츠(톰켓) -> : IP:port (localhost:8080/)
	 * servlet -> /role, doGet(), doPost()
	 * service(비즈니스 로직 ) -> : 요청온 기능을 servlet을 DI즉 결집도를 낮추고 관련 메소드를 묶어 관리 응집도를 높임.
	 * Repository -> 서비스와 비슷
	 * DB
	 * 
	 * filter: 전처리와 후처리로 나눠짐.
	 */


	


}
