package com.mmkjflb.mmloan.viewmodel.fragment.freelance;

import com.mmkjflb.mmloan.model.entity.FreelanceEntity;
import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;

import java.util.Map;

/**
 * Created by
 * Date: On 2018/3/8
 * Author: wangjieyan
 * Email: wangjieyan9308@163.com
 */

public interface FreelanceContract {

    interface View extends BaseView {

        void freelanceinfoSuc(BaseBean baseBean);
        void getFreelanceInfoSuc(BaseBean<FreelanceEntity> baseBean);
    }

    interface Presenter extends BasePresenter<View> {
        void freelanceinfo(Map<String, Object> params);
        void getFreelanceInfo(String sessionId);
    }
}
