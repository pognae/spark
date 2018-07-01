package com.wowpmd.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wowpmd.common.dao.MainDao;
import com.wowpmd.service.BaseService;

@Service
public class MainServiceImpl extends BaseService implements MainService {
	@Autowired
	private MainDao mainDao;
}
