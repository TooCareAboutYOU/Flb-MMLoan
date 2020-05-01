package com.mmkjflb.mmloan.viewmodel.chooseposition;


import com.mmkjflb.mmloan.model.entity.PositionEntity;
import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;

import java.util.ArrayList;

/**
 * Author: wendjia
 * Time: 2018\7\31
 * Description: 选择职业
 */
public class ChoosePositionContract {

	/**
	 * The interface View.
	 */
	public interface View extends BaseView {
		/**
		 * 查询职位信息成功
		 *
		 * @param baseBean the base bean
		 */
		void queryPositionSuc(BaseBean<ArrayList<PositionEntity>> baseBean);

		void queryPositionInfoSuc(BaseBean<ArrayList<PositionEntity>> baseBean);
	}

	/**
	 * The interface Presenter.
	 */
	interface Presenter extends BasePresenter<View> {

		void queryPosition(String sessionId);

		/**
		 * 查询职位信息
		 *
		 * @param sessionId 用户id
		 * @param typeId    查询类型id
		 */
		void queryPositionInfo(String sessionId, long typeId);
	}

}
