package com.standard.guaplayguide.utils.statusbar;

import android.app.Activity;

import com.standard.guaplayguide.utils.StatusBarValue;

/**
 * <请描述这个类是干什么的>
 * 状态栏适配
 *
 * @data: 2016/6/29 15:49
 * @version: V1.0
 */
public class StatusBarCompat {

    public static void compat(Activity activity, StatusBarValue statusBarValue) {
        if (statusBarValue.isStatusBarOccupying) {
            Eyes.setStatusBarColor(activity, statusBarValue.statusBarColor);
        } else {
            Eyes.translucentStatusBar(activity);
        }
    }

}
