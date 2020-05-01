package com.mmkjflb.mmloan.viewmodel.repay;

import com.mmkj.usercenter.model.entity.PaymentData;
import com.mmkjflb.mmloan.model.entity.RepayEntity;
import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkjflb.mmloan.model.entity.RepaymentShopEntity;

import java.util.List;
import java.util.Map;

import retrofit2.http.FieldMap;

public interface RepayContract {
    interface View extends BaseView {
        void queryBillsInfoSuc(BaseBean<RepayEntity> baseBean);

        void submitActiveRepaySuc(BaseBean baseBean);

//        void updateRepayChannelSuc(BaseBean baseBean);

        void getRepaymentShopSuc(BaseBean<List<Integer>> baseBean);

//        void getRepaymentShopNewSuc(BaseBean<RepaymentShopEntity> data);

        void getPaymentCodeSuc(BaseBean<PaymentData> baseBean);
    }

    interface Presenter extends BasePresenter<RepayContract.View> {
        void queryBillsInfo(String sessionId);

        void submitActiveRepay(Map<String, Object> params);

//        void updateRepayChannel(Map<String, Object> params);

        void getRepaymentShop();

//        void getRepaymentShopNew(String sessionId);

        void getPaymentCode(String sessionId);
    }
}
