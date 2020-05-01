package com.mmkj.usercenter.viewmodel.repaymentchannel;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.usercenter.model.entity.RepayChannelBean;

public interface RepaymentChannelContract {
    interface View extends BaseView {
        void getRepayChannelByUserSuc(BaseBean<RepayChannelBean> data);
    }

    interface Presenter extends BasePresenter<View> {
        void getRepayChannelByUser( String sessionId);
    }

}
