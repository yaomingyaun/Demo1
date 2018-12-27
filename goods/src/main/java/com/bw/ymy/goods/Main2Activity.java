package com.bw.ymy.goods;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.ymy.goods.bean.BannerBean;
import com.bw.ymy.goods.presenter.IPresenterlpl;
import com.bw.ymy.goods.view.IView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity implements IView {
    private  int pid;
    private Banner banner;
    private TextView title,price;
    private IPresenterlpl iPresenterlpl;
    private Button jia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //获取资源id
        banner=findViewById(R.id.banner1);
        title=findViewById(R.id.title);
        price=findViewById(R.id.price);
        jia=findViewById(R.id.jia);
        iPresenterlpl=new IPresenterlpl(this);
        //接受过来的值
        Intent intent=getIntent();
        final  String pids=intent.getStringExtra("pid");
        //轮播图
        banner.setImageLoader(new ImageLoaderInterface<ImageView>() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }

            @Override
            public ImageView createImageView(Context context) {
                ImageView imageView=new ImageView(context);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                return imageView;
            }
        });
        //获取
        Map<String,String> map=new HashMap<>();
        map.put("pid",pids);
        iPresenterlpl.getRequest("http://www.zhaoapi.cn/product/getProductDetail",map,BannerBean.class);
    }

    @Override
    public void onsuccess(Object data) {
        List<String> list=new ArrayList<>();
        BannerBean bannerBean= (BannerBean) data;
        String[] split=bannerBean.getData().getImages().split("\\|");
        for (int i=0;i<split.length;i++)
        {
            list.add(split[i]);
        }
        banner.setImages(list);
        //轮播开始
        banner.start();
        //名称
        title.setText(bannerBean.getData().getTitle());
        //价格
        price.setText("价格"+bannerBean.getData().getPrice()+"");

    }
}
