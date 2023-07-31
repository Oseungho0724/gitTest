package com.hotel.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hotel.VO.roomVO;
import com.hotel.dao.IF_RoomDAO;
import com.hotel.dao.IF_Room_AttachDAO;

@Service
public class RoomServiceImpl implements IF_RoomService{
	@Inject
	private IF_RoomDAO rdao;
	
	@Inject
	private IF_Room_AttachDAO radao;
	
	@Override
	public void join_room_save(roomVO rvo) throws Exception {
		// TODO Auto-generated method stub
		
		rdao.join_room_save(rvo);
		
		for(int i = 0 ; i < rvo.getFilename_room().length;i++) {
			if(rvo.getFilename_room()[i] != null) {
				radao.insertImg(rvo.getDetailAddr_roomNum_room(),rvo.getFilename_room()[i] );
			}
			
		}
	}

	@Override
	public List<roomVO> room_selectDetailAddr(String detailAddr_room) throws Exception {
		// TODO Auto-generated method stub
		List<roomVO> roomList = rdao.room_selectDetailAddr(detailAddr_room);
		
		for(int i = 0 ; i < roomList.size();i++) {
			List<String> imgList = radao.room_selectImg(roomList.get(i).getDetailAddr_roomNum_room());
			String[] img = new String[3];
			for(int j = 0 ; j < imgList.size();j++) {
				
				img[j] = imgList.get(j);
			}
			roomList.get(i).setFilename_room(img);
		}
		
		return roomList;
	}

	@Override
	public roomVO room_selectDetailAddr_roomNum(String detailAddr_roomNum_room) throws Exception {
		// TODO Auto-generated method stub
		roomVO rvo = rdao.room_selectDetailAddr_roomNum(detailAddr_roomNum_room);
		String[] img = new String[3];
		
		List<String> imgList = radao.room_selectImg(detailAddr_roomNum_room);
		for(int j = 0 ; j < imgList.size();j++) {
			img[j] = imgList.get(j);
		}
		rvo.setFilename_room(img);
		
		return rvo;
	}

	@Override
	public void update_room_save(roomVO rvo) throws Exception {
		// TODO Auto-generated method stub
		
		if(rvo.getFilename_room()[0] != null) {
			radao.deleteImg(rvo.getDetailAddr_roomNum_room());
			
			for(int i = 0 ; i < rvo.getFilename_room().length;i++) {
				if(rvo.getFilename_room()[i] != null) {
					radao.insertImg(rvo.getDetailAddr_roomNum_room(),rvo.getFilename_room()[i] );
				}
				
			}
		}
		rdao.update_room_save(rvo);
	}

	@Override
	public void delete_room(String detailAddr_roomNum_room) throws Exception {
		// TODO Auto-generated method stub
		rdao.delete_room(detailAddr_roomNum_room);
	}

}
