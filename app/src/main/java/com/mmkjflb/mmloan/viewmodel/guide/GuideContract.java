package com.mmkjflb.mmloan.viewmodel.guide;

import com.mmkjflb.mmloan.model.entity.AdviseEntity;
import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;

import java.util.Map;

public interface GuideContract {
	interface View extends BaseView {
		void getGoogleAdviseSuc(AdviseEntity baseBean);
	}

	interface Presenter extends BasePresenter<View> {
		void getGoogleAdvise(Map<String, Object> options);
	}
}
