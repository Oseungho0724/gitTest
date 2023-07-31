package com.hotel.reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.hotel.VO.hotelVO;
import com.hotel.py.MyClient;
import com.hotel.service.IF_HotelService;
import com.hotel.service.IF_ReservationService;
import com.hotel.service.IF_RoomService;
import com.hotel.service.IF_UserService;
import com.hotel.util.FileDataUtil;

//메인 컨트롤러
@Controller
public class HomeController {
	public static String abc = "abc";
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
	
	public MyClient mc;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	//
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		
		ressrv.reservation_autoDelete();
		return "home";
	}
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homego(Locale locale, Model model) throws Exception {
		
		ressrv.reservation_autoDelete();
		return "home";
	}
	//로그인 page 이후 첫 화면
	@RequestMapping(value = "/mainPage", method = RequestMethod.GET)
	public String mainPage(Model model, HttpSession session) throws Exception{
		model.addAttribute("cList", hotelsrv.hotel_selectCategory());
		ModelAndView mav = new ModelAndView();
		String url = "http://127.0.0.1:5000/tospring";
		String sb = "";
		String[] chuchun = new String[3];
		String ab = (String) session.getAttribute("nowUser");
		String message = ab; // 전송할 값
		
        RestTemplate restTemplate = new RestTemplate();

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 전송할 데이터 설정
        HttpEntity<String> requestEntity = new HttpEntity<>(message, headers);

        // POST 요청 보내기
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            System.out.println(responseBody);
            if(responseBody != null) {
            	chuchun = responseBody.split("/");
            }
        } else {
            System.out.println("Request failed with status: " + response.getStatusCode());
        }
        if(chuchun[0] != null) {
			List<hotelVO> hList = new ArrayList<hotelVO>();
			for (int i = 0; i < chuchun.length; i++) {
				hList.add(hotelsrv.hotel_selectBigAddr(chuchun[i]).get(0));
			}
			model.addAttribute("chuchunList", hList);
		}
//		try {
//			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
//			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
//			
//			String line = null;
//			
//			while((line = br.readLine()) != null) {
//				sb = sb + line + "/";
//			}
//			System.out.println(sb.toString());
//			if(sb.toString().contains("test")) {
//				System.out.println("abc");
//			}
//			br.close();
//			
//			System.out.println(sb.toString());
//			
//			
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		mav.addObject("test1", sb.toString());	//return 을 ModelAndView로 바꾸고 mav를 보내면 바로 mainPage에 값까지 다 넘길 수 있음
//												//model.addAttribute한거는 따로 돌아감 
//		mav.addObject("fail", false);
//		mav.setViewName("mainPage");
		
		
		
		
		
		return "mainPage";
	}
	
	
	
}
