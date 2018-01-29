package com.standard.guaplayguide.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author xiaolong 719243738@qq.com
 * @version v1.0
 * @function <这么写完全是为了可扩展开放！！>
 * @date: 2018/1/26 09:00
 */

public class ModularBean implements Serializable{

    @SerializedName("id")
    public int id;//字段编号
    @SerializedName("title")
    public String title; //当前项名称
    @SerializedName("describe")
    public String describe; //当前项描述
    @SerializedName("imageUrl")
    public String imageUrl;//图片url
    @SerializedName("webUrl")
    public String webUrl;//web页地址
    @SerializedName("dataType")
    public int dataType; //这个字段用来确定子项界面的样式
    @SerializedName("haveChildrenModular")
    public boolean haveChildrenModular;//是否有子项
    @SerializedName("childrenModular")
    public List<ModularBean> childrenModular;//子项列表
}
