package com.mmkjflb.mmloan.viewmodel.emergencycontact;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mmkj.baselibrary.RouteConstant;
import com.mmkj.baselibrary.annotation.ActivityFragmentInject;
import com.mmkj.baselibrary.model.entitry.BaseBean;
import com.mmkj.baselibrary.util.GsonUtils;
import com.mmkj.baselibrary.util.PreferenceUtils;
import com.mmkj.baselibrary.util.RSAHelper;
import com.mmkj.baselibrary.util.RxViewUtil;
import com.mmkj.baselibrary.util.StringUtils;
import com.mmkjflb.mmloan.R;
import com.mmkjflb.mmloan.base.BaseActivity;
import com.mmkjflb.mmloan.databinding.ActivityEmergencycontactBinding;
import com.mmkjflb.mmloan.model.entity.ContactBean;
import com.mmkjflb.mmloan.model.entity.ContactsEntity;
import com.mmkjflb.mmloan.model.http.DataResult;
import com.mmkjflb.mmloan.utils.UiUtils;
import com.mmkjflb.mmloan.viewmodel.idcertification.IdCertificationActivity;
import com.syhmmfqphl.xyxlibrary.utils.JumpManager;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;


/**
 * //联系人信息
 *
 * @author zhangshuai
 */
@Route(path = RouteConstant.EMERGENCY_CONTACT)
@ActivityFragmentInject(contentViewId = R.layout.activity_emergencycontact, toolbarTitle = R.string.contact_information)
public class EmergencyContactActivity extends BaseActivity<EmergencyContactPresenter, ActivityEmergencycontactBinding> implements EmergencyContactContract.View {
    private static final int GOSYSTEMCONTACTS1 = 1;
    private static final int GOSYSTEMCONTACTS2 = 2;
    private String contactName;
    private String contactNumber;
    private List<ContactBean> contactBeanList = null;
    private ContactBean contactEntity;
    private String PhoneOne, NameOne, PhoneTwo, NameTwo;
    private RxPermissions rxPermissions;

    @SuppressLint("CheckResult")
    @Override
    protected void initViews() {
        getActivityComponent().inject(this);
        rxPermissions = new RxPermissions(this);
        mPresenter.getContactsInfo(PreferenceUtils.getUserSessionid());
        initClicks();
        listenEvents();
    }

    @SuppressLint("CheckResult")
    private void initClicks() {
        RxView.clicks(mDataBinding.tvPhoneEmergencyOne).subscribe(o -> {
            jumpContact(GOSYSTEMCONTACTS1);
        });
        RxView.clicks(mDataBinding.tvPhoneEmergencyTwo).subscribe(o -> {
            jumpContact(GOSYSTEMCONTACTS2);
        });
        RxViewUtil.clicks(mDataBinding.btnNext).subscribe(o -> gotoNext());
    }

    @SuppressLint("CheckResult")
    private void jumpContact(int contactType) {
        getEmergencyContact(contactType);
    }

