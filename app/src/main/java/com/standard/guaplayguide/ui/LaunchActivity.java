package com.standard.guaplayguide.ui;

import android.os.Build;
import android.support.annotation.NonNull;

import com.standard.guaplayguide.BuildConfig;
import com.standard.guaplayguide.R;
import com.standard.guaplayguide.base.BaseActivity;
import com.standard.guaplayguide.cache.SPHelp;
import com.standard.guaplayguide.utils.AssetReadUtil;
import com.standard.guaplayguide.utils.LaunchUtil;
import com.standard.guaplayguide.utils.PermissionUtil;
import com.standard.guaplayguide.utils.StatusBarValue;
import com.standard.guaplayguide.utils.ToastUtil;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author xiaolong 719243738@qq.com
 * @version v1.0
 * @function <描述功能>
 * @date: 2018/1/30 09:14
 */

public class LaunchActivity extends BaseActivity {
    private PermissionUtil.PermissionTool permissionTool;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void init() {
        if (Build.VERSION.SDK_INT >= 23) { //针对6.0以后的版本加权限判断
            permissionTool = new PermissionUtil.PermissionTool(() -> initView());
            permissionTool.checkAndRequestPermission(this, permissionTool.requestPermissions);
        } else {
            initView();
        }
    }

    private void initView() {
        boolean isFirstInstall = (boolean) SPHelp.getAppParam(BuildConfig.KEY_FIRST_INSTALL, true);
        Observable.just(isFirstInstall).delay(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        SPHelp.setAppParam(BuildConfig.KEY_FIRST_INSTALL, false);
                        //加载Asset数据并存放到Local中
                        String jsonStr = AssetReadUtil.getJson(this, "Data.json");
                        SPHelp.setAppParam(BuildConfig.KEY_DATA_RECORD, jsonStr);
                        LaunchUtil.launchActivity(this, MainActivity.class);
                    }
                    finish();
                    overridePendingTransition(0, 0);
                });
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionTool.onRequestPermissionResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public StatusBarValue getStatusBar() {
        return new StatusBarValue(false, android.R.color.transparent);
    }
}
