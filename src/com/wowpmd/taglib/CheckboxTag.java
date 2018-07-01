package com.wowpmd.taglib;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;

/**
 * 
 * 클래스명: <code>CheckboxTag</code>
 *
 * <pre>
 *  CustomTagSupport의 기능을 상속받는다.
 *   
 *  ex) &lt;ui:checkbox name="check2" key="orderStatCode" value="10, 30" clazz="check_class" except="100"/&gt;
 * </pre>
 *
 */
@SuppressWarnings("serial")
public class CheckboxTag extends CustomTagSupport {

    private static final String CHECKBOX_TEMPLATE_NAME = "checkboxTag.vm";
    
    public CheckboxTag() {
        setTagFileName(CHECKBOX_TEMPLATE_NAME);
    }
    
    @Override
    public VelocityContext setMoreContextData(VelocityContext context) {
        String value = super.getValue();
        
        if (!StringUtils.isEmpty(value)) {
            String[] values = value.split(",");
            Map<String, String> valueMap = new HashMap<String, String>();
            for (String key : values) {
                valueMap.put(key.trim(), "");
            }

            context.put("value", valueMap);
        }

        return context;
    }
}
