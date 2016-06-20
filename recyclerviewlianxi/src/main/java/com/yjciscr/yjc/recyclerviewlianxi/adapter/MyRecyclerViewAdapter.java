package com.yjciscr.yjc.recyclerviewlianxi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yjciscr.yjc.recyclerviewlianxi.R;
import com.yjciscr.yjc.recyclerviewlianxi.bean.WeiPush;

import java.util.List;

/**
 * Created by YJC on 2016/5/27.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {

    private List<WeiPush> list;

    public MyRecyclerViewAdapter(List<WeiPush> list) {
        this.list = list;
    }
    //点击监听事件
    public interface OnRecyclerViewClickListener{
        void onClick(View view, int position);
    }

    private OnRecyclerViewClickListener listener;
    //设置监听事件
    public void setOnRecyclerViewClickListener(OnRecyclerViewClickListener listener){
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        WeiPush weiPush = list.get(position);
        holder.image.setImageResource(R.drawable.appico_wixin);
        holder.title.setText(weiPush.getTitle());
        holder.description.setText(weiPush.getDescription());
        holder.time.setText(weiPush.getTime());
        if (listener!=null){//如果监听器不为空，则回调
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(holder.itemView,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;
        private TextView title;
        private TextView description;
        private TextView time;
        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.img);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            time = (TextView) itemView.findViewById(R.id.time);
        }
    }
}
