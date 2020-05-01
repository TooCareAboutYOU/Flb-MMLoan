package com.mmkjflb.mmloan.model.entity.juxinli;

import java.io.Serializable;


public class ReportTokenBean implements Serializable {

    /**
     * code : 20000
     * data : {"report_task_token":"baad705c30844d099e07a1cc5631cce8"}
     * message : Invoke api successful
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * report_task_token : baad705c30844d099e07a1cc5631cce8
         */

        private String report_task_token;

        public String getReport_task_token() {
            return report_task_token;
        }

        public void setReport_task_token(String report_task_token) {
            this.report_task_token = report_task_token;
        }
    }
}
