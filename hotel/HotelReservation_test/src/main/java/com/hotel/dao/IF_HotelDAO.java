package com.hotel.dao;

import java.util.HashMap;
import java.util.List;

import com.hotel.VO.hotelVO;


//호텔 관리 DAO
public interface IF_HotelDAO {
	public void join_hotel_save(hotelVO hvo) throws Exception;
	public List<hotelVO> hotel_selectAll(String category) throws Exception;
	public List<String> hotel_selectCategory() throws Exception;
	public hotelVO hotel_selectDetailAddr(String detailAddr_hotel) throws Exception;
	public List<hotelVO> search_hotel(HashMap<String,String> hotelMap) throws Exception;
	public List<hotelVO> hotel_select_my(String id_user) throws Exception;
	public void update_hotel_save(hotelVO hvo) throws Exception;
	public void delete_hotel(String detailAddr_hotel) throws Exception;
	public List<hotelVO> hotel_selectBigAddr(String bigAddr_hotel) throws Exception;
	public void test();
	public void test1();
	public void test2();
}
