package com.mmkjflb.mmloan.model.http;

import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.model.entitry.GCashDetailBean;
import com.mmkj.baselibrary.model.entitry.WithdrawalNumberEntity;
import com.mmkj.baselibrary.util.RxUtil;
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
import com.mmkjflb.mmloan.model.http.api.ApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;


public class RetrofitHelper implements HttpHelper {

    private ApiService mApiHelper;


    @Inject
    public RetrofitHelper(ApiService apiHelper) {
        this.mApiHelper = apiHelper;
    }

    @Override
    public Flowable<BaseBean<ChannelGoogleEntity>> insertChannelGoogle(Map<String, Object> map) {
        return mApiHelper.insertChannelGoogle(map).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<AmountTypeEntity>> getAmountType(String sessionId) {
        return mApiHelper.getAmountType(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> smsInfos(String sessionId, RequestBody requestBody) {
        return mApiHelper.smsInfos(sessionId, requestBody).compose(RxUtil.rxSchedulerHelper());
    }
    @Override
    public Flowable<BaseBean> updateProductCode(String sessionId, int productType) {
        return mApiHelper.updateProductCode(sessionId, productType).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> afterBorrowCheck(Map<String, Object> map) {
        return mApiHelper.afterBorrowCheck(map).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<String>> getQiniuToken(String sessionId) {
        return mApiHelper.getQiniuToken(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> confirmIncreaseCashDeposit(String productCode,String sessionId) {
        return mApiHelper.confirmIncreaseCashDeposit(productCode,sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<CashDepositInfoEntity>> getDepositInfo(String sessionId) {
        return mApiHelper.getDepositInfo(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> saveOcrScore(Map<String, Object> map) {
        return mApiHelper.saveOcrScore(map).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<IdCardCheckEntity>> idCardCheck(Map<String, RequestBody> partMap, MultipartBody.Part multipartPart) {
        return mApiHelper.idCardCheck(partMap, multipartPart).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<WithdrawalNumberEntity>> getWithdrawalNumber(String sessionId) {
        return mApiHelper.getWithdrawalNumber(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<Map<String, Object>>> getWithdrawChannel() {
        return mApiHelper.getWithdrawChannel().compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> renewalMobilePhone(Map<String, Object> map) {
        return mApiHelper.renewalMobilePhone(map).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> checkUserInfo(Map<String, Object> map) {
        return mApiHelper.checkUserInfo(map).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<IsPaidEntity>> isPaid(String sessionId) {
        return mApiHelper.isPaid(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<CollectionAccountEntity>> collectionAccount(String sessionId) {
        return mApiHelper.collectionAccount(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<SpecialMenuEntity>> specialMenu(String sessionId) {
        return mApiHelper.specialMenu(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<ApplyForPromoteEntity>> applyForPromote(String sessionId) {
        return mApiHelper.applyForPromote(sessionId).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean> submintDevice(RequestBody requestBody) {
        return mApiHelper.submintDevice(requestBody).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<QueryProductInfoEntity>> queryProductInfo() {
        return mApiHelper.queryProductInfo().compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<CertificateDetailEntity>> certificateDetail(String sessionId, int bidId, int billId) {
        return mApiHelper.certificateDetail(sessionId, bidId, billId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<QueryRefundInfoEntity>> queryRefundInfo(String sessionId) {
        return mApiHelper.queryRefundInfo(sessionId).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<List<CustomerServiceData>>> getCustomerService() {
        return mApiHelper.getCustomerService().compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<ExtraCreditBean>> getExtraCredit(String sessionId) {
        return mApiHelper.getExtraCredit(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> submitReportToken(String sessionId, String reportTaskToken, String website) {
        return mApiHelper.submitReportToken(sessionId, reportTaskToken, website).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> submitCallRecord(String sessionId, String records) {
        return mApiHelper.submitCallRecord(sessionId, records).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<GCashDetailBean>> getGCashDetail(String sessionId) {
        return mApiHelper.getGCashDetail(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> updateGCash(String sessionId, String userGCashAccount, String captcha) {
        return mApiHelper.updateGCash(sessionId, userGCashAccount, captcha).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> affirmRefund(Map<String, Object> map) {
        return mApiHelper.affirmRefund(map).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean> generateCashDepositVaOrOtc(Map<String, Object> map) {
        return mApiHelper.generateCashDepositVaOrOtc(map).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> generateAuditDepositVaOrOtc(Map<String, Object> map) {
        return mApiHelper.generateAuditDepositVaOrOtc(map).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<PaymentForFasPayEntity>> paymentForFasPay(Map<String, Object> map) {
        return mApiHelper.paymentForFasPay(map).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<List<QueryPaymentChannelEntity>>> queryPaymentChannel(String sessionId) {
        return mApiHelper.queryPaymentChannel(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<AdviseEntity> getGoogleAdvise(Map<String, Object> options) {
        return mApiHelper.getGoogleAdvise(options).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<BankInfoEntity>> getBankInfo(String sessionId) {
        return mApiHelper.getBankInfo(sessionId).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<AssertsInfoEntity>> getAssertsInfo(String sessionId) {
        return mApiHelper.getAssertsInfo(sessionId).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<SocialInfoEntity>> getSocialInfo(String sessionId) {
        return mApiHelper.getSocialInfo(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<BaseInfoEntity>> getBaseInfo(String sessionId) {
        return mApiHelper.getBaseInfo(sessionId).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<NoticeEntity>> getOperatingNotice(String noticeCode) {
        return mApiHelper.getOperatingNotice(noticeCode).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean> sendSms(String mobile, String smsCode) {
        return mApiHelper.sendSms(mobile, smsCode).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<ResponseBody> getVerifycode(String mobile) {
        return mApiHelper.getVerifycode(mobile).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> register(Map<String, Object> map) {
        return mApiHelper.register(map).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<List<BankListEntity>>> bankList(String sessionId) {
        return mApiHelper.bankList(sessionId).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean> bankinfo(Map<String, Object> params) {
        return mApiHelper.bankinfo(params).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean> idcheck(Map<String, Object> partMap) {
        return mApiHelper.idcheck(partMap).compose(RxUtil.rxSchedulerHelper());
    }


    @Override
    public Flowable<BaseBean> baseinfo(Map<String, Object> map) {
        return mApiHelper.baseinfo(map).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean> certificateUpload(Map<String, Object> map) {
        return mApiHelper.certificateUpload(map).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean> socialinfo(Map<String, Object> map) {
        return mApiHelper.socialinfo(map).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean> assertsinfo(Map<String, Object> map) {
        return mApiHelper.assertsinfo(map).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<List<IslandProvinceCityEntity>>> getIslandsInfo(String sessionId) {
        return mApiHelper.getIslandsInfo(sessionId).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<LoginBean>> login(String mobile, String password) {
        return mApiHelper.login(mobile, password).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean> forgotPwd(Map<String, String> map) {
        return mApiHelper.forgotPwd(map).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean> applyLoan(Map<String, Object> map) {
        return mApiHelper.applyLoan(map).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> contacts(String sessionId, String contactsList, String firstContactMobile, String firstContactName, String secondContactMobile, String secondContactName) {
        return mApiHelper.contacts(sessionId, contactsList, firstContactMobile, firstContactName, secondContactMobile, secondContactName).compose(RxUtil.rxSchedulerHelper());
    }


    @Override
    public Flowable<BaseBean> phoneCaptcha(String sessionId, String mobile, String moblieServicePassword, String captcha) {
        return mApiHelper.phoneCaptcha(sessionId, mobile, moblieServicePassword, captcha).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<StringEntity>> initcompare(String sessionId, String name, String idNumber, String phoneNumber) {
        return mApiHelper.initcompare(sessionId, name, idNumber, phoneNumber).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<ZhiMaResultEntity>> queryZhiMaResult(String sessionId, String name, String idNumber, String phoneNumber) {
        return mApiHelper.queryZhiMaResult(sessionId, name, idNumber, phoneNumber).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<AuthentStatusEntity>> authentStatus(String sessionId) {
        return mApiHelper.authentStatus(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> batchUpload(RequestBody sessionId, MultipartBody.Part front, MultipartBody.Part back) {
        return mApiHelper.batchUpload(sessionId, front, back).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<OcrCheckEntity>> userOcrCheck(String sessionId) {
        return mApiHelper.userOcrCheck(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<BooleanEntity>> checkRealName(String sessionId, String name, String idNumber) {
        return mApiHelper.checkRealName(sessionId, name, idNumber).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> userBaseInfo(Map<String, RequestBody> partMap, MultipartBody.Part photo) {
        return mApiHelper.userBaseInfo(partMap, photo).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<StringEntity>> auth(String sessionId, String type, String name, String idNumber, String mobileNo) {
        return mApiHelper.auth(sessionId, type, name, idNumber, mobileNo).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<ZhiMaAuthEntity>> queryAuth(String sessionId, String name, String idNumber, String phoneNumber) {
        return mApiHelper.queryAuth(sessionId, name, idNumber, phoneNumber).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> line(String sessionId) {
        return mApiHelper.line(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> deviceInfo(Map<String, String> map) {
        return mApiHelper.deviceInfo(map).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<ArtificialAuthentStatusEntity>> queryCredit( String sessionId) {
        return mApiHelper.queryCredit( sessionId).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<List<ProductInfoBean>>> getProductInfo(String sessionId, int type) {
        return mApiHelper.getProductInfo(sessionId, type).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<BindCardEntity>> bindingBank(Map<String, String> map) {
        return mApiHelper.bindingBank(map).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<CardManagerEntity>> queryUserBankCard(String sessionId) {
        return mApiHelper.queryUserBankCard(sessionId).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<RechargeEntity>> submitRecharge(Map<String, String> map) {
        return mApiHelper.submitRecharge(map).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<MainEntity>> balance(String sessionId) {
        return mApiHelper.balance(sessionId).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<List<LoanEntity>>> loanInfo(String sessionId) {
        return mApiHelper.loanInfo(sessionId).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<List<PaymentShopListEntity>>> getPaymentShopListAll() {
        return mApiHelper.getPaymentShopListAll().compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<SurelyEntity>> surely(Map<String, Object> params) {
        return mApiHelper.surely(params).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean> applySuggest(Map<String, String> params) {
        return mApiHelper.applySuggest(params).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean> updatePwd(Map<String, String> params) {
        return mApiHelper.updatePwd(params).compose(RxUtil.rxSchedulerHelper());

    }


    @Override
    public Flowable<BaseBean<List<RecordEntity>>> loanRepaymentRecord(String sessionId) {
        return mApiHelper.loanRepaymentRecord(sessionId).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<RepayEntity>> queryBillsInfo(String sessionId) {
        return mApiHelper.queryBillsInfo(sessionId).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<List<Integer>>> getRepaymentShop() {
        return mApiHelper.getRepaymentShop().compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<RepaymentShopEntity>> getRepaymentShopNew(String sessionId) {
        return mApiHelper.getRepaymentShopNew(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<BankTypeEntity>> getPayTypeAndBankType(String sessionId) {
        return mApiHelper.getPayTypeAndBankType(sessionId).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean> submitActiveRepay(Map<String, Object> params) {
        return mApiHelper.submitActiveRepay(params).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean> updateRepayChannel(Map<String, Object> params) {
        return mApiHelper.updateRepayChannel(params).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<PaymentData>> getPaymentCode(String sessionId) {
        return mApiHelper.getPaymentCode(sessionId).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<AuthEntity>> submitAuth(Map<String, String> params) {
        return mApiHelper.submitAuth(params).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<UpVersionBean>> getAppVersion(String sessionId, String packageType) {
        return mApiHelper.getAppVersion(sessionId, packageType).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<GuideEntity>> getAppActivity(Map<String, String> params) {
        return mApiHelper.getAppActivity(params).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<ResponseBody> updateUrl(String fileUrl) {
        return mApiHelper.updateUrl(fileUrl).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<UpdateAgreeNoEntity>> updateCardAgreeNo(Map<String, String> params) {
        return mApiHelper.updateCardAgreeNo(params).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<PreventionEntity>> prevention(String sessionId) {
        return mApiHelper.prevention(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<ReauthenticationEntity>> reauthentication(String sessionId) {
        return mApiHelper.reauthentication(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> saveExtendPeriod(String sessionId, int extendPeriod, String token) {
        return mApiHelper.saveExtendPeriod(sessionId, extendPeriod, token).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<List<QueryActivityEntity>>> queryActivity(String sessionId) {
        return mApiHelper.queryActivity(sessionId).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<QueryInviteActivityEntity>> queryInviteActivity(String sessionId) {
        return mApiHelper.queryInviteActivity(sessionId).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<ArrayList<CouponEntity>>> querycoupon(String sessionId) {
        return mApiHelper.querycoupon(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<ArrayList<PositionEntity>>> queryPosition(String sessionId, long typeId) {
        return mApiHelper.getPositionInfo(sessionId, typeId).compose(RxUtil.rxSchedulerHelper());
    }


    @Override
    public Flowable<BaseBean<PreventionEntity>> preventionForExtend(String sessionId) {
        return mApiHelper.preventionForExtend(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> payConfirmSms(Map<String, Object> params) {
        return mApiHelper.payConfirmSms(params).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> paySmSResend(Map<String, Object> params) {
        return mApiHelper.paySmSResend(params).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> bindCardSmSconfirm(Map<String, Object> params) {
        return mApiHelper.bindCardSmSconfirm(params).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> bindCardSmSResend(Map<String, Object> params) {
        return mApiHelper.bindCardSmSResend(params).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<YiBaoBindCardEntity>> yiBaoBindCard(Map<String, Object> params) {
        return mApiHelper.yiBaoBindCard(params).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean> livingidentifysave(Map<String, Object> params) {
        return mApiHelper.livingidentifysave(params).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean> zmScoreSave(Map<String, Object> params) {
        return mApiHelper.zmScoreSave(params).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean> getOperatingNotice() {
        return mApiHelper.getOperatingNotice().compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<MyBorrowBean>> loanRecord(String sessionId) {
        return mApiHelper.loanRecord(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> freelanceinfo(Map<String, Object> params) {
        return mApiHelper.freelanceinfo(params).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> salariesinfo(Map<String, Object> params) {
        return mApiHelper.salariesinfo(params).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<List<CompanyNatureEntity>>> getCompanyNature() {
        return mApiHelper.getCompanyNature().compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> selfemployinfo(Map<String, RequestBody> params, MultipartBody.Part businessLicense) {
        return mApiHelper.selfemployinfo(params, businessLicense).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> studentinfo(Map<String, RequestBody> params, MultipartBody.Part studentCard) {
        return mApiHelper.studentinfo(params, studentCard).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<ContactsEntity>> getContactsInfo(String sessionId) {
        return mApiHelper.getContactsInfo(sessionId).compose(RxUtil.rxSchedulerHelper());

    }

    @Override
    public Flowable<BaseBean<SalariesEntity>> getSalariesInfo(String sessionId) {
        return mApiHelper.getSalariesInfo(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<PrivateOwnerEntity>> getSelfemployInfo(String sessionId) {
        return mApiHelper.getSelfemployInfo(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<FreelanceEntity>> getFreelanceInfo(String sessionId) {
        return mApiHelper.getFreelanceInfo(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<StudentsEntity>> getStudentInfo(String sessionId) {
        return mApiHelper.getStudentInfo(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> rollbackStatus(String sessionId) {
        return mApiHelper.rollbackStatus(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<AuditDepositEntity>> queryAuditDepositRefundInfo(@Field("sessionId") String sessionId) {
        return mApiHelper.queryAuditDepositRefundInfo(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

//	@Override
//	public Flowable<BaseBean<CreditManagerEntity>> queryCreditManager(String sessionId) {
//		return ;
//	}

    @Override
    public Flowable<BaseBean<RefundDepositEntity>> refundDeposit(String sessionId, String withdrawChannel) {
        return mApiHelper.refundDeposit(sessionId, withdrawChannel).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<RefundInfoEntity>> getRefundInfo(String sessionId) {
        return mApiHelper.getRefundInfo(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<RefundCodeEntity>> getRefundCode(String sessionId) {
        return mApiHelper.getRefundCode(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean> auditDepositAffirmRefund(@Field("sessionId") String sessionId) {
        return mApiHelper.auditDepositAffirmRefund(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<DepositCodeEntity>> getDepositCode(RequestBody sessionId) {
        return mApiHelper.getDepositCode(sessionId).compose(RxUtil.rxSchedulerHelper());
    }

    @Override
    public Flowable<BaseBean<DepositCodeEntity>> queryDepositCode(String sessionId) {
        return mApiHelper.queryDepositCode(sessionId).compose(RxUtil.rxSchedulerHelper());
    }
}
