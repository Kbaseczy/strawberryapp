package com.jack.strawberry;

import android.app.Application;
import android.content.Context;

import com.jack.strawberry.greendao.DaoMaster;
import com.jack.strawberry.greendao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by huangdong on 18/10/8.
 * antony.huang@yeahmobi.com
 */
public class StrawberryApplication extends Application {
    private static DaoSession daoSession;

    public static StrawberryApplication getInstance() {
        return StrawberryApplicationHelper.strawberryApplication;
    }

    static class StrawberryApplicationHelper {
        static StrawberryApplication strawberryApplication = new StrawberryApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setupGreenDao(getApplicationContext());
    }

    private void setupGreenDao(Context context) {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(context, "question_data");
        Database database = openHelper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
