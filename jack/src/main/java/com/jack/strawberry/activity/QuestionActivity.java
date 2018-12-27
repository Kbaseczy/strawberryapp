package com.jack.strawberry.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jack.strawberry.R;
import com.jack.strawberry.utils.PreferenceUtils;

/**
 * Created by huangdong on 18/10/8.
 * antony.huang@yeahmobi.com
 */
public class QuestionActivity extends Activity {

    private Context context;
    private int totlaScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        context = getApplicationContext();
        init();
    }

    private void init() {
        ((TextView) findViewById(R.id.tv_header)).setText("知识问答");
        Button button = findViewById(R.id.back_but);
        button.setVisibility(View.VISIBLE);
        button.setOnClickListener(view -> finish());
        findViewById(R.id.but_question_submit).setOnClickListener(view -> submit());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void submit() {
        RadioGroup one = findViewById(R.id.rg_one);
        int oneID = one.getCheckedRadioButtonId();
        if (oneID == 4) {
            totlaScore = totlaScore + 20;
        }

        RadioGroup two = findViewById(R.id.rg_two);
        int twoID = two.getCheckedRadioButtonId();

        if (twoID == 8) {
            totlaScore = totlaScore + 20;
        }

        RadioGroup three = findViewById(R.id.rg_three);
        int threeID = three.getCheckedRadioButtonId();
        if (threeID == 11) {
            totlaScore = totlaScore + 20;
        }

        RadioGroup four = findViewById(R.id.rg_four);
        int fourID = four.getCheckedRadioButtonId();
        if (fourID == 14) {
            totlaScore = totlaScore + 20;
        }

        RadioGroup five = findViewById(R.id.rg_five);
        int fiveID = five.getCheckedRadioButtonId();
        if (fiveID == 19) {
            totlaScore = totlaScore + 20;
        }

        int score = PreferenceUtils.getInt(this, LoginActivity.SCORE);
        PreferenceUtils.persistInt(this, LoginActivity.SCORE, score + totlaScore);
        Toast.makeText(context, "您的总得分为:" + totlaScore, Toast.LENGTH_LONG).show();
        totlaScore = 0;
    }
}
