package com.mmkjflb.mmloan.viewmodel.fragment.privateowner;

import com.mmkjflb.mmloan.model.entity.IslandProvinceCityEntity;
import com.mmkjflb.mmloan.model.entity.PrivateOwnerEntity;
import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by
 * Date: On 2018/3/9
 * Author: wangjieyan
 * Email: wangjieyan9308@163.com
 */

public interface PrivateOwnerContract {

    interface View extends BaseView {
        void getIslandsInfoSuc(BaseBean<List<IslandProvinceCityEntity>> baseBean);
        void selfemployinfoSuc(BaseBean baseBean);
        void getSelfemployInfoSuc(BaseBean<PrivateOwnerEntity> baseBean);
    }

    interface Presenter extends BasePresenter<View> {
        void getIslandsInfo(String sessionId);
        void selfemployinfo(Map<String, RequestBody> params, MultipartBody.Part companyFront);
        void getSelfemployInfo(String sessionId);
    }
}
