package cn.xiaolongonly.guaplayguide.utils.statusbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;

import cn.xiaolongonly.guaplayguide.utils.StatusBarValue;

/**
 * <请描述这个类是干什么的>
 * 状态栏适配
 *
 * @data: 2016/6/29 15:49
 * @version: V1.0
 */
public class StatusBarCompat {


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void compat(Activity activity, StatusBarValue statusBarValue) {
        if (statusBarValue.isStatusBarOccupying) {
            Eyes.setStatusBarColor(activity, statusBarValue.statusBarColor);
        } else {
            Eyes.translucentStatusBar(activity);
        }
    }

}
