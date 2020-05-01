package com.mmkjflb.mmloan.viewmodel.splash;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.usercenter.model.entity.CustomerServiceData;
import com.mmkjflb.mmloan.model.entity.ChannelGoogleEntity;
import com.mmkjflb.mmloan.model.entity.UpVersionBean;

import java.util.List;
import java.util.Map;

public interface SplashContract {
	interface View extends BaseView {
		void getAppVersionSuc(BaseBean<UpVersionBean> baseBean);
		void getServiceInfoSuc(BaseBean<List<CustomerServiceData>> data);
		void insertChannelGoogleSuc(BaseBean<ChannelGoogleEntity> baseBean);
	}

	interface Presenter extends BasePresenter<SplashContract.View> {
		void getAppVersion(String platformType,String packageType);
		void getServiceInfo();
		void insertChannelGoogle(Map<String, Object> map);
	}
}
