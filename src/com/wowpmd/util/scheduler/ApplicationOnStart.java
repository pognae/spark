package com.wowpmd.util.scheduler;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import com.wowpmd.common.service.SystemService;

@Component
public class ApplicationOnStart {

	@Autowired
    private SystemService systemService;

	String var1;
	public void setVar1(String var1) {
		this.var1 = var1;
	}

	//스프링 시스템 기동시 수행
	public void onApplicationEvent(ApplicationEvent applicationevent) {

	}

	@PostConstruct
	public void init() {
		// 시스템 시작시 해야할 것
		this.systemService.reloadCodeHandler();
	}
}
