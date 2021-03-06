package com.jack.strawberry.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.jack.strawberry.vo.QuestionVO;
import com.jack.strawberry.vo.StoreVO;
import com.jack.strawberry.vo.UserVO;

import com.jack.strawberry.greendao.QuestionVODao;
import com.jack.strawberry.greendao.StoreVODao;
import com.jack.strawberry.greendao.UserVODao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig questionVODaoConfig;
    private final DaoConfig storeVODaoConfig;
    private final DaoConfig userVODaoConfig;

    private final QuestionVODao questionVODao;
    private final StoreVODao storeVODao;
    private final UserVODao userVODao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        questionVODaoConfig = daoConfigMap.get(QuestionVODao.class).clone();
        questionVODaoConfig.initIdentityScope(type);

        storeVODaoConfig = daoConfigMap.get(StoreVODao.class).clone();
        storeVODaoConfig.initIdentityScope(type);

        userVODaoConfig = daoConfigMap.get(UserVODao.class).clone();
        userVODaoConfig.initIdentityScope(type);

        questionVODao = new QuestionVODao(questionVODaoConfig, this);
        storeVODao = new StoreVODao(storeVODaoConfig, this);
        userVODao = new UserVODao(userVODaoConfig, this);

        registerDao(QuestionVO.class, questionVODao);
        registerDao(StoreVO.class, storeVODao);
        registerDao(UserVO.class, userVODao);
    }
    
    public void clear() {
        questionVODaoConfig.clearIdentityScope();
        storeVODaoConfig.clearIdentityScope();
        userVODaoConfig.clearIdentityScope();
    }

    public QuestionVODao getQuestionVODao() {
        return questionVODao;
    }

    public StoreVODao getStoreVODao() {
        return storeVODao;
    }

    public UserVODao getUserVODao() {
        return userVODao;
    }

}
