package com.mmkjflb.mmloan.viewmodel.withdraw;


import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.model.entitry.WithdrawalNumberEntity;

public interface WithDrawContract {
    interface View extends BaseView {
        void getWithdrawalNumberSuc(BaseBean<WithdrawalNumberEntity> baseBean);
    }

    interface Presenter extends BasePresenter<View> {

        void getWithdrawalNumber(String sesstionId);
    }
}