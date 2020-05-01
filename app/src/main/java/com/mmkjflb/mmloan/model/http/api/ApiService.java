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

package com.mmkjflb.mmloan.model.http.api;

import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.model.entitry.GCashDetailBean;
import com.mmkj.baselibrary.model.entitry.WithdrawalNumberEntity;
import com.mmkj.usercenter.model.entity.CustomerServiceData;
import com.mmkj.usercenter.model.entity.PaymentData;
import com.mmkjflb.mmloan.model.entity.AdviseEntity;
import com.mmkjflb.mmloan.model.entity.AmountTypeEntity;
import com.mmkjflb.mmloan.model.entity.ApplyForPromoteEntity;
import com.mmkjflb.mmloan.model.entity.ArtificialAuthentStatusEntity;
import com.mmkjflb.mmloan.model.entity.AssertsInfoEntity;
import com.mmkjflb.mmloan.model.entity.AuditDepositEntity;
import com.mmkjflb.mmloan.model.entity.AuthEntity;
import com.mmkjflb.mmloan.model.entity.AuthentStatusEntity;
import com.mmkjflb.mmloan.model.entity.BankInfoEntity;
import com.mmkjflb.mmloan.model.entity.BankListEntity;
import com.mmkjflb.mmloan.model.entity.BankTypeEntity;
import com.mmkjflb.mmloan.model.entity.BaseInfoEntity;
import com.mmkjflb.mmloan.model.entity.BindCardEntity;
import com.mmkjflb.mmloan.model.entity.BooleanEntity;
import com.mmkjflb.mmloan.model.entity.CardManagerEntity;
import com.mmkjflb.mmloan.model.entity.CashDepositInfoEntity;
import com.mmkjflb.mmloan.model.entity.CertificateDetailEntity;
import com.mmkjflb.mmloan.model.entity.ChannelGoogleEntity;
import com.mmkjflb.mmloan.model.entity.CollectionAccountEntity;
import com.mmkjflb.mmloan.model.entity.CompanyNatureEntity;
import com.mmkjflb.mmloan.model.entity.ContactsEntity;
import com.mmkjflb.mmloan.model.entity.CouponEntity;
import com.mmkjflb.mmloan.model.entity.DepositCodeEntity;
import com.mmkjflb.mmloan.model.entity.ExtraCreditBean;
import com.mmkjflb.mmloan.model.entity.FreelanceEntity;
import com.mmkjflb.mmloan.model.entity.GuideEntity;
import com.mmkjflb.mmloan.model.entity.IdCardCheckEntity;
import com.mmkjflb.mmloan.model.entity.IsPaidEntity;
import com.mmkjflb.mmloan.model.entity.IslandProvinceCityEntity;
import com.mmkjflb.mmloan.model.entity.LoanEntity;
import com.mmkjflb.mmloan.model.entity.LoginBean;
import com.mmkjflb.mmloan.model.entity.MainEntity;
import com.mmkjflb.mmloan.model.entity.MyBorrowBean;
import com.mmkjflb.mmloan.model.entity.NoticeEntity;
import com.mmkjflb.mmloan.model.entity.OcrCheckEntity;
import com.mmkjflb.mmloan.model.entity.PayCodeEntity;
import com.mmkjflb.mmloan.model.entity.PaymentForFasPayEntity;
import com.mmkjflb.mmloan.model.entity.PaymentShopListEntity;
import com.mmkjflb.mmloan.model.entity.PositionEntity;
import com.mmkjflb.mmloan.model.entity.PreventionEntity;
import com.mmkjflb.mmloan.model.entity.PrivateOwnerEntity;
import com.mmkjflb.mmloan.model.entity.ProductInfoBean;
import com.mmkjflb.mmloan.model.entity.QueryActivityEntity;
import com.mmkjflb.mmloan.model.entity.QueryInviteActivityEntity;
import com.mmkjflb.mmloan.model.entity.QueryPaymentChannelEntity;
import com.mmkjflb.mmloan.model.entity.QueryProductInfoEntity;
import com.mmkjflb.mmloan.model.entity.QueryRefundInfoEntity;
import com.mmkjflb.mmloan.model.entity.ReauthenticationEntity;
import com.mmkjflb.mmloan.model.entity.RechargeEntity;
import com.mmkjflb.mmloan.model.entity.RecordEntity;
import com.mmkjflb.mmloan.model.entity.RefundCodeEntity;
import com.mmkjflb.mmloan.model.entity.RefundDepositEntity;
import com.mmkjflb.mmloan.model.entity.RefundInfoEntity;
import com.mmkjflb.mmloan.model.entity.RepayEntity;
import com.mmkjflb.mmloan.model.entity.RepaymentShopEntity;
import com.mmkjflb.mmloan.model.entity.SalariesEntity;
import com.mmkjflb.mmloan.model.entity.SocialInfoEntity;
import com.mmkjflb.mmloan.model.entity.SpecialMenuEntity;
import com.mmkjflb.mmloan.model.entity.StringEntity;
import com.mmkjflb.mmloan.model.entity.StudentsEntity;
import com.mmkjflb.mmloan.model.entity.SurelyEntity;
import com.mmkjflb.mmloan.model.entity.UpVersionBean;
import com.mmkjflb.mmloan.model.entity.UpdateAgreeNoEntity;
import com.mmkjflb.mmloan.model.entity.YiBaoBindCardEntity;
import com.mmkjflb.mmloan.model.entity.ZhiMaAuthEntity;
import com.mmkjflb.mmloan.model.entity.ZhiMaResultEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiService {
    /**
     * 安装统计接口
     */
    @FormUrlEncoded
    @POST("app/insertChannelGoogle")
    Flowable<BaseBean<ChannelGoogleEntity>> insertChannelGoogle(@FieldMap Map<String, Object> map);

    /**
     * 获取额度类型
     */
    @GET("credit/getAmountType")
    Flowable<BaseBean<AmountTypeEntity>> getAmountType(@Query("sessionId") String sessionId);

    /**
     * 用户短信接口上传
     */
    @POST("app/smsInfos")
    Flowable<BaseBean> smsInfos(@Header("sessionId") String sessionId, @Body RequestBody requestBody);

    /**
     * gcash页面提交借款产品类型
     */
    @FormUrlEncoded
    @POST("users/updateProductCode")
    Flowable<BaseBean> updateProductCode(@Field("sessionId") String sessionId, @Field("productType") int productType);

    /**
     * 版本更新接口 ( 启动页 )
     *
     * @param platformType 用来区分android和ios
     * @param packageType  传包名用来区分不同apk
     * @return
     */
    @FormUrlEncoded
    @POST("app/getAppVersion")
    Flowable<BaseBean<UpVersionBean>> getAppVersion(@Field("platformType") String platformType, @Field("packageType") String packageType);

    /**
     * 客服电话 ( 启动页 )
     */
    @GET("app/getCustomerService")
    Flowable<BaseBean<List<CustomerServiceData>>> getCustomerService();

    /**
     * 登录
     *
     * @param mobile
     * @param password password字段传的密码值需要RSA加密
     * @return
     */
    @FormUrlEncoded
    @POST("users/login")
    Flowable<BaseBean<LoginBean>> login(@Field("mobile") String mobile, @Field("password") String password);

    /**
     * 风控需要的各种设备信息
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("app/deviceInfo")
    Flowable<BaseBean> deviceInfo(@FieldMap Map<String, String> map);

    /**
     * 获取验证码
     *
     * @param mobile
     * @param smsCode 短信类型
     *                0:注册短信、gcash发送验证码
     *                1:忘记密码
     *                2:个人中心修改手机号第二步
     *                3:首页借款认证
     * @return
     */
    @FormUrlEncoded
    @POST("users/sendSMS")
    Flowable<BaseBean> sendSms(@Field("mobile") String mobile, @Field("smsCode") String smsCode);

    /**
     * 注册
     * mobile  手机号
     * password   RSA加密
     * captcha   验证码
     * mStr  EncryptUtils.md5(click_id, Constants.MD5_KEY));
     * referrer
     * source",  "Pera Express&" + DeviceUtils.getPackageName(this)); Pera Express用来标志不同app（有两个马甲包）
     */
    @FormUrlEncoded
    @POST("users/register")
    Flowable<BaseBean> register(@FieldMap Map<String, Object> map);

    /**
     * 图形验证码
     *
     * @param mobile 手机号
     * @return
     */
    @FormUrlEncoded
    @POST("users/getVerifycode")
    Flowable<ResponseBody> getVerifycode(@Field("mobile") String mobile);

    /**
     * 申请借款
     * sessionId
     * applyAmount  金额
     * applyDate  时间天数
     */
    @FormUrlEncoded
    @POST("users/applyLoan")
    Flowable<BaseBean> applyLoan(@FieldMap Map<String, Object> map);

    /**
     * 资质认证
     *
     * @param sessionId
     * @return
     */
    @FormUrlEncoded
    @POST("auth/authInfo")
    Flowable<BaseBean<AuthentStatusEntity>> authentStatus(@Field("sessionId") String sessionId);

    /**
     * 获取基本信息
     *
     * @param sessionId
     * @returnbottomSheetDialog
     */
    @GET("auth/getBaseInfo")
    Flowable<BaseBean<BaseInfoEntity>> getBaseInfo(@Query("sessionId") String sessionId);

    /**
     * 提交基本信息
     *
     * @return
     */
    @FormUrlEncoded
    @POST("auth/baseinfo")
    Flowable<BaseBean> baseinfo(@FieldMap Map<String, Object> map);

    /**
     * 获取七牛云上传凭证接口
     */
    @GET("auth/getQiniuToken")
    Flowable<BaseBean<String>> getQiniuToken(@Query("sessionId") String sessionId);

    /**
     * @param sessionId
     * @param contactsList        json字符串
     * @param firstContactMobile
     * @param firstContactName
     * @param secondContactMobile
     * @param secondContactName
     * @return
     */
    @FormUrlEncoded
    @POST("auth/contacts")
    Flowable<BaseBean> contacts(@Field("sessionId") String sessionId,
                                @Field("contactsList") String contactsList
            , @Field("firstContactMobile") String firstContactMobile
            , @Field("firstContactName") String firstContactName
            , @Field("secondContactMobile") String secondContactMobile
            , @Field("secondContactName") String secondContactName
    );

    /**
     * 通讯录信息回填
     *
     * @param sessionId
     * @return
     */
    @GET("auth/getContactsInfo")
    Flowable<BaseBean<ContactsEntity>> getContactsInfo(@Query("sessionId") String sessionId);

    /**
     * @param sessionId
     * @param records   json字符串
     * @return
     */
    @FormUrlEncoded
    @POST("auth/call/record")
    Flowable<BaseBean> submitCallRecord(@Field("sessionId") String sessionId, @Field("records") String records);

    /**
     * 提交身份证信息
     *
     * @param partMap
     * @return
     */
    @FormUrlEncoded
    @POST("auth/idcheck")
    Flowable<BaseBean> idcheck(@FieldMap Map<String, Object> partMap);

    /**
     * 获取就业信息
     *
     * @param sessionId
     * @return
     */
    @GET("auth/getSalariesInfo")
    Flowable<BaseBean<SalariesEntity>> getSalariesInfo(@Query("sessionId") String sessionId);

    /**
     * 提交就业信息
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("auth/salariesinfo")
    Flowable<BaseBean> salariesinfo(@FieldMap Map<String, Object> params);

    /**
     * Nature of the Company 数据获取
     */
    @GET("auth/companyNature")
    Flowable<BaseBean<List<CompanyNatureEntity>>> getCompanyNature();

    @GET("user/gcash/detail")
    Flowable<BaseBean<GCashDetailBean>> getGCashDetail(@Query("sessionId") String sessionId);

    @FormUrlEncoded
    @POST("user/gcash/saveOrUpdate")
    Flowable<BaseBean> updateGCash(@Field("sessionId") String sessionId, @Field("userGCashAccount") String userGCashAccount, @Field("captcha") String captcha);

    /**
     * 审核状态
     *
     * @return 返回审核状态
     */
    @FormUrlEncoded
    @POST("credit/queryCredit")
    Flowable<BaseBean<ArtificialAuthentStatusEntity>> queryCredit(@Field("sessionId") String sessionId);

    @GET("credit/getProductInfo")
    Flowable<BaseBean<List<ProductInfoBean>>> getProductInfo(@Query("sessionId") String sessionId, @Query("type") int type);


    /**
     * 机审结果页点击重新认证
     *
     * @param sessionId
     * @return
     */
    @FormUrlEncoded
    @POST("credit/rollbackStatus")
    Flowable<BaseBean> rollbackStatus(@Field("sessionId") String sessionId);

    /**
     * 借款信息
     *
     * @param sessionId
     * @return
     */
    @FormUrlEncoded
    @POST("loan/loanInfo")
    Flowable<BaseBean<List<LoanEntity>>> loanInfo(@Field("sessionId") String sessionId);

    /**
     * 确认借款
     * productId 字段来自于 loan/loanInfo 接口
     */
    @FormUrlEncoded
    @POST("loan/surely")
    Flowable<BaseBean<SurelyEntity>> surely(@FieldMap Map<String, Object> params);

    /**
     * 复借验证码校验
     */
    @FormUrlEncoded
    @POST("users/afterBorrowCheck")
    Flowable<BaseBean> afterBorrowCheck(@FieldMap Map<String, Object> map);


    /**
     * 确认提额
     */
    @GET("cashDeposit/confirmIncreaseCashDeposit")
    Flowable<BaseBean> confirmIncreaseCashDeposit(@Query("productCode") String productCode,@Query("sessionId") String sessionId);

    /**
     * 获取提额详情
     */
    @GET("cashDeposit/getDepositInfo")
    Flowable<BaseBean<CashDepositInfoEntity>> getDepositInfo(@Query("sessionId") String sessionId);

    /**
     * 保存活体识别分数
     */
    @FormUrlEncoded
    @POST("auth/saveOcrScore")
    Flowable<BaseBean> saveOcrScore(@FieldMap Map<String, Object> map);

    /**
     * 身份证OCR(无效接口)
     */
    @Multipart
    @POST("auth/idCardCheck")
    Flowable<BaseBean<IdCardCheckEntity>> idCardCheck(@PartMap() Map<String, RequestBody> partMap, @Part() MultipartBody.Part multipartPart);

    /**
     * 查询取款码
     * user_centet处有复用此代码逻辑如果要修改要注意一起修改
     */
    @GET("loan/getWithdrawalNumber")
    Flowable<BaseBean<WithdrawalNumberEntity>> getWithdrawalNumber(@Query("sessionId") String sessionId);

    /**
     * 获取提款渠道
     */
    @GET("loan/getWithdrawChannel")
    Flowable<BaseBean<Map<String, Object>>> getWithdrawChannel();


    /**
     * 更换手机号第二步
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("users/renewalMobilePhone")
    Flowable<BaseBean> renewalMobilePhone(@FieldMap Map<String, Object> map);

    /**
     * 更换手机号第一步 检验用户信息
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("users/checkUserInfo")
    Flowable<BaseBean> checkUserInfo(@FieldMap Map<String, Object> map);

    /**
     * 获取银行列表
     *
     * @param sessionId
     * @return
     */
    @FormUrlEncoded
    @POST("bank/bankList")
    Flowable<BaseBean<List<BankListEntity>>> bankList(@Field("sessionId") String sessionId);

    /**
     * 获取银行信息
     *
     * @param sessionId
     * @return
     */
    @GET("auth/getBankInfo")
    Flowable<BaseBean<BankInfoEntity>> getBankInfo(@Query("sessionId") String sessionId);

    /**
     * 提交银行卡信息
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("auth/bankinfo")
    Flowable<BaseBean> bankinfo(@FieldMap Map<String, Object> params);

    /**
     * 忘记密码
     */
    @FormUrlEncoded
    @POST("users/forgotPwd")
    Flowable<BaseBean> forgotPwd(@FieldMap Map<String, String> map);

    /**
     * 借款还款主界面获取借还款信息
     *
     * @param sessionId
     * @return
     */
    @FormUrlEncoded
    @POST("credit/balance")
    Flowable<BaseBean<MainEntity>> balance(@Field("sessionId") String sessionId);

    /**
     * 确认还款
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("payment/submitActiveRepay")
    Flowable<BaseBean> submitActiveRepay(@FieldMap Map<String, Object> params);

    /**
     * 更新还款渠道
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("payment/updateActiveRepay")
    Flowable<BaseBean> updateRepayChannel(@FieldMap Map<String, Object> params);


    /**
     * 意见反馈
     * sessionId
     * content  内容
     * contactType  联系方式
     */
    @FormUrlEncoded
    @POST("center/applySuggest")
    Flowable<BaseBean> applySuggest(@FieldMap Map<String, String> params);

    /**
     * 更换密码
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("center/updatePwd")
    Flowable<BaseBean> updatePwd(@FieldMap Map<String, String> params);


    /**
     * 还款主界面数据接口
     *
     * @param sessionId
     * @return
     */
    @FormUrlEncoded
    @POST("payment/queryBillsInfo")
    Flowable<BaseBean<RepayEntity>> queryBillsInfo(@Field("sessionId") String sessionId);

    /**
     * 选择还款渠道
     *
     * @return
     */
    @POST("repayment/getRepaymentShop")
    Flowable<BaseBean<List<Integer>>> getRepaymentShop();

    /**
     * 选择还款渠道
     *
     * @return
     */
    @FormUrlEncoded
    @POST("repayment/getRepaymentShopNew")
    Flowable<BaseBean<RepaymentShopEntity>> getRepaymentShopNew(@Field("sessionId") String sessionId);


    @FormUrlEncoded
    @POST("repay/certificate/detail")
    Flowable<BaseBean<CertificateDetailEntity>> certificateDetail(@Field("sessionId") String sessionId, @Field("bidId") int bidId, @Field("billId") int billId);

    /**
     * 还款银行获取用户信息接口
     *
     * @param sessionId
     * @return
     */
    @FormUrlEncoded
    @POST("repay/certificate/collection/account")
    Flowable<BaseBean<CollectionAccountEntity>> collectionAccount(@Field("sessionId") String sessionId);

    /**
     * 退保证金
     *
     * @param sessionId
     * @param withdrawChannel
     * @return
     */
    @FormUrlEncoded
    @POST("cashDeposit/refund")
    Flowable<BaseBean<RefundDepositEntity>> refundDeposit(@Field("sessionId") String sessionId, @Field("withdrawChannel") String withdrawChannel);

    /**
     * 获取取款码
     *
     * @param sessionId
     * @return
     */
    @GET("cashDeposit/payCode")
    Flowable<BaseBean<RefundCodeEntity>> getRefundCode(@Query("sessionId") String sessionId);

    /**
     * 获取退保证金信息
     *
     * @param sessionId
     * @return
     */

    @GET("/cashDeposit/refundInfo")
    Flowable<BaseBean<RefundInfoEntity>> getRefundInfo(@Query("sessionId") String sessionId);

    /**
     * 获取还款码
     *
     * @param sessionId
     * @return
     */

    @POST("/cashDeposit/generateCashDepositCode")
    Flowable<BaseBean<DepositCodeEntity>> getDepositCode(@Body RequestBody sessionId);

    /**
     * Va 时候获取Va码
     *
     * @param sessionId
     * @return
     */
    @GET("/cashDeposit/getCashDepositPayCode/{sessionId}")
    Flowable<BaseBean<DepositCodeEntity>> queryDepositCode(@Path("sessionId") String sessionId);

    /**
     * 上传还款银行信息
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("/repay/certificate/upload")
    Flowable<BaseBean> certificateUpload(@FieldMap Map<String, Object> map);


    //==================================下面全部为无效接口=========================================

    /**
     * 无效接口
     */
    @GET("auth/extraCredit")
    Flowable<BaseBean<ExtraCreditBean>> getExtraCredit(@Query("sessionId") String sessionId);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("auth/reptile/info")
    Flowable<BaseBean> submitReportToken(@Field("sessionId") String sessionId, @Field("reportTaskToken") String reportTaskToken, @Field("website") String website);

    /**
     * 无效接口
     */
    @GET("center/specialMenu")
    Flowable<BaseBean<SpecialMenuEntity>> specialMenu(@Query("sessionId") String sessionId);

    /**
     * 无效接口
     */
    @GET("center/isPaid")
    Flowable<BaseBean<IsPaidEntity>> isPaid(@Query("sessionId") String sessionId);

    /**
     * 无效接口
     */
    @GET("cashDeposit/applyForPromote")
    Flowable<BaseBean<ApplyForPromoteEntity>> applyForPromote(@Query("sessionId") String sessionId);

    /**
     * 无效接口
     */
    @POST("pstats/statis")
    Flowable<BaseBean> submintDevice(@Body RequestBody requestBody);

    /**
     * 无效接口
     */
    @GET("cashDeposit/queryProductInfo")
    Flowable<BaseBean<QueryProductInfoEntity>> queryProductInfo();

    /**
     * 无效接口
     */
    @GET("cashDeposit/queryRefundInfo")
    Flowable<BaseBean<QueryRefundInfoEntity>> queryRefundInfo(@Query("sessionId") String sessionId);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("cashDeposit/affirmRefund")
    Flowable<BaseBean> affirmRefund(@FieldMap Map<String, Object> map);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("payment/generateCashDepositVaOrOtc")
    Flowable<BaseBean> generateCashDepositVaOrOtc(@FieldMap Map<String, Object> map);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("payment/generateAuditDepositVaOrOtc")
    Flowable<BaseBean> generateAuditDepositVaOrOtc(@FieldMap Map<String, Object> map);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("payment/paymentForFasPay")
    Flowable<BaseBean<PaymentForFasPayEntity>> paymentForFasPay(@FieldMap Map<String, Object> map);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("/payment/queryPaymentChannel")
    Flowable<BaseBean<List<QueryPaymentChannelEntity>>> queryPaymentChannel(@Field("sessionId") String sessionId);

    /**
     * 无效接口
     */
    @GET("http://pb.clinkad.com/postback")
    Flowable<AdviseEntity> getGoogleAdvise(@QueryMap Map<String, Object> options);

    /**
     * 无效接口
     */
    @GET("auth/getAssertsInfo")
    Flowable<BaseBean<AssertsInfoEntity>> getAssertsInfo(@Query("sessionId") String sessionId);

    /**
     * 无效接口
     */
    @GET("auth/getSocialInfo")
    Flowable<BaseBean<SocialInfoEntity>> getSocialInfo(@Query("sessionId") String sessionId);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("app/getOperatingNotice")
    Flowable<BaseBean<NoticeEntity>> getOperatingNotice(@Field("noticeCode") String noticeCode);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("region/getRegionInfo")
    Flowable<BaseBean<List<IslandProvinceCityEntity>>> getIslandsInfo(@Field("sessionId") String sessionId);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("auth/socialinfo")
    Flowable<BaseBean> socialinfo(@FieldMap Map<String, Object> map);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("auth/assertsinfo")
    Flowable<BaseBean> assertsinfo(@FieldMap Map<String, Object> partMap);

    /**
     * 无效接口
     */
    @Multipart
    @POST("authentication/userBaseInfo")
    Flowable<BaseBean> userBaseInfo(@PartMap() Map<String, RequestBody> partMap, @Part MultipartBody.Part photo);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("authentication/cellphone/operator/reports")
    Flowable<BaseBean> phoneCaptcha(@Field("sessionId") String sessionId, @Field("mobile") String mobile,
                                    @Field("moblieServicePassword") String moblieServicePassword, @Field("captcha") String captcha);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("zhima/initcompare")
    Flowable<BaseBean<StringEntity>> initcompare(@Field("sessionId") String sessionId, @Field("name") String name,
                                                 @Field("idNumber") String idNumber, @Field("phoneNumber") String phoneNumber);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("zhima/queryresult")
    Flowable<BaseBean<ZhiMaResultEntity>> queryZhiMaResult(@Field("sessionId") String sessionId, @Field("name") String name,
                                                           @Field("idNumber") String idNumber, @Field("phoneNumber") String phoneNumber);


    /**
     * 无效接口
     */
    @Multipart
    @POST("batchUpload")
    Flowable<BaseBean> batchUpload(@Part("sessionId") RequestBody sessionId, @Part MultipartBody.Part front, @Part MultipartBody.Part back);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("authentication/userOcrCheck")
    Flowable<BaseBean<OcrCheckEntity>> userOcrCheck(@Field("sessionId") String sessionId);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("authentication/checkRealName")
    Flowable<BaseBean<BooleanEntity>> checkRealName(@Field("sessionId") String sessionId, @Field("name") String name, @Field("idNumber") String idNumber);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("zhima/auth")
    Flowable<BaseBean<StringEntity>> auth(@Field("sessionId") String sessionId, @Field("type") String type, @Field("name") String name,
                                          @Field("idNumber") String idNumber, @Field("mobileNo") String mobileNo);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("zhima/queryAuth")
    Flowable<BaseBean<ZhiMaAuthEntity>> queryAuth(@Field("sessionId") String sessionId, @Field("name") String name,
                                                  @Field("idNumber") String idNumber, @Field("phoneNumber") String phoneNumber);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("authentication/get/credit/line")
    Flowable<BaseBean> line(@Field("sessionId") String sessionId);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("bank/bindingBank")
    Flowable<BaseBean<BindCardEntity>> bindingBank(@FieldMap Map<String, String> map);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("bank/queryUserBankCard")
    Flowable<BaseBean<CardManagerEntity>> queryUserBankCard(@Field("sessionId") String sessionId);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("payment/submitRecharge")
    Flowable<BaseBean<RechargeEntity>> submitRecharge(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST("payment/getPayTypeAndBankType")
    Flowable<BaseBean<BankTypeEntity>> getPayTypeAndBankType(@Field("sessionId") String sessionId);

    /**
     * 无效接口
     */
    @GET("loan/getPaymentShopListAll")
    Flowable<BaseBean<List<PaymentShopListEntity>>> getPaymentShopListAll();


    @FormUrlEncoded
    @POST("payment/getPaymentCode")
    Flowable<BaseBean<PaymentData>> getPaymentCode(@Field("sessionId") String sessionId);


    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("users/loanRepaymentRecord")
    Flowable<BaseBean<List<RecordEntity>>> loanRepaymentRecord(@Field("sessionId") String sessionId);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("payment/saveExtendPeriod")
    Flowable<BaseBean> saveExtendPeriod(@Field("sessionId") String sessionId, @Field("extendPeriod") int extendPeriod, @Field("token") String token);


    /**
     * 无效接口
     */

    @FormUrlEncoded
    @POST("payment/submitAuth")
    Flowable<BaseBean<AuthEntity>> submitAuth(@FieldMap Map<String, String> param);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("app/getAppActivity")
    Flowable<BaseBean<GuideEntity>> getAppActivity(@FieldMap Map<String, String> params);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("payment/updateCardAgreeNo")
    Flowable<BaseBean<UpdateAgreeNoEntity>> updateCardAgreeNo(@FieldMap Map<String, String> params);

    /**
     * 无效接口
     */
    @GET
    Flowable<ResponseBody> updateUrl(@Url String fileUrl);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("app/prevention")
    Flowable<BaseBean<PreventionEntity>> prevention(@Field("sessionId") String sessionId);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("authentication/reauthentication")
    Flowable<BaseBean<ReauthenticationEntity>> reauthentication(@Field("sessionId") String sessionId);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("coupon/queryActivity")
    Flowable<BaseBean<List<QueryActivityEntity>>> queryActivity(@Field("sessionId") String sessionId);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("coupon/queryInviteActivity")
    Flowable<BaseBean<QueryInviteActivityEntity>> queryInviteActivity(@Field("sessionId") String sessionId);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("coupon/queryCoupon")
    Flowable<BaseBean<ArrayList<CouponEntity>>> querycoupon(@Field("sessionId") String sessionId);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("payment/preventionForExtend")
    Flowable<BaseBean<PreventionEntity>> preventionForExtend(@Field("sessionId") String sessionId);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("payment/payConfirmSms")
    Flowable<BaseBean> payConfirmSms(@FieldMap Map<String, Object> params);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("payment/paySmSResend")
    Flowable<BaseBean> paySmSResend(@FieldMap Map<String, Object> params);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("bank/bindCardSmSconfirm")
    Flowable<BaseBean> bindCardSmSconfirm(@FieldMap Map<String, Object> params);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("bank/bindCardSmSResend")
    Flowable<BaseBean> bindCardSmSResend(@FieldMap Map<String, Object> params);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("bank/yiBaoBindCard")
    Flowable<BaseBean<YiBaoBindCardEntity>> yiBaoBindCard(@FieldMap Map<String, Object> params);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("zhima/livingidentifysave")
    Flowable<BaseBean> livingidentifysave(@FieldMap Map<String, Object> params);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("zhima/zmScoreSave")
    Flowable<BaseBean> zmScoreSave(@FieldMap Map<String, Object> params);

    /**
     * 无效接口
     */
    @GET("app/getOperatingNotice")
    Flowable<BaseBean> getOperatingNotice();

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("/center/loanRecord")
    Flowable<BaseBean<MyBorrowBean>> loanRecord(@Field("sessionId") String sessionId);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("/auth/freelanceinfo")
    Flowable<BaseBean> freelanceinfo(@FieldMap Map<String, Object> params);

    /**
     * 无效接口
     */
    @Multipart
    @POST("/auth/selfemployinfo")
    Flowable<BaseBean> selfemployinfo(@PartMap() Map<String, RequestBody> params, @Part MultipartBody.Part businessLicense);

    /**
     * 无效接口
     */
    @Multipart
    @POST("/auth/studentinfo")
    Flowable<BaseBean> studentinfo(@PartMap() Map<String, RequestBody> params, @Part MultipartBody.Part studentCard);

    /**
     * 无效接口
     */
    @GET("/auth/getSelfemployInfo")
    Flowable<BaseBean<PrivateOwnerEntity>> getSelfemployInfo(@Query("sessionId") String sessionId);

    /**
     * 无效接口
     */
    @GET("/auth/getFreelanceInfo")
    Flowable<BaseBean<FreelanceEntity>> getFreelanceInfo(@Query("sessionId") String sessionId);

    /**
     * 无效接口
     */
    @GET("/auth/getStudentInfo")
    Flowable<BaseBean<StudentsEntity>> getStudentInfo(@Query("sessionId") String sessionId);

    /**
     * 无效接口
     */

    @GET("auditDeposit/queryRefundInfo")
    Flowable<BaseBean<AuditDepositEntity>> queryAuditDepositRefundInfo(@Query("sessionId") String sessionId);

    /**
     * 无效接口
     */
    @FormUrlEncoded
    @POST("auditDeposit/affirmRefund")
    Flowable<BaseBean> auditDepositAffirmRefund(@Field("sessionId") String sessionId);

    /**
     * 无效接口
     */
    @GET("auth/getOccupation")
    Flowable<BaseBean<ArrayList<PositionEntity>>> getPositionInfo(@Query("sessionId") String sessionId, @Query("pId") long typeId);

}
