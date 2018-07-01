package com.wowpmd.common;

import java.util.Date;

import com.kr.framework.config.Config;



/**
 * 
 * 클래스명: <code>Code</code>
 * <pre>
 *  코드 정보를 저장하는 모델 클래스
 *  TB_CODE
 * </pre>
 *
 * @author 이경연
 * @date 2013.03.25 
 *
 */
@SuppressWarnings("serial")
public class Code extends ToString implements Parameter {

    final String natnCode;
    
    /**
     * 코드 클래스
     */
    private String codeClass;
    
    /**
     * 코드 이름
     */
    private String codeName;
    
    /**
     * 코드 값
     */
    private String codeValue;
    
    /**
     * 중국어 코드값
     */
    private String codeValueCn;
    
    /**
     * 코드순서
     */
    private int codeOrder;
    
    /**
     * 코드설명
     */
    private String codeDescription;
    
    /**
     * 등록일시
     */
    private Date registDateTime;
    
    private String codeSelectValue;
    
    /**
     * 코드 사용 여부
     */
    private boolean codeUseYn;

    public Code() {
        this.natnCode = Config.getSiteNatnCode();
    }

    public Code(boolean codeUseYn) {
        this.natnCode = null;
        this.codeUseYn = codeUseYn;
    }

    public String getCodeClass() {
        return codeClass;
    }

    public void setCodeClass(String codeClass) {
        this.codeClass = codeClass;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    public int getCodeOrder() {
        return codeOrder;
    }

    public void setCodeOrder(int codeOrder) {
        this.codeOrder = codeOrder;
    }

    public String getCodeDescription() {
        return codeDescription;
    }

    public void setCodeDescription(String codeDescription) {
        this.codeDescription = codeDescription;
    }

    public Date getRegistDateTime() {
        return registDateTime;
    }

    public void setRegistDateTime(Date registDateTime) {
        this.registDateTime = registDateTime;
    }

    public boolean isCodeUseYn() {
        return codeUseYn;
    }

    public void setCodeUseYn(boolean codeUseYn) {
        this.codeUseYn = codeUseYn;
    }

    @Override
    public String getKey() {
        return this.getCodeName();
    }

    @Override
    public String getValue() {
        if (this.codeSelectValue == null) {
            throw new RuntimeException(String.format("존재하지않는 국적코드[%s]", this.natnCode));
        } 
        
        return this.codeSelectValue;
    }

    public String getCodeValueCn() {
        return codeValueCn;
    }

    public void setCodeValueCn(String codeValueCn) {
        this.codeValueCn = codeValueCn;
    }

    public String getCodeSelectValue() {
        return codeSelectValue;
    }

    public void setCodeSelectValue(String codeSelectValue) {
        this.codeSelectValue = codeSelectValue;
    }

    public String getNatnCode() {
        return natnCode;
    }
}
