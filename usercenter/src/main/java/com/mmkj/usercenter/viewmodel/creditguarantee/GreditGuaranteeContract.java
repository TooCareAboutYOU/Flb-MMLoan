package com.mmkj.usercenter.viewmodel.creditguarantee;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.usercenter.model.entity.CashDepositInfoEntity;
import com.mmkj.usercenter.model.entity.CreditManagerEntity;

/**
 * @author wendjia
 * @date 2017/10/24
 */

public interface GreditGuaranteeContract {
    interface View extends BaseView {
        void queryCreditManagerSuc(BaseBean<CreditManagerEntity> baseBean);
        void confirmIncreaseCashDepositSuc(BaseBean baseBean);
        void getDepositInfoSuc(BaseBean<CashDepositInfoEntity> data);
    }


    interface Presenter extends BasePresenter<View> {
        void queryCreditManager(String sessionId);
        void confirmIncreaseCashDeposit(String productCode,String sessionId);
        void getDepositInfo(String sessionId);


    }

}
