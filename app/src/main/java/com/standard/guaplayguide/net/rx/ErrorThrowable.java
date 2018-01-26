package com.standard.guaplayguide.net.rx;

public class ErrorThrowable extends Throwable {

    public int code;

    public String msg;

    public ErrorThrowable(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
