package com.standard.guaplayguide.ui.widget.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.standard.guaplayguide.R;
import com.standard.guaplayguide.base.AppContext;
import com.standard.guaplayguide.bean.ModularBean;
import com.standard.guaplayguide.utils.UiUtil;

/**
 * @author xiaolong 719243738@qq.com
 * @version v1.0
 * @function <描述功能>
 * @date: 2018/2/5 17:23
 */

public class ModularDetailDialog extends BaseAnimDialog {
    private TextView tvTitle;
    private TextView tvContent;
    private ImageView ivImage;
    private static ModularDetailDialog modularDetailDialog;


    public static ModularDetailDialog getInstance(Context context) {
        if (modularDetailDialog == null) {
            synchronized (AppContext.class) {
                if (modularDetailDialog == null) {
                    modularDetailDialog = new ModularDetailDialog(context);
                }
            }
        }
        return modularDetailDialog;
    }

    protected ModularDetailDialog(Context context) {
        super(context);
    }

    @Override
    protected View getContentView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.dialog_modular_detail, null);
    }

    @Override
    protected void initView(View contentView) {
        tvTitle = contentView.findViewById(R.id.tvTitle);
        tvContent = contentView.findViewById(R.id.tvContent);
        ivImage = contentView.findViewById(R.id.ivImage);
    }

    public void showDialog(ModularBean modularBean) {
        super.show();
        tvTitle.setText(modularBean.title);
        tvContent.setText(modularBean.describe);
        UiUtil.setRoundImage(ivImage, modularBean.imageUrl, R.drawable.ic_loading, R.drawable.ic_load_empty, 32);
    }
}
