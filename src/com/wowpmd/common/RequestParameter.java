package com.wowpmd.common;

/**
 * 클래스명: <code>Parameter</code>
 * 
 * <pre>
 * http 리퀘스트에 사용하는
 * 파라매터 클래스
 * 별거없음
 * </pre>
 *
 * @date 2008. 11. 19
 * @author 이경연
 *
 */
public class RequestParameter {

    /**
     * 파라매터명
     */
    private String name;

    /**
     * 파라매터값
     */
    private String value;

    public RequestParameter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * <pre>
     * 클래스정보를
     * name=[value] 형식의 문자열로 리턴한다.
     *
     * </pre>
     *
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder(name.length() + value.length() + 1);
        buffer.append(name).append("=").append(value);
        return buffer.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
