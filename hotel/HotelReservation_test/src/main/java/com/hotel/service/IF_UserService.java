package com.hotel.service;

import java.util.List;

import com.hotel.VO.userVO;


//유저 관련 서비스
public interface IF_UserService {
	public void join_user_save(userVO uvo) throws Exception;
	public List<userVO> user_selectAll() throws Exception;
	public userVO user_selectid(String id_user) throws Exception;
	public void delete_user(String id_User) throws Exception;
	public void update_user_save(userVO uvo) throws Exception;
}
