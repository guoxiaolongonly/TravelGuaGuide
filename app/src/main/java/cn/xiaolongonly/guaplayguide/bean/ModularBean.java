package cn.xiaolongonly.guaplayguide.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author xiaolong 719243738@qq.com
 * @version v1.0
 * @function <这么写完全是为了可扩展开放！！>
 * @date: 2018/1/26 09:00
 */

public class ModularBean implements Serializable {

    //字段编号
    @SerializedName("id")
    public int id;
    //更新日期
    @SerializedName("updateDate")
    public String updateDate;
    //当前项名称
    @SerializedName("title")
    public String title;
    //当前项描述
    @SerializedName("describe")
    public String describe;
    //图片url
    @SerializedName("imageUrl")
    public String imageUrl;
    //web页地址
    @SerializedName("webUrl")
    public String webUrl;
    //这个字段用来确定子项界面的样式
    @SerializedName("dataType")
    public int dataType;
    //子项列表
    @SerializedName("childrenModular")
    public List<ModularBean> childrenModular;
}
