package com.hotel.dao;

import java.util.HashMap;
import java.util.List;

import com.hotel.VO.reservationVO;

public interface IF_ReservationDAO {
	
	public void reservation_save(reservationVO resvo) throws Exception;
	public List<reservationVO> reservation_selectAll(String detailAddr_roomNum_res) throws Exception; 
	public List<reservationVO> reservation_selectId(String id_res) throws Exception;
	public void reservation_autoDelete() throws Exception;
	public void reservation_delete(HashMap<String,String> deleteMap) throws Exception;
	public List<reservationVO> reservation_selectDetailAddr(String detailAddr) throws Exception;
	public List<reservationVO> do_reservation_select() throws Exception;
	public void do_reservation_autoSave(reservationVO resvo) throws Exception;
}
