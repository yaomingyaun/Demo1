package com.bw.ymy.goods.model;

import com.bw.ymy.goods.mycallback.MyCallBack;

import java.util.Map;

public interface IModel {
    void  getRequest(String urlstr, Map<String, String> map, Class clazz, MyCallBack myCallBack);
}
