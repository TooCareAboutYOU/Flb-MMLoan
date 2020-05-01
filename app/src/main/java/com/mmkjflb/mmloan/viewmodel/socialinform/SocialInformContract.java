package com.mmkjflb.mmloan.viewmodel.socialinform;

import com.mmkjflb.mmloan.model.entity.SocialInfoEntity;
import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;

import java.util.Map;

public interface SocialInformContract {
	interface View extends BaseView {
		void socialinfoSuc(BaseBean baseBean);

		void getSocialInfoSuc(BaseBean<SocialInfoEntity> baseBean);
	}

	interface Presenter extends BasePresenter<SocialInformContract.View> {
		void socialinfo(Map<String, Object> map);

		void getSocialInfo(String sessionId);
	}
}
