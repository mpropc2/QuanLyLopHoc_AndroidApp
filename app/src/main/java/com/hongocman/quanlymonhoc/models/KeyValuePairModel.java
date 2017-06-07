package com.hongocman.quanlymonhoc.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * MODEL: 		KeyValuePairModel
 * 				include 2 attribute: ID and description
 * @author:		vandn
 * @created on:	12/08/2013
 * */
public class KeyValuePairModel implements Parcelable{
	private int ID;//iID
	private String sID;
	private String Name;//description
	private String hint; // to use location list with code of region
	//Update by: DuHK
	
	public KeyValuePairModel(){
		
	}	
	
	private int type;
	/**
	 * @return the hint
	 */
	public String getHint() {
		return hint;
	}
	/**
	 * @param hint the hint to set
	 */
	public void setHint(String hint) {
		this.hint = hint;
	}
	public String getsID() {
		return sID;
	}
	public void setsID(String sID) {
		this.sID = sID;
	}

	public KeyValuePairModel(int iD, String desc){
		this.ID = iD;
		this.Name = desc;
	}
	
	public KeyValuePairModel (String sID, String desc){
		this.sID = sID;
		this.Name = desc;
	}
	public KeyValuePairModel (String sID, String desc, String hint){
		this.sID = sID;
		this.Name = desc;
		this.hint = hint;
	}
	
	public KeyValuePairModel (int iD, String desc, String hint){
		this.ID = iD;
		this.Name = desc;
		this.hint = hint;
	}
	
	public KeyValuePairModel (int iD, String desc, String hint, int type){
		this.ID = iD;
		this.Name = desc;
		this.hint = hint;
		this.type = type;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		this.ID = iD;
	}
	public String getDescription() {
		return Name;
	}
	public void setDescription(String description) {
		this.Name = description;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel pc, int flags) {
		// TODO Auto-generated method stub		
		pc.writeString(sID);
		pc.writeString(Name);		
	}
	public KeyValuePairModel(Parcel source) {		
		sID = source.readString();
		Name = source.readString();
	}
	
	public void setType(int type) {
		this.type = type;
	}
	public int getType() {
		return type;
	}

	/** Static field used to regenerate object, individually or as arrays */
	public static final Creator<KeyValuePairModel> CREATOR = new Creator<KeyValuePairModel>() {
		@Override
		public KeyValuePairModel createFromParcel(Parcel source) {
			return new KeyValuePairModel(source);
		}

		@Override
		public KeyValuePairModel[] newArray(int size) {
			return new KeyValuePairModel[size];
		}
	};
	
	
}