    /**
     * 获取并上传通讯录联系人
     */
    @SuppressLint("CheckResult")
    private void gotoNext() {
        rxPermissions.request(Manifest.permission.READ_CONTACTS).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    getContacts();
                    mPresenter.contacts(PreferenceUtils.getUserSessionid(),
                            RSAHelper.getRSADataubsection(contactBeanList == null ? "" : GsonUtils.createGsonString(contactBeanList)),
                            PhoneOne, StringUtils.filterEmoji(NameOne), PhoneTwo, StringUtils.filterEmoji(NameTwo));
                } else {
                    UiUtils.openSetting(EmergencyContactActivity.this, R.string.open_contact_permission);
                }
            }
        });
    }

    /**
     * 先判断是否提交通讯录，然后去通讯录选择手机号
     */
    @SuppressLint("CheckResult")
    private void getEmergencyContact(int intentcode) {     //判断是否提交过通讯录
        rxPermissions.request(Manifest.permission.READ_CONTACTS).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    getSystemContacts(intentcode);
                } else {
                    UiUtils.openSetting(EmergencyContactActivity.this, R.string.open_contact_permission);
                }
            }
        });
    }

    /**
     * 获取手机通讯录
     */
    private void getContacts() {
        contactBeanList = new ArrayList<>();
        try {
            Cursor c = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
            if (c != null) { // adds data to list
                c.moveToFirst();
                do {
                    contactEntity = new ContactBean();
                    contactName = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    contactNumber = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    if (contactNumber.length() > 6) {
                        contactEntity.setContactName(contactName);
                        contactEntity.setContactPhone(contactNumber);
                        contactBeanList.add(contactEntity);
                    }
                } while (c.moveToNext());
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getSystemContacts(int intentcode) {
        try {
            Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            startActivityForResult(intent, intentcode);

        } catch (Exception e) {
            UiUtils.openSetting(EmergencyContactActivity.this, R.string.open_contact_permission);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != data) {
            switch (requestCode) {
                case GOSYSTEMCONTACTS1:
                    try {
                        Log.i("teshu", "teshu=" + StringUtils.filterEmoji(getPhoneContacts(data.getData())[0]));
                        mDataBinding.etRealnameEmergencyOne.setText(StringUtils.filterEmoji(getPhoneContacts(data.getData())[0]));
                        mDataBinding.tvPhoneEmergencyOne.setText(getPhoneContacts(data.getData())[1]); //获取通讯录用户手机号
                        Editable editableAccount = mDataBinding.etRealnameEmergencyOne.getText();
                        mDataBinding.etRealnameEmergencyOne.setSelection(editableAccount.length());
                        judgeSamePerson();
                    } catch (Exception e) {
                        e.printStackTrace();
                        toast(getResources().getString(R.string.toast_phone_number_is_true));
                    }
                    break;
                case GOSYSTEMCONTACTS2:
                    try {
                        mDataBinding.etRealnameEmergencyTwo.setText(StringUtils.filterEmoji(getPhoneContacts(data.getData())[0]));
                        mDataBinding.tvPhoneEmergencyTwo.setText(getPhoneContacts(data.getData())[1]);
                        Editable editableAccount = mDataBinding.etRealnameEmergencyTwo.getText();
                        mDataBinding.etRealnameEmergencyTwo.setSelection(editableAccount.length());
                        judgeSamePerson();
                    } catch (Exception e) {
                        e.printStackTrace();
                        toast(getResources().getString(R.string.toast_phone_number_is_true));
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void judgeSamePerson() {
        String phone1 = mDataBinding.tvPhoneEmergencyOne.getText().toString();
        String phone2 = mDataBinding.tvPhoneEmergencyTwo.getText().toString();
        if (!TextUtils.isEmpty(phone1) && !TextUtils.isEmpty(phone2) && phone1.equals(phone2)) {
            toast(getResources().getString(R.string.toast_same_person));
            mDataBinding.tvPhoneEmergencyTwo.setText("");
            mDataBinding.etRealnameEmergencyTwo.setText("");
        }
    }

    /**
     * 获取通讯录手机号（获取名字防止后期需要）
     */
    private String[] getPhoneContacts(Uri uri) {
        String[] contact = new String[2];//得到ContentResolver对象
        ContentResolver cr = getContentResolver();//取得电话本中开始一项的光标
        Cursor cursor = cr.query(uri, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();//取得联系人姓名
            int nameFieldColumnIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            contact[0] = cursor.getString(nameFieldColumnIndex);//取得电话号码
            String ContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId, null, null);
            if (phone != null) {
                phone.moveToFirst();
                contact[1] = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            phone.close();
            cursor.close();
        } else {
            return null;
        }
        return contact;
    }


    @SuppressLint("CheckResult")
    private void listenEvents() {                              //.skip(1) 这里不能设置会出现按钮不可点击
        Flowable<CharSequence> observable1 = RxTextView.textChanges(mDataBinding.tvPhoneEmergencyOne).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> observable2 = RxTextView.textChanges(mDataBinding.etRealnameEmergencyOne).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> observable3 = RxTextView.textChanges(mDataBinding.tvPhoneEmergencyTwo).toFlowable(BackpressureStrategy.LATEST);
        Flowable<CharSequence> observable4 = RxTextView.textChanges(mDataBinding.etRealnameEmergencyTwo).toFlowable(BackpressureStrategy.LATEST);
        Flowable.combineLatest(observable1, observable2, observable3, observable4,
                (charSequence1, charSequence2, charSequence3, charSequence4) -> {
                    PhoneOne = charSequence1.toString().trim();
                    NameOne = charSequence2.toString().trim();
                    PhoneTwo = charSequence3.toString().trim();
                    NameTwo = charSequence4.toString().trim();
                    return !TextUtils.isEmpty(charSequence1.toString().trim()) &&
                            !TextUtils.isEmpty(charSequence2.toString().trim()) &&
                            !TextUtils.isEmpty(charSequence3.toString().trim()) &&
                            !TextUtils.isEmpty(charSequence4.toString().trim()) &&
                            !PhoneOne.equals(PhoneTwo);
                }).subscribe(aBoolean -> mDataBinding.btnNext.setEnabled(aBoolean), throwable -> {
        });
    }

    @Override
    public void contactsSuc(BaseBean baseBean) {
        if (DataResult.isSuccessUnToast(this, baseBean)) {
//            JumpManager.jumpToClose(EmergencyContactActivity.this, IdCertificationActivity.class);
            ARouter.getInstance().build(RouteConstant.ID_CERTIFICATION).navigation();finish();
        }
    }

    @Override
    public void getContactsInfoSuc(BaseBean<ContactsEntity> baseBean) {
        if (DataResult.isSuccessUnToast(EmergencyContactActivity.this, baseBean)) {
            ContactsEntity contactsEntity = baseBean.getData();
            if (contactsEntity != null) {
                PhoneOne = contactsEntity.getFirstContactMobile();
                NameOne = contactsEntity.getFirstContactName();

                PhoneTwo = contactsEntity.getSecondContactMobile();
                NameTwo = contactsEntity.getSecondContactName();

                mDataBinding.etRealnameEmergencyOne.setText(contactsEntity.getFirstContactName());
                mDataBinding.tvPhoneEmergencyOne.setText(contactsEntity.getFirstContactMobile());

                mDataBinding.etRealnameEmergencyTwo.setText(contactsEntity.getSecondContactName());
                mDataBinding.tvPhoneEmergencyTwo.setText(contactsEntity.getSecondContactMobile());
            }
        }
    }

//    @Override
//    public void submitCallRecordSuc(BaseBean baseBean, int contactType) {
//        if (DataResult.isSuccessUnToast(this, baseBean)) {
//            getEmergencyContact(contactType);
//        }
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (contactBeanList != null) {
            contactBeanList.clear();
            contactBeanList = null;
        }
    }
}
