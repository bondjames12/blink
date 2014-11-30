package com.nashlincoln.blink.model;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import com.nashlincoln.blink.model.Attribute;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table ATTRIBUTE.
*/
public class AttributeDao extends AbstractDao<Attribute, Long> {

    public static final String TABLENAME = "ATTRIBUTE";

    /**
     * Properties of entity Attribute.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Value = new Property(1, String.class, "value", false, "VALUE");
        public final static Property ValueLocal = new Property(2, String.class, "valueLocal", false, "VALUE_LOCAL");
        public final static Property AttributableId = new Property(3, Long.class, "attributableId", false, "ATTRIBUTABLE_ID");
        public final static Property AttributableType = new Property(4, String.class, "attributableType", false, "ATTRIBUTABLE_TYPE");
        public final static Property AttributeTypeId = new Property(5, Long.class, "attributeTypeId", false, "ATTRIBUTE_TYPE_ID");
    };

    private DaoSession daoSession;

    private Query<Attribute> device_AttributesQuery;
    private Query<Attribute> group_AttributesQuery;
    private Query<Attribute> sceneDevice_AttributesQuery;

    public AttributeDao(DaoConfig config) {
        super(config);
    }
    
    public AttributeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'ATTRIBUTE' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'VALUE' TEXT," + // 1: value
                "'VALUE_LOCAL' TEXT," + // 2: valueLocal
                "'ATTRIBUTABLE_ID' INTEGER," + // 3: attributableId
                "'ATTRIBUTABLE_TYPE' TEXT," + // 4: attributableType
                "'ATTRIBUTE_TYPE_ID' INTEGER);"); // 5: attributeTypeId
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_ATTRIBUTE_ATTRIBUTABLE_ID_ATTRIBUTABLE_TYPE_ATTRIBUTE_TYPE_ID ON ATTRIBUTE" +
                " (ATTRIBUTABLE_ID,ATTRIBUTABLE_TYPE,ATTRIBUTE_TYPE_ID);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'ATTRIBUTE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Attribute entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String value = entity.getValue();
        if (value != null) {
            stmt.bindString(2, value);
        }
 
        String valueLocal = entity.getValueLocal();
        if (valueLocal != null) {
            stmt.bindString(3, valueLocal);
        }
 
        Long attributableId = entity.getAttributableId();
        if (attributableId != null) {
            stmt.bindLong(4, attributableId);
        }
 
        String attributableType = entity.getAttributableType();
        if (attributableType != null) {
            stmt.bindString(5, attributableType);
        }
 
        Long attributeTypeId = entity.getAttributeTypeId();
        if (attributeTypeId != null) {
            stmt.bindLong(6, attributeTypeId);
        }
    }

    @Override
    protected void attachEntity(Attribute entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Attribute readEntity(Cursor cursor, int offset) {
        Attribute entity = new Attribute( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // value
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // valueLocal
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // attributableId
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // attributableType
            cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5) // attributeTypeId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Attribute entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setValue(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setValueLocal(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAttributableId(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setAttributableType(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setAttributeTypeId(cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Attribute entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Attribute entity) {
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
    
    /** Internal query to resolve the "attributes" to-many relationship of Device. */
    public List<Attribute> _queryDevice_Attributes(Long attributableId, String attributableType) {
        synchronized (this) {
            if (device_AttributesQuery == null) {
                QueryBuilder<Attribute> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.AttributableId.eq(null));
                queryBuilder.where(Properties.AttributableType.eq(null));
                device_AttributesQuery = queryBuilder.build();
            }
        }
        Query<Attribute> query = device_AttributesQuery.forCurrentThread();
        query.setParameter(0, attributableId);
        query.setParameter(1, attributableType);
        return query.list();
    }

    /** Internal query to resolve the "attributes" to-many relationship of Group. */
    public List<Attribute> _queryGroup_Attributes(Long attributableId, String attributableType) {
        synchronized (this) {
            if (group_AttributesQuery == null) {
                QueryBuilder<Attribute> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.AttributableId.eq(null));
                queryBuilder.where(Properties.AttributableType.eq(null));
                group_AttributesQuery = queryBuilder.build();
            }
        }
        Query<Attribute> query = group_AttributesQuery.forCurrentThread();
        query.setParameter(0, attributableId);
        query.setParameter(1, attributableType);
        return query.list();
    }

    /** Internal query to resolve the "attributes" to-many relationship of SceneDevice. */
    public List<Attribute> _querySceneDevice_Attributes(Long attributableId, String attributableType) {
        synchronized (this) {
            if (sceneDevice_AttributesQuery == null) {
                QueryBuilder<Attribute> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.AttributableId.eq(null));
                queryBuilder.where(Properties.AttributableType.eq(null));
                sceneDevice_AttributesQuery = queryBuilder.build();
            }
        }
        Query<Attribute> query = sceneDevice_AttributesQuery.forCurrentThread();
        query.setParameter(0, attributableId);
        query.setParameter(1, attributableType);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getAttributeTypeDao().getAllColumns());
            builder.append(" FROM ATTRIBUTE T");
            builder.append(" LEFT JOIN ATTRIBUTE_TYPE T0 ON T.'ATTRIBUTE_TYPE_ID'=T0.'_id'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Attribute loadCurrentDeep(Cursor cursor, boolean lock) {
        Attribute entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        AttributeType attributeType = loadCurrentOther(daoSession.getAttributeTypeDao(), cursor, offset);
        entity.setAttributeType(attributeType);

        return entity;    
    }

    public Attribute loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Attribute> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Attribute> list = new ArrayList<Attribute>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Attribute> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Attribute> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
