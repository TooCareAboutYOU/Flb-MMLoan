package com.mmkjflb.mmloan.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.provider.CallLog;

import com.mmkj.baselibrary.util.DateUtils;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author zhangshuai
 * 通话记录
 */
public class CallRecordsUtil {
    public static boolean isHasCallRecord(Activity mContext) {
        Cursor cursor = mContext.getContentResolver().query(CallLog.Calls.CONTENT_URI, new String[]{
                CallLog.Calls.CACHED_NAME// 通话记录的联系人
                , CallLog.Calls.NUMBER// 通话记录的电话号码
                , CallLog.Calls.DATE// 通话记录的日期
                , CallLog.Calls.DURATION// 通话时长
                , CallLog.Calls.TYPE}, null, null, CallLog.Calls.DEFAULT_SORT_ORDER);
        return cursor != null && cursor.getCount() > 0;
    }

    public static List<CallLogInfo> getCallHistoryList(Activity mContext) {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(mContext, new String[]{Manifest.permission.READ_CALL_LOG}, 1000);
        }
        List<CallLogInfo> mLogInfoList = new ArrayList<>();
        //三个月前
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -3);
        String where = "date > " + calendar.getTime().getTime();
        Cursor cursor = mContext.getContentResolver().query(CallLog.Calls.CONTENT_URI, new String[]{
                CallLog.Calls.CACHED_NAME// 通话记录的联系人
                , CallLog.Calls.NUMBER// 通话记录的电话号码
                , CallLog.Calls.DATE// 通话记录的日期
                , CallLog.Calls.DURATION// 通话时长
                , CallLog.Calls.TYPE}, where, null, CallLog.Calls.DEFAULT_SORT_ORDER);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
                String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                int duration = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.DURATION));
                long dateLong = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE));
                String date = DateUtils.getYMDHMSTime(dateLong);
                int type = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));
//                String typeString = "";
//                switch (type) {
//                    case CallLog.Calls.INCOMING_TYPE:
//                        typeString = "打入";
//                        break;
//                    case CallLog.Calls.OUTGOING_TYPE:
//                        typeString = "打出";
//                        break;
//                    case CallLog.Calls.MISSED_TYPE:
//                        typeString = "未接";
//                        break;
//                    default:
//                        break;
//                }
                CallLogInfo callLogInfo = new CallLogInfo();
                callLogInfo.setName(name);
                callLogInfo.setNumber(number);
                callLogInfo.setDate(date);
                callLogInfo.setType(type);
                callLogInfo.setDuration(duration);
                mLogInfoList.add(callLogInfo);
            }
            cursor.close();
        }
        return mLogInfoList;
    }

    public static class CallLogInfo implements Serializable {
        //联系人
        private String name;
        //手机号
        private String number;
        //通话时间
        private String date;
        //通话类型 1：来电，2：去电，3：未接, 4: 语音通话，5：直接拒绝，，6：未拨通，7：呼叫转移
        private int type;
        //通话时长
        private int duration;

        public CallLogInfo() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"name\":\"")
                    .append(name).append('\"');
            sb.append(",\"number\":\"")
                    .append(number).append('\"');
            sb.append(",\"date\":\"")
                    .append(date).append('\"');
            sb.append(",\"type\":")
                    .append(type);
            sb.append(",\"duration\":")
                    .append(duration);
            sb.append('}');
            return sb.toString();
        }
    }
}
