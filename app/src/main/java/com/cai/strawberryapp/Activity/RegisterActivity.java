package com.cai.strawberryapp.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.cai.strawberryapp.BaseClass.ConnWeb;
import java.io.PrintStream;

public class RegisterActivity extends Activity {
    private String code;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler()
    {
        public void handleMessage(Message paramAnonymousMessage)
        {
            super.handleMessage(paramAnonymousMessage);
            switch (Integer.parseInt((String)paramAnonymousMessage.obj))
            {
                default:
                    return;
                case 1:
                    Toast.makeText(RegisterActivity.this, "���������������", 1000).show();
                    paramAnonymousMessage = new Intent(RegisterActivity.this, LoginActivity.class);
                    RegisterActivity.this.startActivity(paramAnonymousMessage);
                    RegisterActivity.this.finish();
                    return;
                case 2:
                    Toast.makeText(RegisterActivity.this, "������������������������", 1000).show();
                    return;
                case 3:
                    Toast.makeText(RegisterActivity.this, "���������������������������", 1000).show();
                    return;
            }
            Toast.makeText(RegisterActivity.this, "���������������", 1000).show();
        }
    };
    private Button mBackBut;
    private EditText mCode;
    private TextView mGetCode;
    private EditText mOkPassword = null;
    private String mOkPasswordStr = "";
    private EditText mPassword = null;
    private String mPasswordStr = "";
    private EditText mPhone = null;
    private String mPhoneStr = "";
    private Button mRegisterBut = null;
    private EditText mUserName = null;
    private String mUserNameStr = "";
    private String phone;

    public void CountDown()
    {
        new CountDownTimer(60000L, 1000L)
        {
            public void onFinish()
            {
                RegisterActivity.this.mGetCode.setClickable(true);
                RegisterActivity.this.mGetCode.setText("���������������");
            }

            public void onTick(long paramAnonymousLong)
            {
                RegisterActivity.this.mGetCode.setText(paramAnonymousLong / 1000L + "���������������");
            }
        }.start();
    }

    public void backButClick()
    {
        this.mBackBut.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                RegisterActivity.this.finish();
            }
        });
    }

    public void getCodeClick()
    {
        this.mGetCode.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                RegisterActivity.this.code = "";
                if ((RegisterActivity.this.mPhone.getText().toString().equals("")) || (RegisterActivity.this.mPhone.getText().toString() == null))
                {
                    Toast.makeText(RegisterActivity.this, "���������������������������", 1000).show();
                    return;
                }
                Toast.makeText(RegisterActivity.this, "���������������������������������...", 1000).show();
                RegisterActivity.this.mGetCode.setClickable(false);
                RegisterActivity.this.phone = RegisterActivity.this.mPhone.getText().toString();
                RegisterActivity.this.CountDown();
                RegisterActivity.this.uploadPhone();
            }
        });
    }

    public void init()
    {
        this.mUserName = ((EditText)findViewById(2131361858));
        this.mPassword = ((EditText)findViewById(2131361859));
        this.mOkPassword = ((EditText)findViewById(2131361860));
        this.mPhone = ((EditText)findViewById(2131361861));
        this.mRegisterBut = ((Button)findViewById(2131361864));
        this.mGetCode = ((TextView)findViewById(2131361863));
        this.mCode = ((EditText)findViewById(2131361862));
        this.mBackBut = ((Button)findViewById(2131361857));
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(2130968590);
        init();
        backButClick();
        getCodeClick();
        register();
    }

    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
        if (paramMotionEvent.getAction() == 0)
        {
            InputMethodManager localInputMethodManager = (InputMethodManager)getSystemService("input_method");
            if ((getCurrentFocus() != null) && (getCurrentFocus().getWindowToken() != null)) {
                localInputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
            }
        }
        return super.onTouchEvent(paramMotionEvent);
    }

    public void register()
    {
        this.mRegisterBut.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                if (!RegisterActivity.this.mCode.getText().toString().equals(RegisterActivity.this.code))
                {
                    Toast.makeText(RegisterActivity.this, "������������������������������", 1000).show();
                    return;
                }
                RegisterActivity.this.mUserNameStr = RegisterActivity.this.mUserName.getText().toString().trim();
                RegisterActivity.this.mPasswordStr = RegisterActivity.this.mPassword.getText().toString().trim();
                RegisterActivity.this.mOkPasswordStr = RegisterActivity.this.mOkPassword.getText().toString().trim();
                RegisterActivity.this.mPhoneStr = RegisterActivity.this.mPhone.getText().toString().trim();
                if ((RegisterActivity.this.mUserNameStr == "") || (RegisterActivity.this.mPasswordStr == "") || (RegisterActivity.this.mOkPasswordStr == "") || (RegisterActivity.this.mPhoneStr == ""))
                {
                    Toast.makeText(RegisterActivity.this, "������������������", 1000).show();
                    return;
                }
                if (RegisterActivity.this.mPasswordStr.length() < 6)
                {
                    Toast.makeText(RegisterActivity.this, "������������������6������", 1000).show();
                    return;
                }
                if (!RegisterActivity.this.mPasswordStr.equals(RegisterActivity.this.mOkPasswordStr))
                {
                    Toast.makeText(RegisterActivity.this, "���������������������������", 1000).show();
                    return;
                }
                new Thread()
                {
                    public void run()
                    {
                        String str = "username=" + RegisterActivity.this.mUserNameStr + "&password=" + RegisterActivity.this.mPasswordStr + "&phone=" + RegisterActivity.this.mPhoneStr;
                        str = ConnWeb.ConnForJson(RegisterActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/register/AddUserAjax", str, "POST", 0, null, null);
                        System.out.println("result" + str);
                        Message localMessage = new Message();
                        localMessage.obj = str;
                        RegisterActivity.this.handler.sendMessage(localMessage);
                    }
                }.start();
            }
        });
    }

    public void uploadPhone()
    {
        new Thread()
        {
            public void run()
            {
                try
                {
                    String str = ConnWeb.ConnForJson(RegisterActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/register/TestCodeAjax", "phone=" + RegisterActivity.this.phone, "POST", 0, null, null);
                    System.out.println("================xxxxxxxx================");
                    RegisterActivity.this.code = str;
                    return;
                }
                catch (Exception localException)
                {
                    localException.printStackTrace();
                }
            }
        }.start();
    }
}
