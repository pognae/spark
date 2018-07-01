package com.wowpmd.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wowpmd.common.dao.UserDAO;
import com.wowpmd.common.model.LoginUser;
import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.service.BaseService;
import com.wowpmd.vo.ResultVO;

@Service
public class UserServiceImpl extends BaseService implements UserService {
	@Autowired
	private UserDAO userDao;

	@Override
	public int userIdInsert(LoginUser user) {
		return userDao.userIdInsert(user);
	}
	
	@Override
	public int userIdUpdate(LoginUser user) {
		return userDao.userIdUpdate(user);
	}
	
	@Override
	public int lstCttDtUpdate(LoginUser user) {
		return userDao.lstCttDtUpdate(user);
	}
	
	@Override
	public LoginUser findByUserIdAndPassword(LoginUser user) {
		return userDao.findByUserIdAndPassword(user);
	}
	
	@Override
	public LoginUser lstCttDtSearch(LoginUser user) {
		return userDao.lstCttDtSearch(user);
	}
	
	@Transactional
    public ResultVO comp0001Update(ParamsVO params) {
		
		userDao.comp0001Update(params);
		
		return success("비밀번호가 변경되었습니다. 다시 로그인하여 주시기 바랍니다.");
	}
}
