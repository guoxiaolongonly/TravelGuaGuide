package cn.xiaolongonly.guaplayguide.ui.widget.group;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pnikosis.materialishprogress.ProgressWheel;

import cn.xiaolongonly.guaplayguide.base.AppContext;

public class LoadingHelp {
    private View loadingView;
    private RelativeLayout rlLoading;
    private ProgressWheel mProgressWheel;
    private TextView tvMsg;

    private RelativeLayout rlFailed;
    private ImageView ivLoadImg;
    private TextView tvLoadMsg;
    //    private TextView btnLoadRefresh;
    private TextView tvLoadHint;
    private OnFailClickListener onFailClickListener;


    public LoadingHelp(Context context) {

    }

    public void initView(Context context) {
        loadingView = LayoutInflater.from(context).inflate(cn.xiaolongonly.guaplayguide.R.layout.tcc_loading_page, null);

        rlLoading = (RelativeLayout) loadingView.findViewById(cn.xiaolongonly.guaplayguide.R.id.rlLoading);
        mProgressWheel = (ProgressWheel) loadingView.findViewById(cn.xiaolongonly.guaplayguide.R.id.progress);
        tvMsg = (TextView) loadingView.findViewById(cn.xiaolongonly.guaplayguide.R.id.tv_msg);


        rlFailed = (RelativeLayout) loadingView.findViewById(cn.xiaolongonly.guaplayguide.R.id.rlFailed);
        ivLoadImg = (ImageView) loadingView.findViewById(cn.xiaolongonly.guaplayguide.R.id.ivLoadImg);
        tvLoadMsg = (TextView) loadingView.findViewById(cn.xiaolongonly.guaplayguide.R.id.tvLoadMsg);
        tvLoadHint = (TextView) loadingView.findViewById(cn.xiaolongonly.guaplayguide.R.id.tvLoadHint);
//        btnLoadRefresh = (TextView) loadingView.findViewById(R.id.btnLoadRefresh);

        rlLoading.setVisibility(View.GONE);
        rlFailed.setVisibility(View.GONE);
    }

    public void showLoading() {
        rlFailed.setVisibility(View.GONE);
        rlLoading.setVisibility(View.VISIBLE);
        tvMsg.setText(AppContext.getString(cn.xiaolongonly.guaplayguide.R.string.load_loading));
    }

    public void showErrorPage(int failCode, LoadResource loadResource) {
        rlLoading.setVisibility(View.GONE);
        rlFailed.setVisibility(View.VISIBLE);
        ivLoadImg.setImageResource(loadResource.getImage());
        tvLoadMsg.setText(loadResource.getErrorText());
        tvLoadHint.setText(loadResource.getErrorHint());
//        btnLoadRefresh.setOnClickListener(v -> {
//            if (onFailClickListener != null) {
//                onFailClickListener.onFailClick(failCode);
//            }
//        });
    }

    public View getRootView() {
        return loadingView;
    }

    public void setOnFailClickListener(OnFailClickListener onFailClickListener) {
        this.onFailClickListener = onFailClickListener;
    }
}
