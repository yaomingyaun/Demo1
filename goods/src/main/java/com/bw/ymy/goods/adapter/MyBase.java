package com.bw.ymy.goods.adapter;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.ymy.goods.R;
import com.bw.ymy.goods.bean.UserBean;

import java.util.ArrayList;
import java.util.List;


public class MyBase extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<UserBean.DataBean> mlist;
    private Context mcontext;

    public MyBase(Context context) {
        this.mcontext = context;
        mlist=new ArrayList<>();
    }
    public  void  setlist(List<UserBean.DataBean> datas)
    {
        mlist.clear();
        mlist.addAll(datas);
        notifyDataSetChanged();
    }
    public  void  addlist(List<UserBean.DataBean> datas)
    {

        mlist.addAll(datas);
        notifyDataSetChanged();
    }
    //点击
    public  int getpid(int position)
    {
        return mlist.get(position).getPid();
    }

    //获取布局
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(mcontext,R.layout.item_goods,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    //
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        final UserBean.DataBean bean = mlist.get(i);
        final ViewHolder viewHolder1= (ViewHolder) viewHolder;
            //点击删除
        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
                builder.setTitle("删除");
                builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //获取删除的下标
                        int pos = viewHolder.getLayoutPosition();
                        //从数组里面删除
                        mlist.remove(i);
                        //刷新
                        notifyDataSetChanged();
                    }
                });
                //点击取消删除
                builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
                return true;
            }

        });

        //显示名称
        viewHolder1.title.setText("自营："+mlist.get(i).getTitle());
        //显示名称
        viewHolder1.price.setText(mlist.get(i).getPrice()+"");
        String images=mlist.get(i).getImages();
        String[] split=images.split("\\|");
        Glide.with(mcontext).load(split[0]).into(viewHolder1.icon);
        //点击跳转
       viewHolder1.linearLayout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(listenter!=null)
               {
                   listenter.onClick(i);
               }
           }
       });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder{
        private ImageView icon;
        private TextView title,price;
        private LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //获取
            icon=itemView.findViewById(R.id.icon);
            title=itemView.findViewById(R.id.title);
            price=itemView.findViewById(R.id.price);
            linearLayout=itemView.findViewById(R.id.linear);

        }
    }
    //监听
    public ClickListenter listenter;
    public void  setOnclickListener(ClickListenter clickesiner)
    {
        listenter=clickesiner;
    }
    public  interface ClickListenter
    {
        void onClick(int position);

    }
}
