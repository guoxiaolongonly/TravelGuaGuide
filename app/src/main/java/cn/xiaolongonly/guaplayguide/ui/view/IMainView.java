package cn.xiaolongonly.guaplayguide.ui.view;

import cn.xiaolongonly.guaplayguide.base.ILoadingView;
import cn.xiaolongonly.guaplayguide.bean.ModularBean;

/**
 * Created by 小龙 on 2018/1/27.
 */

public interface IMainView extends ILoadingView {
    void getDataSuccess(ModularBean modularBean);
}
