package com.standard.guaplayguide.api;

import com.standard.guaplayguide.net.retrofit.RetrofitDao;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * <请描述这个类是干什么的>
 *
 * @author caiyk@cncn.com
 * @data: 2016/7/7 11:28
 * @version: V1.0
 */
public class Dao {
    private static ApiService mApiService;

    public static ApiService getApiService() {
        if (mApiService == null) {
            synchronized (Dao.class) {
                if (mApiService == null) {
                    mApiService = RetrofitDao.buildRetrofit(new RetrofitDao.IBuildPublicParams() {
                        @Override
                        public HttpUrl.Builder buildPublicParams(HttpUrl.Builder builder) {
                            return buildPublicParams(builder);
                        }
                    }).create(ApiService.class);
                }
            }
        }
        return mApiService;
    }

    private static HttpUrl.Builder buildPublicParams(HttpUrl.Builder builder) {
//        builder.addHeader("ver", BuildConfig.INTER_VERSION);
//        builder.addHeader("platform", BuildConfig.DEVICE_TYPE);
        return builder;
    }
}
