package com.hotel.reservation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.multipart.MultipartFile;

import com.hotel.VO.hotelVO;
import com.hotel.VO.reservationVO;
import com.hotel.VO.roomVO;
import com.hotel.service.IF_HotelService;
import com.hotel.service.IF_ReservationService;
import com.hotel.service.IF_RoomService;
import com.hotel.service.IF_UserService;
import com.hotel.util.FileDataUtil;
import static java.time.temporal.ChronoUnit.DAYS;

@Controller
public class HotelController {
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

	// 숙소 등록 화면 전송
	@RequestMapping(value = "/join_hotel", method = RequestMethod.GET)
	public String join_hotel(Model model) {

		return "join_hotel";
	}

	// 슥소 등록 버튼 시 숙소 정보 저장
	@RequestMapping(value = "/join_hotel_save", method = RequestMethod.POST)
	public String join_hotel_save(@ModelAttribute("") hotelVO hvo, MultipartFile[] file) throws Exception {

		String[] newName = filedatautil.fileUpload(file);

		String mainImg = newName[0];

		hvo.setImg_hotel(mainImg);

		System.out.println(hvo.getCheckIn_hotel());
		System.out.println(hvo.getCheckOut_hotel());
		hotelsrv.join_hotel_save(hvo);

		return "redirect:mainPage";
	}

	// 호텔 리스트 불러오는 page
	@RequestMapping(value = "/list_hotel", method = RequestMethod.GET)
	public String list_hotel(Model model, @RequestParam("category_hotel") String category) throws Exception {
		List<hotelVO> hotelList = hotelsrv.hotel_selectAll(category);
		model.addAttribute("hotelList", hotelList);
		model.addAttribute("cList", hotelsrv.hotel_selectCategory());
		model.addAttribute("cate", category);

		return "list_hotel";
	}

	// 각 호텔 클릭시 리스트 보기 (미구현)
	@RequestMapping(value = "/detail_hotel", method = RequestMethod.GET)
	public String detail_hotel(Model model, @RequestParam("detailAddr_hotel") String detailAddr_hotel,
			@RequestParam("id_hotel") String id_hotel) throws Exception {
		hotelVO detail_hotel = hotelsrv.hotel_selectDetailAddr(detailAddr_hotel);
		// List<roomVO>
		List<roomVO> roomList = roomsrv.room_selectDetailAddr(detailAddr_hotel);
		LocalDate sysdate = LocalDate.now();
		sysdate = sysdate.plusDays(1);
		LocalDate sysdatePlus = sysdate.plusDays(1);
		
		model.addAttribute("sysdate",sysdate);
		model.addAttribute("sysdatePlus",sysdatePlus);
		model.addAttribute("cList", hotelsrv.hotel_selectCategory());
		model.addAttribute("hotelvo", detail_hotel);
		model.addAttribute("detailAddr_hotel", detailAddr_hotel);
		model.addAttribute("id_hotel", id_hotel);
		model.addAttribute("roomList", roomList);
		return "detail_hotel";
	}

	// 숙소 제목으로 검색
	@RequestMapping(value = "/search_hotel", method = RequestMethod.POST)
	public String search_hotel(Model model, @RequestParam("search_category_hotel") String search_category_hotel,
			@RequestParam("search_name_hotel") String search_name_hotel) throws Exception {

		// System.out.println(search_category_hotel + " / " + search_name_hotel);
		HashMap<String, String> hotelMap = new HashMap<String, String>();
		hotelMap.put("search_category", search_category_hotel);
		hotelMap.put("search_name", search_name_hotel);
		List<hotelVO> hotelList = hotelsrv.search_hotel(hotelMap);

		model.addAttribute("hotelList", hotelList);
		model.addAttribute("cList", hotelsrv.hotel_selectCategory());
		model.addAttribute("cate", search_category_hotel);
		return "list_hotel";
	}

