package com.jack.strawberry.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.jack.strawberry.vo.QuestionVO;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "QUESTION_VO".
*/
public class QuestionVODao extends AbstractDao<QuestionVO, Long> {

    public static final String TABLENAME = "QUESTION_VO";

    /**
     * Properties of entity QuestionVO.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Content_question = new Property(1, String.class, "content_question", false, "CONTENT_QUESTION");
        public final static Property Anwser_a = new Property(2, String.class, "anwser_a", false, "ANWSER_A");
        public final static Property Anwser_b = new Property(3, String.class, "anwser_b", false, "ANWSER_B");
        public final static Property Anwser_real = new Property(4, String.class, "anwser_real", false, "ANWSER_REAL");
    }


    public QuestionVODao(DaoConfig config) {
        super(config);
    }
    
    public QuestionVODao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"QUESTION_VO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CONTENT_QUESTION\" TEXT," + // 1: content_question
                "\"ANWSER_A\" TEXT," + // 2: anwser_a
                "\"ANWSER_B\" TEXT," + // 3: anwser_b
                "\"ANWSER_REAL\" TEXT);"); // 4: anwser_real
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"QUESTION_VO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, QuestionVO entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String content_question = entity.getContent_question();
        if (content_question != null) {
            stmt.bindString(2, content_question);
        }
 
        String anwser_a = entity.getAnwser_a();
        if (anwser_a != null) {
            stmt.bindString(3, anwser_a);
        }
 
        String anwser_b = entity.getAnwser_b();
        if (anwser_b != null) {
            stmt.bindString(4, anwser_b);
        }
 
        String anwser_real = entity.getAnwser_real();
        if (anwser_real != null) {
            stmt.bindString(5, anwser_real);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, QuestionVO entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String content_question = entity.getContent_question();
        if (content_question != null) {
            stmt.bindString(2, content_question);
        }
 
        String anwser_a = entity.getAnwser_a();
        if (anwser_a != null) {
            stmt.bindString(3, anwser_a);
        }
 
        String anwser_b = entity.getAnwser_b();
        if (anwser_b != null) {
            stmt.bindString(4, anwser_b);
        }
 
        String anwser_real = entity.getAnwser_real();
        if (anwser_real != null) {
            stmt.bindString(5, anwser_real);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public QuestionVO readEntity(Cursor cursor, int offset) {
        QuestionVO entity = new QuestionVO( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // content_question
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // anwser_a
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // anwser_b
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // anwser_real
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, QuestionVO entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setContent_question(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAnwser_a(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAnwser_b(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAnwser_real(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(QuestionVO entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(QuestionVO entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(QuestionVO entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
