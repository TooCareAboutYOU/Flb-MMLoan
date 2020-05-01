package com.mmkj.usercenter.di.component;

import android.app.Activity;

import com.mmkj.baselibrary.di.scope.ActivityScope;
import com.mmkj.usercenter.di.module.UserCenterActivityMoule;
import com.mmkj.usercenter.viewmodel.creditguarantee.CreditGuaranteeActivity;
import com.mmkj.usercenter.viewmodel.loanlist.LoanListActivity;
import com.mmkj.usercenter.viewmodel.repaydetail.RepayDetailActivity;
import com.mmkj.usercenter.viewmodel.repaymentchannel.RepaymentChannelActivity;
import com.mmkj.usercenter.viewmodel.reypaylist.RepayListActivity;
import com.mmkj.usercenter.viewmodel.service.CustomerServiceActivity;
import com.mmkj.usercenter.viewmodel.setting.SettingActivity;
import com.mmkj.usercenter.viewmodel.usercenter.UserCenterActivity;

import dagger.Component;

/**
 * Created by codeest on 16/8/7.
 */

@ActivityScope
@Component(dependencies = UserCenterAppComponent.class, modules = UserCenterActivityMoule.class)
public interface UserCenterActivityComponent {
	Activity getActivity();
	void inject(UserCenterActivity activity);
	void inject(RepaymentChannelActivity activity);
	void inject(CreditGuaranteeActivity activity);
	void inject(SettingActivity activity);
	void inject(RepayListActivity activity);
	void inject(RepayDetailActivity activity);
    void inject(LoanListActivity activity);
    void inject(CustomerServiceActivity activity);

}
