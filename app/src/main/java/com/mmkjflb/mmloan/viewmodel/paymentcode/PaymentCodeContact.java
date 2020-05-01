package com.mmkjflb.mmloan.viewmodel.paymentcode;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkjflb.mmloan.model.entity.DepositCodeEntity;

public interface PaymentCodeContact {

    interface View extends BaseView{
        void getDepositCodeSuc(BaseBean<DepositCodeEntity> baseBean);
        void queryDepositCodeSuc(BaseBean<DepositCodeEntity> baseBean);
    }

    interface Presenter extends BasePresenter<View>{
        void getDepositCode(String sessionId);
        void queryDepositCode(String sessionId);
    }

}
