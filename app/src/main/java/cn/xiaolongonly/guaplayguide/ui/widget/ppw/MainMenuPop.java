package cn.xiaolongonly.guaplayguide.ui.widget.ppw;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import cn.xiaolongonly.guaplayguide.ui.AboutActivity;
import cn.xiaolongonly.guaplayguide.utils.LaunchUtil;

/**
 * Created by 小龙 on 2018/2/1.
 */

public class MainMenuPop extends BaseMenuPpw {
    private TextView tvRefresh;
    private TextView tvAbout;

    private View.OnClickListener onRefresHClcikListener;

    @Override
    protected View getPpwContentView() {
        return LayoutInflater.from(mContext).inflate(cn.xiaolongonly.guaplayguide.R.layout.layout_menu, null);
    }

    @Override
    protected void initView(View contentView) {
        tvRefresh = contentView.findViewById(cn.xiaolongonly.guaplayguide.R.id.tvRefresh);
        tvAbout = contentView.findViewById(cn.xiaolongonly.guaplayguide.R.id.tvAbout);
        setListener();
    }

    private void setListener() {
        tvAbout.setOnClickListener(view -> {
            LaunchUtil.launchActivity(mContext, AboutActivity.class);
        });
        tvRefresh.setOnClickListener(view -> {
            if (onRefresHClcikListener != null) {
                onRefresHClcikListener.onClick(view);
            }
        });
    }

    public void setOnRefresHClcikListener(View.OnClickListener onRefresHClcikListener) {
        this.onRefresHClcikListener = onRefresHClcikListener;
    }
}
