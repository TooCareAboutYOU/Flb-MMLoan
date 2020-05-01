package com.mmkjflb.mmloan.viewmodel.chooseposition;

import android.app.Activity;
import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityChoosePositionBinding;
import com.mmkjflb.mmloan.model.entity.PositionEntity;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.syhmmfqphl.xyxlibrary.utils.ExtraKeys;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.PreferenceUtils;

import java.util.ArrayList;

@Route(path = RouteConstant.CHOOSE_POSITION)
@ActivityFragmentInject(contentViewId = R.layout.activity_choose_position, toolbarTitle = R.string.credit_deposit)
public class ChoosePositionActivity extends BaseActivity<ChoosePositionPresenter, ActivityChoosePositionBinding> implements ChoosePositionContract.View {
	PositionTypeAdapter positionTypeAdapter;
	PositionInfoAdapter positionInfoAdapter;
	boolean needEnable = false;


	@Autowired
	public PositionEntity positionEntity = null;

	@Override
	protected void initViews() {
		getActivityComponent().inject(this);
		initRv();
//		positionEntity = getIntent().getParcelableExtra(ExtraKeys.Key_Msg1);
		if (positionEntity != null) {
			needEnable = true;
		}
		mPresenter.queryPosition(PreferenceUtils.getUserSessionid());
		initClicks();
	}

	private void initRv() {
		mDataBinding.rvPositionType.setLayoutManager(new LinearLayoutManager(this));
		mDataBinding.rvPosition.setLayoutManager(new LinearLayoutManager(this));
		positionTypeAdapter = new PositionTypeAdapter(null);
		positionInfoAdapter = new PositionInfoAdapter(null);
		mDataBinding.rvPositionType.setAdapter(positionTypeAdapter);
		mDataBinding.rvPosition.setAdapter(positionInfoAdapter);
	}

	private void initClicks() {
		positionTypeAdapter.setOnItemClickListener((adapter, view, position) -> {
			PositionEntity entity = positionTypeAdapter.getItem(position);
			if (entity != null) {
				if (entity.getId() != positionTypeAdapter.getSelectId()) {
					mPresenter.queryPositionInfo(PreferenceUtils.getUserSessionid(), entity.getId());
					positionTypeAdapter.setSelectId(entity.getId());
				}
			}
		});
		positionInfoAdapter.setOnItemChildClickListener((adapter, view, position) -> {
			positionInfoAdapter.setSelectItemAndNotify(position);
			positionEntity = positionInfoAdapter.getSelectItemt();
		});
//		RxViewUtil.clicks(mDataBinding.tvSubmit).subscribe(o->{
//
//		});
	}

	@Override
	public void onToolbarNavigationClick() {
		Intent it = new Intent();
		it.putExtra(ExtraKeys.Key_Msg1, positionInfoAdapter.getSelectItemt());
		setResult(Activity.RESULT_OK, it);
		finish();
	}

	@Override
	public void onBackPressed() {
//		super.onBackPressed();
		Intent it = new Intent();
		it.putExtra(ExtraKeys.Key_Msg1, positionInfoAdapter.getSelectItemt());
		setResult(Activity.RESULT_OK, it);
		finish();
	}


	@Override
	public void queryPositionSuc(BaseBean<ArrayList<PositionEntity>> baseBean) {
		if (DataResult.isSuccessUnToast(this, baseBean)) {
			positionTypeAdapter.setNewData(baseBean.getData(), positionEntity);
			if (baseBean.getData() != null && baseBean.getData().size() > 0) {
				mPresenter.queryPositionInfo(PreferenceUtils.getUserSessionid(), positionTypeAdapter.getSelectId());
			}
		}
	}

	@Override
	public void queryPositionInfoSuc(BaseBean<ArrayList<PositionEntity>> baseBean) {
		if (DataResult.isSuccessUnToast(this, baseBean)) {
			positionInfoAdapter.setNewData(baseBean.getData(), positionEntity);
		}
	}


}
