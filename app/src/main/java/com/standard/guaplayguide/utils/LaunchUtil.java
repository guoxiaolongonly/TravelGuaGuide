package com.standard.guaplayguide.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.standard.guaplayguide.base.BaseActivity;
import com.standard.guaplayguide.bean.ModularBean;
import com.standard.guaplayguide.ui.CommonActivity;
import com.standard.guaplayguide.ui.TextViewActivity;
import com.standard.guaplayguide.ui.web.WebActivity;
import com.standard.guaplayguide.ui.web.WebConfig;

/**
 * <启动Activity的工具类>
 *
 * @author CRAWLER
 * @data: 2016/1/4 18:41
 * @version: V1.0
 */
public class LaunchUtil {

    public static boolean launchActivity(Context context, Class<? extends BaseActivity> cls) {
        return launchActivity(context, cls, null);
    }

    public static boolean launchActivity(Context context, Class<? extends BaseActivity> cls, Bundle bundle) {
        return launchActivity(context, cls, bundle, 0);
    }

    public static boolean launchActivity(Context context, Class<? extends BaseActivity> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (requestCode != 0) {
            ((BaseActivity) context).startActivityForResult(intent, requestCode);
        } else {
            context.startActivity(intent);
        }
        return true;
    }

    public static boolean launchDefaultWeb(Context context, String url, String title) {
        WebConfig webConfig = new WebConfig(url, title, true);
        return launchActivity(context, WebActivity.class, WebActivity.buildBundle(webConfig));
    }

    public static void launchActivity(Context context, ModularBean modularBean) {
        launchActivity(context, modularBean, 0);
    }

    public static void launchActivity(Context context, ModularBean modularBean, int requestCode) {
        switch (modularBean.dataType) {
            case Constant.PAGETYPE_LARGERPIC_GRID:
            case Constant.PAGETYPE_LARGERPIC_LIST:
            case Constant.PAGETYPE_MIDDLEPIC_GRID:
            case Constant.PAGETYPE_SMALLPIC_GRID:
            case Constant.PAGETYPE_TEXT_LIST:
                launchActivity(context, CommonActivity.class, CommonActivity.buildBundle(modularBean), requestCode);
                break;
            case Constant.PAGETYPE_TEXT:
                launchActivity(context, TextViewActivity.class, TextViewActivity.buildBundle(modularBean));
                break;
            case Constant.PAGETYPE_WEB:
                launchDefaultWeb(context, modularBean.webUrl, "");
                break;
        }
    }

}
