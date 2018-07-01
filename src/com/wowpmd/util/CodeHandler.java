package com.wowpmd.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wowpmd.common.Code;


/**
 * 클래스명: <code>CodeHandler</code>
 *
 * <pre>
 *  코드목록을 메모리에 관리하는 클래스
 * </pre>
 *
 * @author newstar000
 * @date 2012. 5. 18.
 *
 */
public class CodeHandler {

    private Log log = LogFactory.getLog(this.getClass());
    
    private List<Code> codeList;
    
    private Map<String,List<Code>> codeMap = new HashMap<String,List<Code>>();
    
    public CodeHandler() {
        
    }
    
    public CodeHandler(List<Code> codeList) {
        this.codeList = codeList;
        init();
    }
    
    public void init() {
        if(log.isDebugEnabled()){
            log.debug("[CodeHandler] initialize Code Data");
        }
        
        this.setCodeMap();
    }
    
    /**
     * set codeMap(key : code_class , value : List<CODE>)
     */
    private void setCodeMap() {
        String tempCodeClass = "";
        for (Code tempCode : codeList) {
            if(StringUtils.isEmpty(tempCodeClass)) {
                tempCodeClass = tempCode.getCodeClass();
                List<Code> tempCodeList = new ArrayList<Code>();
                tempCodeList.add(tempCode);
                codeMap.put(tempCode.getCodeClass(), tempCodeList);
            } else {
                if(StringUtils.equals(tempCodeClass, tempCode.getCodeClass())) {
                    List<Code> tempCodeList = codeMap.get(tempCode.getCodeClass());
                    tempCodeList.add(tempCode);
                } else {
                    tempCodeClass = tempCode.getCodeClass();
                    List<Code> tempCodeList = new ArrayList<Code>();
                    tempCodeList.add(tempCode);
                    codeMap.put(tempCode.getCodeClass(), tempCodeList);
                }
            }
        }
    }
    
    public List<Code> getCodes(String codeClass) {
        return this.codeMap.get(codeClass);
    }
    
    public Code getCode(String codeClass, String codeName) {
        if(this.codeMap.get(codeClass) != null) {
            List<Code> tempCodeList = this.codeMap.get(codeClass);
            for(Code tempCode : tempCodeList) {
                if(StringUtils.equals(tempCode.getCodeName(), codeName)) {
                    return tempCode;
                }
            }
        }
        
        return null;
    }

    public void reload() {
        
    }
}
