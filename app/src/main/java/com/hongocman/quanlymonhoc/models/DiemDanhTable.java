package com.hongocman.quanlymonhoc.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.greenrobot.dao.DaoException;

/**
 * Created by Laptop on 4/28/2016.
 */
public class DiemDanhTable {

    private Context mContext;
    private DaoMaster.OpenHelper helper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private DiemDanhModelDao myDao;

    public DiemDanhTable(Context context){
        this.mContext = context;
        helper = new DaoMaster.OpenHelper(mContext, Helper.TAG_DB_NAME, null) {

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                // TODO Auto-generated method stub

            }
        };
		/*db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        messageDao = daoSession.getMessageModelDao();*/
    }

    private void openDB(){
        try{
            db = helper.getWritableDatabase();
            daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();
            myDao = daoSession.getDiemDanhModelDao();
        }catch (Exception e){
            e.getMessage();
        }

    }

    private void closeDB(){
        if(helper != null)
            helper.close();
    }


    public void insert(DiemDanhModel item) {
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        try {
            myDao.insert(item);
        }catch (Exception e){
            throw e;
        }
        closeDB();
    }

    public void delete(DiemDanhModel item) {
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(item);
        closeDB();
    }

    /** Convenient call for . Entity must attached to an entity context. */
    public void update(DiemDanhModel item) {
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(item);
        closeDB();
    }

    /** Convenient call for . Entity must attached to an entity context. */
    public void refresh(DiemDanhModel item) {
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(item);
        closeDB();
    }

    public List<DiemDanhModel> getAll(){
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        List<DiemDanhModel> result = myDao.loadAll();
        closeDB();
        return result;
    }

    public void insertDiemDanh(String thuID,String buoiHocID, String monHocID, String sinhVienID, boolean status, SinhVienModel sv){
        try{
            Date date = new Date();
            String dateStr = Helper.convertDateToString(date, Helper.TAG_FORMATE_DATE_VN);
            date = Helper.convertStringToDate(dateStr, Helper.TAG_FORMATE_DATE_VN);
            DiemDanhModel diemdanh = new DiemDanhModel();
            diemdanh.setThuID(thuID);
            diemdanh.setBuoiID(buoiHocID);
            diemdanh.setMonHocID(monHocID);
            diemdanh.setSinhVienID(sinhVienID);
            diemdanh.setStatus(status);
            diemdanh.setDate(date);
            DiemDanhModel existItem = exist(diemdanh);
            if(existItem == null){
                insert(diemdanh);
            }else{
                sv.setDiemDanhStatus(existItem.getStatus());
            }
        }catch (Exception e){
            throw  e;
        }
    }

    public List<DiemDanhModel> getDiemDanhByMonHoc(String monHocID, boolean status){
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        List<DiemDanhModel> result = myDao.queryBuilder()
                .where(DiemDanhModelDao.Properties.MonHocID.eq(monHocID), DiemDanhModelDao.Properties.Status.eq(status))
                .orderAsc(DiemDanhModelDao.Properties.SinhVienID)
                .list();
        closeDB();
        return result;
    }

    public List<DiemDanhModel> getDiemDanhByMonHoc(String monHocID,String sinhVienID){
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        List<DiemDanhModel> result = myDao.queryBuilder()
                .where(DiemDanhModelDao.Properties.MonHocID.eq(monHocID), DiemDanhModelDao.Properties.SinhVienID.eq(sinhVienID))
                .orderAsc(DiemDanhModelDao.Properties.Date)
                .list();
        closeDB();
        return result;
    }

    public void diemDanh(String thuID,String buoiHocID, String monHocID, String sinhVienID, boolean status){
        try{
            Date date = new Date();
            String dateStr = Helper.convertDateToString(date, Helper.TAG_FORMATE_DATE_VN);
            date = Helper.convertStringToDate(dateStr, Helper.TAG_FORMATE_DATE_VN);
            DiemDanhModel diemdanh = new DiemDanhModel();
            diemdanh.setThuID(thuID);
            diemdanh.setBuoiID(buoiHocID);
            diemdanh.setMonHocID(monHocID);
            diemdanh.setSinhVienID(sinhVienID);
            diemdanh.setStatus(status);
            diemdanh.setDate(date);
            DiemDanhModel existItem = exist(diemdanh);
            if(existItem == null){
                insert(diemdanh);
            }else {
                diemdanh.setID(existItem.getID());
                update(diemdanh);
            }
        }catch (Exception e){
            throw  e;
        }
    }
    public DiemDanhModel exist(DiemDanhModel item){
        openDB();
        try {
            List<DiemDanhModel> existList = myDao.queryBuilder()
                    .where(DiemDanhModelDao.Properties.ThuID.eq(item.getThuID()),
                            DiemDanhModelDao.Properties.BuoiID.eq(item.getBuoiID()),
                            DiemDanhModelDao.Properties.MonHocID.eq(item.getMonHocID()),
                            DiemDanhModelDao.Properties.SinhVienID.eq(item.getSinhVienID()),
                            DiemDanhModelDao.Properties.Date.ge(item.getDate()))
                    .list();
            closeDB();
            if(existList != null && existList.size()>0) {
                return existList.get(0);
            }
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
        return null;
    }
}
