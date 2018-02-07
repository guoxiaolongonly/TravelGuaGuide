package cn.xiaolongonly.guaplayguide.api;

import com.google.gson.JsonParseException;
import cn.xiaolongonly.guaplayguide.BuildConfig;
import cn.xiaolongonly.guaplayguide.api.Exception.NoNetWorkException;
import cn.xiaolongonly.guaplayguide.api.Exception.RetryWhenNetworkException;
import cn.xiaolongonly.guaplayguide.base.AppContext;
import cn.xiaolongonly.guaplayguide.net.ReturnCode;
import cn.xiaolongonly.guaplayguide.net.rx.ErrorThrowable;


import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * <请描述这个类是干什么的>
 *
 * @data: 2016/7/7 13:39
 * @version: V1.0
 */
public class ResponseHandle {

    public static <T> Func1<Throwable, Observable<? extends T>> errorResumeFunc() {
        return throwable -> {
            if (throwable instanceof UnknownHostException || throwable instanceof JsonParseException) {
                return Observable.error(new ErrorThrowable(ReturnCode.LOCAL_ERROR_TYPE_ERROR, BuildConfig.DEBUG_LOG ? throwable.toString() : AppContext.getString(cn.xiaolongonly.guaplayguide.R.string.load_system_busy)));
            } else if (throwable instanceof SocketTimeoutException || throwable instanceof ConnectException) {
                return Observable.error(new ErrorThrowable(ReturnCode.LOCAL_TIMEOUT_ERROR, BuildConfig.DEBUG_LOG ? throwable.toString() : AppContext.getString(cn.xiaolongonly.guaplayguide.R.string.load_time_out)));
            } else if (throwable instanceof NoNetWorkException) {
                return Observable.error(new ErrorThrowable(ReturnCode.LOCAL_NO_NETWORK, AppContext.getContext().getString(cn.xiaolongonly.guaplayguide.R.string.load_no_network)));
            } else if (throwable instanceof ErrorThrowable) {
                return Observable.error(throwable);
            }
            return Observable.error(new ErrorThrowable(ReturnCode.LOCAL_UNKNOWN_ERROR, BuildConfig.DEBUG_LOG ? throwable.toString() : AppContext.getString(cn.xiaolongonly.guaplayguide.R.string.load_system_busy)));
        };
    }


    public static <T> Observable.Transformer<T , T> applySchedulersWithToken() {
        return tObservable -> tObservable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .onErrorResumeNext(errorResumeFunc())
                .retryWhen(new RetryWhenNetworkException())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
