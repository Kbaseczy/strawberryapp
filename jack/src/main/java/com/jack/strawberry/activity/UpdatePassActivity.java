package com.jack.strawberry.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jack.strawberry.R;
import com.jack.strawberry.utils.PreferenceUtils;
import com.jack.strawberry.utils.ToastUtils;


public class UpdatePassActivity extends Activity {

    private Button but_update_submit;
    private EditText et_update_newpass;
    private TextView tv_update_username;
    private Context context;
    private EditText et_update_oknewpass;


    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_update_pass);
        context = this.getApplicationContext();
        init();
    }

    public void init() {
        ((TextView) findViewById(R.id.tv_header)).setText("修改密码");
        Button button = findViewById(R.id.back_but);
        button.setVisibility(View.VISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tv_update_username = findViewById(R.id.tv_update_username);
        et_update_newpass = findViewById(R.id.et_update_newpass);
        et_update_oknewpass = findViewById(R.id.et_update_oknewpass);
        but_update_submit = findViewById(R.id.but_update_submit);

        String userName = PreferenceUtils.getString(context, LoginActivity.CURRENTACCOUNT);
        if (!TextUtils.isEmpty(userName)) {
            tv_update_username.setText(userName);
        }


        but_update_submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {

                String username = tv_update_username.getText().toString();
                String newpass = et_update_newpass.getText().toString();
                String oknewpass = et_update_oknewpass.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(newpass)) {
                    ToastUtils.show(context, "账户名密码不能为空");
                    return;
                }

                if (newpass.length() < 6) {
                    ToastUtils.show(context, "密码长度不能小于6位");
                    return;
                }

                if (!newpass.equals(oknewpass)) {
                    ToastUtils.show(context, "两次输入密码不一致");
                    return;
                }

                PreferenceUtils.persistString(context, username, newpass);
                ToastUtils.show(context, "密码修改成功");
                finish();

            }
        });
    }

}
