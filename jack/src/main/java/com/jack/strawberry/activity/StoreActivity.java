package com.jack.strawberry.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.jack.strawberry.R;
import com.jack.strawberry.StrawberryApplication;
import com.jack.strawberry.adapter.RecyclerAdapterStore;
import com.jack.strawberry.greendao.StoreVODao;
import com.jack.strawberry.vo.StoreVO;

import java.util.List;

public class StoreActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        initView();
    }

    private void initView() {
        StoreVODao storeVODao =StrawberryApplication.getDaoSession().getStoreVODao();
        List<StoreVO> list = storeVODao.queryBuilder().list();
        ((TextView) findViewById(R.id.tv_header)).setText("仓库");
        findViewById(R.id.back_but).setOnClickListener(v->finish());
        RecyclerView recyclerView = findViewById(R.id.recycler_shop);
        RecyclerAdapterStore adapterStore = new RecyclerAdapterStore(this);
        adapterStore.setList(list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapterStore);
    }
}
