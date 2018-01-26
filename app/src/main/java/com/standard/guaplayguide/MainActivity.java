package com.standard.guaplayguide;

import android.os.Bundle;

import com.standard.guaplayguide.api.DataManager;
import com.standard.guaplayguide.base.BaseFuncActivity;
import com.standard.guaplayguide.utils.LogUtil;

public class MainActivity extends BaseFuncActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        DataManager.loadData().subscribe(modularBean -> {
            LogUtil.d(modularBean.data);
        });
    }

    @Override
    protected void setListener() {

    }
}
