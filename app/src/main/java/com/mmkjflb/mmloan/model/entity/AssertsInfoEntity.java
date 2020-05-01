package com.mmkjflb.mmloan.model.entity;

/**
 * @author xuyexiang
 * @date 2018/5/15 14:43
 */

public class AssertsInfoEntity {
	/**
	 * transportation : 0
	 * model : null
	 * numberPlate : null
	 * license : 116.62.4.87:9333/null
	 * houseNum : null
	 * housePic : null
	 */

	private int transportation;
	private String model;
	private String numberPlate;
	private String license;

	public int getTransportation() {
		return transportation;
	}

	public void setTransportation(int transportation) {
		this.transportation = transportation;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getNumberPlate() {
		return numberPlate;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}
}
