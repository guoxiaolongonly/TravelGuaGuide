package com.standard.guaplayguide.ui.presenter;

import android.app.Activity;

import com.standard.guaplayguide.api.DataManager;
import com.standard.guaplayguide.base.BasePresenter;
import com.standard.guaplayguide.ui.view.IMainView;

/**
 * Created by 小龙 on 2018/1/27.
 */

public class MainPresenter extends BasePresenter<IMainView> {

    public MainPresenter(Activity activity) {
        super(activity);
    }

    public void getData() {
        addSubscribe(DataManager.loadData().subscribe(getSubscriber(modularBean -> mView.getDataSuccess(modularBean))));
    }
}
