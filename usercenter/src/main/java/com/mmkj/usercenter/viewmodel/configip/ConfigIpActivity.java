package com.mmkj.usercenter.viewmodel.configip;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.HeadToolbar;
import com.mmkj.baselibrary.annotation.HeaderBuilder;
import com.mmkj.baselibrary.base.DataBindingActivity;
import com.mmkj.lib.flb.BaseConfig;
import com.mmkj.usercenter.R;
import com.mmkj.usercenter.databinding.ActivityConfigipBinding;

import java.util.ArrayList;

/**
 * Author: wendjia
 * Time: 2018\9\12 0012
 * Description:
 **/
//@Route(path = RouteConstant.CONFIG_IP)
//public class ConfigIpActivity extends DataBindingActivity<ActivityConfigipBinding> {
//
//	ArrayList<IPEntity> ips;
//
//	@Override
//	protected HeadToolbar getHeadToolbar() {
//		return new HeaderBuilder().mContentViewId(R.layout.activity_configip).build();
//	}
//
//	@Override
//	protected void initViews() {
//		mDataBinding.edit.setText(BaseConfig.getInstance().getBaseUrl());
//		initData();
//		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//		mDataBinding.rvList.setLayoutManager(layoutManager);
//		ConfigIpAdapter adapter = new ConfigIpAdapter(ips);
//		mDataBinding.rvList.setAdapter(adapter);
//	}
//
//	private void initData() {
//		ips = new ArrayList<>();
//		ips.add(new IPEntity("测试", "http://114.55.90.111:8433/"));
//		ips.add(new IPEntity("宏波", "http://172.16.20.181:8084/"));
//		ips.add(new IPEntity("海东", "http://172.16.20.236:8084/"));
//		ips.add(new IPEntity("孝田", "http://172.16.20.205:8084/"));
//		ips.add(new IPEntity("瑞", "http://172.16.20.99:8084/"));
//		ips.add(new IPEntity("嘉文", "http://172.16.20.178:8084/"));
//	}
//}
