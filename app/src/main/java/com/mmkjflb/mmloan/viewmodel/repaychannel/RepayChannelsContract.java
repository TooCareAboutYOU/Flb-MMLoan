package com.mmkjflb.mmloan.viewmodel.repaychannel;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkjflb.mmloan.model.entity.RepaymentShopEntity;

import java.util.Map;

public interface RepayChannelsContract {

    interface View extends BaseView {
        void getRepaymentShopNewSuc(BaseBean<RepaymentShopEntity> data);
        void updateRepayChannelSuc(BaseBean baseBean);
    }

    interface Presenter extends BasePresenter<RepayChannelsContract.View> {
        void getRepaymentShopNew(String sessionId);

        void updateRepayChannel(Map<String, Object> params);

    }
}
