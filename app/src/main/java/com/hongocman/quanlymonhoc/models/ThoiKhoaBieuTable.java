package com.hongocman.quanlymonhoc.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.DaoException;

/**
 * Created by Laptop on 4/28/2016.
 */
public class ThoiKhoaBieuTable {

    private Context mContext;
    private DaoMaster.OpenHelper helper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private ThoiKhoaBieuModelDao myDao;

    public ThoiKhoaBieuTable(Context context){
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
            myDao = daoSession.getThoiKhoaBieuModelDao();
        }catch (Exception e){
            e.getMessage();
        }

    }

    private void closeDB(){
        if(helper != null)
            helper.close();
    }


    public int insert(ThoiKhoaBieuModel item) {
        try {
            if(get(item) == null) {
                openDB();
                if (myDao == null) {
                    throw new DaoException("Entity is detached from DAO context");
                }
                myDao.insert(item);
                closeDB();
                return 1;
            }else
                return 0;
        }catch (Exception e){
            myDao.update(item);
        }
        return 0;
    }

    public void delete(ThoiKhoaBieuModel item) {
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(item);
        closeDB();
    }

    public ThoiKhoaBieuModel get(ThoiKhoaBieuModel item){
        List<ThoiKhoaBieuModel> result = new ArrayList<>();
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        try {
            result = myDao.queryBuilder()
                    .where(ThoiKhoaBieuModelDao.Properties.BuoiID.eq(item.getBuoiID()),
                            ThoiKhoaBieuModelDao.Properties.MonHocID.eq(item.getMonHocID()),
                            ThoiKhoaBieuModelDao.Properties.ThuID.eq(item.getThuID()))
                    .limit(1).list();
        }catch (Exception e){
            e.printStackTrace();
        }
        closeDB();
        return  (result != null && result.size()> 0)? result.get(0):null;
    }
    /** Convenient call for . Entity must attached to an entity context. */
    public void update(ThoiKhoaBieuModel item) {
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(item);
        closeDB();
    }

    /** Convenient call for . Entity must attached to an entity context. */
    public void refresh(ThoiKhoaBieuModel item) {
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(item);
        closeDB();
    }

    public List<ThoiKhoaBieuModel> getAll(){
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        List<ThoiKhoaBieuModel> result = myDao.loadAll();
        closeDB();
        return result;
    }

    public List<ThoiKhoaBieuDescriptionModel> getAllOrderByThuBuoi(){
        List<ThoiKhoaBieuDescriptionModel> result = new ArrayList<>();
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        List<ThoiKhoaBieuModel> lstTKB = myDao.queryBuilder()
                .orderAsc(ThoiKhoaBieuModelDao.Properties.ThuID)
                .orderAsc(ThoiKhoaBieuModelDao.Properties.BuoiID)
                .orderAsc(ThoiKhoaBieuModelDao.Properties.MonHocID)
                .list();
        closeDB();
        if(lstTKB != null && lstTKB.size()>0){
            ThoiKhoaBieuDescriptionModel tbkDesc;
            ThuModel thu;
            BuoiHocModel buoiHoc;
            MonHocModel monHoc;
            ThuTable tableThu = new ThuTable(mContext);
            BuoiHocTable tableBuoiHoc = new BuoiHocTable(mContext);
            MonHocTable tableMonHoc = new MonHocTable(mContext);
            for(int i=0;i<lstTKB.size();i++){
                tbkDesc = new ThoiKhoaBieuDescriptionModel();
                thu = tableThu.get(lstTKB.get(i).getThuID());
                buoiHoc = tableBuoiHoc.get(lstTKB.get(i).getBuoiID());
                monHoc = tableMonHoc.get(lstTKB.get(i).getMonHocID());
                tbkDesc.setThuDesc(thu.getDescription());
                tbkDesc.setBuoiHocDesc(buoiHoc.getDescription());
                tbkDesc.setMonHocDesc(monHoc.getDescription());
                result.add(tbkDesc);
            }
        }
        return result;
    }

    /*Lay DS cac Buoi */
    public List<ThoiKhoaBieuModel> getThoiKhoaBieuTheoThu(String thu){
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        List<ThoiKhoaBieuModel> result = myDao.loadAll();
        result = myDao.queryBuilder().where(ThoiKhoaBieuModelDao.Properties.ThuID.eq(thu)).orderAsc(ThoiKhoaBieuModelDao.Properties.BuoiID).list();
        closeDB();
        return result;
    }

    public List<ThoiKhoaBieuModel> getThoiKhoaBieuTheoThuBuoiHoc(String buoiHocID, String thuID){
        openDB();
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        List<ThoiKhoaBieuModel> result = myDao.loadAll();
        result = myDao.queryBuilder().where(ThoiKhoaBieuModelDao.Properties.ThuID.eq(thuID), ThoiKhoaBieuModelDao.Properties.BuoiID.eq(buoiHocID)
                        ).orderAsc(ThoiKhoaBieuModelDao.Properties.BuoiID).list();
        closeDB();
        return result;
    }
}
