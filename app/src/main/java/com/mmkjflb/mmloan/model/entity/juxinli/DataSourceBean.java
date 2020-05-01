package com.mmkjflb.mmloan.model.entity.juxinli;

import java.io.Serializable;
import java.util.List;

public class DataSourceBean implements Serializable {


    /**
     * code : 20000
     * data : {"websites":{"insurance":[{"support_authorize_type":["CRAWLER"],"website":"bpjstku","authorize_type":"CRAWLER","title":"BPJS-TKU","icon_name":"icon-BPJS-Employment","status":"ONLINE"},{"support_authorize_type":["CRAWLER"],"website":"bpjshealth","authorize_type":"CRAWLER","title":"BPJS-Kesehatan","icon_name":"icon-BPJS-Kesehatan","status":"ONLINE"}],"im":[{"support_authorize_type":["CRAWLER"],"website":"yogrt","authorize_type":"CRAWLER","title":"Yogrt","icon_name":"icon-Yogrt","status":"ONLINE"}],"ECommerce":[{"support_authorize_type":["CRAWLER"],"website":"lazadaid","authorize_type":"CRAWLER","title":"LazadaID","icon_name":"icon-Lazada","status":"ONLINE"},{"support_authorize_type":["CRAWLER"],"website":"tokopedia","authorize_type":"CRAWLER","title":"Tokopedia","icon_name":"icon-ss-fuben","status":"ONLINE"},{"support_authorize_type":["CRAWLER"],"website":"shopee","authorize_type":"CRAWLER","title":"Shopee","icon_name":"icon-Shopee","status":"ONLINE"},{"support_authorize_type":["CRAWLER"],"website":"jdshop","authorize_type":"CRAWLER","title":"Jd ID","icon_name":"icon-JDid","status":"ONLINE"}],"EPayment":[],"rides":[{"support_authorize_type":["CRAWLER"],"website":"gojek","authorize_type":"CRAWLER","title":"Gojek","icon_name":"icon-Gojek","status":"ONLINE"},{"support_authorize_type":["NATIVE"],"website":"grab","authorize_type":"NATIVE","title":"Grab","icon_name":"icon-Grab","status":"ONLINE"}],"utilities":[],"life":[{"support_authorize_type":["CRAWLER"],"website":"golife","authorize_type":"CRAWLER","title":"Golife","icon_name":"icon-Golife","status":"ONLINE"}],"bank":[{"support_authorize_type":["NATIVE","CRAWLER"],"website":"bcabank","authorize_type":"CRAWLER","title":"BCA","icon_name":"icon-BCA","status":"ONLINE"},{"support_authorize_type":["CRAWLER"],"website":"bribank","authorize_type":"CRAWLER","title":"BRI","icon_name":"icon-BRI","status":"ONLINE"},{"support_authorize_type":["CRAWLER"],"website":"bnibank","authorize_type":"CRAWLER","title":"BNI","icon_name":"icon-BNI","status":"ONLINE"}],"identity":[{"support_authorize_type":["CRAWLER"],"website":"npwp","authorize_type":"CRAWLER","title":"NPWP","icon_name":"icon-Identity1","status":"ONLINE"},{"support_authorize_type":["CRAWLER"],"website":"ktp","authorize_type":"CRAWLER","title":"KTP","icon_name":"icon-KTP","status":"ONLINE"},{"support_authorize_type":["CRAWLER"],"website":"telkomselVerify","authorize_type":"CRAWLER","title":"Register Check","icon_name":"icon-shouquan","status":"ONLINE"}],"sns":[{"support_authorize_type":["OAUTH"],"website":"facebook","authorize_type":"OAUTH","title":"Facebook","icon_name":"icon-facebookf","status":"ONLINE"}],"telecom":[{"support_authorize_type":["CRAWLER"],"website":"telkomsel","authorize_type":"CRAWLER","title":"Telkomsel","icon_name":"icon-Telkomsel","status":"ONLINE"},{"support_authorize_type":["CRAWLER"],"website":"xl","authorize_type":"CRAWLER","title":"XL Axiata","icon_name":"icon-XL","status":"ONLINE"}],"payment":[],"job":[{"support_authorize_type":["CRAWLER"],"website":"linkedin","authorize_type":"CRAWLER","title":"LinkedIn","icon_name":"icon-linkedin","status":"ONLINE"}],"travel":[],"IDW":[]},"language":"ID"}
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
         * websites : {"insurance":[{"support_authorize_type":["CRAWLER"],"website":"bpjstku","authorize_type":"CRAWLER","title":"BPJS-TKU","icon_name":"icon-BPJS-Employment","status":"ONLINE"},{"support_authorize_type":["CRAWLER"],"website":"bpjshealth","authorize_type":"CRAWLER","title":"BPJS-Kesehatan","icon_name":"icon-BPJS-Kesehatan","status":"ONLINE"}],"im":[{"support_authorize_type":["CRAWLER"],"website":"yogrt","authorize_type":"CRAWLER","title":"Yogrt","icon_name":"icon-Yogrt","status":"ONLINE"}],"ECommerce":[{"support_authorize_type":["CRAWLER"],"website":"lazadaid","authorize_type":"CRAWLER","title":"LazadaID","icon_name":"icon-Lazada","status":"ONLINE"},{"support_authorize_type":["CRAWLER"],"website":"tokopedia","authorize_type":"CRAWLER","title":"Tokopedia","icon_name":"icon-ss-fuben","status":"ONLINE"},{"support_authorize_type":["CRAWLER"],"website":"shopee","authorize_type":"CRAWLER","title":"Shopee","icon_name":"icon-Shopee","status":"ONLINE"},{"support_authorize_type":["CRAWLER"],"website":"jdshop","authorize_type":"CRAWLER","title":"Jd ID","icon_name":"icon-JDid","status":"ONLINE"}],"EPayment":[],"rides":[{"support_authorize_type":["CRAWLER"],"website":"gojek","authorize_type":"CRAWLER","title":"Gojek","icon_name":"icon-Gojek","status":"ONLINE"},{"support_authorize_type":["NATIVE"],"website":"grab","authorize_type":"NATIVE","title":"Grab","icon_name":"icon-Grab","status":"ONLINE"}],"utilities":[],"life":[{"support_authorize_type":["CRAWLER"],"website":"golife","authorize_type":"CRAWLER","title":"Golife","icon_name":"icon-Golife","status":"ONLINE"}],"bank":[{"support_authorize_type":["NATIVE","CRAWLER"],"website":"bcabank","authorize_type":"CRAWLER","title":"BCA","icon_name":"icon-BCA","status":"ONLINE"},{"support_authorize_type":["CRAWLER"],"website":"bribank","authorize_type":"CRAWLER","title":"BRI","icon_name":"icon-BRI","status":"ONLINE"},{"support_authorize_type":["CRAWLER"],"website":"bnibank","authorize_type":"CRAWLER","title":"BNI","icon_name":"icon-BNI","status":"ONLINE"}],"identity":[{"support_authorize_type":["CRAWLER"],"website":"npwp","authorize_type":"CRAWLER","title":"NPWP","icon_name":"icon-Identity1","status":"ONLINE"},{"support_authorize_type":["CRAWLER"],"website":"ktp","authorize_type":"CRAWLER","title":"KTP","icon_name":"icon-KTP","status":"ONLINE"},{"support_authorize_type":["CRAWLER"],"website":"telkomselVerify","authorize_type":"CRAWLER","title":"Register Check","icon_name":"icon-shouquan","status":"ONLINE"}],"sns":[{"support_authorize_type":["OAUTH"],"website":"facebook","authorize_type":"OAUTH","title":"Facebook","icon_name":"icon-facebookf","status":"ONLINE"}],"telecom":[{"support_authorize_type":["CRAWLER"],"website":"telkomsel","authorize_type":"CRAWLER","title":"Telkomsel","icon_name":"icon-Telkomsel","status":"ONLINE"},{"support_authorize_type":["CRAWLER"],"website":"xl","authorize_type":"CRAWLER","title":"XL Axiata","icon_name":"icon-XL","status":"ONLINE"}],"payment":[],"job":[{"support_authorize_type":["CRAWLER"],"website":"linkedin","authorize_type":"CRAWLER","title":"LinkedIn","icon_name":"icon-linkedin","status":"ONLINE"}],"travel":[],"IDW":[]}
         * language : ID
         */

