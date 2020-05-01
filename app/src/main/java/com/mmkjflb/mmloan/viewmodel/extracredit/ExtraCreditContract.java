package com.mmkjflb.mmloan.viewmodel.extracredit;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkjflb.mmloan.model.entity.ExtraCreditBean;

public interface ExtraCreditContract {
    interface View extends BaseView {
        void getExtraCreditSuc(BaseBean<ExtraCreditBean> bean);
        void submitReportTokenSuc(BaseBean bean);
    }

    interface Presenter extends BasePresenter<View> {
        void getExtraCredit(String sessionId);
        void submitReportToken(String sessionId,String reportTaskToken,String website);
    }
}
