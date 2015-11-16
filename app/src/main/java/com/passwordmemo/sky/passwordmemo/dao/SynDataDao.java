package com.passwordmemo.sky.passwordmemo.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.passwordmemo.sky.passwordmemo.dao.SynData;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SYN_DATA".
*/
public class SynDataDao extends AbstractDao<SynData, Long> {

    public static final String TABLENAME = "SYN_DATA";

    /**
     * Properties of entity SynData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property UserpasswordId = new Property(1, String.class, "userpasswordId", false, "USERPASSWORD_ID");
        public final static Property AdminId = new Property(2, Integer.class, "adminId", false, "ADMIN_ID");
        public final static Property Mark = new Property(3, String.class, "mark", false, "MARK");
    };


    public SynDataDao(DaoConfig config) {
        super(config);
    }
    
    public SynDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SYN_DATA\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"USERPASSWORD_ID\" TEXT," + // 1: userpasswordId
                "\"ADMIN_ID\" INTEGER," + // 2: adminId
                "\"MARK\" TEXT);"); // 3: mark
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SYN_DATA\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, SynData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String userpasswordId = entity.getUserpasswordId();
        if (userpasswordId != null) {
            stmt.bindString(2, userpasswordId);
        }
 
        Integer adminId = entity.getAdminId();
        if (adminId != null) {
            stmt.bindLong(3, adminId);
        }
 
        String mark = entity.getMark();
        if (mark != null) {
            stmt.bindString(4, mark);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public SynData readEntity(Cursor cursor, int offset) {
        SynData entity = new SynData( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // userpasswordId
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // adminId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // mark
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, SynData entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserpasswordId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAdminId(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setMark(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(SynData entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(SynData entity) {
        if(entity != null) {
            return entity.getId();
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