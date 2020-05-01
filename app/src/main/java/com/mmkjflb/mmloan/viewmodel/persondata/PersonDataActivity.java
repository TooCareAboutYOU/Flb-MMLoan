package com.mmkjflb.mmloan.viewmodel.persondata;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.databinding.ActivityPersondataBinding;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.base.DataBindingActivity;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.StringUtils;

/**
 * Created by Administrator on 2017/10/16.
 */
@Route(path = RouteConstant.PERSON_DATA)
@ActivityFragmentInject(contentViewId = R.layout.activity_persondata, toolbarTitle = R.string.person_data)
public class PersonDataActivity extends DataBindingActivity<ActivityPersondataBinding> {
	@Override
	protected void initViews() {
		mDataBinding.tvPhone.setText(StringUtils.secureShow(PreferenceUtils.getString(PreferenceUtils.USER_PHONE,""),3,4,4));
		mDataBinding.tvIdcard.setText(StringUtils.secureShow(PreferenceUtils.getString(PreferenceUtils.USER_IDCARD,""),1,1,10));
	}
}
