package com.wowpmd.common.service;

import com.wowpmd.common.model.LoginUser;
import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.vo.ResultVO;

public interface UserService {
	
	int userIdInsert(LoginUser user);
	
	int userIdUpdate(LoginUser user);
	
	int lstCttDtUpdate(LoginUser user);
	
	LoginUser findByUserIdAndPassword(LoginUser user);

	LoginUser lstCttDtSearch(LoginUser user);
	
	public ResultVO comp0001Update(ParamsVO params);
}
