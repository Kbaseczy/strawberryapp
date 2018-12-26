package com.jack.strawberry.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.jack.strawberry.R;
import com.jack.strawberry.adapter.RecyclerAdapterShop;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopActivity extends Activity {
    List<Map<String, String>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        initData();
    }

    private void initView() {
        ((TextView) findViewById(R.id.tv_header)).setText("积分商城");
        findViewById(R.id.back_but).setOnClickListener(v -> finish());
        RecyclerView recyclerView = findViewById(R.id.recycler_shop);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        RecyclerAdapterShop adapterShop = new RecyclerAdapterShop(this);
        adapterShop.setList(list);
        recyclerView.setAdapter(adapterShop);
        recyclerView.setLayoutManager(manager);
    }

    void initData() {
        list = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        Map<String, String> map3 = new HashMap<>();
        Map<String, String> map4 = new HashMap<>();
        Map<String, String> map5 = new HashMap<>();
        Map<String, String> map6 = new HashMap<>();
        Map<String, String> map7 = new HashMap<>();
        Map<String, String> map8 = new HashMap<>();
        Map<String, String> map9 = new HashMap<>();
        Map<String, String> map10 = new HashMap<>();
        Map<String, String> map11 = new HashMap<>();
        Map<String, String> map12 = new HashMap<>();
        Map<String, String> map13 = new HashMap<>();
        Map<String, String> map14 = new HashMap<>();
        Map<String, String> map15 = new HashMap<>();
        Map<String, String> map16 = new HashMap<>();
        map1.put("name", "阿尔卑斯");
        map1.put("score", "10");

        map2.put("name", "绿箭");
        map2.put("score", "20");

        map3.put("name", "玄麦");
        map3.put("score", "10");

        map4.put("name", "数据线");
        map4.put("score", "23");

        map5.put("name", "香水");
        map5.put("score", "50");

        map6.put("name", "钢笔");
        map6.put("score", "100");

        map7.put("name", "笔记本");
        map7.put("score", "50");

        map8.put("name", "棉花糖");
        map8.put("score", "80");

        map9.put("name", "布偶");
        map9.put("score", "40");

        map10.put("name", "木偶");
        map10.put("score", "23");

        map11.put("name", "草稿纸");
        map11.put("score", "50");

        map12.put("name", "圣诞树");
        map12.put("score", "100");

        map13.put("name", "魔法棒");
        map13.put("score", "90");

        map14.put("name", "牛奶");
        map14.put("score", "26");

        map15.put("name", "咖啡");
        map15.put("score", "19");

        map16.put("name", "酸奶");
        map16.put("score", "29");

        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);

        list.add(map7);
        list.add(map8);
        list.add(map9);
        list.add(map10);
        list.add(map11);
        list.add(map12);
        list.add(map13);
        list.add(map14);
        list.add(map15);
        list.add(map16);
        initView();
    }
}
