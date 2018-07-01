package com.wowpmd.common;

/**
 * 
 * 클래스명: <code>ParameterImpl</code>
 *
 * <pre>
 *  KEY, VALUE 값을 가지고있는 Parameter 구현체
 * </pre>
 *
 * @author newstar000
 * @date 2011. 11. 9.
 *
 */
@SuppressWarnings("serial")
public class ParameterImpl extends ToString implements Parameter {

    /**
     * KEY
     */
    private String key;
    
    /**
     * VALUE
     */
    private String value;
    
    public ParameterImpl() {
        
    }
    
    
    /**
     * key, value 기본값 세팅
     * 
     * @param key
     * @param value
     */
    public ParameterImpl(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
