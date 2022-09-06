package com.rnpp.greendao_example.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MS_USER_INFO".
*/
public class ms_userInfoDao extends AbstractDao<ms_userInfo, Long> {

    public static final String TABLENAME = "MS_USER_INFO";

    /**
     * Properties of entity ms_userInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Data_sync_status = new Property(0, String.class, "data_sync_status", false, "DATA_SYNC_STATUS");
        public final static Property Data_sync_date = new Property(1, String.class, "data_sync_date", false, "DATA_SYNC_DATE");
        public final static Property Update_at = new Property(2, String.class, "update_at", false, "UPDATE_AT");
        public final static Property Create_at = new Property(3, String.class, "create_at", false, "CREATE_AT");
        public final static Property Create_by = new Property(4, String.class, "create_by", false, "CREATE_BY");
        public final static Property Update_by = new Property(5, String.class, "update_by", false, "UPDATE_BY");
        public final static Property Id = new Property(6, Long.class, "id", true, "_id");
        public final static Property Uid = new Property(7, String.class, "uid", false, "UID");
        public final static Property Email = new Property(8, String.class, "email", false, "EMAIL");
        public final static Property Name_first = new Property(9, String.class, "name_first", false, "NAME_FIRST");
        public final static Property Name_last = new Property(10, String.class, "name_last", false, "NAME_LAST");
        public final static Property Name_middle = new Property(11, String.class, "name_middle", false, "NAME_MIDDLE");
        public final static Property Birth_date = new Property(12, String.class, "birth_date", false, "BIRTH_DATE");
        public final static Property Gender = new Property(13, String.class, "gender", false, "GENDER");
    }


    public ms_userInfoDao(DaoConfig config) {
        super(config);
    }
    
    public ms_userInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MS_USER_INFO\" (" + //
                "\"DATA_SYNC_STATUS\" TEXT NOT NULL ," + // 0: data_sync_status
                "\"DATA_SYNC_DATE\" TEXT NOT NULL ," + // 1: data_sync_date
                "\"UPDATE_AT\" TEXT NOT NULL ," + // 2: update_at
                "\"CREATE_AT\" TEXT NOT NULL ," + // 3: create_at
                "\"CREATE_BY\" TEXT NOT NULL ," + // 4: create_by
                "\"UPDATE_BY\" TEXT NOT NULL ," + // 5: update_by
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 6: id
                "\"UID\" TEXT NOT NULL ," + // 7: uid
                "\"EMAIL\" TEXT NOT NULL ," + // 8: email
                "\"NAME_FIRST\" TEXT NOT NULL ," + // 9: name_first
                "\"NAME_LAST\" TEXT NOT NULL ," + // 10: name_last
                "\"NAME_MIDDLE\" TEXT NOT NULL ," + // 11: name_middle
                "\"BIRTH_DATE\" TEXT NOT NULL ," + // 12: birth_date
                "\"GENDER\" TEXT NOT NULL );"); // 13: gender
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MS_USER_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ms_userInfo entity) {
        stmt.clearBindings();
        stmt.bindString(1, entity.getData_sync_status());
        stmt.bindString(2, entity.getData_sync_date());
        stmt.bindString(3, entity.getUpdate_at());
        stmt.bindString(4, entity.getCreate_at());
        stmt.bindString(5, entity.getCreate_by());
        stmt.bindString(6, entity.getUpdate_by());
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(7, id);
        }
        stmt.bindString(8, entity.getUid());
        stmt.bindString(9, entity.getEmail());
        stmt.bindString(10, entity.getName_first());
        stmt.bindString(11, entity.getName_last());
        stmt.bindString(12, entity.getName_middle());
        stmt.bindString(13, entity.getBirth_date());
        stmt.bindString(14, entity.getGender());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ms_userInfo entity) {
        stmt.clearBindings();
        stmt.bindString(1, entity.getData_sync_status());
        stmt.bindString(2, entity.getData_sync_date());
        stmt.bindString(3, entity.getUpdate_at());
        stmt.bindString(4, entity.getCreate_at());
        stmt.bindString(5, entity.getCreate_by());
        stmt.bindString(6, entity.getUpdate_by());
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(7, id);
        }
        stmt.bindString(8, entity.getUid());
        stmt.bindString(9, entity.getEmail());
        stmt.bindString(10, entity.getName_first());
        stmt.bindString(11, entity.getName_last());
        stmt.bindString(12, entity.getName_middle());
        stmt.bindString(13, entity.getBirth_date());
        stmt.bindString(14, entity.getGender());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6);
    }    

    @Override
    public ms_userInfo readEntity(Cursor cursor, int offset) {
        ms_userInfo entity = new ms_userInfo( //
            cursor.getString(offset + 0), // data_sync_status
            cursor.getString(offset + 1), // data_sync_date
            cursor.getString(offset + 2), // update_at
            cursor.getString(offset + 3), // create_at
            cursor.getString(offset + 4), // create_by
            cursor.getString(offset + 5), // update_by
            cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6), // id
            cursor.getString(offset + 7), // uid
            cursor.getString(offset + 8), // email
            cursor.getString(offset + 9), // name_first
            cursor.getString(offset + 10), // name_last
            cursor.getString(offset + 11), // name_middle
            cursor.getString(offset + 12), // birth_date
            cursor.getString(offset + 13) // gender
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ms_userInfo entity, int offset) {
        entity.setData_sync_status(cursor.getString(offset + 0));
        entity.setData_sync_date(cursor.getString(offset + 1));
        entity.setUpdate_at(cursor.getString(offset + 2));
        entity.setCreate_at(cursor.getString(offset + 3));
        entity.setCreate_by(cursor.getString(offset + 4));
        entity.setUpdate_by(cursor.getString(offset + 5));
        entity.setId(cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
        entity.setUid(cursor.getString(offset + 7));
        entity.setEmail(cursor.getString(offset + 8));
        entity.setName_first(cursor.getString(offset + 9));
        entity.setName_last(cursor.getString(offset + 10));
        entity.setName_middle(cursor.getString(offset + 11));
        entity.setBirth_date(cursor.getString(offset + 12));
        entity.setGender(cursor.getString(offset + 13));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ms_userInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ms_userInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ms_userInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
