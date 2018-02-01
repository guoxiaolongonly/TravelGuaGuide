package com.standard.guaplayguide.ui;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.standard.guaplayguide.R;
import com.standard.guaplayguide.base.BaseTitleBar;
import com.standard.guaplayguide.base.BaseTitleBarActivity;
import com.standard.guaplayguide.bean.ModularBean;
import com.standard.guaplayguide.ui.adapter.LargerGridViewAdapter;
import com.standard.guaplayguide.ui.presenter.MainPresenter;
import com.standard.guaplayguide.ui.view.IMainView;
import com.standard.guaplayguide.utils.Constant;
import com.standard.guaplayguide.utils.LaunchUtil;

public class MainActivity extends BaseTitleBarActivity<MainPresenter> implements IMainView {
    private RelativeLayout rlContent;
    private LargerGridViewAdapter mModularGridViewAdapter;
    private TextView tvTitle;
    private MainMenuPop mainMenuPop;

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
        mPresenter.getData();
        mainMenuPop = new MainMenuPop();
    }

    @Override
    protected void setListener() {
        mainMenuPop.setOnRefresHClcikListener(view -> {
            mPresenter.loadDataFromNet();
        });
    }


    @Override
    public void getDataSuccess(ModularBean modularBean) {
        tvTitle.setText(modularBean.title);
        initRecyclerView(modularBean);
    }

    private void initRecyclerView(ModularBean modularBean) {
        GridLayoutManager gridLayoutManager = null;
        switch (modularBean.dataType) {
            case Constant.PAGETYPE_LARGERPIC_GRID:
                mModularGridViewAdapter = new LargerGridViewAdapter(this, modularBean.childrenModular, R.layout.item_larger_grid);
                gridLayoutManager = new GridLayoutManager(this, 2);
                break;
            case Constant.PAGETYPE_MIDDLEPIC_GRID:
                mModularGridViewAdapter = new LargerGridViewAdapter(this, modularBean.childrenModular, R.layout.item_middle_grid);
                gridLayoutManager = new GridLayoutManager(this, 3);
                break;
            case Constant.PAGETYPE_SMALLPIC_GRID:
                mModularGridViewAdapter = new LargerGridViewAdapter(this, modularBean.childrenModular, R.layout.item_small_grid);
                gridLayoutManager = new GridLayoutManager(this, 5);
                break;
            case Constant.PAGETYPE_LARGERPIC_LIST:
                mModularGridViewAdapter = new LargerGridViewAdapter(this, modularBean.childrenModular, R.layout.item_larger_list);
                gridLayoutManager = new GridLayoutManager(this, 1);
                break;
            case Constant.PAGETYPE_TEXT_LIST:
                mModularGridViewAdapter = new LargerGridViewAdapter(this, modularBean.childrenModular, R.layout.item_text_list);
                gridLayoutManager = new GridLayoutManager(this, 1);
                break;
        }
        RecyclerView rvContent = new RecyclerView(this);

        rvContent.setAdapter(mModularGridViewAdapter);
        rvContent.setLayoutManager(gridLayoutManager);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        rlContent.addView(rvContent, layoutParams);
        mModularGridViewAdapter.setOnModularClickListener(view -> {
            ModularBean childrenModuler = (ModularBean) view.getTag();
            LaunchUtil.launchActivity(this, childrenModuler);
        });
    }


    @Override
    public void initTitleBar(BaseTitleBar titleBar) {
        tvTitle = (TextView) titleBar.center;
        titleBar.left.setVisibility(View.GONE);
        titleBar.right.setVisibility(View.VISIBLE);
        titleBar.right.setOnClickListener(view -> mainMenuPop.showPopupWindow(titleBar.right));
    }

}
