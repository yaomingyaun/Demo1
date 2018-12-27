package com.bw.ymy.goods.JG;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.bw.ymy.goods.Main2Activity;
import com.bw.ymy.goods.MainActivity;

public class MyService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //跳转到那个页面
        if(intent.getAction().equals("cn.jpush.android.intent.NOTIFICATION_OPENED")){
            Intent intent1 = new Intent(context, Main2Activity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent1);
        }
    }


}
