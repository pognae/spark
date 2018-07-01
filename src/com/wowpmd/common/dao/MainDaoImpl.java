package com.wowpmd.common.dao;

import org.springframework.stereotype.Repository;

import com.wowpmd.dao.BaseDAO;

@Repository
public  class MainDaoImpl extends BaseDAO implements MainDao {

	@Override
	public String getUserPwd(String userid) {
		return (String) select("main.getUserPwd",userid);
	}

}


