package com.mmkjflb.mmloan.viewmodel.loan;


import com.mmkj.baselibrary.model.entitry.GCashDetailBean;
import com.mmkjflb.mmloan.model.entity.LoanEntity;
import com.mmkjflb.mmloan.model.entity.PaymentShopListEntity;
import com.mmkjflb.mmloan.model.entity.SurelyEntity;
import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;

import java.util.List;
import java.util.Map;

public interface LoanContract {
    interface View extends BaseView {
        void loanInfoSuc(BaseBean<List<LoanEntity>> baseBean);
        void getGCashDetailSuc(BaseBean<GCashDetailBean> baseBean);
        void getPaymentShopListAllSuc(BaseBean<List<PaymentShopListEntity>> baseBean);
        void surelySuc(BaseBean<SurelyEntity> baseBean);

    }

    interface Presenter extends BasePresenter<View> {
        void loanInfo(String sesstionId);
        void getGCashDetail(String sessionId);
        void getPaymentShopListAll();
        void surely(Map<String, Object> params);

    }
}
