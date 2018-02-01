package com.standard.guaplayguide.ui.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.standard.guaplayguide.R;


/**
 * <请描述这个类是干什么的>
 *
 * @data: 2016/6/20 16:46
 * @version: V1.0
 */
public abstract class BaseMenuPpw extends PopupWindow implements Animator.AnimatorListener {

    private RelativeLayout ppwContent;
    public Context mContext;
    public View mParentView;
    private View ppwContentView;
    private ObjectAnimator animatorOut;
    private ObjectAnimator animatorIn;


    public BaseMenuPpw() {
        super(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(new ColorDrawable(0x55000000));
        setAnimationStyle(R.style.popwin_anim);
        setOutsideTouchable(true);

    }


    public void showPopupWindow(View parentView) {
        this.mParentView = parentView;
        this.mContext = parentView.getContext();
        if (isShowing()) {
            dismiss();
            return;
        }

        if (ppwContentView == null) {
            ppwContentView = LayoutInflater.from(mContext).inflate(R.layout.ppw_base, null);
            ppwContent = ppwContentView.findViewById(R.id.ppwContent);
            ppwContentView.setOnClickListener(view -> dismiss());
            View contentView = getPpwContentView();
            initView(contentView);
            ppwContent.addView(contentView, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        }
        setContentView(ppwContentView);
        showAsDropDown(parentView);
        animatorIn = ObjectAnimator.ofFloat(ppwContent, "translationY", -1000f, 0f).setDuration(300);
        animatorIn.start();
    }

    @Override
    public void dismiss() {
        if (isShowing() && ppwContent != null) {
            animatorOut = ObjectAnimator.ofFloat(ppwContent, "translationY", 0, -1000f).setDuration(300);
            animatorOut.start();
            animatorOut.addListener(this);
        }

        if (animatorIn != null) {
            if (animatorIn.isStarted() || animatorIn.isRunning()) {
                animatorIn.cancel();
            }
        }
        if (animatorOut != null) {
            if (animatorOut.isStarted() || animatorOut.isRunning()) {
                animatorOut.cancel();
            }
        }
    }

    @Override
    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            setHeight(height);
        }
        super.showAsDropDown(anchor);
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        super.dismiss();
    }

    @Override
    public void onAnimationCancel(Animator animation) {
        super.dismiss();
    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    protected abstract View getPpwContentView();

    protected abstract void initView(View contentView);

}
