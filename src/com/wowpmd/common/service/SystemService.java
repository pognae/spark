package com.wowpmd.common.service;

import java.util.List;

import com.wowpmd.common.Code;
import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.util.CodeHandler;


public interface SystemService {

    CodeHandler getCodeHandler();

    void reloadCodeHandler();

    public List<Code> getCodeListForCodeHandler();

    @SuppressWarnings("rawtypes")
	public List getMenuList(ParamsVO params);
}
