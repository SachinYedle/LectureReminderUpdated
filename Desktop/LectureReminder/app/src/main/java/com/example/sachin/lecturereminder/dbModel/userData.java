package com.example.sachin.lecturereminder.dbModel;

import java.util.List;
import com.example.sachin.lecturereminder.dbModel.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "USER_DATA".
 */
public class userData {

    private Long id;
    /** Not-null value. */
    private String name;
    /** Not-null value. */
    private String className;
    /** Not-null value. */
    private String email;
    /** Not-null value. */
    private String mobile;
    /** Not-null value. */
    private String bloodGroup;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient userDataDao myDao;

    private List<classData> classes;

    public userData() {
    }

    public userData(Long id) {
        this.id = id;
    }

    public userData(Long id, String name, String className, String email, String mobile, String bloodGroup) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.email = email;
        this.mobile = mobile;
        this.bloodGroup = bloodGroup;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserDataDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getName() {
        return name;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setName(String name) {
        this.name = name;
    }

    /** Not-null value. */
    public String getClassName() {
        return className;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setClassName(String className) {
        this.className = className;
    }

    /** Not-null value. */
    public String getEmail() {
        return email;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setEmail(String email) {
        this.email = email;
    }

    /** Not-null value. */
    public String getMobile() {
        return mobile;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /** Not-null value. */
    public String getBloodGroup() {
        return bloodGroup;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<classData> getClasses() {
        if (classes == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            classDataDao targetDao = daoSession.getClassDataDao();
            List<classData> classesNew = targetDao._queryUserData_Classes(id);
            synchronized (this) {
                if(classes == null) {
                    classes = classesNew;
                }
            }
        }
        return classes;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetClasses() {
        classes = null;
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
