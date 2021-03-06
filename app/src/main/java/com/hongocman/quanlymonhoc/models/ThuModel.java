package com.hongocman.quanlymonhoc.models;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "THU_MODEL".
 */
public class ThuModel {

    /** Not-null value. */
    private String ID;
    private String Description;

    public ThuModel() {
    }

    public ThuModel(String ID) {
        this.ID = ID;
    }

    public ThuModel(String ID, String Description) {
        this.ID = ID;
        this.Description = Description;
    }

    /** Not-null value. */
    public String getID() {
        return ID;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setID(String ID) {
        this.ID = ID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

}
