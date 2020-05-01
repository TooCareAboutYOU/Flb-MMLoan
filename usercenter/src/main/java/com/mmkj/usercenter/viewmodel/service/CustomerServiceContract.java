package com.mmkj.usercenter.viewmodel.service;

import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.usercenter.model.entity.CustomerServiceData;

import java.util.List;

public interface CustomerServiceContract {
    interface View extends BaseView{
        void getServiceInfoSuc(BaseBean<List<CustomerServiceData>> data);
    }

    interface Presenter extends BasePresenter<CustomerServiceContract.View>{
        void getServiceInfo();
    }

}
