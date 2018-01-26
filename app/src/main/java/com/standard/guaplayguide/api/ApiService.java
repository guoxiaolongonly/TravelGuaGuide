package com.standard.guaplayguide.api;

import com.standard.guaplayguide.bean.ModularBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * <请描述这个类是干什么的>
 *
 * @author caiyk@cncn.com
 * @data: 2016/7/7 11:28
 * @version: V1.0
 */
public interface ApiService {

    @GET("master/app/src/main/assets/data.json")
    Observable<ModularBean> loadData();

}
