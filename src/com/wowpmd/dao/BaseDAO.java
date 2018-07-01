/*
 * @(#)BaseDAO.java 1.0 2016/04/12
 * 
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.component.BaseComponent;
import com.wowpmd.vo.PagingVO;

/**
 * 기본 DAO 클래스이다.
 * 
 * @author 이동엽
 * @version 1.0 2016/04/12
 */
public class BaseDAO extends BaseComponent {
    /**
     * 상단 데이터 접미어
     */
    public static final String SUFFIX_UPPER = "Upper";
    
    /**
     * 검색 카운트 접미어
     */
    public static final String SUFFIX_COUNT = "Count";
    
    /**
     * 전체 카운트 접미어
     */
    public static final String SUFFIX_TOTAL = "Total";
    
    /**
     * 매뉴얼 페이징 모드
     */
    public static final int PAGING_MANUAL = 0;
    
    /**
     * 스크롤 페이징 모드
     */
    public static final int PAGING_SCROLL = 1;
    
    /**
     * SQL 세션
     */
    @Autowired
    protected SqlSession sqlSession;
    
    /**
     * 디폴트 생성자이다.
     */
    protected BaseDAO() {
        super();
    }
    
    /**
     * 데이터를 검색한다.
     * 
     * @param id 아이디
     * @return 검색결과
     */
    public List<Object> search(String id) {
        return sqlSession.selectList(id);
    }
    
    /**
     * 데이터를 검색한다.
     * 
     * @param id 아이디
     * @param params 파라메터
     * @return 검색결과
     */
    public List<Object> search(String id, Object params) {
        return sqlSession.selectList(id, params);
    }
    
    /**
     * 데이터를 검색한다.
     * 
     * @param id 아이디
     * @param page 페이지 번호
     * @param rows 페이지 크기
     * @return 검색결과
     */
    public PagingVO search(String id, int page, int rows) {
        return search(id, null, page, rows, PAGING_MANUAL, false, false);
    }
    
    /**
     * 데이터를 검색한다.
     * 
     * @param id 아이디
     * @param page 페이지 번호
     * @param rows 페이지 크기
     * @param mode 페이징 방식
     * @return 검색결과
     */
    public PagingVO search(String id, int page, int rows, int mode) {
        return search(id, null, page, rows, mode, false, false);
    }
    
    /**
     * 데이터를 검색한다.
     * 
     * @param id 아이디
     * @param params 파라메터
     * @param page 페이지 번호
     * @param rows 페이지 크기
     * @return 검색결과
     */
    public PagingVO search(String id, Object params, int page, int rows) {
        return search(id, params, page, rows, PAGING_MANUAL, false, false);
    }
    
    /**
     * 데이터를 검색한다.
     * 
     * @param id 아이디
     * @param params 파라메터
     * @param page 페이지 번호
     * @param rows 페이지 크기
     * @param mode 페이징 방식
     * @return 검색결과
     */
    public PagingVO search(String id, Object params, int page, int rows, int mode) {
        return search(id, params, page, rows, mode, false, false);
    }
    
    /**
     * 데이터를 검색한다.
     * 
     * @param id 아이디
     * @param params 파라메터
     * @param page 페이지 번호
     * @param rows 페이지 크기
     * @param mode 페이징 방식
     * @param announce 상단 데이터
     * @param totality 전체 카운트
     * @return 검색결과
     */
    public PagingVO search(String id, Object params, int page, int rows, int mode, boolean announce, boolean totality) {
        List<Object> data = new ArrayList<Object>();
        
        if (announce) {
            // 상단 데이터를 검색한다.
            data.addAll(sqlSession.selectList(id + SUFFIX_UPPER, params));
        }
        
        int count = 0;
        int total = 0;
        
        if (totality) {
            // 전체 카운트를 조회한다.
            total = ((Integer) sqlSession.selectOne(id + SUFFIX_TOTAL, params)).intValue();
            
            if (total > 0) {
                // 검색 카운트를 조회한다.
                count = ((Integer) sqlSession.selectOne(id + SUFFIX_COUNT, params)).intValue();
            }
        }
        else {
            // 검색 카운트를 조회한다.
            total = count = ((Integer) sqlSession.selectOne(id + SUFFIX_COUNT, params)).intValue();
        }
        
        int pages = 0;
        int index = 0;
        
        if (count > 0) {
            pages = (count / rows) + (count % rows > 0 ? 1 : 0);
            
            index = (page > pages ? pages : page) - 1;
            
            // 스크롤 페이징 모드인 경우
            if (PAGING_SCROLL == mode) {
                // 데이터를 검색한다.
                data.addAll(sqlSession.selectList(id, params, new RowBounds(index * rows, rows)));
            }
            // 매뉴얼 페이징 모드인 경우
            else {
                Object parameters = params != null ? params : new ParamsVO();
                
                if (parameters instanceof ParamsVO) {
                    ((ParamsVO) parameters).put(ParamsVO.PAGING, PAGING_MANUAL);
                    ((ParamsVO) parameters).put(ParamsVO.START,  (index * rows) + 1);
                    ((ParamsVO) parameters).put(ParamsVO.END,    (index + 1) * rows);
                }
                
                // 데이터를 검색한다.
                data.addAll(sqlSession.selectList(id, parameters));
            }
        }
        
        PagingVO paging = new PagingVO();
        
        paging.put(PagingVO.PAGE,  index + 1);
        paging.put(PagingVO.ROWS,  rows);
        paging.put(PagingVO.DATA,  data);
        paging.put(PagingVO.COUNT, count);
        paging.put(PagingVO.TOTAL, total);
        paging.put(PagingVO.PAGES, pages);
        
        return paging;
    }
    
    /**
     * 데이터를 등록한다.
     * 
     * @param id 아이디
     * @return 등록결과
     */
    public int insert(String id) {
        return sqlSession.insert(id);
    }
    
    /**
     * 데이터를 등록한다.
     * 
     * @param id 아이디
     * @param params 파라메터
     * @return 등록결과
     */
    public int insert(String id, Object params) {
        return sqlSession.insert(id, params);
    }
    
    /**
     * 데이터를 조회한다.
     * 
     * @param id 아이디
     * @return 조회결과
     */
    public Object select(String id) {
        return sqlSession.selectOne(id);
    }
    
    /**
     * 데이터를 조회한다.
     * 
     * @param id 아이디
     * @param params 파라메터
     * @return 조회결과
     */
    public Object select(String id, Object params) {
        return sqlSession.selectOne(id, params);
    }
    
    /**
     * 데이터를 수정한다.
     * 
     * @param id 아이디
     * @return 수정결과
     */
    public int update(String id) {
        return sqlSession.update(id);
    }
    
    /**
     * 데이터를 수정한다.
     * 
     * @param id 아이디
     * @param params 파라메터
     * @return 수정결과
     */
    public int update(String id, Object params) {
        return sqlSession.update(id, params);
    }
    
    /**
     * 데이터를 삭제한다.
     * 
     * @param id 아이디
     * @return 삭제결과
     */
    public int delete(String id) {
        return sqlSession.delete(id);
    }
    
    /**
     * 데이터를 삭제한다.
     * 
     * @param id 아이디
     * @param params 파라메터
     * @return 삭제결과
     */
    public int delete(String id, Object params) {
        return sqlSession.delete(id, params);
    }
}