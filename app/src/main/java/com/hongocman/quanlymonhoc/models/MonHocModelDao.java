package com.hongocman.quanlymonhoc.models;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.hongocman.quanlymonhoc.models.MonHocModel;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MON_HOC_MODEL".
*/
public class MonHocModelDao extends AbstractDao<MonHocModel, Void> {

    public static final String TABLENAME = "MON_HOC_MODEL";

    /**
     * Properties of entity MonHocModel.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property ID = new Property(0, String.class, "ID", false, "ID");
        public final static Property Description = new Property(1, String.class, "Description", false, "DESCRIPTION");
        public final static Property FromDate = new Property(2, java.util.Date.class, "FromDate", false, "FROM_DATE");
        public final static Property ToDate = new Property(3, java.util.Date.class, "ToDate", false, "TO_DATE");
    };


    public MonHocModelDao(DaoConfig config) {
        super(config);
    }
    
    public MonHocModelDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MON_HOC_MODEL\" (" + //
                "\"ID\" TEXT," + // 0: ID
                "\"DESCRIPTION\" TEXT," + // 1: Description
                "\"FROM_DATE\" INTEGER," + // 2: FromDate
                "\"TO_DATE\" INTEGER);"); // 3: ToDate
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MON_HOC_MODEL\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, MonHocModel entity) {
        stmt.clearBindings();
 
        String ID = entity.getID();
        if (ID != null) {
            stmt.bindString(1, ID);
        }
 
        String Description = entity.getDescription();
        if (Description != null) {
            stmt.bindString(2, Description);
        }
 
        java.util.Date FromDate = entity.getFromDate();
        if (FromDate != null) {
            stmt.bindLong(3, FromDate.getTime());
        }
 
        java.util.Date ToDate = entity.getToDate();
        if (ToDate != null) {
            stmt.bindLong(4, ToDate.getTime());
        }
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public MonHocModel readEntity(Cursor cursor, int offset) {
        MonHocModel entity = new MonHocModel( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // ID
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // Description
            cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)), // FromDate
            cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)) // ToDate
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, MonHocModel entity, int offset) {
        entity.setID(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setDescription(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setFromDate(cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)));
        entity.setToDate(cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(MonHocModel entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(MonHocModel entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
