package com.hotel.reservation;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotel.VO.userVO;
import com.hotel.service.IF_UserService;

//로그인 관련 컨트롤러
@Controller
public class LoginController {
	
	@Inject
	private IF_UserService usersrv;
	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login( Model model, HttpSession session,
			@RequestParam("id_user") String id_user) throws Exception{
		
		String notUser = "비회원";
		
		if(!id_user.equals(notUser)) {
			userVO uvo = usersrv.user_selectid(id_user);
			session.setAttribute("nowUser", id_user);
			session.setAttribute("userType", uvo.getType_user());
		}
		else {
			session.setAttribute("nowUser", "비회원");
		}
		
		return "redirect:mainPage";
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String home(Locale locale, Model model,
			HttpSession session) {
		session.invalidate();  //세션을 무력화 시키는 메서드
		
		
		return "redirect:home";
	}
	
	@RequestMapping("/LoginCheck.do")
	public @ResponseBody int LoginCheck(HttpServletRequest request) throws Exception {
		String ID = request.getParameter("ID");
		String PW = request.getParameter("PW");
		List<userVO> uvoList = usersrv.user_selectAll();
		boolean flag = true;
		
		for(int i = 0 ; i < uvoList.size();i++) {
			if(uvoList.get(i).getId_user().equals(ID) && uvoList.get(i).getPw_user().equals(PW)) {
				flag = false;
				break;
			}
		}
		if(!flag) {
			return 1;
		}
		else {
			return 0;
		}
	}
}
