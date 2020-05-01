package com.mmkjflb.mmloan.viewmodel.certificateresult;

import com.mmkjflb.mmloan.model.entity.ArtificialAuthentStatusEntity;
import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;

public interface CertificationResultContract {
    interface View extends BaseView {
        void queryCreditSuc(BaseBean<ArtificialAuthentStatusEntity> baseBean);

        void rollbackStatusSuc(BaseBean baseBean);

    }

    interface Presenter extends BasePresenter<CertificationResultContract.View> {
        void queryCredit( String sessionId);

        void rollbackStatus(String sessionId);
    }
}
