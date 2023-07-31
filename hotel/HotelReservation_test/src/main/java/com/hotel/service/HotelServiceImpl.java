package com.hotel.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hotel.VO.hotelVO;
import com.hotel.dao.IF_HotelDAO;

@Service
public class HotelServiceImpl implements IF_HotelService{

	@Inject
	private IF_HotelDAO hdao;

	@Override
	public void join_hotel_save(hotelVO hvo) throws Exception {
		// TODO Auto-generated method stub
		hdao.join_hotel_save(hvo);
	}

	@Override
	public List<hotelVO> hotel_selectAll(String category) throws Exception {
		// TODO Auto-generated method stub
		return hdao.hotel_selectAll(category);
	}

	@Override
	public List<String> hotel_selectCategory() throws Exception {
		// TODO Auto-generated method stub
		return hdao.hotel_selectCategory();
	}

	@Override
	public hotelVO hotel_selectDetailAddr(String detailAddr_hotel) throws Exception {
		// TODO Auto-generated method stub
		return hdao.hotel_selectDetailAddr(detailAddr_hotel);
	}

	@Override
	public List<hotelVO> search_hotel(HashMap<String, String> hotelMap) throws Exception {
		// TODO Auto-generated method stub
		return hdao.search_hotel(hotelMap);
	}

	@Override
	public List<hotelVO> hotel_select_my(String id_user) throws Exception {
		// TODO Auto-generated method stub
		return hdao.hotel_select_my(id_user);
	}

	@Override
	public void update_hotel_save(hotelVO hvo) throws Exception {
		// TODO Auto-generated method stub
		hdao.update_hotel_save(hvo);
	}

	@Override
	public void delete_hotel(String detailAddr_hotel) throws Exception {
		// TODO Auto-generated method stub
		hdao.delete_hotel(detailAddr_hotel);
	}

	@Override
	public List<hotelVO> hotel_selectBigAddr(String bigAddr_hotel) throws Exception {
		// TODO Auto-generated method stub
		return hdao.hotel_selectBigAddr(bigAddr_hotel);
	}
}
