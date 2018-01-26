package com.standard.guaplayguide.bean;

import java.util.List;

/**
 * @author xiaolong 719243738@qq.com
 * @version v1.0
 * @function <这么写完全是为了可扩展开放！！>
 * @date: 2018/1/26 09:00
 */

public class ModularBean {
    public String modularName;
    public int dataType; //这边就用来做实体配置对应的实体类，方便做解析
    public String data;//实体类数据
    public int id;//模块id
    public String imageUrl;//
    public boolean isWebView;//是否是web页
    public String webUrl;
    public List<ModularBean> childrenModular;//子模块列表
}
