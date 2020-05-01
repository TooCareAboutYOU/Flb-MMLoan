package com.mmkjflb.mmloan.viewmodel.active;

import com.mmkjflb.mmloan.model.entity.QueryActivityEntity;
import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;

import java.util.List;

public interface ActiveContract {
    interface View extends BaseView {
        void queryActivitySuc(BaseBean<List<QueryActivityEntity>> baseBean);

    }

    interface Presenter extends BasePresenter<ActiveContract.View> {
        void queryActivity(String sessionId);
    }
}