	// 사업주 내 호텔 리스트 확인
	@RequestMapping(value = "/myHotel", method = RequestMethod.GET)
	public String myhotel(Model model, @RequestParam("id_user") String id_user) throws Exception {
		List<hotelVO> hotelList = hotelsrv.hotel_select_my(id_user);
		model.addAttribute("hotelList", hotelList);
		model.addAttribute("cList", hotelsrv.hotel_selectCategory());
		// model.addAttribute("cate",category);

		return "list_hotel";
	}

	// 숙소 업데이트 입력 화면
	@RequestMapping(value = "/update_hotel", method = RequestMethod.GET)
	public String update_hotel(Model model, @RequestParam("detailAddr_hotel") String detailAddr_hotel)
			throws Exception {
		hotelVO hvo = hotelsrv.hotel_selectDetailAddr(detailAddr_hotel);

		model.addAttribute("hvo", hvo);
		return "update_hotel";
	}

	// 숙소 업데이트
	@RequestMapping(value = "/update_hotel_save", method = RequestMethod.POST)
	public String update_hotel_save(Model model, @ModelAttribute("") hotelVO hvo, MultipartFile[] file)
			throws Exception {

		String[] newName = filedatautil.fileUpload(file);

		String mainImg = newName[0];
		if (mainImg == null) {
			mainImg = "";
		}
		hvo.setImg_hotel(mainImg);

		hotelsrv.update_hotel_save(hvo);
		model.addAttribute("detailAddr_hotel", hvo.getDetailAddr_hotel());
		model.addAttribute("id_hotel", hvo.getId_hotel());

		return "redirect:detail_hotel";
	}

	// 숙소 삭제
	@RequestMapping(value = "/delete_hotel", method = RequestMethod.GET)
	public String delete_hotel(Model model, @RequestParam("detailAddr_hotel") String detailAddr_hotel,
			@RequestParam("id_user") String id_user) throws Exception {
		// 미리 내 숙소정보가져와서 저장해놓고 삭제 후
		// 미리 가져온 정보를 기반으로 리스트 검색해서 가져오기
		hotelsrv.delete_hotel(detailAddr_hotel);
		List<hotelVO> hotelList = hotelsrv.hotel_select_my(id_user);
		
		model.addAttribute("hotelList", hotelList);
		model.addAttribute("cList", hotelsrv.hotel_selectCategory());

		return "list_hotel";
	}

	// 비동기)가격 곱해지기 + 예약가능한 방 보여주기
	@RequestMapping("/CheckDate.do")
	public @ResponseBody List<roomVO> priceCheck(HttpServletRequest request) throws Exception {
		String checkIn = request.getParameter("checkIn");
		String checkOut = request.getParameter("checkOut");
		String detailAddr = request.getParameter("detailAddr");
		System.out.println(checkIn + "/" + checkOut + "/" + detailAddr);

		List<reservationVO> resList = ressrv.reservation_selectDetailAddr(detailAddr);
		//System.out.println(resList.get(0).getDetailAddr_roomNum_res());
		System.out.println(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date1 = LocalDate.parse(checkIn, formatter);
		LocalDate date2 = LocalDate.parse(checkOut, formatter);
		List<roomVO> roomList = roomsrv.room_selectDetailAddr(detailAddr);
		System.out.println(1);
		List<String> a = new ArrayList<String>();
		System.out.println(1);
//		System.out.println(resList.size() + " / " + resList.get(0).getDetailAddr_roomNum_res());
		System.out.println(resList.size());
		if (resList.size() > 0) {
			for (reservationVO resvo : resList) {
				if (resvo.getCheckIn_res().compareTo(checkOut) >= 0 || resvo.getCheckOut_res().compareTo(checkIn) <= 0) {
					System.out.println("예약가능");
				} else {
					a.add(resvo.getDetailAddr_roomNum_res());
					System.out.println("예약불가능");
				}
			}
			for (int i = 0; i < a.size(); i++) {
				for (int j = 0; j < roomList.size(); j++) {
					if (a.get(i).equals(roomList.get(j).getDetailAddr_roomNum_room())) {
						roomList.remove(j);
						break;
					}
				}
			}
		}
		long days = DAYS.between(date1, date2);
		int abc = (int) days;
		for (roomVO rvo : roomList) {
			rvo.setDays(abc);
		}
		return roomList;

	}
}
