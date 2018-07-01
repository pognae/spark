package com.wowpmd.common.dao;

import com.wowpmd.common.model.LoginUser;
import com.wowpmd.common.model.ParamsVO;

public interface UserDAO  {

	public String getUserPwd(String userid);
	
	int userIdInsert(LoginUser user);
	
	int userIdUpdate(LoginUser user);
	
	int lstCttDtUpdate(LoginUser user);

	LoginUser findByUserIdAndPassword(LoginUser user);
	
	LoginUser lstCttDtSearch(LoginUser user);
	
	public int comp0001Update(ParamsVO params);
}
