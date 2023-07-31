package com.hotel.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class Room_AttachImpl implements IF_Room_AttachDAO{

	@Inject
	SqlSession sqlSession;
	
	private static String mapperQuery= "com.hotel.dao.IF_Room_AttachDAO";
	
	@Override
	public void insertImg( String detailAddr_roomNum_imgfile,String filename) throws Exception {
		// TODO Auto-generated method stub
		HashMap<String,String> fmap = new HashMap<String,String>();
		fmap.put("detailAddr_roomNum_imgfile",detailAddr_roomNum_imgfile);
		fmap.put("filename",filename);
		sqlSession.insert(mapperQuery+".insertImg",fmap);
		
	}

	@Override
	public List<String> room_selectImg(String detailAddr_roomNum_imgfile) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(mapperQuery+".room_selectImg", detailAddr_roomNum_imgfile);
	}

	@Override
	public void deleteImg(String detailAddr_roomNum_imgfile) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(mapperQuery+".deleteImg", detailAddr_roomNum_imgfile);
	}

}
