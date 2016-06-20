package com.yjciscr.yjc.ptr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yjciscr.yjc.ptr.R;
import com.yjciscr.yjc.ptr.bean.Cnm;

import java.util.List;

/**
 * Created by YJC on 2016/5/25.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_DATU = 1;
    private static final int TYPE_PUTONG = 0;
    private static final int TYPE_ZHUANTI = 100;

    private Context context;
    private List<Cnm> cnmList;

    public MyRecyclerViewAdapter(Context context, List<Cnm> cnmList) {
        this.context = context;
        this.cnmList = cnmList;
    }

    @Override
    public int getItemViewType(int position) {
        Cnm cnm = cnmList.get(position);
        int type = Integer.parseInt(cnm.getType());
        if (type == TYPE_PUTONG) {
            return TYPE_PUTONG;
        } else if (type == TYPE_ZHUANTI) {
            return TYPE_ZHUANTI;
        }
        return TYPE_DATU;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_DATU:
                return new DatuViewHolder(LayoutInflater.from(context).inflate(R.layout.item_datu, parent, false));
            case TYPE_PUTONG:
                return new PutongViewHolder(LayoutInflater.from(context).inflate(R.layout.item_putong, parent, false));
            case TYPE_ZHUANTI:
                return new ZhuantiViewHolder(LayoutInflater.from(context).inflate(R.layout.item_zhuanti, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemType = getItemViewType(position);
        Cnm cnm = cnmList.get(position);
        switch (itemType) {
            case TYPE_DATU:
                DatuViewHolder datuViewHolder = (DatuViewHolder) holder;
                datuViewHolder.title_datu.setText(cnm.getTitle());
                Picasso.with(context)
                        .load(cnm.getGroupthumbnail())
                        .into(datuViewHolder.image_datu);
                break;
            case TYPE_PUTONG:
                PutongViewHolder putongViewHolder = (PutongViewHolder) holder;
                putongViewHolder.title_putong.setText(cnm.getTitle());
                putongViewHolder.summary_putong.setText(cnm.getSummary());
                Picasso.with(context)
                        .load(cnm.getGroupthumbnail())
                        .into(putongViewHolder.image_putong);
                break;
            case TYPE_ZHUANTI:
                ZhuantiViewHolder zhuantiViewHolder = (ZhuantiViewHolder) holder;
                zhuantiViewHolder.title_zhuangti.setText(cnm.getTitle());
                Picasso.with(context)
                        .load(cnm.getGroupthumbnail())
                        .into(zhuantiViewHolder.image_zhuangti);
                break;
        }
    }

    @Override
    public int getItemCount() {
        Log.e("yjciscr", cnmList.size() + "");
        return cnmList.size();
    }

    class DatuViewHolder extends RecyclerView.ViewHolder {
        private TextView title_datu;
        private ImageView image_datu;

        public DatuViewHolder(View itemView) {
            super(itemView);
            title_datu = (TextView) itemView.findViewById(R.id.title_datu);
            image_datu = (ImageView) itemView.findViewById(R.id.image_datu);
        }
    }

    class PutongViewHolder extends RecyclerView.ViewHolder {
        private TextView title_putong;
        private TextView summary_putong;
        private ImageView image_putong;

        public PutongViewHolder(View itemView) {
            super(itemView);
            title_putong = (TextView) itemView.findViewById(R.id.title_putong);
            summary_putong = (TextView) itemView.findViewById(R.id.summary_putong);
            image_putong = (ImageView) itemView.findViewById(R.id.image_putong);
        }
    }

    class ZhuantiViewHolder extends RecyclerView.ViewHolder {
        private TextView title_zhuangti;
        private ImageView image_zhuangti;

        public ZhuantiViewHolder(View itemView) {
            super(itemView);
            title_zhuangti = (TextView) itemView.findViewById(R.id.title_zhuanti);
            image_zhuangti = (ImageView) itemView.findViewById(R.id.image_zhuanti);
        }
    }
}
