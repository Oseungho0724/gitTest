package com.hotel.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hotel.VO.reservationVO;
import com.hotel.dao.IF_ReservationDAO;

@Service
public class ReservationServiceImpl implements IF_ReservationService{

	@Inject
	private IF_ReservationDAO resdao;

	
	@Override
	public void reservation_save(reservationVO resvo) throws Exception {
		// TODO Auto-generated method stub
		resdao.reservation_save(resvo);
	}


	@Override
	public List<reservationVO> reservation_selectAll(String detailAddr_roomNum_res) throws Exception {
		// TODO Auto-generated method stub
		return resdao.reservation_selectAll(detailAddr_roomNum_res);
	}


	@Override
	public List<reservationVO> reservation_selectId(String id_res) throws Exception {
		// TODO Auto-generated method stub
		return resdao.reservation_selectId(id_res);
	}


	@Override
	public void reservation_autoDelete() throws Exception {
		// TODO Auto-generated method stub
		List<reservationVO> resList = resdao.do_reservation_select();
		if(resList.size() > 0) {
			for(reservationVO resvo : resList) {
				resdao.do_reservation_autoSave(resvo);
			}
		}
		resdao.reservation_autoDelete();
	}


	@Override
	public void reservation_delete(HashMap<String, String> deleteMap) throws Exception {
		// TODO Auto-generated method stub
		resdao.reservation_delete(deleteMap);
	}


	@Override
	public List<reservationVO> reservation_selectDetailAddr(String detailAddr) throws Exception {
		// TODO Auto-generated method stub
		return resdao.reservation_selectDetailAddr(detailAddr);
	}
	
}
