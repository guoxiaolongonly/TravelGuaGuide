package com.standard.guaplayguide.ui.web;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RelativeLayout;

import com.standard.guaplayguide.R;
import com.standard.guaplayguide.base.BasePresenter;
import com.standard.guaplayguide.base.BaseTitleBar;
import com.standard.guaplayguide.base.BaseTitleBarActivity;
import com.standard.guaplayguide.ui.widget.group.LoadingPage;
import com.standard.guaplayguide.ui.widget.group.Scene;
import com.standard.guaplayguide.utils.LaunchUtil;
import com.standard.guaplayguide.utils.StatusBarValue;


/**
 * <请描述这个类是干什么的>
 *
 * @author CRAWLER
 * @data: 2016/7/12 15:55
 * @version: V1.0
 */
public class WebActivity<T extends BasePresenter> extends BaseTitleBarActivity<T> {
    protected BaseTitleBar titleBar;
    protected WebGroup webGroup;
    protected WebConfig config;

    public static Bundle buildBundle(WebConfig config) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("WebConfig", config);
        return bundle;
    }

    @Override
    public void getExtra() {
        config = (WebConfig) getIntent().getExtras().getSerializable("WebConfig");
        if (config == null) {
            return;
        }
    }

    @Override
    public void initTitleBar(BaseTitleBar titleBar) {
        this.titleBar = titleBar;
        titleBar.setTitleText(TextUtils.isEmpty(config.title) ? "" : config.title);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_base_web;
    }

    @Override
    public void init() {
        webGroup = WebGroup.create(this, config, new LoadingPage(this, Scene.DEFAULT));
        ((RelativeLayout) findView(R.id.webContainer)).addView(webGroup.getRootView());
        (findView(R.id.webContainer)).setPadding(0, 0, 0, 0);
    }

    @Override
    public void setListener() {
        webGroup.setOnShouldOverrideUrlListener(url -> LaunchUtil.launchDefaultWeb(this, url, ""));
        webGroup.setOnReceiveTitleListener(title -> {
            if (TextUtils.isEmpty(config.title)) {
                titleBar.setTitleText(title);
            }
        });
    }

    @Override
    public StatusBarValue getStatusBar() {
        return new StatusBarValue(false, R.color.transparent);
    }
}
