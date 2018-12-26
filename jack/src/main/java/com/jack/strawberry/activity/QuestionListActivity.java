package com.jack.strawberry.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jack.strawberry.R;
import com.jack.strawberry.StrawberryApplication;
import com.jack.strawberry.adapter.RecyclerAdapter;
import com.jack.strawberry.greendao.QuestionVODao;
import com.jack.strawberry.utils.DividerItemGridDecoration;
import com.jack.strawberry.vo.QuestionVO;

import java.util.List;

public class QuestionListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);
        findViewById(R.id.back_but).setOnClickListener(v -> {
            startActivity(new Intent(this, InputActivity.class));
            finish();
        });
        initView();
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_question);
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        RecyclerAdapter adapter = new RecyclerAdapter(this);
        QuestionVODao questionVODao = StrawberryApplication.getDaoSession().getQuestionVODao();
        List<QuestionVO> list = questionVODao.queryBuilder().list();
        adapter.setList(list);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemGridDecoration(this));
    }
}
