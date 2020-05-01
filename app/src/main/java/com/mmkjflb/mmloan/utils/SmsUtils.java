package com.mmkjflb.mmloan.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.mmkj.baselibrary.util.DateUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * User: Lazy_xu
 * Data: 2019/07/25
 * Description:
 */
public class SmsUtils {
    public static boolean isHasSms(Context mContext) {
        Uri SMS_INBOX = Uri.parse("content://sms/");
        ContentResolver cr = mContext.getContentResolver();
        String[] projection = new String[]{"_id", "address", "person", "body", "date", "type"};
        Cursor cur = cr.query(SMS_INBOX, projection, null, null, "date desc");
        return null != cur && cur.getCount() > 0;
    }

    public static List<SmsInform> getSmsList(Context mContext) {
        Uri SMS_INBOX = Uri.parse("content://sms/");
        List<SmsInform> mSmsInformList = new ArrayList<>();
        ContentResolver cr = mContext.getContentResolver();
        String[] projection = new String[]{"_id", "address", "person", "body", "date", "type"};
        //一个星期前
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -7);
        String where = "date > " + calendar.getTime().getTime();
        Cursor cur = cr.query(SMS_INBOX, projection, where, null, "date desc");
        if (null != cur && cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String number = cur.getString(cur.getColumnIndex("address"));//手机号
                String name = cur.getString(cur.getColumnIndex("person"));//联系人姓名列表
                String body = cur.getString(cur.getColumnIndex("body"));//短信内容
                long date = cur.getLong(cur.getColumnIndex("date"));//时间
                String type = cur.getString(cur.getColumnIndex("type"));//短信类型1是接收到的，2是已发出
                //做过滤操作
//            Pattern pattern = Pattern.compile(" [a-zA-Z0-9]{10}");
//            Matcher matcher = pattern.matcher(body);
//            if (matcher.find()) {
//                String res = matcher.group().substring(1, 11);
//            }
                SmsUtils.SmsInform smsInform = new SmsUtils.SmsInform();
                smsInform.setAddress(number);
                smsInform.setPerson(name);
                smsInform.setBody(body);
                smsInform.setDate(DateUtils.getYMDHMSTime(date));
                smsInform.setType(type);
                mSmsInformList.add(smsInform);
            }
            cur.close();
        }
        return mSmsInformList;
    }

    public static class SmsInform implements Serializable {
        private String address;
        private String person;
        private String body;

        @Override
        public String toString() {
            return "SmsInform{" +
                    "address='" + address + '\'' +
                    ", person='" + person + '\'' +
                    ", body='" + body + '\'' +
                    ", date='" + date + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }

        private String date;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPerson() {
            return person;
        }

        public void setPerson(String person) {
            this.person = person;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        private String type;


        public SmsInform() {
        }
    }
}
