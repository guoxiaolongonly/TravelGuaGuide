package com.standard.guaplayguide.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.standard.guaplayguide.R;
import com.standard.guaplayguide.base.BaseTitleBar;
import com.standard.guaplayguide.base.BaseTitleBarActivity;
import com.standard.guaplayguide.bean.ModularBean;
import com.standard.guaplayguide.ui.adapter.LargerGridViewAdapter;
import com.standard.guaplayguide.ui.presenter.MainPresenter;
import com.standard.guaplayguide.ui.view.IMainView;

import java.util.ArrayList;
import java.util.List;

public class CommonActivity extends BaseTitleBarActivity<MainPresenter> implements IMainView {
    private RelativeLayout rlContent;
    private LargerGridViewAdapter mModularGridViewAdapter;

    public static Bundle buildBundle(ModularBean modularBean)
    {
        return null;
    }

    @Override
    public MainPresenter getPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        rlContent = findView(R.id.rlContent);
//        rlContent.setLayoutManager(new GridLayoutManager(this, 2));
//        mModularGridViewAdapter = new LargerGridViewAdapter(this, new ArrayList<>(), R.layout.item_larger_grid);
//        rvContent.setAdapter(mModularGridViewAdapter);
        mPresenter.getData();
    }

    @Override
    protected void setListener() {

    }


    @Override
    public void getDataSuccess(ModularBean modularBean) {
        List<ModularBean> modularBeanList = new ArrayList<>();
        modularBeanList.add(modularBean);
        mModularGridViewAdapter.setModularBeanList(modularBeanList);
        mModularGridViewAdapter.notifyDataSetChanged();
    }


    @Override
    public void initTitleBar(BaseTitleBar titleBar) {
        titleBar.setTitleText("旅行青蛙攻略");
    }
}
