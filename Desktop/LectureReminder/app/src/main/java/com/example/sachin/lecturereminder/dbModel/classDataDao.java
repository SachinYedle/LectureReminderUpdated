package com.example.sachin.lecturereminder.dbModel;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import com.example.sachin.lecturereminder.dbModel.classData;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CLASS_DATA".
*/
public class classDataDao extends AbstractDao<classData, Long> {

    public static final String TABLENAME = "CLASS_DATA";

    /**
     * Properties of entity classData.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Topic = new Property(2, String.class, "topic", false, "TOPIC");
        public final static Property Professor = new Property(3, String.class, "professor", false, "PROFESSOR");
        public final static Property DateTime = new Property(4, java.util.Date.class, "dateTime", false, "DATE_TIME");
        public final static Property Location = new Property(5, String.class, "location", false, "LOCATION");
        public final static Property UserDataId = new Property(6, long.class, "userDataId", false, "USER_DATA_ID");
    };

    private Query<classData> userData_ClassesQuery;

    public classDataDao(DaoConfig config) {
        super(config);
    }
    
    public classDataDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CLASS_DATA\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NAME\" TEXT NOT NULL ," + // 1: name
                "\"TOPIC\" TEXT NOT NULL ," + // 2: topic
                "\"PROFESSOR\" TEXT NOT NULL ," + // 3: professor
                "\"DATE_TIME\" INTEGER NOT NULL ," + // 4: dateTime
                "\"LOCATION\" TEXT NOT NULL ," + // 5: location
                "\"USER_DATA_ID\" INTEGER NOT NULL );"); // 6: userDataId
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CLASS_DATA\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, classData entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getName());
        stmt.bindString(3, entity.getTopic());
        stmt.bindString(4, entity.getProfessor());
        stmt.bindLong(5, entity.getDateTime().getTime());
        stmt.bindString(6, entity.getLocation());
        stmt.bindLong(7, entity.getUserDataId());
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public classData readEntity(Cursor cursor, int offset) {
        classData entity = new classData( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // name
            cursor.getString(offset + 2), // topic
            cursor.getString(offset + 3), // professor
            new java.util.Date(cursor.getLong(offset + 4)), // dateTime
            cursor.getString(offset + 5), // location
            cursor.getLong(offset + 6) // userDataId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, classData entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.getString(offset + 1));
        entity.setTopic(cursor.getString(offset + 2));
        entity.setProfessor(cursor.getString(offset + 3));
        entity.setDateTime(new java.util.Date(cursor.getLong(offset + 4)));
        entity.setLocation(cursor.getString(offset + 5));
        entity.setUserDataId(cursor.getLong(offset + 6));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(classData entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(classData entity) {
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
    
    /** Internal query to resolve the "classes" to-many relationship of userData. */
    public List<classData> _queryUserData_Classes(long userDataId) {
        synchronized (this) {
            if (userData_ClassesQuery == null) {
                QueryBuilder<classData> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.UserDataId.eq(null));
                userData_ClassesQuery = queryBuilder.build();
            }
        }
        Query<classData> query = userData_ClassesQuery.forCurrentThread();
        query.setParameter(0, userDataId);
        return query.list();
    }

}