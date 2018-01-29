package com.standard.guaplayguide.ui;

import android.os.Bundle;

import com.standard.guaplayguide.R;
import com.standard.guaplayguide.base.BaseTitleBar;
import com.standard.guaplayguide.base.BaseTitleBarActivity;
import com.standard.guaplayguide.bean.ModularBean;

/**
 * @author xiaolong 719243738@qq.com
 * @version v1.0
 * @function <描述功能>
 * @date: 2018/1/29 16:44
 */

public class TextViewActivity extends BaseTitleBarActivity {

    public static Bundle buildBundle(ModularBean modularBean) {
        return null;
    }

    @Override
    public void initTitleBar(BaseTitleBar titleBar) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_textview;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void setListener() {

    }
}
