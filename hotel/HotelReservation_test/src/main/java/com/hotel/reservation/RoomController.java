package com.hotel.reservation;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hotel.VO.hotelVO;
import com.hotel.VO.roomVO;
import com.hotel.service.IF_HotelService;
import com.hotel.service.IF_RoomService;
import com.hotel.service.IF_UserService;
import com.hotel.util.FileDataUtil;

@Controller
public class RoomController {
	@Inject
	private IF_UserService usersrv;

	@Inject
	private IF_HotelService hotelsrv;

	@Inject
	private IF_RoomService roomsrv;

	@Inject
	private FileDataUtil filedatautil;

	// 방 자세히 보기
	@RequestMapping(value = "/detail_room", method = RequestMethod.GET)
	public String detail_room(Model model, @RequestParam("detailAddr_roomNum_room") String detailAddr_roomNum_room,
			@RequestParam("id_hotel") String id_hotel,@RequestParam("checkIn")String checkIn, @RequestParam("checkOut")String checkOut) throws Exception {

		roomVO rvo = roomsrv.room_selectDetailAddr_roomNum(detailAddr_roomNum_room);
		LocalDate sysdate = LocalDate.now();
		sysdate = sysdate.minusDays(1);
		
		model.addAttribute("sysdate",sysdate);
		model.addAttribute("checkIn", checkIn);
		model.addAttribute("checkOut", checkOut);
		model.addAttribute("id_hotel", id_hotel);
		model.addAttribute("roomvo", rvo);
		model.addAttribute("cList", hotelsrv.hotel_selectCategory());
		return "detail_room";
	}
	@RequestMapping(value = "/detail_room_res", method = RequestMethod.GET)
	public String detail_room_res(Model model, @RequestParam("detailAddr_roomNum_room") String detailAddr_roomNum_room,
			@RequestParam("id_hotel") String id_hotel) throws Exception {
		roomVO rvo = roomsrv.room_selectDetailAddr_roomNum(detailAddr_roomNum_room);
		model.addAttribute("id_hotel", id_hotel);
		model.addAttribute("roomvo", rvo);
		model.addAttribute("cList", hotelsrv.hotel_selectCategory());
		return "detail_room_res";
	}
	// 방 등록 화면
	@RequestMapping(value = "/join_room", method = RequestMethod.GET)
	public String join_room(Model model, @RequestParam("detailAddr_hotel") String detailAddr_hotel) throws Exception {
		
		model.addAttribute("detailAddr_hotel", detailAddr_hotel);
		model.addAttribute("hotelvo", hotelsrv.hotel_selectDetailAddr(detailAddr_hotel));
		return "join_room";
	}

	// 방 등록 정보 저장
	@RequestMapping(value = "/join_room_save", method = RequestMethod.POST)
	public String join_room_save(Model model, @ModelAttribute("") roomVO roomvo, MultipartFile[] file)
			throws Exception {
		roomvo.setDetailAddr_roomNum_room(roomvo.getDetailAddr_room() +"/"+ roomvo.getRoomNum_room());
		String[] newName = filedatautil.fileUpload(file);

		roomvo.setFilename_room(newName);
		roomsrv.join_room_save(roomvo);

		hotelVO hvo = hotelsrv.hotel_selectDetailAddr(roomvo.getDetailAddr_room());
		String detailAddr_hotel = hvo.getDetailAddr_hotel();
		List<roomVO> roomList = roomsrv.room_selectDetailAddr(detailAddr_hotel);
		
		
		model.addAttribute("detailAddr_hotel", detailAddr_hotel);
		model.addAttribute("id_hotel", hvo.getId_hotel());
		

		return "redirect:detail_hotel";
	}

	// 방 정보 업데이트 입력 화면
	@RequestMapping(value = "/update_room", method = RequestMethod.GET)
	public String update_room(Model model, @RequestParam("detailAddr_roomNum_room") String detailAddr_roomNum_room,
			HttpSession session) throws Exception {
		roomVO roomvo = roomsrv.room_selectDetailAddr_roomNum(detailAddr_roomNum_room);

		model.addAttribute("roomvo", roomvo);
		return "update_room";
	}

	// 방 정보 업데이트
	@RequestMapping(value = "/update_room_save", method = RequestMethod.POST)
	public String update_room_save(Model model, @ModelAttribute("") roomVO rvo, MultipartFile[] file,
			HttpSession session) throws Exception {
		// roomVO roomvo =
		// roomsrv.room_selectDetailAddr_roomNum(detailAddr_roomNum_room);

		String[] newName = filedatautil.fileUpload(file);
		rvo.setFilename_room(newName);
		roomsrv.update_room_save(rvo);
		
		model.addAttribute("id_hotel", session.getAttribute("nowUser"));
		model.addAttribute("checkIn", rvo.getCheckIn_room());
		model.addAttribute("checkOut", rvo.getCheckOut_room());
		model.addAttribute("cList", hotelsrv.hotel_selectCategory());
		model.addAttribute("detailAddr_roomNum_room", rvo.getDetailAddr_roomNum_room());
		
		return "redirect:detail_room";
	}

	@RequestMapping(value = "/delete_room", method = RequestMethod.GET)
	public String delete_room(Model model, @RequestParam("detailAddr_roomNum_room") String detailAddr_roomNum_room,
			HttpSession session) throws Exception {
		roomVO rvo = roomsrv.room_selectDetailAddr_roomNum(detailAddr_roomNum_room);

		roomsrv.delete_room(detailAddr_roomNum_room);

		hotelVO detail_hotel = hotelsrv.hotel_selectDetailAddr(rvo.getDetailAddr_room());
		// List<roomVO>
		List<roomVO> roomList = roomsrv.room_selectDetailAddr(rvo.getDetailAddr_room());

		model.addAttribute("cList", hotelsrv.hotel_selectCategory());
		model.addAttribute("hotelvo", detail_hotel);
		model.addAttribute("detailAddr_hotel", rvo.getDetailAddr_room());
		model.addAttribute("id_hotel", session.getAttribute("nowUser"));
		model.addAttribute("roomList", roomList);

		return "redirect:detail_hotel";
	}

}
