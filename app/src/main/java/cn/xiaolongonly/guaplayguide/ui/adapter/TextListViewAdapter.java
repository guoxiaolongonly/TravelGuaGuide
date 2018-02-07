package cn.xiaolongonly.guaplayguide.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cn.xiaolongonly.guaplayguide.bean.ModularBean;
import cn.xiaolongonly.guaplayguide.utils.LogUtil;
import cn.xiaolongonly.guaplayguide.utils.UiUtil;

import java.util.List;

/**
 * 纯文本模块适配器<文字>
 * Created by 小龙 on 2018/1/27.
 */

public class TextListViewAdapter extends RecyclerView.Adapter<TextListViewAdapter.ViewHolder> {
    private final String TAG = getClass().getName();
    private List<ModularBean> mModularBeanList;
    private Context mContext;
    private View.OnClickListener mOnModularClickListener;

    public TextListViewAdapter(Context context, List<ModularBean> modularBeanList) {
        mContext = context;
        mModularBeanList = modularBeanList;
    }

    /**
     * @param modularBeanList
     */
    public void setModularBeanList(List<ModularBean> modularBeanList) {
        if (mModularBeanList == null) {
            LogUtil.e(TAG + "please init data list in construction");
            return;
        }
        mModularBeanList.clear();
        this.mModularBeanList.addAll(modularBeanList);
        notifyDataSetChanged();
    }


    public void setOnModularClickListener(View.OnClickListener onModularClickListener) {
        this.mOnModularClickListener = onModularClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(cn.xiaolongonly.guaplayguide.R.layout.item_text_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(mModularBeanList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mModularBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvModularName;
        private ImageView ivModularImage;

        public ViewHolder(View itemView) {
            super(itemView);
            tvModularName = itemView.findViewById(cn.xiaolongonly.guaplayguide.R.id.tvModularName);
            ivModularImage = itemView.findViewById(cn.xiaolongonly.guaplayguide.R.id.ivModularImage);
        }

        public void setData(ModularBean data, int position) {
            tvModularName.setText(data.title);
            UiUtil.setCircleImage(ivModularImage, data.imageUrl, ivModularImage.getWidth(), cn.xiaolongonly.guaplayguide.R.drawable.ic_loading, cn.xiaolongonly.guaplayguide.R.drawable.ic_loading);
        }
    }
}
