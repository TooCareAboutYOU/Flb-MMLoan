package com.mmkjflb.mmloan.model.entity;

import com.bigkoo.pickerview.model.IPickerViewData;

import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 */

public class ProvinceCityEntity implements IPickerViewData {

	/**
	 * province : 北京市
	 * city : ["东城区","西城区","崇文区","宣武区","朝阳区","丰台区","石景山区","海淀区","门头沟区","房山区","通州区","顺义区","昌平区","大兴 区","怀柔区","平谷区","密云县","延庆县"]
	 */

	private String province;
	private List<String> city;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public List<String> getCity() {
		return city;
	}

	public void setCity(List<String> city) {
		this.city = city;
	}

	@Override
	public String getPickerViewText() {
		return this.province;
	}
}
