package com.mmkjflb.mmloan.model.entity;

/**
 * date: 2018/3/5 19:48
 * author: xuyexiang
 * title:
 */

public class IdCertificateUploadEntity<T> {
	private String name;
	private  byte[]  imagUrl;

	public String getName() {
		return name;
	}

	public IdCertificateUploadEntity(String name,  byte[]  imagUrl) {
		this.name = name;
		this.imagUrl = imagUrl;
	}

	public void setName(String name) {
		this.name = name;
	}

	public  byte[]  getImagByte() {
		return imagUrl;
	}

	public void setImagByte( byte[]  imagByte) {
		this.imagUrl = imagByte;
	}


}
