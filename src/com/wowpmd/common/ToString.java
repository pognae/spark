package com.wowpmd.common;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * 클래스명: <code>ToString</code>
 * <pre>
 *  로그용 ToString클래스
 *  로그가 이쁘게 찍히고 싶다면 상속받아쓴다.
 * </pre>
 *
 * @date 2010. 6. 10.
 * @author 이경연
 *
 */
@SuppressWarnings("serial")
public class ToString implements Serializable {
    public static String toString(Object o) {
        if (o == null) {
            return "null";
        } else {
            return ToStringBuilder.reflectionToString(o, ToStringStyle.MULTI_LINE_STYLE);
        }
    }
    
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}