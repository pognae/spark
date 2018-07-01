/*
 * @(#)RecordVO.java 1.0 2016/04/12
 * 
 * Copyright (c) 2016 WOWPMD Co., Inc. All rights reserved.
 */
package com.wowpmd.common.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import com.kr.framework.util.CamelUtil;
import com.wowpmd.exception.SystemException;
import com.wowpmd.vo.BaseVO;

import oracle.sql.BLOB;
import oracle.sql.CLOB;

/**
 * 레코드 VO 클래스이다.
 * 
 * @author 이동엽
 * @version 1.0 2016/04/12
 */
public class RecordVO extends BaseVO {
    /**
     * 시리얼 버전 아이디
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 디폴트 생성자이다.
     */
    public RecordVO() {
        super();
    }
    
    /* 
     * (non-Javadoc)
     * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
     */
    public Object put(Object key, Object value) {
        if (value instanceof byte[]) {
            return super.put(CamelUtil.convert2CamelCase((String) key), handleByteArray((byte[]) value));
        }
        if (value instanceof char[]) {
            return super.put(CamelUtil.convert2CamelCase((String) key), handleCharArray((char[]) value));
        }
        if (value instanceof BLOB) {
            return super.put(CamelUtil.convert2CamelCase((String) key), handleBlob((BLOB) value));
        }
        if (value instanceof CLOB) {
            return super.put(CamelUtil.convert2CamelCase((String) key), handleClob((CLOB) value));
        }
        
        return super.put(CamelUtil.convert2CamelCase((String) key), value);
    }
    
    /**
     * 바이트 배열을 처리한다.
     * 
     * @param byteArray 바이트 배열
     * @return 문자열
     */
    private String handleByteArray(byte[] byteArray) {
        try {
            return new String(byteArray, "UTF-8");
        }
        catch (UnsupportedEncodingException uee) {
            error("Detected exception: ", uee);
            
            throw new SystemException(uee.getMessage());
        }
    }
    
    /**
     * 캐릭터 배열을 처리한다.
     * 
     * @param charArray 바이트 배열
     * @return 문자열
     */
    private String handleCharArray(char[] charArray) {
        return new String(charArray);
    }
    
    /**
     * BLOB을 처리한다.
     * 
     * @param blob BLOB
     * @return 문자열
     */
    private String handleBlob(BLOB blob) {
        BufferedReader br = null;
        
        try {
            br = new BufferedReader(new InputStreamReader(blob.getBinaryStream(), "UTF-8"));
            
            StringBuffer sb = new StringBuffer();
            
            while (true) {
                String line = br.readLine();
                
                if (line == null) {
                    break;
                }
                
                if (sb.length() > 0) {
                    sb.append("\n");
                }
                
                sb.append(line);
            }
            
            return sb.toString();
        }
        catch (SQLException sqle) {
            error("Detected exception: ", sqle);
            
            throw new SystemException(sqle.getMessage());
        }
        catch (UnsupportedEncodingException uee) {
            error("Detected exception: ", uee);
            
            throw new SystemException(uee.getMessage());
        }
        catch (IOException ioe) {
            error("Detected exception: ", ioe);
            
            throw new SystemException(ioe.getMessage());
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                }
                catch (IOException ioe) {
                    warn("Detected exception: ", ioe);
                }
            }
            if (blob != null) {
                try {
                    blob.close();
                }
                catch (SQLException sqle) {
                    warn("Detected exception: ", sqle);
                }
            }
        }
    }
    
    /**
     * CLOB을 처리한다.
     * 
     * @param clob CLOB
     * @return 문자열
     */
    private String handleClob(CLOB clob) {
        BufferedReader br = null;
        
        try {
            br = new BufferedReader(clob.getCharacterStream());
            
            StringBuffer sb = new StringBuffer();
            
            while (true) {
                String line = br.readLine();
                
                if (line == null) {
                    break;
                }
                
                if (sb.length() > 0) {
                    sb.append("\n");
                }
                
                sb.append(line);
            }
            
            return sb.toString();
        }
        catch (SQLException sqle) {
            error("Detected exception: ", sqle);
            
            throw new SystemException(sqle.getMessage());
        }
        catch (IOException ioe) {
            error("Detected exception: ", ioe);
            
            throw new SystemException(ioe.getMessage());
        }
        finally {
            if (br != null) {
                try {
                    br.close();
                }
                catch (IOException ioe) {
                    warn("Detected exception: ", ioe);
                }
            }
            if (clob != null) {
                try {
                    clob.close();
                }
                catch (SQLException sqle) {
                    warn("Detected exception: ", sqle);
                }
            }
        }
    }
}