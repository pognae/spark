package com.wowpmd.common;

/**
 * 
 * 클래스명: <code>Parameter</code>
 *
 * <pre>
 *  KEY, VALUE 값을 가지고있는 Parameter interface
 * </pre>
 *
 * @author newstar000
 * @date 2011. 11. 9.
 *
 */
public interface Parameter {

    /**
     * <pre>
     * KEY
     * </pre>
     * @return
     */
    String getKey();
    
    /**
     * <pre>
     * VALUE
     * </pre>
     * @return
     */
    String getValue();
}
