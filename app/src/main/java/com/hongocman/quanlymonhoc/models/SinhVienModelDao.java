package com.hongocman.quanlymonhoc.models;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.hongocman.quanlymonhoc.models.SinhVienModel;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SINH_VIEN_MODEL".
*/
public class SinhVienModelDao extends AbstractDao<SinhVienModel, String> {

    public static final String TABLENAME = "SINH_VIEN_MODEL";

    /**
     * Properties of entity SinhVienModel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property ID = new Property(0, String.class, "ID", true, "ID");
        public final static Property FullName = new Property(1, String.class, "FullName", false, "FULL_NAME");
        public final static Property Sex = new Property(2, Boolean.class, "Sex", false, "SEX");
        public final static Property Password = new Property(3, String.class, "Password", false, "PASSWORD");
        public final static Property Passport = new Property(4, String.class, "Passport", false, "PASSPORT");
        public final static Property PhoneNumber = new Property(5, String.class, "PhoneNumber", false, "PHONE_NUMBER");
        public final static Property BirthDay = new Property(6, java.util.Date.class, "BirthDay", false, "BIRTH_DAY");
    };


    public SinhVienModelDao(DaoConfig config) {
        super(config);
    }
    
    public SinhVienModelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SINH_VIEN_MODEL\" (" + //
                "\"ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: ID
                "\"FULL_NAME\" TEXT," + // 1: FullName
                "\"SEX\" INTEGER," + // 2: Sex
                "\"PASSWORD\" TEXT," + // 3: Password
                "\"PASSPORT\" TEXT," + // 4: Passport
                "\"PHONE_NUMBER\" TEXT," + // 5: PhoneNumber
                "\"BIRTH_DAY\" INTEGER);"); // 6: BirthDay
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SINH_VIEN_MODEL\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, SinhVienModel entity) {
        stmt.clearBindings();
        stmt.bindString(1, entity.getID());
 
        String FullName = entity.getFullName();
        if (FullName != null) {
            stmt.bindString(2, FullName);
        }
 
        Boolean Sex = entity.getSex();
        if (Sex != null) {
            stmt.bindLong(3, Sex ? 1L: 0L);
        }
 
        String Password = entity.getPassword();
        if (Password != null) {
            stmt.bindString(4, Password);
        }
 
        String Passport = entity.getPassport();
        if (Passport != null) {
            stmt.bindString(5, Passport);
        }
 
        String PhoneNumber = entity.getPhoneNumber();
        if (PhoneNumber != null) {
            stmt.bindString(6, PhoneNumber);
        }
 
        java.util.Date BirthDay = entity.getBirthDay();
        if (BirthDay != null) {
            stmt.bindLong(7, BirthDay.getTime());
        }
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.getString(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public SinhVienModel readEntity(Cursor cursor, int offset) {
        SinhVienModel entity = new SinhVienModel( //
            cursor.getString(offset + 0), // ID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // FullName
            cursor.isNull(offset + 2) ? null : cursor.getShort(offset + 2) != 0, // Sex
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // Password
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // Passport
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // PhoneNumber
            cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)) // BirthDay
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, SinhVienModel entity, int offset) {
        entity.setID(cursor.getString(offset + 0));
        entity.setFullName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSex(cursor.isNull(offset + 2) ? null : cursor.getShort(offset + 2) != 0);
        entity.setPassword(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPassport(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPhoneNumber(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setBirthDay(cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)));
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(SinhVienModel entity, long rowId) {
        return entity.getID();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(SinhVienModel entity) {
        if(entity != null) {
            return entity.getID();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
