package com.standard.guaplayguide.net.rx;



public interface ISubscriber<T> {
    void onPrepare();

    void onError(ErrorThrowable throwable);

    void onSuccess(T t);
}
