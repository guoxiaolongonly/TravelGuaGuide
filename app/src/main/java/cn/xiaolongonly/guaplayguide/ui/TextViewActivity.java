package cn.xiaolongonly.guaplayguide.ui;

import android.os.Bundle;
import android.widget.TextView;

import cn.xiaolongonly.guaplayguide.base.BaseTitleBar;
import cn.xiaolongonly.guaplayguide.base.BaseTitleBarActivity;
import cn.xiaolongonly.guaplayguide.bean.ModularBean;

/**
 * @author xiaolong 719243738@qq.com
 * @version v1.0
 * @function <描述功能>
 * @date: 2018/1/29 16:44
 */

public class TextViewActivity extends BaseTitleBarActivity {
    private ModularBean modularBean;
    private TextView tvContent;

    public static Bundle buildBundle(ModularBean modularBean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("modular", modularBean);
        return bundle;
    }

    @Override
    public void initTitleBar(BaseTitleBar titleBar) {
        titleBar.setTitleText(modularBean.title);
    }

    @Override
    public void getExtra() {
        modularBean = (ModularBean) getIntent().getSerializableExtra("modular");
    }

    @Override
    protected int getLayoutId() {
        return cn.xiaolongonly.guaplayguide.R.layout.activity_textview;
    }

    @Override
    protected void init() {
        tvContent = findView(cn.xiaolongonly.guaplayguide.R.id.tvContent);
        tvContent.setText(modularBean.describe);
    }

    @Override
    protected void setListener() {

    }
}
