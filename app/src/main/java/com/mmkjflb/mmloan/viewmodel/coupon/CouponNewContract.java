package com.mmkjflb.mmloan.viewmodel.coupon;

import com.mmkjflb.mmloan.model.entity.CouponEntity;
import com.mmkj.baselibrary.base.BasePresenter;
import com.mmkj.baselibrary.base.BaseView;
import com.mmkj.baselibrary.model.entitry.BaseBean;

import java.util.ArrayList;

/**
 * Created by
 * Date: On 2018/1/15
 * Author: wangjieyan
 * Email: wangjieyan9308@163.com
 */

public class CouponNewContract {

    public interface View extends BaseView {
        /**
         * 请求优惠劵
         * @param  baseBean 返回参数
         */
        void querycouponSuc(BaseBean<ArrayList<CouponEntity>> baseBean);
    }

    interface Presenter extends BasePresenter<CouponNewContract.View> {
        /**
         * 查询优惠劵接口
         * @param  sessionId 入参
         */
        void querycoupon(String sessionId);
    }

}
