package com.standard.guaplayguide.base;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.standard.guaplayguide.R;


/**
 * <请描述这个类是干什么的>
 *
 * @data: 2016/7/7 11:11
 * @version: V1.0
 */
public abstract class BaseTitleBarActivity<T extends BasePresenter> extends BaseFuncActivity<T> {

    @Override
    public void setContentView(View contentView) {
        ViewGroup superContentView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.activity_base, null);
        Toolbar toolbar = (Toolbar) superContentView.findViewById(R.id.toolbar);
        RelativeLayout content = (RelativeLayout) superContentView.findViewById(R.id.content);
        content.addView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setSupportActionBar(toolbar);
        BaseTitleBar mTitleBar = new BaseTitleBar(this, toolbar, getDefaultTitleBarLayout());
        mTitleBar.setOnLeftClickListener(view -> onTitleLeftClick());
        initTitleBar(mTitleBar);
        super.setContentView(superContentView);
    }

    public void onTitleLeftClick() {
        finish();
    }

    public int getDefaultTitleBarLayout() {
        return R.layout.titlebar_normal;
    }

    public abstract void initTitleBar(BaseTitleBar titleBar);

}
