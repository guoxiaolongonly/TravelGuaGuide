package com.standard.guaplayguide.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * @author CRAWLER
 */
public class CommonActivity extends BaseTitleBarActivity {
    private RelativeLayout rlContent;
    private LargerGridViewAdapter mModularGridViewAdapter;
    private TextView tvTitle;
    private ModularBean mModularBean;

    public static Bundle buildBundle(ModularBean modularBean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("modularBean", modularBean);
        return bundle;
    }

    @Override
    public void getExtra() {
        mModularBean = (ModularBean) getIntent().getSerializableExtra("modularBean");
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
        tvTitle.setText(mModularBean.title);
        initRecyclerView(mModularBean);
    }

    @Override
    protected void setListener() {
    }


    private void initRecyclerView(ModularBean modularBean) {
        RecyclerView rvContent = new RecyclerView(this);
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
                gridLayoutManager = new GridLayoutManager(this, 4);
                break;
            case Constant.PAGETYPE_LARGERPIC_LIST:
                mModularGridViewAdapter = new LargerGridViewAdapter(this, modularBean.childrenModular, R.layout.item_larger_list);
                gridLayoutManager = new GridLayoutManager(this, 1);
                break;
            case Constant.PAGETYPE_TEXT_LIST:
                mModularGridViewAdapter = new LargerGridViewAdapter(this, modularBean.childrenModular, R.layout.item_text_list);
                gridLayoutManager = new GridLayoutManager(this, 1);
                rvContent.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            default:
                break;
        }

        rvContent.setAdapter(mModularGridViewAdapter);
        rvContent.setLayoutManager(gridLayoutManager);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.setMargins(0,60,0,0);
        rlContent.addView(rvContent, layoutParams);
        mModularGridViewAdapter.setOnModularClickListener(view -> {
            ModularBean childrenModuler = (ModularBean) view.getTag();
            if (childrenModuler.dataType == 0) {
                showPopupWindow(childrenModuler);
            } else {
                LaunchUtil.launchActivity(this, childrenModuler);
            }
        });

    }

    private void showPopupWindow(ModularBean childrenModuler) {

    }


    @Override
    public void initTitleBar(BaseTitleBar titleBar) {
        tvTitle = (TextView) titleBar.center;
    }

}