        private WebsitesBean websites;
        private String language;

        public WebsitesBean getWebsites() {
            return websites;
        }

        public void setWebsites(WebsitesBean websites) {
            this.websites = websites;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public static class WebsitesBean {
            private List<InsuranceBean> insurance;
            private List<ImBean> im;
            private List<ECommerceBean> ECommerce;
            private List<?> EPayment;
            private List<RidesBean> rides;
            private List<?> utilities;
            private List<LifeBean> life;
            private List<BankBean> bank;
            private List<IdentityBean> identity;
            private List<SnsBean> sns;
            private List<TelecomBean> telecom;
            private List<?> payment;
            private List<JobBean> job;
            private List<?> travel;
            private List<?> IDW;

            public List<InsuranceBean> getInsurance() {
                return insurance;
            }

            public void setInsurance(List<InsuranceBean> insurance) {
                this.insurance = insurance;
            }

            public List<ImBean> getIm() {
                return im;
            }

            public void setIm(List<ImBean> im) {
                this.im = im;
            }

            public List<ECommerceBean> getECommerce() {
                return ECommerce;
            }

            public void setECommerce(List<ECommerceBean> ECommerce) {
                this.ECommerce = ECommerce;
            }

            public List<?> getEPayment() {
                return EPayment;
            }

            public void setEPayment(List<?> EPayment) {
                this.EPayment = EPayment;
            }

            public List<RidesBean> getRides() {
                return rides;
            }

            public void setRides(List<RidesBean> rides) {
                this.rides = rides;
            }

            public List<?> getUtilities() {
                return utilities;
            }

            public void setUtilities(List<?> utilities) {
                this.utilities = utilities;
            }

            public List<LifeBean> getLife() {
                return life;
            }

            public void setLife(List<LifeBean> life) {
                this.life = life;
            }

            public List<BankBean> getBank() {
                return bank;
            }

            public void setBank(List<BankBean> bank) {
                this.bank = bank;
            }

            public List<IdentityBean> getIdentity() {
                return identity;
            }

            public void setIdentity(List<IdentityBean> identity) {
                this.identity = identity;
            }

            public List<SnsBean> getSns() {
                return sns;
            }

            public void setSns(List<SnsBean> sns) {
                this.sns = sns;
            }

            public List<TelecomBean> getTelecom() {
                return telecom;
            }

            public void setTelecom(List<TelecomBean> telecom) {
                this.telecom = telecom;
            }

            public List<?> getPayment() {
                return payment;
            }

            public void setPayment(List<?> payment) {
                this.payment = payment;
            }

            public List<JobBean> getJob() {
                return job;
            }

            public void setJob(List<JobBean> job) {
                this.job = job;
            }

            public List<?> getTravel() {
                return travel;
            }

            public void setTravel(List<?> travel) {
                this.travel = travel;
            }

            public List<?> getIDW() {
                return IDW;
            }

            public void setIDW(List<?> IDW) {
                this.IDW = IDW;
            }

            public static class InsuranceBean {
                /**
                 * support_authorize_type : ["CRAWLER"]
                 * website : bpjstku
                 * authorize_type : CRAWLER
                 * title : BPJS-TKU
                 * icon_name : icon-BPJS-Employment
                 * status : ONLINE
                 */

                private String website;
                private String authorize_type;
                private String title;
                private String icon_name;
                private String status;
                private List<String> support_authorize_type;

                public String getWebsite() {
                    return website;
                }

                public void setWebsite(String website) {
                    this.website = website;
                }

                public String getAuthorize_type() {
                    return authorize_type;
                }

                public void setAuthorize_type(String authorize_type) {
                    this.authorize_type = authorize_type;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getIcon_name() {
                    return icon_name;
                }

                public void setIcon_name(String icon_name) {
                    this.icon_name = icon_name;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public List<String> getSupport_authorize_type() {
                    return support_authorize_type;
                }

                public void setSupport_authorize_type(List<String> support_authorize_type) {
                    this.support_authorize_type = support_authorize_type;
                }
            }

            public static class ImBean {
                /**
                 * support_authorize_type : ["CRAWLER"]
                 * website : yogrt
                 * authorize_type : CRAWLER
                 * title : Yogrt
                 * icon_name : icon-Yogrt
                 * status : ONLINE
                 */

                private String website;
                private String authorize_type;
                private String title;
                private String icon_name;
                private String status;
                private List<String> support_authorize_type;

                public String getWebsite() {
                    return website;
                }

                public void setWebsite(String website) {
                    this.website = website;
                }

                public String getAuthorize_type() {
                    return authorize_type;
                }

                public void setAuthorize_type(String authorize_type) {
                    this.authorize_type = authorize_type;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getIcon_name() {
                    return icon_name;
                }

                public void setIcon_name(String icon_name) {
                    this.icon_name = icon_name;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public List<String> getSupport_authorize_type() {
                    return support_authorize_type;
                }

                public void setSupport_authorize_type(List<String> support_authorize_type) {
                    this.support_authorize_type = support_authorize_type;
                }
            }

            public static class ECommerceBean {
                /**
                 * support_authorize_type : ["CRAWLER"]
                 * website : lazadaid
                 * authorize_type : CRAWLER
                 * title : LazadaID
                 * icon_name : icon-Lazada
                 * status : ONLINE
                 */

                private String website;
                private String authorize_type;
                private String title;
                private String icon_name;
                private String status;
                private List<String> support_authorize_type;

                public String getWebsite() {
                    return website;
                }

                public void setWebsite(String website) {
                    this.website = website;
                }

                public String getAuthorize_type() {
                    return authorize_type;
                }

                public void setAuthorize_type(String authorize_type) {
                    this.authorize_type = authorize_type;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getIcon_name() {
                    return icon_name;
                }

                public void setIcon_name(String icon_name) {
                    this.icon_name = icon_name;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public List<String> getSupport_authorize_type() {
                    return support_authorize_type;
                }

                public void setSupport_authorize_type(List<String> support_authorize_type) {
                    this.support_authorize_type = support_authorize_type;
                }
            }

            public static class RidesBean {
                /**
                 * support_authorize_type : ["CRAWLER"]
                 * website : gojek
                 * authorize_type : CRAWLER
                 * title : Gojek
                 * icon_name : icon-Gojek
                 * status : ONLINE
                 */

                private String website;
                private String authorize_type;
                private String title;
                private String icon_name;
                private String status;
                private List<String> support_authorize_type;

                public String getWebsite() {
                    return website;
                }

                public void setWebsite(String website) {
                    this.website = website;
                }

                public String getAuthorize_type() {
                    return authorize_type;
                }

                public void setAuthorize_type(String authorize_type) {
                    this.authorize_type = authorize_type;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getIcon_name() {
                    return icon_name;
                }

                public void setIcon_name(String icon_name) {
                    this.icon_name = icon_name;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public List<String> getSupport_authorize_type() {
                    return support_authorize_type;
                }

                public void setSupport_authorize_type(List<String> support_authorize_type) {
                    this.support_authorize_type = support_authorize_type;
                }
            }

            public static class LifeBean {
                /**
                 * support_authorize_type : ["CRAWLER"]
                 * website : golife
                 * authorize_type : CRAWLER
                 * title : Golife
                 * icon_name : icon-Golife
                 * status : ONLINE
                 */

                private String website;
                private String authorize_type;
                private String title;
                private String icon_name;
                private String status;
                private List<String> support_authorize_type;

                public String getWebsite() {
                    return website;
                }

                public void setWebsite(String website) {
                    this.website = website;
                }

                public String getAuthorize_type() {
                    return authorize_type;
                }

                public void setAuthorize_type(String authorize_type) {
                    this.authorize_type = authorize_type;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getIcon_name() {
                    return icon_name;
                }

                public void setIcon_name(String icon_name) {
                    this.icon_name = icon_name;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public List<String> getSupport_authorize_type() {
                    return support_authorize_type;
                }

                public void setSupport_authorize_type(List<String> support_authorize_type) {
                    this.support_authorize_type = support_authorize_type;
                }
            }

            public static class BankBean {
                /**
                 * support_authorize_type : ["NATIVE","CRAWLER"]
                 * website : bcabank
                 * authorize_type : CRAWLER
                 * title : BCA
                 * icon_name : icon-BCA
                 * status : ONLINE
                 */

                private String website;
                private String authorize_type;
                private String title;
                private String icon_name;
                private String status;
                private List<String> support_authorize_type;

                public String getWebsite() {
                    return website;
                }

                public void setWebsite(String website) {
                    this.website = website;
                }

                public String getAuthorize_type() {
                    return authorize_type;
                }

                public void setAuthorize_type(String authorize_type) {
                    this.authorize_type = authorize_type;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getIcon_name() {
                    return icon_name;
                }

                public void setIcon_name(String icon_name) {
                    this.icon_name = icon_name;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public List<String> getSupport_authorize_type() {
                    return support_authorize_type;
                }

                public void setSupport_authorize_type(List<String> support_authorize_type) {
                    this.support_authorize_type = support_authorize_type;
                }
            }

            public static class IdentityBean {
                /**
                 * support_authorize_type : ["CRAWLER"]
                 * website : npwp
                 * authorize_type : CRAWLER
                 * title : NPWP
                 * icon_name : icon-Identity1
                 * status : ONLINE
                 */

                private String website;
                private String authorize_type;
                private String title;
                private String icon_name;
                private String status;
                private List<String> support_authorize_type;

                public String getWebsite() {
                    return website;
                }

                public void setWebsite(String website) {
                    this.website = website;
                }

                public String getAuthorize_type() {
                    return authorize_type;
                }

                public void setAuthorize_type(String authorize_type) {
                    this.authorize_type = authorize_type;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getIcon_name() {
                    return icon_name;
                }

                public void setIcon_name(String icon_name) {
                    this.icon_name = icon_name;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public List<String> getSupport_authorize_type() {
                    return support_authorize_type;
                }

                public void setSupport_authorize_type(List<String> support_authorize_type) {
                    this.support_authorize_type = support_authorize_type;
                }
            }

            public static class SnsBean {
                /**
                 * support_authorize_type : ["OAUTH"]
                 * website : facebook
                 * authorize_type : OAUTH
                 * title : Facebook
                 * icon_name : icon-facebookf
                 * status : ONLINE
                 */

                private String website;
                private String authorize_type;
                private String title;
                private String icon_name;
                private String status;
                private List<String> support_authorize_type;

                public String getWebsite() {
                    return website;
                }

                public void setWebsite(String website) {
                    this.website = website;
                }

                public String getAuthorize_type() {
                    return authorize_type;
                }

                public void setAuthorize_type(String authorize_type) {
                    this.authorize_type = authorize_type;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getIcon_name() {
                    return icon_name;
                }

                public void setIcon_name(String icon_name) {
                    this.icon_name = icon_name;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public List<String> getSupport_authorize_type() {
                    return support_authorize_type;
                }

                public void setSupport_authorize_type(List<String> support_authorize_type) {
                    this.support_authorize_type = support_authorize_type;
                }
            }

            public static class TelecomBean {
                /**
                 * support_authorize_type : ["CRAWLER"]
                 * website : telkomsel
                 * authorize_type : CRAWLER
                 * title : Telkomsel
                 * icon_name : icon-Telkomsel
                 * status : ONLINE
                 */

                private String website;
                private String authorize_type;
                private String title;
                private String icon_name;
                private String status;
                private List<String> support_authorize_type;

                public String getWebsite() {
                    return website;
                }

                public void setWebsite(String website) {
                    this.website = website;
                }

                public String getAuthorize_type() {
                    return authorize_type;
                }

                public void setAuthorize_type(String authorize_type) {
                    this.authorize_type = authorize_type;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getIcon_name() {
                    return icon_name;
                }

                public void setIcon_name(String icon_name) {
                    this.icon_name = icon_name;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public List<String> getSupport_authorize_type() {
                    return support_authorize_type;
                }

                public void setSupport_authorize_type(List<String> support_authorize_type) {
                    this.support_authorize_type = support_authorize_type;
                }
            }

            public static class JobBean {
                /**
                 * support_authorize_type : ["CRAWLER"]
                 * website : linkedin
                 * authorize_type : CRAWLER
                 * title : LinkedIn
                 * icon_name : icon-linkedin
                 * status : ONLINE
                 */

                private String website;
                private String authorize_type;
                private String title;
                private String icon_name;
                private String status;
                private List<String> support_authorize_type;

                public String getWebsite() {
                    return website;
                }

                public void setWebsite(String website) {
                    this.website = website;
                }

                public String getAuthorize_type() {
                    return authorize_type;
                }

                public void setAuthorize_type(String authorize_type) {
                    this.authorize_type = authorize_type;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getIcon_name() {
                    return icon_name;
                }

                public void setIcon_name(String icon_name) {
                    this.icon_name = icon_name;
                }

                public String getStatus() {
                    return status;
                }

                public void setStatus(String status) {
                    this.status = status;
                }

                public List<String> getSupport_authorize_type() {
                    return support_authorize_type;
                }

                public void setSupport_authorize_type(List<String> support_authorize_type) {
                    this.support_authorize_type = support_authorize_type;
                }
            }
        }
    }
}
