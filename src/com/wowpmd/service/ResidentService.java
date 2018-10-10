package com.wowpmd.service;

import java.util.List;

import com.wowpmd.common.model.ParamsVO;
import com.wowpmd.vo.ResultVO;

/**
 * BasicService
 *
 * @version 1.0 2016/04/29
 */

public interface ResidentService {

	public List<Object> rsd1010Search(ParamsVO params);

	public ResultVO insertResident(ParamsVO params);
}