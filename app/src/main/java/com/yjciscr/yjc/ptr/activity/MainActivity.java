package com.yjciscr.yjc.ptr.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.yjciscr.yjc.ptr.R;
import com.yjciscr.yjc.ptr.adapter.MyRecyclerViewAdapter;
import com.yjciscr.yjc.ptr.bean.Cnm;
import com.yjciscr.yjc.ptr.utils.Constans;
import com.yjciscr.yjc.ptr.utils.JsonParser;
import com.yjciscr.yjc.ptr.utils.RecyclerItemClickListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MyRecyclerViewAdapter adapter;
    private List<Cnm> cnmList = new ArrayList<>();

    private PtrFrameLayout mPtrFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inintView();
        loadData();
    }

    private void inintView() {

        mPtrFrameLayout = (PtrFrameLayout) findViewById(R.id.ptr_main);
        //初始化头部对象
        StoreHouseHeader header = new StoreHouseHeader(this);

        header.initWithString("ARE YOU OK");
        header.setPadding(0,40,0,0);
        header.setTextColor(Color.RED);
        //将头部队形设置，添加到ptrframelayout中
        mPtrFrameLayout.setHeaderView(header);
        mPtrFrameLayout.addPtrUIHandler(header);

       /* mPtrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
                mPtrFrameLayout.autoRefresh();
            }
        },300);*/

        mPtrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPtrFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cnmList.clear();
                        loadData();
                        mPtrFrameLayout.refreshComplete();
                    }
                },2000);
            }
        });

        adapter = new MyRecyclerViewAdapter(this,cnmList);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main);
        SpaceItemDecoration decoration = new SpaceItemDecoration(16);
        //设置边距
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String url = cnmList.get(position).getSurl();
                Intent intent = new Intent(MainActivity.this,WebActivity.class);
                intent.putExtra("urlweb",url);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }

    class SpaceItemDecoration extends RecyclerView.ItemDecoration{
        private int mSpace;

        public SpaceItemDecoration(int mSpace) {
            this.mSpace = mSpace;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.bottom = mSpace;
            outRect.left = mSpace;
            outRect.right = mSpace;
            outRect.top = mSpace;
        }
    }

    private void loadData(){
        Request request = new Request
                .Builder()
                .url(Constans.URL)
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()){
                    ResponseBody responseBody = response.body();
                    final String result = responseBody.string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            cnmList.addAll(JsonParser.getTravelsFromJson(result));
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
    }
}
