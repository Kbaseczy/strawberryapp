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

import com.jack.strawberry.R;
import com.jack.strawberry.StrawberryApplication;
import com.jack.strawberry.greendao.UserVODao;
import com.jack.strawberry.utils.PreferenceUtils;
import com.jack.strawberry.utils.ToastUtils;
import com.jack.strawberry.vo.UserVO;

public class RegisterActivity extends Activity {

    private Button mBackBut;
    private EditText mUserName;
    private EditText mPassword;
    private EditText mOkPassword;
    private Button mRegisterBut;
    private Context context;

    private UserVODao userVODao;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_register);
        context = this;

        this.mUserName = findViewById(R.id.re_username);
        this.mPassword = findViewById(R.id.re_password);
        this.mOkPassword = findViewById(R.id.okpassword);
        this.mRegisterBut = findViewById(R.id.register_but);
        this.mBackBut = findViewById(R.id.but_register_back);


        mBackBut.setOnClickListener(view -> RegisterActivity.this.finish());

        mRegisterBut.setOnClickListener(view -> register());
    }

    public void register() {
        String mUserNameStr = mUserName.getText().toString().trim();
        String mPasswordStr = mPassword.getText().toString().trim();
        String mOkPasswordStr = mOkPassword.getText().toString().trim();

        if (TextUtils.isEmpty(mUserNameStr) || TextUtils.isEmpty(mPasswordStr) || TextUtils.isEmpty(mOkPasswordStr)) {
            ToastUtils.show(context, "请输入账户名密码");
            return;
        }

        if (mPasswordStr.length() < 6) {
            ToastUtils.show(context, "密码长度不能小于6位");
            return;
        }

        if (!mPasswordStr.equals(mOkPasswordStr)) {
            ToastUtils.show(context, "两次输入密码不一致");
            return;
        }

        PreferenceUtils.persistString(context, mUserNameStr, mPasswordStr);
        userVODao = StrawberryApplication.getDaoSession().getUserVODao();
        userVODao.insertOrReplace(new UserVO(null,0,mUserNameStr,mPasswordStr));
        ToastUtils.show(context, "注册成功");

        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
        RegisterActivity.this.finish();
    }
}
