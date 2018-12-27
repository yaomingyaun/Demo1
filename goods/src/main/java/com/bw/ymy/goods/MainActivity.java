package com.bw.ymy.goods;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.bw.ymy.goods.adapter.MyBase;
import com.bw.ymy.goods.bean.UserBean;
import com.bw.ymy.goods.presenter.IPresenterlpl;
import com.bw.ymy.goods.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IView {

    private XRecyclerView xRecyclerView;
    private  int page=1;
    private MyBase adapter;
    IPresenterlpl iPresenterlpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取资源id
        xRecyclerView=findViewById(R.id.xlistview);
        iPresenterlpl=new IPresenterlpl(this);
        //布局
        manager();
        //点击跳转
        adapter.setOnclickListener(new MyBase.ClickListenter() {
            @Override
            public void onClick(int position) {
                int pid=adapter.getpid(position);
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("pid",pid+"");
                startActivity(intent);
            }
        });
    }


    //网格布局
    private void manager() {

        //
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(gridLayoutManager);
        //适配器
        adapter=new MyBase(this);
        xRecyclerView.setAdapter(adapter);
        //上拉
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
            page=1;
            lodata();
            }

            @Override
            public void onLoadMore() {
        lodata();
            }
        });
        lodata();
    }

    //列表网址
    private  String url="http://www.zhaoapi.cn/product/searchProducts";
    public  void  lodata()
    {
        Map<String,String> map=new HashMap<>();
        map.put("keywords","手机");
        map.put("page",page+"");
        iPresenterlpl.getRequest(url,map,UserBean.class);
    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onsuccess(Object data) {

        if(data instanceof UserBean)
        {
            UserBean bean= (UserBean) data;
            if(page==1)
            {
                adapter.setlist(bean.getData());
            }else
            {
                adapter.addlist(bean.getData());
            }
            page++;
            xRecyclerView.refreshComplete();
            xRecyclerView.loadMoreComplete();
        }

    }
}
