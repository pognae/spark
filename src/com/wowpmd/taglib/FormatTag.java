/*
 * @(#)FormatTag.java 1.0 2016/04/12
 *
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 포맷 태그 클래스이다.
 *
 * @author 이동엽
 * @version 1.0 2016/04/12
 */
public class FormatTag extends SimpleTagSupport {
    /**
     * 값
     */
    private String value;

    /**
     * 패턴
     */
    private String pattern;

    /**
     * 구분자
     */
    private String delimiter;

    /**
     * 값을 반환한다.
     *
     * @return 값
     */
    public String getValue() {
        return value;
    }

    /**
     * 값을 설정한다.
     *
     * @param value 값
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 패턴을 반환한다.
     *
     * @return 패턴
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * 패턴을 설정한다.
     *
     * @param pattern 패턴
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    /**
     * 구분자를 반환한다.
     *
     * @return 구분자
     */
    public String getDelimiter() {
        return delimiter;
    }

    /**
     * 구분자를 설정한다.
     *
     * @param delimiter 구분자
     */
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * 태그를 실행한다.
     *
     * @throws JspException 발생오류
     * @throws IOException 발생오류
     */
    public void doTag() throws JspException, IOException {
        String v = value     != null ? value     : "";
        String p = pattern   != null ? pattern   : "";
        String d = delimiter != null ? delimiter : "-";

        String s = "";

        if (v.length() == p.replaceAll(d, "").length()) {
            String[] a = p.split(d);

            int n = 0;

            for (int i = 0; i < a.length; i++) {
                if (i > 0) {
                    s += d;
                }

                int l = a[i].length();

                s += v.substring(n, n + l);

                n = n + l;
            }
        } else {
            s = v;
        }

        getJspContext().getOut().print(s);
    }
}