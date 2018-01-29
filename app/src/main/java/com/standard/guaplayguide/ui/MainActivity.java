package com.standard.guaplayguide.ui;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.standard.guaplayguide.R;
import com.standard.guaplayguide.base.BaseTitleBar;
import com.standard.guaplayguide.base.BaseTitleBarActivity;
import com.standard.guaplayguide.bean.ModularBean;
import com.standard.guaplayguide.ui.adapter.BigPicPoolAdapter;
import com.standard.guaplayguide.ui.presenter.MainPresenter;
import com.standard.guaplayguide.ui.view.IMainView;

import java.util.ArrayList;

public class MainActivity extends BaseTitleBarActivity<MainPresenter> implements IMainView {
    private RecyclerView rvContent;
    private BigPicPoolAdapter bigPicPoolAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        rvContent = findView(R.id.rvContent);
        rvContent.setLayoutManager(new GridLayoutManager(this, 2));
        bigPicPoolAdapter = new BigPicPoolAdapter(this, new ArrayList<>());
        rvContent.setAdapter(bigPicPoolAdapter);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void initTitleBar(BaseTitleBar titleBar) {
        titleBar.setTitleText("旅行青蛙攻略");
    }

    @Override
    public void getDataSuccess(ModularBean modularBean) {
    }
}
