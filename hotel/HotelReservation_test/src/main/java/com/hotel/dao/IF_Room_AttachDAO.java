package com.hotel.dao;

import java.util.List;


//방 이미지 관리 DAO
public interface IF_Room_AttachDAO {
	public void insertImg(String detailAddr_roomNum_imgfile , String filename) throws Exception;
	public List<String> room_selectImg(String detailAddr_roomNum_imgfile) throws Exception;
	public void deleteImg(String detailAddr_roomNum_imgfile) throws Exception;
}
