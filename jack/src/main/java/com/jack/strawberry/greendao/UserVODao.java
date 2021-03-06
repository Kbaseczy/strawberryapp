package com.jack.strawberry.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.jack.strawberry.vo.UserVO;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_VO".
*/
public class UserVODao extends AbstractDao<UserVO, Long> {

    public static final String TABLENAME = "USER_VO";

    /**
     * Properties of entity UserVO.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property User_id = new Property(0, Long.class, "user_id", true, "_id");
        public final static Property Score = new Property(1, int.class, "score", false, "SCORE");
        public final static Property Sex = new Property(2, String.class, "sex", false, "SEX");
        public final static Property Name = new Property(3, String.class, "name", false, "NAME");
    }


    public UserVODao(DaoConfig config) {
        super(config);
    }
    
    public UserVODao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_VO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: user_id
                "\"SCORE\" INTEGER NOT NULL ," + // 1: score
                "\"SEX\" TEXT," + // 2: sex
                "\"NAME\" TEXT);"); // 3: name
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_VO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserVO entity) {
        stmt.clearBindings();
 
        Long user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindLong(1, user_id);
        }
        stmt.bindLong(2, entity.getScore());
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(3, sex);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserVO entity) {
        stmt.clearBindings();
 
        Long user_id = entity.getUser_id();
        if (user_id != null) {
            stmt.bindLong(1, user_id);
        }
        stmt.bindLong(2, entity.getScore());
 
        String sex = entity.getSex();
        if (sex != null) {
            stmt.bindString(3, sex);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public UserVO readEntity(Cursor cursor, int offset) {
        UserVO entity = new UserVO( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // user_id
            cursor.getInt(offset + 1), // score
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // sex
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3) // name
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserVO entity, int offset) {
        entity.setUser_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setScore(cursor.getInt(offset + 1));
        entity.setSex(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserVO entity, long rowId) {
        entity.setUser_id(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserVO entity) {
        if(entity != null) {
            return entity.getUser_id();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserVO entity) {
        return entity.getUser_id() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
