package com.mmkj.baselibrary.model.http;

import com.mmkj.baselibrary.model.http.api.BaseApiService;

import javax.inject.Inject;


public class RetrofitHelper implements HttpHelper {

	private BaseApiService mApiHelper;


	@Inject
	public RetrofitHelper(BaseApiService apiHelper) {
		this.mApiHelper = apiHelper;
	}


}
