package cn.xiaolongonly.guaplayguide.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import cn.xiaolongonly.guaplayguide.bean.ModularBean;

import java.util.List;

/**
 * @author xiaolong 719243738@qq.com
 * @version v1.0
 * @function <描述功能>
 * @date: 2018/1/30 11:37
 */

public abstract class BaseAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private List<ModularBean> mModularBeanList;
    private Context mContext;
    private View.OnClickListener mOnModularClickListener;
    public BaseAdapter(Context context,List<ModularBean> modularBeanList) {
        this.mContext=context;
        this.mModularBeanList =modularBeanList;
    }


    public void setModularBeanList(List<ModularBean> modularBeanList) {
        this.mModularBeanList = modularBeanList;
    }

    public void setOnModularClickListener(View.OnClickListener onModularClickListener) {
        this.mOnModularClickListener = onModularClickListener;
    }
}
