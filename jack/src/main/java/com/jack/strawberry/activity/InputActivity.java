package com.jack.strawberry.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jack.strawberry.R;
import com.jack.strawberry.StrawberryApplication;
import com.jack.strawberry.greendao.QuestionVODao;
import com.jack.strawberry.vo.QuestionVO;

public class InputActivity extends Activity {

    Button button, btn_back, btn_list;
    EditText question, anwser_a, anwser_b, anwser_real;
    TextView textView;
    private QuestionVODao questionVODao;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
//        toolbar = findViewById(R.id.toolbar_input);
//        toolbar.setNavigationOnClickListener(v-> finish());
//        toolbar.setTitle(R.string.input);
        question = findViewById(R.id.edit_question);
        anwser_a = findViewById(R.id.edit_anwser_a);
        anwser_b = findViewById(R.id.edit_anwser_b);
        anwser_real = findViewById(R.id.edit_anwser_real);
        button = findViewById(R.id.btn_submit);
        btn_back = findViewById(R.id.back_but);
        btn_list = findViewById(R.id.back_list);
        textView = findViewById(R.id.tv_header);
        textView.setText("添加问题");

        btn_back.setOnClickListener(v -> startActivity(new Intent(this, IndexActivity.class)));
        findViewById(R.id.btn_clear).setOnClickListener(v -> {
            question.setText("");
            anwser_a.setText("");
            anwser_b.setText("");
            anwser_real.setText("");
        });

        questionVODao = StrawberryApplication.getDaoSession().getQuestionVODao();

        button.setOnClickListener(v -> {
            String questions = question.getText().toString().trim();
            String anwser_A = anwser_a.getText().toString().trim();
            String anwser_B = anwser_b.getText().toString().trim();
            String anwser = anwser_real.getText().toString().trim();
            if (anwser.equals(anwser_A) || anwser.equals(anwser_B)) {
                QuestionVO questionVO = new QuestionVO(null, questions, anwser_A, anwser_B, anwser);
                Log.v("jankin_内容", questions + " 答案 " + anwser_A + anwser_B + anwser);
                questionVODao.insertOrReplace(questionVO);
                startActivity(new Intent(this, QuestionListActivity.class).putExtra("zhuanjia", 1));
                finish();
            } else {
                Toast.makeText(this, "录入有误，请重新录入", Toast.LENGTH_SHORT).show();
            }

        });

        btn_list.setOnClickListener(v ->
                startActivity(new Intent(this, QuestionListActivity.class)));
    }
}
