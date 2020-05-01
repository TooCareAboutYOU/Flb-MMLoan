package com.mmkjflb.mmloan.viewmodel.fragment.student;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RxBus;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.app.BaseApplication;
import com.mmkjflb.mmloan.app.Constants;
import com.mmkjflb.mmloan.app.PermissionGroup;
import com.mmkjflb.mmloan.base.BaseFragment;
import com.mmkjflb.mmloan.databinding.FragmentStudentBinding;
import com.mmkjflb.mmloan.model.entity.ClickEvent;
import com.mmkjflb.mmloan.model.entity.StudentsEntity;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.mmkjflb.mmloan.utils.UiUtils;
import com.mmkjflb.mmloan.viewmodel.extracredit.ExtraCreditActivity;
import com.syhmmfqphl.xyxlibrary.utils.JumpManager;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.nereo.multi_image_selector.MultiImageSelector;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by
 * Date: On 2018/3/5
 * Author: wangjieyan
 * Email: wangjieyan9308@163.com
 */
@ActivityFragmentInject(contentViewId = R.layout.fragment_student)
public class StudentsFragment extends BaseFragment<StudentsPresenter, FragmentStudentBinding> implements StudentsContract.View {
    //school name
    private String schoolName = "";
    //student number
    private String studentNumber = "";
    private RxPermissions rxPermissions;
    private File imageurl = null;
    private Disposable disposable;

    @Override
    protected void initViews() {
        getFragmentComponent().inject(this);
        rxPermissions = new RxPermissions(getActivity());
//        initData();
        listenEditTextEvents();
        initClick();
    }


    //初始化回填数据
    private void initData() {
        mPresenter.getStudentInfo(PreferenceUtils.getString(PreferenceUtils.USER_SESSIONID, ""));
    }

    @SuppressLint("CheckResult")
    private void initClick() {
        //注册监听者
        RxBus.getDefault().toObservable(ClickEvent.class)
                .observeOn(AndroidSchedulers.mainThread()).subscribe(performance -> {
            if (performance.getStatus() == 3) {
                Flowable.fromArray(imageurl)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(file -> {
                            RequestBody faceRequestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
                            MultipartBody.Part part = MultipartBody.Part.createFormData("studentCard", "studentCard" + System.currentTimeMillis() + ".jpg", faceRequestBody);
                            schoolName = mDataBinding.edSchoolName.getText().toString().trim();
                            studentNumber = mDataBinding.edStudentNumber.getText().toString().trim();
                            Map<String, RequestBody> request = new HashMap<>();
                            request.put("sessionId", RequestBody.create(null, PreferenceUtils.getString(PreferenceUtils.USER_SESSIONID, "")));
                            request.put("performance", RequestBody.create(null, String.valueOf(performance.getStatus())));
                            request.put("school", RequestBody.create(null, schoolName));
                            request.put("scholar", RequestBody.create(null, studentNumber));
                            mPresenter.studentinfo(request, part);
                        }, throwable -> toast(R.string.img_upload_error));
            }

            if (performance.getType() == 3 && performance.getUrl() != null) {
                imageurl = performance.getUrl();
                Glide.with(BaseApplication.getInstance()).load(imageurl).placeholder(R.drawable.data_update_bg).error(R.drawable.data_update_bg)
                        .format(DecodeFormat.PREFER_ARGB_8888).into(mDataBinding.ivDataUpdate);
                mDataBinding.tvDataUpdateHint.setVisibility(View.GONE);
            }
        });

        RxViewUtil.clicks(mDataBinding.ivDataUpdate).subscribe(o -> rxPermissions.request(PermissionGroup.PERMISSION_PHOTO)
                .subscribe(granted -> {
                    if (granted) {
                        //添加图片
                        if (disposable != null) {
                            disposable.dispose();
                        }
                        MultiImageSelector selector = MultiImageSelector.create();
                        selector.showCamera(true);
                        selector.single();
                        selector.start(getActivity(), 2);
                    } else {
                        UiUtils.openSetting(getActivity(), R.string.open_camera_file_permission);
                    }
                }, throwable -> {
                }));
    }

    private void listenEditTextEvents() {
        Flowable<CharSequence> repaymentSourceObservable = RxTextView.textChanges(mDataBinding.edSchoolName).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> monthIncomeObservable = RxTextView.textChanges(mDataBinding.edStudentNumber).toFlowable(BackpressureStrategy.LATEST);
        Flowable.combineLatest(repaymentSourceObservable, monthIncomeObservable,
                (schoolName, studentNumber) -> {
                    Constants.STUDENTETLISTENONE = !TextUtils.isEmpty(schoolName.toString().trim()) && !TextUtils.isEmpty(studentNumber.toString().trim());
                    return Constants.STUDENTETLISTENONE && Constants.STUDENTSELECTIMAGE;
                }).subscribe(aBoolean -> {
//			if (aBoolean) {
//				com.hwangjr.rxbus.RxBus.get().post(EventConstants.INFORM_BTN_CHANGE, 1);
//			} else {
//				com.hwangjr.rxbus.RxBus.get().post(EventConstants.INFORM_BTN_CHANGE, 0);
//			}
        }, throwable -> {

        });
    }

    @Override
    public void studentinfoSuc(BaseBean baseBean) {
        if (DataResult.isSuccessToast(getActivity(), baseBean)) {
//            getActivity().setResult(Activity.RESULT_OK);
//            getActivity().finish();
//            JumpManager.jumpToClose(getActivity(), ExtraCreditActivity.class);
            ARouter.getInstance().build( RouteConstant.EXTRA_CREDIT).navigation();getActivity().finish();

        }
    }

    @Override
    public void getStudentInfoSuc(BaseBean<StudentsEntity> baseBean) {
        if (DataResult.isSuccessUnToast(getActivity(), baseBean)) {
            if (baseBean.getData() != null) {
                mDataBinding.edSchoolName.setText(baseBean.getData().getSchool());
                mDataBinding.edStudentNumber.setText(baseBean.getData().getScholar());
                disposable = Flowable.just(baseBean.getData().getStudentCard())
                        .subscribeOn(Schedulers.io())
                        .map(url -> Glide.with(this).download(url).submit().get())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(file -> {
                            imageurl = file;
                            Glide.with(BaseApplication.getInstance()).load(imageurl).placeholder(R.drawable.data_update_bg).error(R.drawable.data_update_bg)
                                    .format(DecodeFormat.PREFER_ARGB_8888).into(mDataBinding.ivDataUpdate);
                            mDataBinding.tvDataUpdateHint.setVisibility(View.GONE);
                        }, throwable -> {
                        });
            }
        }
    }
}
