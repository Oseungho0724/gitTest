package com.hotel.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.hotel.VO.userVO;

@Repository
public class UserImpl implements IF_UserDAO{

	@Inject
	SqlSession sqlSession;
	
	private static String mapperQuery= "com.hotel.dao.IF_UserDAO";

	@Override
	public void join_user_save(userVO uvo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.insert(mapperQuery+".join_user_save",uvo);
	}

	@Override
	public List<userVO> user_selectAll() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(mapperQuery+".user_selectAll");
	}

	@Override
	public userVO user_selectid(String id_user) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(mapperQuery+".user_selectId", id_user);
	}

	@Override
	public void delete_user(String id_User) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.delete(mapperQuery+".delete_user", id_User);
	}

	@Override
	public void update_user_save(userVO uvo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update(mapperQuery+".update_user_save", uvo);
	}
}
