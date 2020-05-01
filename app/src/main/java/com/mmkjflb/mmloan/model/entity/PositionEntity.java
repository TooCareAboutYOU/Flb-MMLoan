package com.mmkjflb.mmloan.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: wendjia
 * Time: 2018\7\31 0031
 * Description:
 **/
public class PositionEntity implements Parcelable {
	@Override
	public String toString() {
		return "PositionEntity{" +
				"cName='" + cName + '\'' +
				", yName='" + yName + '\'' +
				", pId=" + pId +
				", id=" + id +
				'}';
	}

	private String cName;
	private String yName;
	private long pId;
	private long id;

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getyName() {
		return yName;
	}

	public void setyName(String yName) {
		this.yName = yName;
	}

	public long getPid() {
		return pId;
	}

	public void setPid(long pId) {
		this.pId = pId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.cName);
		dest.writeString(this.yName);
		dest.writeLong(this.pId);
		dest.writeLong(this.id);
	}

	public PositionEntity() {
	}

	protected PositionEntity(Parcel in) {
		this.cName = in.readString();
		this.yName = in.readString();
		this.pId = in.readLong();
		this.id = in.readLong();
	}

	public static final Parcelable.Creator<PositionEntity> CREATOR = new Parcelable.Creator<PositionEntity>() {
		@Override
		public PositionEntity createFromParcel(Parcel source) {
			return new PositionEntity(source);
		}

		@Override
		public PositionEntity[] newArray(int size) {
			return new PositionEntity[size];
		}
	};
}
