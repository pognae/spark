package com.wowpmd.common.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.wowpmd.common.Code;
import com.wowpmd.common.dao.ComCodeDAO;
import com.wowpmd.common.dao.SystemDAO;
import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.util.CodeHandler;

/**
 * 클래스명: <code></code>
 *
 * <pre>
 *  설명을 기입하세요
 * </pre>
 *
 * @author smuft76
 * @date 2013. 6. 9.
 *
 */
@Service
public class SystemServiceImpl implements SystemService {

    final Log log = LogFactory.getLog(this.getClass());

    private CodeHandler codeHandler;

    @Inject
    private ComCodeDAO comCodeDAO;

    @Inject
    private SystemDAO systemDAO;

    @Override
    public void reloadCodeHandler() {
        synchronized (this) {
            this.codeHandler = new CodeHandler(this.getCodeListForCodeHandler());
        }
    }

    @Override
    public List<Code> getCodeListForCodeHandler() {
        return this.comCodeDAO.getCodeListForCodeHandler();
    }

	@Override
	public CodeHandler getCodeHandler() {
        if (this.codeHandler == null) {
            synchronized (this) {
                this.codeHandler = new CodeHandler(this.getCodeListForCodeHandler());
            }
        }

        return this.codeHandler;
	}

	@Override
	public List getMenuList(ParamsVO params) {
		return this.systemDAO.getMenuList(params);
	}

}
