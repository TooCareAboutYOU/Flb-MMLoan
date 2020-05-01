package com.mmkjflb.mmloan.model.entity.juxinli;

import java.io.Serializable;
import java.util.List;


public class AccountStatusBean implements Serializable {

    /**
     * code : 20000
     * data : {"websites":[{"auth_token":"dbc5ef96a44b4555a6ae02e6bf3e9db5","category":"sns","running_status":"FINISHED","update_time":1558440728000,"website":"facebook"},{"auth_token":"40bb28bc261e42d8af164c9f32022bcb","category":"rides","running_status":"FINISHED","update_time":1555662600000,"website":"gojek"}],"report_task_status":"UNINITIALIZED"}
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
         * websites : [{"auth_token":"dbc5ef96a44b4555a6ae02e6bf3e9db5","category":"sns","running_status":"FINISHED","update_time":1558440728000,"website":"facebook"},{"auth_token":"40bb28bc261e42d8af164c9f32022bcb","category":"rides","running_status":"FINISHED","update_time":1555662600000,"website":"gojek"}]
         * report_task_status : UNINITIALIZED
         */

        private String report_task_status;
        private List<WebsitesBean> websites;

        public String getReport_task_status() {
            return report_task_status;
        }

        public void setReport_task_status(String report_task_status) {
            this.report_task_status = report_task_status;
        }

        public List<WebsitesBean> getWebsites() {
            return websites;
        }

        public void setWebsites(List<WebsitesBean> websites) {
            this.websites = websites;
        }

        public static class WebsitesBean {
            /**
             * auth_token : dbc5ef96a44b4555a6ae02e6bf3e9db5
             * category : sns
             * running_status : FINISHED
             * update_time : 1558440728000
             * website : facebook
             */

            private String auth_token;
            private String category;
            private String running_status;
            private long update_time;
            private String website;

            public String getAuth_token() {
                return auth_token;
            }

            public void setAuth_token(String auth_token) {
                this.auth_token = auth_token;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getRunning_status() {
                return running_status;
            }

            public void setRunning_status(String running_status) {
                this.running_status = running_status;
            }

            public long getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(long update_time) {
                this.update_time = update_time;
            }

            public String getWebsite() {
                return website;
            }

            public void setWebsite(String website) {
                this.website = website;
            }
        }
    }
}
