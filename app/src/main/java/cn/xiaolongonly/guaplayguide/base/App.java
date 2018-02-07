package cn.xiaolongonly.guaplayguide.base;

import android.app.Application;

import cn.xiaolongonly.guaplayguide.BuildConfig;
import cn.xiaolongonly.guaplayguide.net.NetworkConfig;
import cn.xiaolongonly.guaplayguide.utils.LogUtil;


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
