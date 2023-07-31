package com.hotel.reservation;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotel.VO.hotelVO;
import com.hotel.VO.reservationVO;
import com.hotel.VO.userVO;
import com.hotel.service.IF_HotelService;
import com.hotel.service.IF_ReservationService;
import com.hotel.service.IF_RoomService;
import com.hotel.service.IF_UserService;
import com.hotel.util.FileDataUtil;

@Controller
public class UserController {
	@Inject
	private IF_UserService usersrv;

	@Inject
	private IF_HotelService hotelsrv;

	@Inject
	private IF_RoomService roomsrv;

	@Inject
	private FileDataUtil filedatautil;

	@Inject
	private IF_ReservationService ressrv;
	// 회원가입 Page
	@RequestMapping(value = "/join_user", method = RequestMethod.GET)
	public String join_user() {

		return "join_user";
	}

	// 회원가입 버튼 클릭 시 유저 정보 저장
	@RequestMapping(value = "/join_user_save", method = RequestMethod.POST)
	public String join_user_save(Model model, @ModelAttribute("") userVO uvo) throws Exception {

		usersrv.join_user_save(uvo);

		return "home";
	}

	// 내 정보 확인
	@RequestMapping(value = "/myPage", method = RequestMethod.GET)
	public String myPage(Model model, @RequestParam("id_user") String id_user) throws Exception {
		userVO uvo = usersrv.user_selectid(id_user);
		List<reservationVO> resList = ressrv.reservation_selectId(id_user);
		if(resList.size() > 0 ) {
			for(reservationVO resvo : resList) {
				String splDetailAddr = resvo.getDetailAddr_roomNum_res().split("/")[0];
				hotelVO hvo = hotelsrv.hotel_selectDetailAddr(splDetailAddr);
				resvo.setHotelname(hvo.getName_hotel() + " / " + resvo.getDetailAddr_roomNum_res().split("/")[1]);
			}
		}
		model.addAttribute("uservo", uvo);
		model.addAttribute("cList", hotelsrv.hotel_selectCategory());
		model.addAttribute("resList", resList);
		return "myPage";
	}

	// 유저 삭제
	@RequestMapping(value = "/delete_user", method = RequestMethod.GET)
	public String delete_user(Model model, @RequestParam("id_user") String id_user) throws Exception {
		usersrv.delete_user(id_user);

		return "redirect:logout";
	}

	// 유저 업데이트 입력 화면
	@RequestMapping(value = "/update_user", method = RequestMethod.GET)
	public String update_user(Model model, @RequestParam("id_user") String id_user) throws Exception {
		userVO uvo = usersrv.user_selectid(id_user);

		model.addAttribute("uvo", uvo);
		return "update_user";
	}

	// 유저 업데이트
	@RequestMapping(value = "/update_user_save", method = RequestMethod.POST)
	public String update_user_save(Model model, @ModelAttribute("") userVO uvo) throws Exception {
		usersrv.update_user_save(uvo);
		model.addAttribute("id_user", uvo.getId_user());
		return "redirect:myPage";
	}
	
	@RequestMapping("/join_user_check.do")
	public @ResponseBody int join_user_check(HttpServletRequest request) throws Exception {
		String ID = request.getParameter("ID");
		userVO uvo = usersrv.user_selectid(ID);
		if(uvo != null) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
}
