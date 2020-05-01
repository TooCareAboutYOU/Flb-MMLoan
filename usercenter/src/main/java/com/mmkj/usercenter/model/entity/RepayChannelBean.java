package com.mmkj.usercenter.model.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RepayChannelBean {

    /**
     * data : {"1":[{"channelName":"G-Cash","channelType":1}],"2":[{"channelName":"7-Eleven","channelType":2},{"channelName":"Gaisano Grand","channelType":2},{"channelName":"NCCC Malls","channelType":2},{"channelName":"LCC Malls","channelType":2},{"channelName":"Shopwise","channelType":2},{"channelName":"Wellcome","channelType":2},{"channelName":"Prince Retail","channelType":2},{"channelName":"Fooda","channelType":2},{"channelName":"Lee Plaza","channelType":2}],"3":[{"channelName":"RD Pawnshops","channelType":3},{"channelName":"M. Lhuillier Pawnshops","channelType":3},{"channelName":"Tambunting Pawnshops (selected outlets)","channelType":3},{"channelName":"Gemmary Pawnshops","channelType":3},{"channelName":"GRJ JARO Pawnshops","channelType":3},{"channelName":"FEROCE Pawnshops","channelType":3},{"channelName":"Royal House Pawnshops","channelType":3},{"channelName":"Jewel House Pawnshops","channelType":3},{"channelName":"Owen & Son\u2019s Pawnshops","channelType":3},{"channelName":"Dalton Pawnshops","channelType":3},{"channelName":"De Leon Pawnshops","channelType":3},{"channelName":"A.D. Sarabia Pawnshops","channelType":3},{"channelName":"CEBU Gold Pawnshops","channelType":3},{"channelName":"Tagala Pawnshops","channelType":3},{"channelName":"Megatrend Pawnshops","channelType":3},{"channelName":"CVM Pawnshops","channelType":3},{"channelName":"A.L.G. Panwshops","channelType":3}],"4":[{"channelName":"ExpressPay","channelType":4},{"channelName":"Global Access","channelType":4},{"channelName":"Magic Appliance Center","channelType":4},{"channelName":"TrueMoney","channelType":4}],"5":[{"channelName":"Rang-ay Bank","channelType":5},{"channelName":"GR Bank","channelType":5},{"channelName":"Enterprise Bank","channelType":5},{"channelName":"Malayan Bank","channelType":5},{"channelName":"GateBank","channelType":5},{"channelName":"Zambank","channelType":5},{"channelName":"Bank of Floridablanca","channelType":5}]}
     * num : 10
     * payChannel : SKYPAY
     * payCode : SKY07xxxx
     * prefix : SKY07
     * searchType : 1
     */

    private DataBean data;
    private String num;
    private String payChannel;
    private String payCode;
    private String prefix;
    private int searchType;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public static class DataBean {
        @SerializedName("1")
        private List<ChannelBean> channel1;
        @SerializedName("2")
        private List<ChannelBean> channel2;
        @SerializedName("3")
        private List<ChannelBean> channel3;
        @SerializedName("4")
        private List<ChannelBean> channel4;
        @SerializedName("5")
        private List<ChannelBean> channel5;

        public List<ChannelBean> getChannel1() {
            return channel1;
        }

        public void setChannel1(List<ChannelBean> channel1) {
            this.channel1 = channel1;
        }

        public List<ChannelBean> getChannel2() {
            return channel2;
        }

        public void setChannel2(List<ChannelBean> channel2) {
            this.channel2 = channel2;
        }

        public List<ChannelBean> getChannel3() {
            return channel3;
        }

        public void setChannel3(List<ChannelBean> channel3) {
            this.channel3 = channel3;
        }

        public List<ChannelBean> getChannel4() {
            return channel4;
        }

        public void setChannel4(List<ChannelBean> channel4) {
            this.channel4 = channel4;
        }

        public List<ChannelBean> getChannel5() {
            return channel5;
        }

        public void setChannel5(List<ChannelBean> channel5) {
            this.channel5 = channel5;
        }

        public static class ChannelBean {
            /**
             * channelName : G-Cash
             * channelType : 1
             */

            private String channelName;
            private int channelType;

            public String getChannelName() {
                return channelName;
            }

            public void setChannelName(String channelName) {
                this.channelName = channelName;
            }

            public int getChannelType() {
                return channelType;
            }

            public void setChannelType(int channelType) {
                this.channelType = channelType;
            }
        }

    }


}
