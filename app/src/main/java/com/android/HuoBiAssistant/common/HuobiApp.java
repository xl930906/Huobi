package com.android.HuoBiAssistant.common;

import android.app.Application;

import com.android.HuoBiAssistant.model.dbmodel.EntrustDB;

import org.litepal.LitePalApplication;
import org.litepal.crud.DataSupport;

import java.util.Collections;
import java.util.List;

/**
 * Created by heyiyong on 2016/4/15.
 */
public class HuobiApp extends Application {

    public static List<EntrustDB> currentEntrusts;
    public static List<EntrustDB> currentEntrusts2;
    @Override
    public void onCreate() {
        super.onCreate();
        LitePalApplication.initialize(this);//初始化LitePal

        // 初始化数据，拿到当前委托的任务，并让其成为线程安全的列表
        currentEntrusts = Collections.synchronizedList(EntrustDB.where("status = ? or status = ? ", "1", "2").find(EntrustDB.class));
        currentEntrusts2=Collections.synchronizedList(EntrustDB.where("status = ? ", "3").limit(3).find(EntrustDB.class));
        System.out.println("currentEntrusts = " + currentEntrusts);
    }


    public static EntrustDB getEntrustByUUId(long uuid) {
        for (EntrustDB entrustDB : currentEntrusts) {
            if (entrustDB.getBaseObjId() == uuid) {
                return entrustDB;
            }
        }
        return null;
    }
}
