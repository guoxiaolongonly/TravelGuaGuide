package com.standard.guaplayguide.api;


import com.standard.guaplayguide.bean.ModularBean;

import rx.Observable;

/**
 * <请描述这个类是干什么的>
 *
 * @author lucky
 * @data: 2016/7/7 13:55
 * @version: V1.0
 */
public class DataManager extends ResponseHandle {

    /**
     * 加载数据
     */
    public static Observable<ModularBean> loadData() {
        return Dao.getApiService().loadData().compose(applySchedulersWithToken());
    }

}
