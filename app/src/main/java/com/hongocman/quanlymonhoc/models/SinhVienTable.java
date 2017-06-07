package com.hongocman.quanlymonhoc.models;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.DaoException;

/**
 * Created by Laptop on 4/28/2016.
 */
public class SinhVienTable {

    private Context mContext;
    private DaoMaster.OpenHelper helper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private SinhVienModelDao myDao;

    public SinhVienTable(Context context){
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
            myDao = daoSession.getSinhVienModelDao();
        }catch (Exception e){
            e.getMessage();
        }

    }

    private void closeDB(){
        if(helper != null)
            helper.close();
    }


    public void insert(SinhVienModel item) {
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        try {
            myDao.insert(item);
        }catch (Exception e){
            myDao.update(item);
        }
        closeDB();
    }

    public void delete(SinhVienModel item) {
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(item);
        closeDB();
    }

    /** Convenient call for . Entity must attached to an entity context. */
    public void update(SinhVienModel item) {
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(item);
        closeDB();
    }

    /** Convenient call for . Entity must attached to an entity context. */
    public void refresh(SinhVienModel item) {
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(item);
        closeDB();
    }

    public List<SinhVienModel> getAll(){
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        List<SinhVienModel> result = myDao.loadAll();
        closeDB();
        return result;
    }

    public void xoaTatCa(){
        openDB();
        if (myDao == null){
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.deleteAll();
    }

    public SinhVienModel getItem(String id){
        List<SinhVienModel> result = new ArrayList<>();
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        try {
            result = myDao.queryBuilder().where(SinhVienModelDao.Properties.ID.eq(id)).limit(1).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        closeDB();
        return  (result != null && result.size()> 0)? result.get(0):null;
    }

    public  List<SinhVienModel> getSinhVienSearch(String hoten){
        List<SinhVienModel> result = new ArrayList<>();
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        try {
            result = myDao.queryBuilder().where(SinhVienModelDao.Properties.FullName.like("%"+hoten+"%")).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        closeDB();
        return  result;
    }

    public String getNextMaSo(){
        String maso = "";
        int maso1 = 0;
        List<SinhVienModel> result = new ArrayList<>();
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        try {
            result = myDao.queryBuilder().orderDesc(SinhVienModelDao.Properties.ID).limit(1).list();
            if(result.size() != 0) {
                maso = result.get(0).getID();
                maso1 = Integer.parseInt(maso.substring(2));
                maso1++;
                if (maso1 < 10) {
                    return "SV000" + maso1;
                } else if (maso1 < 100) {
                    return "SV00" + maso1;
                } else if (maso1 < 1000) {
                    return "SV0" + maso1;
                } else if (maso1 < 10000) {
                    return "SV" + maso1;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        closeDB();
        return "SV0001";
    }

}
