package com.jack.strawberry.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jack.strawberry.R;
import com.jack.strawberry.utils.PreferenceUtils;
import com.jack.strawberry.utils.ToastUtils;

public class ForgetPassActivity extends Activity {

    private Button but_forget_submit;
    private EditText et_forget_newpass;
    private EditText et_forget_oknewpass;
    private EditText et_forget_username;
    private Context context;


    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_forget_pass);
        context = this.getApplicationContext();

        ((TextView) findViewById(R.id.tv_header)).setText("忘记密码");
        Button button = findViewById(R.id.back_but);
        button.setVisibility(View.VISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        
        this.et_forget_username = findViewById(R.id.et_forget_username);
        this.et_forget_newpass = findViewById(R.id.et_forget_newpass);
        this.et_forget_oknewpass = findViewById(R.id.et_forget_oknewpass);
        this.but_forget_submit = findViewById(R.id.but_forget_submit);

        but_forget_submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                but_forget_submitClick();
            }
        });
    }


    public void but_forget_submitClick() {

        String userName = et_forget_username.getText().toString().trim();
        String newPass = et_forget_newpass.getText().toString().trim();
        String okNewPass = et_forget_oknewpass.getText().toString().trim();

        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(newPass) || TextUtils.isEmpty(okNewPass)) {
            ToastUtils.show(context, "请输入账户名密码");
            return;
        }

        boolean isExist = PreferenceUtils.isExist(context, userName);
        if (!isExist) {
            ToastUtils.show(context, "账户名不存在");
            return;
        }

        if (newPass.length() < 6) {
            ToastUtils.show(context, "密码长度不能小于6位");
            return;
        }

        if (!newPass.equals(okNewPass)) {
            ToastUtils.show(context, "两次输入密码不一致");
            return;
        }

        PreferenceUtils.persistString(context, userName, newPass);
        ToastUtils.show(context, "重置密码成功");

        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
        ForgetPassActivity.this.finish();
    }

}
