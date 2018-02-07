package cn.xiaolongonly.guaplayguide.ui.widget.group;


import android.view.ViewGroup;

public interface IGroupLoadingHelp {

    void createLoadingPage(ViewGroup rootView);

    void showLoading();

    boolean isShowLaunchPage();

    void showFailPage(int failCode);

    void showEmptyLoadingPage();

    void setOnFailClickListener(OnFailClickListener onFailClickListener);


    void hideLoading();

}
