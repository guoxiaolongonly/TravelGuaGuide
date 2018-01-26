package com.standard.guaplayguide.base;

import android.app.Application;

import com.standard.guaplayguide.BuildConfig;
import com.standard.guaplayguide.net.NetworkConfig;
import com.standard.guaplayguide.utils.LogUtil;


/**
 *
 * @author CRAWLER
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext.getInstance().init(this);
        LogUtil.init(BuildConfig.DEBUG_LOG, "CHECKS");
        NetworkConfig.setBaseUrl(BuildConfig.HOST_URL);
    }
}
