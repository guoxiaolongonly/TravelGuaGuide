package cn.xiaolongonly.guaplayguide.ui.presenter;

import android.app.Activity;
import android.text.TextUtils;

import com.google.gson.Gson;
import cn.xiaolongonly.guaplayguide.BuildConfig;
import cn.xiaolongonly.guaplayguide.api.DataManager;
import cn.xiaolongonly.guaplayguide.base.BasePresenter;
import cn.xiaolongonly.guaplayguide.bean.ModularBean;
import cn.xiaolongonly.guaplayguide.cache.SPHelp;
import cn.xiaolongonly.guaplayguide.net.rx.CSubscriber;
import cn.xiaolongonly.guaplayguide.net.rx.ErrorThrowable;
import cn.xiaolongonly.guaplayguide.ui.view.IMainView;

import rx.Observable;

/**
 * Created by 小龙 on 2018/1/27.
 */

public class MainPresenter extends BasePresenter<IMainView> {

    public MainPresenter(Activity activity) {
        super(activity);
    }

    public void getData() {
        addSubscribe(loadDataFromLocal().subscribe(new CSubscriber<ModularBean>() {
            @Override
            public void onPrepare() {
                mView.showLoading();
            }

            @Override
            public void onError(ErrorThrowable throwable) {
                //本地无数据或数据异常，从网络加载
                loadDataFromNet();
            }

            @Override
            public void onSuccess(ModularBean modularBean) {
                mView.hideLoading();
                mView.getDataSuccess(modularBean);
            }
        }));
    }

    public void loadDataFromNet() {
        addSubscribe(DataManager.loadData().subscribe(getSubscriber(modularBean -> mView.getDataSuccess(modularBean))));
    }

    public Observable<ModularBean> loadDataFromLocal() {
        return Observable.create(subscriber -> {
            String str = (String) SPHelp.getAppParam(BuildConfig.KEY_DATA_RECORD, "");
            if (!TextUtils.isEmpty(str)) {
                ModularBean modularBean = new Gson().fromJson(str, ModularBean.class);
                subscriber.onNext(modularBean);
                subscriber.onCompleted();
            } else {
                subscriber.onError(new NoDataCacheException());
            }
        });
    }
}
