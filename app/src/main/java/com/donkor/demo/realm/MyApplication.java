package com.donkor.demo.realm;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * @author donkor
 * csdn blog:http://blog.csdn.net/donkor_
 * 个人微信公众号：donkor
 * 欢迎关注我的微信公众号。有什么问题也可以直接留言，看到之后我会及时回复您。
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        //默认配置
        //RealmConfiguration configuration=new RealmConfiguration.Builder().build();
        //自定义配置
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("donkor.realm")
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }
}
