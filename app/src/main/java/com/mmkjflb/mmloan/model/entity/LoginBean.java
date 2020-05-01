package com.mmkjflb.mmloan.model.entity;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2017/6/21.
 */

public class LoginBean {

	/**
	 * sessionId : SS-16be634a-91de-4dc0-81c8-e8e60dfe61fd
	 * user : {"auditStep":1,"id":201710311527060006,"idcard":"","mobilePhone":"18321288315","userName":""}
	 */

	private String sessionId;
	private UserBean user;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
