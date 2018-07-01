/*
 * @(#)PaginationTag.java 1.0 2016/04/12
 *
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.taglib;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;

import com.wowpmd.common.Code;


/**
 * 페이징 태그 클래스이다.
 *
 * @author 이동엽
 * @version 1.0 2016/04/12
 */
public class PaginationTag extends CustomTagSupport {
    /**
     * 시리얼 버전 아이디
     */
    private static final long serialVersionUID = 1L;

    /**
     * 태그 파일 이름
     */
    private static final String TAG_FILE_NAME = "paginationTag.vm";

    /**
     * 디폴트 페이지 번호
     */
    private static final String DEFAULT_PAGE = "1";

    /**
     * 디폴트 페이지 크기
     */
    private static final String DEFAULT_ROWS = "10";

    /**
     * 디폴트 데이터 갯수
     */
    private static final String DEFAULT_COUNT = "0";

    /**
     * 디폴트 페이지 번호
     */
    private static final String DEFAULT_PAGES = "0";

    /**
     * 디폴트 페이지 뷰수
     */
    private static final String DEFAULT_VIEWS = "10";

    /**
     * 페이지 번호
     */
    private String page;

    /**
     * 페이지 크기
     */
    private String rows;

    /**
     * 데이터 갯수
     */
    private String count;

    /**
     * 페이지 갯수
     */
    private String pages;

    /**
     * 페이지 뷰수
     */
    private String views;

    /**
     * 페이지 함수
     */
    private String function;

    /**
     * 디폴트 생성자이다.
     */
    public PaginationTag() {
        setTagFileName(TAG_FILE_NAME);
    }

    /**
     * 페이지 번호를 반환한다.
     *
     * @return 페이지 번호
     */
    public String getPage() {
        return page;
    }

    /**
     * 페이지 번호를 설정한다.
     *
     * @param page 페이지 번호
     */
    public void setPage(String page) {
        this.page = page;
    }

    /**
     * 페이지 크기를 반환한다.
     *
     * @return 페이지 크기
     */
    public String getRows() {
        return rows;
    }

    /**
     * 페이지 크기를 설정한다.
     *
     * @param rows 페이지 크기
     */
    public void setRows(String rows) {
        this.rows = rows;
    }

    /**
     * 데이터 갯수를 반환한다.
     *
     * @return 데이터 갯수
     */
    public String getCount() {
        return count;
    }

    /**
     * 데이터 갯수를 설정한다.
     *
     * @param count 데이터 갯수
     */
    public void setCount(String count) {
        this.count = count;
    }

    /**
     * 페이지 갯수를 반환한다.
     *
     * @return 페이지 갯수
     */
    public String getPages() {
        return pages;
    }

    /**
     * 페이지 갯수를 설정한다.
     *
     * @param pages 페이지 갯수
     */
    public void setPages(String pages) {
        this.pages = pages;
    }

    /**
     * 페이지 뷰수를 반환한다.
     *
     * @return 페이지 뷰수
     */
    public String getViews() {
        return views;
    }

    /**
     * 페이지 뷰수를 설정한다.
     *
     * @param views 페이지 뷰수
     */
    public void setViews(String views) {
        this.views = views;
    }

    /**
     * 페이지 함수를 반환한다.
     *
     * @return 페이지 함수
     */
    public String getFunction() {
        return function;
    }

    /**
     * 페이지 함수를 설정한다.
     *
     * @param function 페이지 함수
     */
    public void setFunction(String function) {
        this.function = function;
    }

    /*
     * (non-Javadoc)
     * @see com.kr.framework.taglib.CustomTagSupport#getOptionData()
     */
    @Override
    public Object getOptionData() {
        return new ArrayList<Code>();
    }

    /*
     * (non-Javadoc)
     * @see com.kr.framework.taglib.CustomTagSupport#setMoreContextData(org.apache.velocity.VelocityContext)
     */
    @Override
    public VelocityContext setMoreContextData(VelocityContext context) {
        int page  = toInteger(this.page, DEFAULT_PAGE);
        int rows  = toInteger(this.rows, DEFAULT_ROWS);
        int count = toInteger(this.count, DEFAULT_COUNT);
        int pages = toInteger(this.pages, DEFAULT_PAGES);
        int views = toInteger(this.views, DEFAULT_VIEWS);

        int firstPage = getFirstPage(page, views);
        int lastPage  = getLastPage(firstPage, pages, views);

        boolean showFirst = getShowFirst(firstPage, pages);
        boolean showPrevious = getShowPrevious(page, pages);
        boolean showNext = getShowNext(page, pages);
        boolean showLast = getShowLast(lastPage, pages);

        context.put("page", page);
        context.put("rows", rows);
        context.put("count", count);
        context.put("pages", pages);
        context.put("views", views);
        context.put("function", function);
        context.put("firstPage", firstPage);
        context.put("lastPage", lastPage);
        context.put("showFirst", showFirst);
        context.put("showPrevious", showPrevious);
        context.put("showNext", showNext);
        context.put("showLast", showLast);

        return context;
    }

    /**
     * 정수로 변환한다.
     *
     * @param value 값
     * @param defaultValue 디폴트 값
     * @return 정수값
     */
    private int toInteger(String value, String defaultValue) {
        if (StringUtils.isEmpty(value)) {
            return Integer.valueOf(defaultValue);
        }

        return Integer.valueOf(value);
    }

    /**
     * 첫번째 페이지 번호를 반환한다.
     *
     * @return 첫번째 페이지 번호
     */
    private int getFirstPage(int page, int views) {
        return ((page - 1) / views) * views + 1;
    }

    /**
     * 마지막 페이지 번호를 반환한다.
     *
     * @param firstPage 첫번째 페이지 번호
     * @return 마지막 페이지 번호
     */
    private int getLastPage(int firstPage, int pages, int views) {
        if (pages > 0) {
            int lastPage = firstPage + views - 1;

            if (lastPage > pages) {
                return pages;
            }
            else {
                return lastPage;
            }
        }
        else {
            return firstPage;
        }
    }

    /**
     * 맨앞 표시 여부를 반환한다.
     *
     * @param firstPage 첫번째 페이지 번호
     * @return 맨앞 표시 여부
     */
    private boolean getShowFirst(int firstPage, int pages) {
        if (pages > 0) {
            if (firstPage > 1) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * 이전 표시 여부를 반환한다.
     *
     * @return 이전 표시 여부
     */
    private boolean getShowPrevious(int page, int pages) {
        if (pages > 0) {
            if (page > 1) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * 다음 표시 여부를 반환한다.
     *
     * @return 다음 표시 여부
     */
    private boolean getShowNext(int page, int pages) {
        if (pages > 0) {
            if (page < pages) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /**
     * 맨뒤 표시 여부를 반환한다.
     *
     * @param lastPage 마지막 페이지 번호
     * @return 맨뒤 표시 여부
     */
    private boolean getShowLast(int lastPage, int pages) {
        if (pages > 0) {
            if (lastPage < pages) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }
}