package com.standard.guaplayguide.ui;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.standard.guaplayguide.R;
import com.standard.guaplayguide.base.BaseTitleBar;
import com.standard.guaplayguide.base.BaseTitleBarActivity;
import com.standard.guaplayguide.utils.LaunchUtil;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by 小龙 on 2018/2/1.
 */

public class AboutActivity extends BaseTitleBarActivity {
    private TextView tvProject;
    private TextView tvDataFrom;
    private TextView tvIssue;
    private ImageView ivIcon;

    @Override
    public void initTitleBar(BaseTitleBar titleBar) {
        titleBar.setTitleText("关于");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void init() {
        tvProject = findView(R.id.tvProject);
        tvDataFrom = findView(R.id.tvDataFrom);
        tvIssue = findView(R.id.tvIssue);
        ivIcon = findView(R.id.ivIcon);
    }

    @Override
    protected void setListener() {
        interceptHyperLink(tvProject);
        interceptHyperLink(tvDataFrom);
        interceptHyperLink(tvIssue);
//        ClickView(tvProject).subscribe(o -> LaunchUtil.launchDefaultWeb(this, tvProject.getUrls()[0].getURL(), ""));
//        ClickView(tvDataFrom).subscribe(o -> LaunchUtil.launchDefaultWeb(this, tvDataFrom.getUrls()[0].getURL(), ""));
//        ClickView(tvIssue).subscribe(o -> LaunchUtil.launchDefaultWeb(this, tvIssue.getUrls()[0].getURL(), ""));
        Glide.with(this).load(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .bitmapTransform(new RoundedCornersTransformation(this, 16, 0, RoundedCornersTransformation.CornerType.ALL))
                .into(ivIcon);
    }

    /**
     * 拦截超链接
     *
     * @param tv
     */
    private void interceptHyperLink(TextView tv) {
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        CharSequence text = tv.getText();
        if (text instanceof Spannable) {
            int end = text.length();
            Spannable spannable = (Spannable) tv.getText();
            URLSpan[] urlSpans = spannable.getSpans(0, end, URLSpan.class);
            if (urlSpans.length == 0) {
                return;
            }

            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
            // 循环遍历并拦截 所有http://开头的链接
            for (URLSpan uri : urlSpans) {
                String url = uri.getURL();
                if (url.indexOf("http://") == 0 || url.indexOf("https://") == 0) {
                    spannableStringBuilder.setSpan(
                            new ClickableSpan() {
                                @Override
                                public void onClick(View view) {
                                    LaunchUtil.launchDefaultWeb(AboutActivity.this, url, "");
                                }
                            }, spannable.getSpanStart(uri),
                            spannable.getSpanEnd(uri), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                }
            }
            tv.setText(spannableStringBuilder);
        }
    }
}
