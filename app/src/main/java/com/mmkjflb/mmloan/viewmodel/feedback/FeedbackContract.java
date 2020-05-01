package com.mmkjflb.mmloan.viewmodel.feedback;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;

import java.util.Map;

/**
 * Created by Administrator on 2017/10/28.
 */

public class FeedbackContract {
	interface View extends BaseView {
		void applySuggestSuc(BaseBean baseBean);


	}

	interface Presenter extends BasePresenter<FeedbackContract.View> {
		void applySuggest(Map<String, String> map);
	}
}
