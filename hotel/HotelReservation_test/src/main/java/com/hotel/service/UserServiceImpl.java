package com.hotel.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hotel.VO.userVO;
import com.hotel.dao.IF_UserDAO;

@Service
public class UserServiceImpl implements IF_UserService{
	@Inject 
	private IF_UserDAO udao;

	@Override
	public void join_user_save(userVO uvo) throws Exception {
		// TODO Auto-generated method stub
		udao.join_user_save(uvo);
	}

	@Override
	public List<userVO> user_selectAll() throws Exception {
		// TODO Auto-generated method stub
		return udao.user_selectAll();
	}

	@Override
	public userVO user_selectid(String id_user) throws Exception {
		// TODO Auto-generated method stub
		return udao.user_selectid(id_user);
	}

	@Override
	public void delete_user(String id_User) throws Exception {
		// TODO Auto-generated method stub
		udao.delete_user(id_User);
	}

	@Override
	public void update_user_save(userVO uvo) throws Exception {
		// TODO Auto-generated method stub
		udao.update_user_save(uvo);
	}
}
