package com.hongocman.quanlymonhoc.models;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.hongocman.quanlymonhoc.models.SinhVienModel;
import com.hongocman.quanlymonhoc.models.GiangVienModel;
import com.hongocman.quanlymonhoc.models.MonHocModel;
import com.hongocman.quanlymonhoc.models.DangKyMonHocModel;
import com.hongocman.quanlymonhoc.models.BuoiHocModel;
import com.hongocman.quanlymonhoc.models.ThuModel;
import com.hongocman.quanlymonhoc.models.ThoiKhoaBieuModel;
import com.hongocman.quanlymonhoc.models.DiemDanhModel;

import com.hongocman.quanlymonhoc.models.SinhVienModelDao;
import com.hongocman.quanlymonhoc.models.GiangVienModelDao;
import com.hongocman.quanlymonhoc.models.MonHocModelDao;
import com.hongocman.quanlymonhoc.models.DangKyMonHocModelDao;
import com.hongocman.quanlymonhoc.models.BuoiHocModelDao;
import com.hongocman.quanlymonhoc.models.ThuModelDao;
import com.hongocman.quanlymonhoc.models.ThoiKhoaBieuModelDao;
import com.hongocman.quanlymonhoc.models.DiemDanhModelDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig sinhVienModelDaoConfig;
    private final DaoConfig giangVienModelDaoConfig;
    private final DaoConfig monHocModelDaoConfig;
    private final DaoConfig dangKyMonHocModelDaoConfig;
    private final DaoConfig buoiHocModelDaoConfig;
    private final DaoConfig thuModelDaoConfig;
    private final DaoConfig thoiKhoaBieuModelDaoConfig;
    private final DaoConfig diemDanhModelDaoConfig;

    private final SinhVienModelDao sinhVienModelDao;
    private final GiangVienModelDao giangVienModelDao;
    private final MonHocModelDao monHocModelDao;
    private final DangKyMonHocModelDao dangKyMonHocModelDao;
    private final BuoiHocModelDao buoiHocModelDao;
    private final ThuModelDao thuModelDao;
    private final ThoiKhoaBieuModelDao thoiKhoaBieuModelDao;
    private final DiemDanhModelDao diemDanhModelDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        sinhVienModelDaoConfig = daoConfigMap.get(SinhVienModelDao.class).clone();
        sinhVienModelDaoConfig.initIdentityScope(type);

        giangVienModelDaoConfig = daoConfigMap.get(GiangVienModelDao.class).clone();
        giangVienModelDaoConfig.initIdentityScope(type);

        monHocModelDaoConfig = daoConfigMap.get(MonHocModelDao.class).clone();
        monHocModelDaoConfig.initIdentityScope(type);

        dangKyMonHocModelDaoConfig = daoConfigMap.get(DangKyMonHocModelDao.class).clone();
        dangKyMonHocModelDaoConfig.initIdentityScope(type);

        buoiHocModelDaoConfig = daoConfigMap.get(BuoiHocModelDao.class).clone();
        buoiHocModelDaoConfig.initIdentityScope(type);

        thuModelDaoConfig = daoConfigMap.get(ThuModelDao.class).clone();
        thuModelDaoConfig.initIdentityScope(type);

        thoiKhoaBieuModelDaoConfig = daoConfigMap.get(ThoiKhoaBieuModelDao.class).clone();
        thoiKhoaBieuModelDaoConfig.initIdentityScope(type);

        diemDanhModelDaoConfig = daoConfigMap.get(DiemDanhModelDao.class).clone();
        diemDanhModelDaoConfig.initIdentityScope(type);

        sinhVienModelDao = new SinhVienModelDao(sinhVienModelDaoConfig, this);
        giangVienModelDao = new GiangVienModelDao(giangVienModelDaoConfig, this);
        monHocModelDao = new MonHocModelDao(monHocModelDaoConfig, this);
        dangKyMonHocModelDao = new DangKyMonHocModelDao(dangKyMonHocModelDaoConfig, this);
        buoiHocModelDao = new BuoiHocModelDao(buoiHocModelDaoConfig, this);
        thuModelDao = new ThuModelDao(thuModelDaoConfig, this);
        thoiKhoaBieuModelDao = new ThoiKhoaBieuModelDao(thoiKhoaBieuModelDaoConfig, this);
        diemDanhModelDao = new DiemDanhModelDao(diemDanhModelDaoConfig, this);

        registerDao(SinhVienModel.class, sinhVienModelDao);
        registerDao(GiangVienModel.class, giangVienModelDao);
        registerDao(MonHocModel.class, monHocModelDao);
        registerDao(DangKyMonHocModel.class, dangKyMonHocModelDao);
        registerDao(BuoiHocModel.class, buoiHocModelDao);
        registerDao(ThuModel.class, thuModelDao);
        registerDao(ThoiKhoaBieuModel.class, thoiKhoaBieuModelDao);
        registerDao(DiemDanhModel.class, diemDanhModelDao);
    }
    
    public void clear() {
        sinhVienModelDaoConfig.getIdentityScope().clear();
        giangVienModelDaoConfig.getIdentityScope().clear();
        monHocModelDaoConfig.getIdentityScope().clear();
        dangKyMonHocModelDaoConfig.getIdentityScope().clear();
        buoiHocModelDaoConfig.getIdentityScope().clear();
        thuModelDaoConfig.getIdentityScope().clear();
        thoiKhoaBieuModelDaoConfig.getIdentityScope().clear();
        diemDanhModelDaoConfig.getIdentityScope().clear();
    }

    public SinhVienModelDao getSinhVienModelDao() {
        return sinhVienModelDao;
    }

    public GiangVienModelDao getGiangVienModelDao() {
        return giangVienModelDao;
    }

    public MonHocModelDao getMonHocModelDao() {
        return monHocModelDao;
    }

    public DangKyMonHocModelDao getDangKyMonHocModelDao() {
        return dangKyMonHocModelDao;
    }

    public BuoiHocModelDao getBuoiHocModelDao() {
        return buoiHocModelDao;
    }

    public ThuModelDao getThuModelDao() {
        return thuModelDao;
    }

    public ThoiKhoaBieuModelDao getThoiKhoaBieuModelDao() {
        return thoiKhoaBieuModelDao;
    }

    public DiemDanhModelDao getDiemDanhModelDao() {
        return diemDanhModelDao;
    }

}
