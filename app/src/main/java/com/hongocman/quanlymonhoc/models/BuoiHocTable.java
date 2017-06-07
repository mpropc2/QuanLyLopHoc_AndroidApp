package com.hongocman.quanlymonhoc.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.greenrobot.dao.DaoException;

/**
 * Created by Laptop on 4/28/2016.
 */
public class BuoiHocTable {

    private Context mContext;
    private DaoMaster.OpenHelper helper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private  BuoiHocModelDao myDao;

    public BuoiHocTable(Context context){
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
            myDao = daoSession.getBuoiHocModelDao();
        }catch (Exception e){
            e.getMessage();
        }

    }

    private void closeDB(){
        if(helper != null)
            helper.close();
    }


    public void insert(BuoiHocModel item) {
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

    public void delete(BuoiHocModel item) {
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(item);
        closeDB();
    }

    /** Convenient call for . Entity must attached to an entity context. */
    public void update(BuoiHocModel item) {
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(item);
        closeDB();
    }

    /** Convenient call for . Entity must attached to an entity context. */
    public void refresh(BuoiHocModel item) {
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(item);
        closeDB();
    }

    public List<BuoiHocModel> getAll(){
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        List<BuoiHocModel> result = myDao.loadAll();
        closeDB();
        return result;
    }

    public BuoiHocModel get(String id){
        BuoiHocModel result = null;
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        List<BuoiHocModel> lst = myDao.queryBuilder().where(BuoiHocModelDao.Properties.ID.eq(id)).list();
        if(lst != null && lst.size()>0)
            return  lst.get(0);
        closeDB();
        return result;
    }

    public List<BuoiHocModel> getBuoiHocHomNay(){
        List<BuoiHocModel> result = new ArrayList<>();
        Calendar toDay = Calendar.getInstance();
        int homnay = toDay.get(Calendar.DAY_OF_WEEK);
        ThoiKhoaBieuTable thuTbl = new ThoiKhoaBieuTable(mContext);
        List<ThoiKhoaBieuModel> lstTKB = thuTbl.getThoiKhoaBieuTheoThu(String.valueOf(homnay));
        if(lstTKB != null && lstTKB.size() > 0){
            openDB();
            for (int i=0; i<lstTKB.size();i++ ) {
                try{
                    ThoiKhoaBieuModel tkb = lstTKB.get(i);
                    BuoiHocModel item = myDao.queryBuilder().where(BuoiHocModelDao.Properties.ID.eq(tkb.getBuoiID())).list().get(0);
                    result.add(item);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            closeDB();
        }

        return result;
    }
}
