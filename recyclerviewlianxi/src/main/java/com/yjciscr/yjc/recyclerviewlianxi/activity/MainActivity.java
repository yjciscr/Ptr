package com.yjciscr.yjc.recyclerviewlianxi.activity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.yjciscr.yjc.recyclerviewlianxi.R;
import com.yjciscr.yjc.recyclerviewlianxi.adapter.MyRecyclerViewAdapter;
import com.yjciscr.yjc.recyclerviewlianxi.bean.WeiPush;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<WeiPush> list = new ArrayList<>();
    private MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            WeiPush weiPush = new WeiPush();
            weiPush.setImage(R.drawable.appico_wixin);
            weiPush.setTitle("长者");
            weiPush.setDescription("苟利国家生死以，岂因福祸趋避之");
            weiPush.setTime("20:" + i);
            list.add(weiPush);
        }
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_main);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //默认动画效果
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new MyRecyclerViewAdapter(list);
        adapter.setOnRecyclerViewClickListener(new MyRecyclerViewAdapter.OnRecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "我为" + list.get(position).getTitle() + "续" + position + "s", Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            Paint paint = new Paint();

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }

            @Override
            public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDrawOver(c, parent, state);
                paint.setColor(Color.LTGRAY);
                paint.setStrokeWidth(4);
                for (int i = 0, size = parent.getChildCount(); i < size; i++) {
                    View child = parent.getChildAt(i);
                    c.drawLine(child.getLeft(), child.getBottom(), child.getRight(), child.getBottom(), paint);
                }
            }
        });
        //可以提高效率
        mRecyclerView.setHasFixedSize(true);
    }
}
