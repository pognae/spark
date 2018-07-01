package com.wowpmd.common.dao;

import org.springframework.stereotype.Repository;

import com.wowpmd.common.model.LoginUser;
import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.dao.BaseDAO;

@Repository
public  class UserDAOImpl extends BaseDAO implements UserDAO {

	@Override
	public String getUserPwd(String userid) {
		return (String) select("main.getUserPwd",userid);
	}

	@Override
	public int userIdInsert(LoginUser user) {
		int ret = sqlSession.insert("comCode.userIdInsert", user);
    	return ret;
	}

	@Override
	public int userIdUpdate(LoginUser user) {
		int ret = sqlSession.update("comCode.userIdUpdate", user);
    	return ret;
	}

	@Override
	public int lstCttDtUpdate(LoginUser user) {
		int ret = sqlSession.update("comCode.lstCttDtUpdate", user);
    	return ret;
	}

	@Override
	public LoginUser findByUserIdAndPassword(LoginUser user) {
		LoginUser ret = (LoginUser) select("comCode.findByUserIdAndPassword", user);
    	return ret;
	}

	@Override
	public LoginUser lstCttDtSearch(LoginUser user) {
		LoginUser ret = (LoginUser) select("comCode.lstCttDtSearch", user);
    	return ret;
	}

	public int comp0001Update(ParamsVO params) {
        return update("comCode.comp0001Update", params);
    }
}


