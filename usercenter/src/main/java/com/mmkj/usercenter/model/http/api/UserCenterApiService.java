/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.mmkj.usercenter.model.http.api;

import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.model.entitry.GCashDetailBean;
import com.mmkj.baselibrary.model.entitry.WithdrawalNumberEntity;
import com.mmkj.usercenter.model.entity.CashDepositInfoEntity;
import com.mmkj.usercenter.model.entity.CreditManagerEntity;
import com.mmkj.usercenter.model.entity.CustomerServiceData;
import com.mmkj.usercenter.model.entity.LoanListData;
import com.mmkj.usercenter.model.entity.PaymentData;
import com.mmkj.usercenter.model.entity.PaymentListData;
import com.mmkj.usercenter.model.entity.RepayChannelBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface UserCenterApiService {
    /**
     * 确认提额
     */
    @GET("cashDeposit/confirmIncreaseCashDeposit")
    Flowable<BaseBean> confirmIncreaseCashDeposit(@Query("productCode") String productCode, @Query("sessionId") String sessionId);

    /**
     * 获取提额详情
     */
    @GET("cashDeposit/getDepositInfo")
    Flowable<BaseBean<CashDepositInfoEntity>> getDepositInfo(@Query("sessionId") String sessionId);

    @FormUrlEncoded
    @POST("/center/loginOut")
    Flowable<BaseBean> loginOut(@Field("sessionId") String sessionId);

    @FormUrlEncoded
    @POST("/payment/getPaymentCode")
    Flowable<BaseBean<PaymentData>> getPaymentCode(@Field("sessionId") String sessionId);

    @GET("/payment/getBillsDetails")
    Flowable<BaseBean<PaymentListData>> getBillsDetails(@Query("sessionId") String sessionId, @Query("billId") String billId);

    @GET("/center/getLoanRecords")
    Flowable<BaseBean<List<LoanListData>>> getLoanRecords(@Query("sessionId") String sessionId, @Query("type") int type);

    /**
     * 查询取款码
     */
    @GET("loan/getWithdrawalNumber")
    Flowable<BaseBean<WithdrawalNumberEntity>> getWithdrawalNumber(@Query("sessionId") String sessionId);

    @GET("app/getCustomerService")
    Flowable<BaseBean<List<CustomerServiceData>>> getCustomerService();

    @GET("cashDeposit/creditManager/{sessionId}")
    Flowable<BaseBean<CreditManagerEntity>> queryCreditManager(@Path("sessionId") String sessionId);


    @FormUrlEncoded
    @POST("/repayment/getRepayChannelByUser")
    Flowable<BaseBean<RepayChannelBean>> getRepayChannelByUser(@Field("sessionId") String sessionId);

    @GET("/user/gcash/detail")
    Flowable<BaseBean<GCashDetailBean>> getGCashDetail(@Query("sessionId") String sessionId);
}
