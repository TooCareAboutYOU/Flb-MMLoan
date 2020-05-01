package com.mmkj.usercenter.viewmodel.configip;

/**
 * Author: wendjia
 * Time: 2018\9\12 0012
 * Description:
 **/
public class IPEntity {
	private String name;
	private String ip;

	public IPEntity(String name, String ip) {
		this.name = name;
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
