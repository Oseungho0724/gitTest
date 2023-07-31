package com.hotel.service;

import java.util.List;

import com.hotel.VO.roomVO;


//방 관련 서비스
public interface IF_RoomService {
	public void join_room_save(roomVO rvo) throws Exception;
	public List<roomVO> room_selectDetailAddr(String detailAddr_room) throws Exception;
	public roomVO room_selectDetailAddr_roomNum(String detailAddr_roomNum_room) throws Exception;
	public void update_room_save(roomVO rvo) throws Exception;
	public void delete_room(String detailAddr_roomNum_room) throws Exception;
}
