package com.wowpmd.taglib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wowpmd.common.Code;
import com.wowpmd.common.Parameter;
import com.wowpmd.common.ParameterImpl;
import com.wowpmd.common.service.SystemService;
import com.wowpmd.util.Utils;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 *
 * 클래스명: <code>CustomTagSupport</code>
 *
 * <pre>
 *  설명을 기입하세요
 *
 *  공통 커스텀태그
 *  체크박스/select박스/라디오박스
 *  에 해당하는 커스텀태그
 *
 *  기본으로는 사용자가 입력한 input에서 찾는다
 *  없으면 코드에서 뒤진다.
 * </pre>
 *
 *
 */
@SuppressWarnings("serial")
public class CustomTagSupport extends BodyTagSupport {

    /**
     * 벨로시티 init 여부
     */
    private static boolean velocityInitYn;

    /**
     * 템플릿파일명
     */
    private String tagFileName;

    /**
     * 파라매터명
     */
    private String name;

    /**
     * id
     */
    private String iD;

    /**
     * key(리퀘스트 변수명)
     */
    private String key;

    /**
     * 선택되어지는 값
     */
    private String value;

    /**
     * 클래스
     */
    private String clazz;

    /**
     * 줄바뀌는 라인카운트(몇개가 나오고 줄이바뀌는지)
     */
    private int line = 0;

    /**
     * disabled
     */
    private String disabled;

    /**
     * 키이름 (,로 구분)
     */
    private String keyName;

    /**
     * style(하드코딩시에)
     */
    private String style;

    /**
     * 제외값
     */
    private String except;

    /**
     * default값
     */
    private String defaultValue;

    /**
     * label 값
     */
    private String lavel;

    /**
     * label에 다른 클래스를 적용할 값
     */
    private String point;

    /**
     * label에 다른 클래스를 적용할 클래스명
     */
    private String pointClass;

    @Override
    public int doEndTag() throws JspException {

        if(!velocityInitYn){
            try {
                velocityInit();
                velocityInitYn = true;
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        Object optionData = getOptionData();
        Template template = null;
        VelocityContext context = null;

        try {
            if (optionData == null) {
                return SKIP_BODY;
            } else if (optionData instanceof List<?>) {

            	@SuppressWarnings("unchecked")
                List<Parameter> options = (List<Parameter>) optionData;
                template = Velocity.getTemplate(this.tagFileName);
                context = new VelocityContext();

                if(options.size() > 0){
                    context.put("options", options);
                }
                context.put("name", this.name);
                context.put("value", this.value);
                context.put("clazz", this.clazz);
                context.put("line", this.line);
                context.put("disabled", this.disabled);
                context.put("id", this.iD);
                context.put("style", this.style);
                context.put("defaultValue", this.defaultValue);
                context.put("lavel", this.lavel);
                context.put("pointClass", this.pointClass);
                setExcept(context);
                setPoint(context);
                context = setMoreContextData(context);

                template.merge(context, pageContext.getOut());

            } else {
                return SKIP_BODY;
            }

        } catch (Exception e) {
            throw new JspException();
        }

        return SKIP_BODY;
    }

    /**
     * 벨로시티 init
     * @brief
     * @details
     * @throws Exception
     */
    private void velocityInit() throws Exception {

        velocityInitYn = true;

        Properties properties = new Properties();

        properties.setProperty("resource.loader", "file");
        properties.setProperty("file.resource.loader.description", "Velocity File Resource Loader");
        properties.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
        properties.setProperty("file.resource.loader.path", "/WEB-INF/template");
        properties.setProperty("file.resource.loader.cache", "true");
        properties.setProperty("file.resource.loader.modificationCheckInterval", "1");
        properties.setProperty("output.encoding", "UTF-8");
        properties.setProperty("input.encoding", "UTF-8");
        properties.setProperty("default.contentType", "UTF-8");
        properties.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogChute");

        ServletRequestAttributes ra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        ServletContext sc = ra.getRequest().getSession().getServletContext();

        String loaderPath = properties.getProperty("file.resource.loader.path");
        String realPath = sc.getRealPath(loaderPath);

        properties.setProperty("file.resource.loader.path", realPath);

        Velocity.init(properties);
    }

    public Object getUserInput() {
        return pageContext.getAttribute(this.key, PageContext.REQUEST_SCOPE);
    }

    /**
     * <option> 데이터 생성
     * @brief
     * @details
     * @return
     */
    public Object getOptionData() {
        Object optionData = getUserInput();
        if (optionData == null) {
            optionData = getCode();
        }

        return optionData;
    }

    public Object getCode() {
        ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(super.pageContext.getServletContext());
        SystemService systemService = applicationContext.getBean(SystemService.class);
        List<Code> codes = systemService.getCodeHandler().getCodes(Utils.javaVariableNamingToDbNaming(this.key));
        if (codes != null) {
            List<Parameter> result = new ArrayList<Parameter>(codes.size());
//            if (CodeName.SITE_NATN_CODE_CN.equals(this.langCode)) {
                for (Code code: codes) {
                    result.add(new ParameterImpl(code.getCodeName(), code.getCodeValueCn()));
                }
                return (Object)result;
//            } else {
//                return (Object)codes;
//            }
        } else{
            return null;
        }
    }

    public VelocityContext setMoreContextData(VelocityContext context) {
        return context;
    }

    /**
     * 제외시킬 목록
     * @brief
     * @details
     * @param context
     */
    public void setExcept(VelocityContext context) {
        if(!StringUtils.isEmpty(except)) {

            String[] excepts = except.split(",");
            Map<String, String> exceptMap = new HashMap<String, String>();
            for (String key : excepts) {
                exceptMap.put(key.trim(), "except");
            }

            context.put("except", exceptMap);
        }
    }

    /**
     * @brief 다른 클래스명을 적용할 값들
     * @details
     * @param
     * @return
     * @throws
     */
    public void setPoint(VelocityContext context) {

        if(!StringUtils.isEmpty(point)) {
            String[] points = point.split(",");
            Map<String, String> pointMap = new HashMap<String, String>();
            for (String key : points) {
                pointMap.put(key.trim(), "point");
            }

            context.put("point", pointMap);
        }
    }


    public String getTagFileName() {
        return tagFileName;
    }

    public void setTagFileName(String tagFileName) {
        this.tagFileName = tagFileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getExcept() {
        return except;
    }

    public void setExcept(String except) {
        this.except = except;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getLavel() {
        return lavel;
    }

    public void setLavel(String lavel) {
        this.lavel = lavel;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getPointClass() {
        return pointClass;
    }

    public void setPointClass(String pointClass) {
        this.pointClass = pointClass;
    }

}
