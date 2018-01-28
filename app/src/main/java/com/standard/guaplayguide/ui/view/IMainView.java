package com.standard.guaplayguide.ui.view;

import com.standard.guaplayguide.base.ILoadingView;
import com.standard.guaplayguide.bean.ModularBean;

/**
 * Created by 小龙 on 2018/1/27.
 */

public interface IMainView extends ILoadingView {
    void getDataSuccess(ModularBean modularBean);
}
