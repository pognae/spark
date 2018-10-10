package com.wowpmd.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;

/**
 *
 * 클래스명: <code>SelectTag</code>
 *
 * <pre>
 *  select tag
 * </pre>
 */
@SuppressWarnings("serial")
public class SelectTag extends CustomTagSupport {

    private static final String SELECT_TEMPLATE_NAME = "selectTag.vm";

    private String defaultValue;

    private String onchange;

    private String disabled;

    private String tabindex;

    private String keyvalue;

    private String required;

    public SelectTag() {
        setTagFileName(SELECT_TEMPLATE_NAME);
    }

    @Override
    public int doAfterBody() throws JspException {
        BodyContent body = super.getBodyContent();
        if (body != null) {
            this.defaultValue = body.getString();
        }

        return SKIP_BODY;
    }

    @Override
    public VelocityContext setMoreContextData(VelocityContext context) {
        context.put("onchange", this.onchange);
        context.put("disabled", this.disabled);
        context.put("tabindex", this.tabindex);
        context.put("required", this.required);
        context.put("defaultValue", this.defaultValue);

        if(!StringUtils.isEmpty(this.keyvalue)){
            Object value = pageContext.getAttribute(this.keyvalue, PageContext.REQUEST_SCOPE);
            if(value != null){
                context.put("value", value.toString());
            }
        }

        return context;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getOnchange() {
        return onchange;
    }

    public void setOnchange(String onchange) {
        this.onchange = onchange;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getTabindex() {
        return tabindex;
    }

    public void setTabindex(String tabindex) {
        this.tabindex = tabindex;
    }

    public String getKeyvalue() {
        return keyvalue;
    }

    public void setKeyvalue(String keyvalue) {
        this.keyvalue = keyvalue;
    }

    public static String getSelectTemplateName() {
        return SELECT_TEMPLATE_NAME;
    }

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}
}
