package com.mmkjflb.mmloan.model.entity;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.util.List;

/**
 * date: 2018/3/6 14:38
 * author: xuyexiang
 * title:
 */

public class IslandProvinceCityEntity implements IPickerViewData {


    private String code;
    private int id;
    private String name;
    private List<DictProvinceListBean> dictProvinceList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DictProvinceListBean> getDictProvinceList() {
        return dictProvinceList;
    }

    public void setDictProvinceList(List<DictProvinceListBean> dictProvinceList) {
        this.dictProvinceList = dictProvinceList;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }

    public static class DictProvinceListBean implements IPickerViewData{
        /**
         * code : 00010001
         * dictCityList : [{"code":"000100010001","id":1,"name":"Kabupaten Aceh Barat","provinceCode":"00010001"},{"code":"000100010002","id":2,"name":"Kabupaten Aceh Barat Daya","provinceCode":"00010001"},{"code":"000100010003","id":3,"name":"Kabupaten Aceh Besar","provinceCode":"00010001"},{"code":"000100010004","id":4,"name":"Kabupaten Aceh Jaya","provinceCode":"00010001"},{"code":"000100010005","id":5,"name":"Kabupaten Aceh Selatan","provinceCode":"00010001"},{"code":"000100010006","id":6,"name":"Kabupaten Aceh Singkil","provinceCode":"00010001"},{"code":"000100010007","id":7,"name":"Kabupaten Aceh Tamiang","provinceCode":"00010001"},{"code":"000100010008","id":8,"name":"Kabupaten Aceh Tengah","provinceCode":"00010001"},{"code":"000100010009","id":9,"name":"Kabupaten Aceh Tenggara","provinceCode":"00010001"},{"code":"000100010010","id":10,"name":"Kabupaten Aceh Timur","provinceCode":"00010001"},{"code":"000100010011","id":11,"name":"Kabupaten Aceh Utara","provinceCode":"00010001"},{"code":"000100010012","id":12,"name":"Kabupaten Bener Meriah","provinceCode":"00010001"},{"code":"000100010013","id":13,"name":"Kabupaten Bireuen","provinceCode":"00010001"},{"code":"000100010014","id":14,"name":"Kabupaten Gayo Lues","provinceCode":"00010001"},{"code":"000100010015","id":15,"name":"Kabupaten Nagan Raya","provinceCode":"00010001"},{"code":"000100010016","id":16,"name":"Kabupaten Pidie","provinceCode":"00010001"},{"code":"000100010017","id":17,"name":"Kabupaten Pidie Jaya","provinceCode":"00010001"},{"code":"000100010018","id":18,"name":"Kabupaten Simeulue","provinceCode":"00010001"},{"code":"000100010019","id":19,"name":"Kota Banda Aceh","provinceCode":"00010001"},{"code":"000100010020","id":20,"name":"Kota Langsa","provinceCode":"00010001"},{"code":"000100010021","id":21,"name":"Kota Lhokseumawe","provinceCode":"00010001"},{"code":"000100010022","id":22,"name":"Kota Sabang","provinceCode":"00010001"},{"code":"000100010023","id":23,"name":"Kota Subulussalam","provinceCode":"00010001"}]
         * id : 1
         * islandsCode : 0001
         * name : Aceh
         */

        private String code;
        private int id;
        private String islandsCode;
        private String name;
        private List<DictCityListBean> dictCityList;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getIslandsCode() {
            return islandsCode;
        }

        public void setIslandsCode(String islandsCode) {
            this.islandsCode = islandsCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<DictCityListBean> getDictCityList() {
            return dictCityList;
        }

        public void setDictCityList(List<DictCityListBean> dictCityList) {
            this.dictCityList = dictCityList;
        }

        @Override
        public String getPickerViewText() {
            return name;
        }

        public static class DictCityListBean implements IPickerViewData{
            /**
             * code : 000100010001
             * id : 1
             * name : Kabupaten Aceh Barat
             * provinceCode : 00010001
             */

            private String code;
            private int id;
            private String name;
            private String provinceCode;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProvinceCode() {
                return provinceCode;
            }

            public void setProvinceCode(String provinceCode) {
                this.provinceCode = provinceCode;
            }

            @Override
            public String getPickerViewText() {
                return name;
            }
        }
    }
}
