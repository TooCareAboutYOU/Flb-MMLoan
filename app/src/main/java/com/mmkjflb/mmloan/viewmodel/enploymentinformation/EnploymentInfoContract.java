package com.mmkjflb.mmloan.viewmodel.enploymentinformation;


import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkjflb.mmloan.model.entity.AuthentStatusEntity;

import okhttp3.RequestBody;

/**
 * Created by Administrator on 2017/10/24.
 */

public interface EnploymentInfoContract {
    interface View extends BaseView {
        void authentStatusSuc(BaseBean<AuthentStatusEntity> authentStatusEntityBaseBean);
    }

    interface Presenter extends BasePresenter<View> {
        void authentStatus(String sessionId);
    }
}