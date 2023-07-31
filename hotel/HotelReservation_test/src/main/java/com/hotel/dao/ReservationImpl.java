package com.hotel.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hotel.VO.reservationVO;

@Repository
public class ReservationImpl implements IF_ReservationDAO{
	
	@Inject
	SqlSession sqlSession;

	private static String mapperQuery= "com.hotel.dao.IF_ReservationDAO";
	
	@Override
	public void reservation_save(reservationVO resvo) throws Exception {
		// TODO Auto-generated method stub
		
		sqlSession.insert(mapperQuery+".reservation_save", resvo);
	}

	@Override
	public List<reservationVO> reservation_selectAll(String detailAddr_roomNum_res) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(mapperQuery+".reservation_selectAll",detailAddr_roomNum_res);
	}

	@Override
	public List<reservationVO> reservation_selectId(String id_res) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(mapperQuery+".reservation_selectId", id_res);
	}

	@Override
	public void reservation_autoDelete() throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(mapperQuery+".reservation_autoDelete");
	}

	@Override
	public void reservation_delete(HashMap<String, String> deleteMap) throws Exception {
		// TODO Auto-generated method stub
		
		sqlSession.delete(mapperQuery+".reservation_delete", deleteMap);
	}

	@Override
	public List<reservationVO> reservation_selectDetailAddr(String detailAddr) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(mapperQuery+".reservation_selectDetailAddr", detailAddr);
	}

	@Override
	public List<reservationVO> do_reservation_select() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(mapperQuery+".do_reservation_select");
	}

	@Override
	public void do_reservation_autoSave(reservationVO resvo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(mapperQuery+".do_reservation_autoSave", resvo);
	}
	
	
}
