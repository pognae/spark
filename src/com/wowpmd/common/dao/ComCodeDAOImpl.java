/*
 * @(#)ComCodeDAOImpl.java 1.0 2016/04/12
 *
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wowpmd.common.Code;
import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.dao.BaseDAO;

/**
 * 공통코드를 검색하는 DAO 클래스이다.
 *
 * @author 정민승
 * @version 1.0 2016/04/12
 */
@Repository
public  class ComCodeDAOImpl extends BaseDAO implements ComCodeDAO {
    /**
     * 공통코드를 검색한다.
     *
     * @param params 파라메터
     * @return 검색결과
     */
    public List<Object> getComCode(ParamsVO params) {
        // 공통코드를 검색한다.
        return search("comCode.getComCode", params);
    }

    public List<Object> getComCodeList(Map<String, Object> paramMap) {
        return search("comCode.getComCodeList", paramMap);
    }

    public List<Object> getTopMeneList(){
    	return search("comCode.getTopMenuList");
    }

    public List<Object> getTopMeneList1(ParamsVO params) {
        return search("comCode.getTopMenuList1", params);
    }

    public int logMenuInsert(ParamsVO params){
    	return insert("comCode.logHstr", params);
    }

	@Override
	public List<Code> getCodeListForCodeHandler() {
		return sqlSession.selectList("comCode.getCodeListForCodeHandler");
	}

}