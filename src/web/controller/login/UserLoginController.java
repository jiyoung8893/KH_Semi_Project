package web.controller.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import web.dto.User;
import web.service.face.UserService;
import web.service.impl.UserServiceImpl;


@WebServlet("/login")
public class UserLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//포워딩
		req.getRequestDispatcher("/WEB-INF/views/login/login.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		
		//전달파라미터 얻기 - 로그인정보
		User user = userService.getLoginUser(req);
		
		//로그인 인증
		boolean login = userService.login(user);
		
		//로그인 성공
		if(login) {
			//로그인 사용자 정보 얻어오기
			user = userService.info(user);
			
			//세션 저장
			HttpSession session = req.getSession();
			session.setAttribute("login", login);
			session.setAttribute("userid", user.getUserId());
			session.setAttribute("username", user.getUserName());
			session.setAttribute("usernick", user.getUserNick());
			session.setAttribute("userAuth", user.getUserAuth());
			
			//클라이언트에 보낼 정보
			Map map = new HashMap();
			map.put("login", "success" );
			map.put("userAuth", user.getUserAuth());
			
			
			//로그인 성공했다는 것을 클라이언트에 알려줘야함
			//자바스크립트가 알아들을 수 있도록
			PrintWriter out = resp.getWriter();
			out.println( new Gson().toJson(map) );
			
		}
		
		//로그인 실패
		if(!login) {
			
			//클라이언트에 보낼 정보
			Map map = new HashMap();
			map.put("login", "fail");
			
			//로그인 실패했다는 것을 클라이언트에 알려줘야함
			//자바스크립트가 알아들을 수 있도록
			PrintWriter out = resp.getWriter();
			out.println( new Gson().toJson(map) );
			
		}
		
	}
	
	
}
