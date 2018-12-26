package com.jack.strawberry.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

import com.jack.strawberry.R;
import com.jack.strawberry.StrawberryApplication;
import com.jack.strawberry.greendao.UserVODao;
import com.jack.strawberry.utils.PreferenceUtils;
import com.jack.strawberry.utils.ToastUtils;

public class LoginActivity extends Activity implements View.OnClickListener {

    public static final String REMBERPASSTAG = "rember_pass_tag";
    public static final String CURRENTACCOUNT = "current_account";
    public static final String SCORE = "score";
    public static final String SCORE_LAST = "score_last";
    private Context context;
    private AutoCompleteTextView mUserEdit;  //用户名
    private EditText mPassEdit;       //密码
    private CheckBox mRemberPass;     //是否记住密码
    private TextView mForgetPass;     //忘记秘密
    private Button mLoginBut;         //登录
    private TextView mRegister,professor;       //注册账号

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_login);
        context = this.getApplicationContext();

        this.mUserEdit = findViewById(R.id.username);
        this.mPassEdit = findViewById(R.id.password);
        this.mRemberPass = findViewById(R.id.rem_cbox);
        this.mForgetPass = findViewById(R.id.tv_notknow_pass);
        this.mLoginBut = findViewById(R.id.login_but);
        this.mRegister = findViewById(R.id.register);
        this.professor = findViewById(R.id.professor);

        mRemberPass.setOnCheckedChangeListener((compoundButton, b) ->
                PreferenceUtils.persistString(context, REMBERPASSTAG, String.valueOf(b)));

        mLoginBut.setOnClickListener(this);
        mForgetPass.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        professor.setOnClickListener(this);
        autoFillPsss();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_but:
                login();
                finish();
                break;
            case R.id.tv_notknow_pass:
                forgetButClick();
                break;
            case R.id.register:
                register();
                break;
            case R.id.professor:
                startActivity(new Intent(this,LoginProffessorActivity.class));
                break;
        }
    }

    //自动填充密码
    public void autoFillPsss() {
        String isRemberPassStr = PreferenceUtils.getString(context, REMBERPASSTAG, "false");
        boolean isRemberPass = Boolean.valueOf(isRemberPassStr);
        mRemberPass.setChecked(isRemberPass);

        if (isRemberPass) {
            mUserEdit.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {

                    String userName = editable.toString().trim();
                    if (!TextUtils.isEmpty(userName)) {
                        boolean isExist = PreferenceUtils.isExist(context, userName);
                        if (isExist) {
                            String passWord = PreferenceUtils.getString(context, userName);
                            mPassEdit.setText(passWord);
                        }
                    }
                }
            });
        }
    }

    //登录
    public void login() {
        String mUserName = mUserEdit.getText().toString().trim();
        String mPassword = mPassEdit.getText().toString().trim();

        if (TextUtils.isEmpty(mUserName) || TextUtils.isEmpty(mPassword)) {
            ToastUtils.show(context, "账户名密码不能为空");
            return;
        }

        boolean isExist = PreferenceUtils.isExist(context, mUserName);
        if (!isExist) {
            ToastUtils.show(context, "账户名不存在");
            return;
        }

        String password = PreferenceUtils.getString(context, mUserName);
        if (TextUtils.isEmpty(password)) {
            ToastUtils.show(context, "账户异常");
            return;
        }

        if (!password.equals(mPassword)) {
            ToastUtils.show(context, "密码输入错误");
            return;
        }

        ToastUtils.show(context, "登录成功");
        PreferenceUtils.persistString(context, CURRENTACCOUNT, mUserName);
        Intent indexIntent = new Intent(context, IndexActivity.class);
        startActivity(indexIntent);
        this.finish();
    }


    public void register() {
        Intent registerIntent = new Intent(context, RegisterActivity.class);
        startActivity(registerIntent);
    }


    public void forgetButClick() {
        Intent forgetIntent = new Intent(context, ForgetPassActivity.class);
        LoginActivity.this.startActivity(forgetIntent);
    }


    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
        if (paramInt == 4) {
            showExitDialog();
        }
        return super.onKeyDown(paramInt, paramKeyEvent);
    }

    private void showExitDialog() {
        Builder localBuilder = new Builder(this);
        localBuilder.setMessage("是否退出？");
        localBuilder.setPositiveButton("是", (paramAnonymousDialogInterface, paramAnonymousInt)
                -> System.exit(0));
        localBuilder.setNeutralButton("否", (paramAnonymousDialogInterface, paramAnonymousInt)
                -> paramAnonymousDialogInterface.cancel());
        localBuilder.show();
    }


}
