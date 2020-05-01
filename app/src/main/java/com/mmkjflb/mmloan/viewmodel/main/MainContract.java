package com.mmkjflb.mmloan.viewmodel.main;

import com.mmkjflb.mmloan.model.entity.CashDepositInfoEntity;
import com.mmkjflb.mmloan.model.entity.MainEntity;
import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;

import java.util.Map;

public interface MainContract {
    interface View extends BaseView {
        void balanceSuc(BaseBean<MainEntity> baseBean);
        void confirmIncreaseCashDepositSuc(BaseBean baseBean);
        void sendCodeSuc(BaseBean baseBean);
        void afterBorrowCheckSuc(BaseBean baseBean);
        void getDepositInfoSuc(BaseBean<CashDepositInfoEntity> data);
    }


    interface Presenter extends BasePresenter<View> {
        void balance(boolean unfirst, String sesstionId);
        void confirmIncreaseCashDeposit(String productCode,String sesstionId);
        void sendCode(String mobile, String smsCode);
        void afterBorrowCheck(Map<String, Object> map);
        void getDepositInfo(String sesstionId);
    }

}
