package cn.xiaolongonly.guaplayguide.base;


import cn.xiaolongonly.guaplayguide.net.rx.ErrorThrowable;

/**
 * <请描述这个类是干什么的>
 *
 * @version: V1.0
 */
public interface ILoadingView {
    void showLoading();

    void showError(ErrorThrowable errorThrowable);

    void hideLoading();
}
