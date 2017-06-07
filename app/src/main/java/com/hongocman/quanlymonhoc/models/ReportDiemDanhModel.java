package com.hongocman.quanlymonhoc.models;

/**
 * Created by Laptop on 4/29/2016.
 */
public class ReportDiemDanhModel {
    private String sinhVienID;
    private String monHocID;
    private int total;
    private String sinhVienFullName;
    private String monHocName;

    public ReportDiemDanhModel(){

    }
    public ReportDiemDanhModel(String sinhVienID, String monHocID, int total, String sinhVienFullName, String monHocName) {
        this.sinhVienID = sinhVienID;
        this.monHocID = monHocID;
        this.total = total;
        this.sinhVienFullName = sinhVienFullName;
        this.monHocName = monHocName;
    }


    public String getSinhVienID() {
        return sinhVienID;
    }

    public void setSinhVienID(String sinhVienID) {
        this.sinhVienID = sinhVienID;
    }

    public String getMonHocID() {
        return monHocID;
    }

    public void setMonHocID(String monHocID) {
        this.monHocID = monHocID;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getSinhVienFullName() {
        return sinhVienFullName;
    }

    public void setSinhVienFullName(String sinhVienFullName) {
        this.sinhVienFullName = sinhVienFullName;
    }

    public String getMonHocName() {
        return monHocName;
    }

    public void setMonHocName(String monHocName) {
        this.monHocName = monHocName;
    }


}

