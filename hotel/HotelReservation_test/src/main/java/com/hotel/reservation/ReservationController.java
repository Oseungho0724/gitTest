package com.hotel.reservation;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotel.VO.reservationVO;
import com.hotel.VO.roomVO;
import com.hotel.VO.userVO;
import com.hotel.service.IF_HotelService;
import com.hotel.service.IF_ReservationService;
import com.hotel.service.IF_RoomService;
import com.hotel.service.IF_UserService;
import com.hotel.util.FileDataUtil;
import static java.time.temporal.ChronoUnit.DAYS;

@Controller
public class ReservationController {
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
	
	@RequestMapping(value = "/reservation", method = RequestMethod.GET)
	public String reservation(Locale locale, Model model, @RequestParam("detailAddr_roomNum_room")String detailAddr_roomNum_room, @RequestParam("id_hotel") String id_hotel, @RequestParam("checkIn")String checkIn, @RequestParam("checkOut")String checkOut) throws Exception {
		roomVO rvo = roomsrv.room_selectDetailAddr_roomNum(detailAddr_roomNum_room);
		
		// 편의시설dao, service, mapper 작업 후 편의시설vo 넘겨받기
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDate date1 = LocalDate.parse(checkIn, formatter);
	    LocalDate date2 = LocalDate.parse(checkOut, formatter);
	    long days = DAYS.between(date1, date2);
	    int abc = (int)days;
	    rvo.setDays(abc);
		
		model.addAttribute("roomvo", rvo);
		model.addAttribute("checkIn", checkIn);
		model.addAttribute("checkOut", checkOut);
		model.addAttribute("cList", hotelsrv.hotel_selectCategory());
		return "reservation";
	}
	
	/*
	 * @RequestMapping(value = "/reservation", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public List<reservationVO> sendList() throws Exception{
	 * List<reservationVO> resvoList = ressrv.reservation_selectAll(); return
	 * resvoList; }
	 */
	
	@RequestMapping(value = "/reservation_save", method = RequestMethod.GET)
	public String reservation_save(Model model, @ModelAttribute("")reservationVO resvo) throws Exception{
		roomVO rvo = roomsrv.room_selectDetailAddr_roomNum(resvo.getDetailAddr_roomNum_res());
		resvo.setCheckInDate(resvo.getCheckIn_res());
		resvo.setCheckIn_res(resvo.getCheckIn_res()+"/"+rvo.getCheckIn_room());
		resvo.setCheckOut_res(resvo.getCheckOut_res()+"/"+rvo.getCheckOut_room());
		
		
		userVO uvo = usersrv.user_selectid(resvo.getId_res());
		
		ressrv.reservation_save(resvo);
		
		model.addAttribute("id_user", uvo.getId_user());
		
		return "redirect:myPage";
	}
	
	@RequestMapping(value = "/reservation_delete", method = RequestMethod.GET)
	public String reservation_delete(Model model, HttpServletRequest request) throws Exception {
		String delete_res_id = request.getParameter("id_res");
		String delete_res_detailAddr_roomNum = request.getParameter("detailAddr_roomNum_res");
		String delete_res_checkInDate = request.getParameter("checkInDate").split(" ")[0];
		HashMap<String,String> deleteMap = new HashMap<String,String>();
		
		deleteMap.put("id_res", delete_res_id);
		deleteMap.put("detailAddr_roomNum_res",delete_res_detailAddr_roomNum);
		deleteMap.put("checkInDate", delete_res_checkInDate);
		
		ressrv.reservation_delete(deleteMap);
		model.addAttribute("id_user", delete_res_id);
		return "redirect:myPage";
	}
	
//	@RequestMapping("/CheckDate.do")
//	public @ResponseBody int midCheck(HttpServletRequest request) throws Exception {
//		
//		String set = request.getParameter("set");
//		boolean flag = true;
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		
//		
//		String[] spl = set.split("/");
//		Date checkIn = formatter.parse(spl[0]);
//		Date checkOut = formatter.parse(spl[1]);
//		String detailAddr_roomNum = spl[2];
//		List<reservationVO> resvoList = ressrv.reservation_selectAll(detailAddr_roomNum);
//		
//		//System.out.println(set);
//		if(resvoList.size() > 0) {
//			for (int i = 0; i < resvoList.size(); i++) {
//				String Res = resvoList.get(i).getCheckIn_res();
//				Date StartRes = formatter.parse(Res.split("/")[0]);
//				Res = resvoList.get(i).getCheckOut_res();
//				Date EndRes = formatter.parse(Res.split("/")[0]);
//				int checkInResult = checkIn.compareTo(EndRes);
//				int checkOutResult = checkOut.compareTo(StartRes);
//				// System.out.println(checkIn + " / " + checkOut);
//				// System.out.println(1);
//				// System.out.println(Res);
//				// System.out.println(2);
//				// System.out.println(StartRes + " / " + EndRes);
//				if (checkInResult >= 0 || checkOutResult <= 0) {
//
//				} else {
//					flag = false;
//				}
//
//			}
//			if(flag) { 
//				return 1; 
//			}
//			else { 
//				return 0; 
//			}
//		}
//		else {
//			return 1;
//		}
//		 
//	}
	
	
	@RequestMapping("/priceCheck.do")
	public @ResponseBody int priceCheck(HttpServletRequest request) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String detailAddr_roomNum = request.getParameter("detailAddr_roomNum");
		String checkIn = request.getParameter("checkIn");
		String checkOut = request.getParameter("checkOut");
		System.out.println(checkIn + " / " + checkOut + " / " + detailAddr_roomNum);
		/*
		 * String startcheck = pricePlus.split("/")[0]; String endcheck =
		 * pricePlus.split("/")[1]; LocalDate date1 = LocalDate.parse(startcheck,
		 * formatter); LocalDate date2 = LocalDate.parse(endcheck, formatter); long days
		 * = DAYS.between(date1, date2); int abc = (int)days; System.out.println(abc);
		 * return abc;
		 */
		return 1;
	}
	
}
