package com.wowpmd.taglib;

/**
 * 
 * 클래스명: <code>RadioTag</code>
 *
 * <pre>
 *  radio tag
 * </pre>
 *
 * @author newstar000
 * @date 2011. 11. 9.
 *
 */
@SuppressWarnings("serial")
public class RadioTag extends CustomTagSupport {

    private static final String RADIO_TEMPLATE_NAME = "radioTag.vm";
    
    public RadioTag() {
        setTagFileName(RADIO_TEMPLATE_NAME);
    }
    
}
