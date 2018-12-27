package com.bw.ymy.goods.model;

import com.bw.ymy.goods.mycallback.MyCallBack;
import com.bw.ymy.goods.okhttp.ICallBack;
import com.bw.ymy.goods.okhttp.OkHttpNutils;


import java.util.Map;

public class IModellpl implements IModel {
    @Override
    public void getRequest(String urlstr, Map<String, String> map, Class clazz, final MyCallBack myCallBack) {
            //调用Ok
        OkHttpNutils.getInstance().PoseEnqueue(urlstr, map, clazz, new ICallBack() {
            @Override
            public void onsuccess(Object obj) {
                myCallBack.onsuccess(obj);
            }

            @Override
            public void onFail(Exception e) {

                myCallBack.onsuccess(e);
            }
        });
    }
}
