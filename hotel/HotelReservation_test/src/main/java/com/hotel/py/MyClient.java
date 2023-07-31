package com.hotel.py;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.hotel.VO.hotelVO;
import com.hotel.reservation.HomeController;
import com.hotel.reservation.HotelController;

public class MyClient {


	public void sendData(String userid) {
		// TODO Auto-generated method stub
		String abc = HomeController.abc;
		//List<hotelVO> hlist = HotelController.hotelsrv.hotel_selectAll(null);
		String url = "http://localhost:5000"; // Flask 서버의 URL
        String message = abc; // 전송할 값

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
        } else {
            System.out.println("Request failed with status: " + response.getStatusCode());
        }

	}

}
