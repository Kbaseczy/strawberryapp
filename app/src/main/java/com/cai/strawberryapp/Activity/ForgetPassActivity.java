package com.cai.strawberryapp.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
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
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.cai.strawberryapp.BaseClass.ConnWeb;
import com.cai.strawberryapp.BaseClass.HeaderMenu;
import java.io.File;
import java.io.PrintStream;

public class ForgetPassActivity
        extends Activity
{
    private Button but_forget_submit;
    private String code;
    private EditText et_forget_code;
    private EditText et_forget_newpass;
    private EditText et_forget_oknewpass;
    private EditText et_forget_username;
    private Handler handler;
    private RelativeLayout header;
    private String mCode;
    private TextView mGetCode;
    private EditText mPhone;
    private String newpass;
    private String oknewpass;
    private String phone;
    private String username;
    private ProgressDialog waitingDialog;

    public void CountDown()
    {
        new CountDownTimer(60000L, 1000L)
        {
            public void onFinish()
            {
                ForgetPassActivity.this.mGetCode.setClickable(true);
                ForgetPassActivity.this.mGetCode.setText("���������������");
            }

            public void onTick(long paramAnonymousLong)
            {
                ForgetPassActivity.this.mGetCode.setText(paramAnonymousLong / 1000L + "���������������");
                ForgetPassActivity.this.mGetCode.setClickable(false);
            }
        }.start();
    }

    public void but_forget_submitClick()
    {
        this.but_forget_submit.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                ForgetPassActivity.this.mCode = ForgetPassActivity.this.et_forget_code.getText().toString();
                if (ForgetPassActivity.this.mCode.equals(ForgetPassActivity.this.code))
                {
                    if ((ForgetPassActivity.this.username.equals("")) || ((ForgetPassActivity.this.username == null) && (ForgetPassActivity.this.newpass.equals(""))) || ((ForgetPassActivity.this.newpass == null) && (ForgetPassActivity.this.oknewpass.equals(""))) || (ForgetPassActivity.this.oknewpass == null))
                    {
                        if (ForgetPassActivity.this.newpass.equals(ForgetPassActivity.this.oknewpass))
                        {
                            ForgetPassActivity.this.waitingDialog = new ProgressDialog(ForgetPassActivity.this);
                            ForgetPassActivity.this.waitingDialog.setTitle("������������...");
                            ForgetPassActivity.this.waitingDialog.setMessage("���������...");
                            ForgetPassActivity.this.waitingDialog.setIndeterminate(true);
                            ForgetPassActivity.this.waitingDialog.setCancelable(false);
                            ForgetPassActivity.this.waitingDialog.show();
                            new Thread()
                            {
                                public void run()
                                {
                                    try
                                    {
                                        String str = ConnWeb.ConnForJson(ForgetPassActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/register/UpdateUserAjax", "phone=" + ForgetPassActivity.this.phone + "&username=" + ForgetPassActivity.this.username + "&newpass=" + ForgetPassActivity.this.newpass, "POST", 0, null, null);
                                        System.out.println("================xxxxxxxx================");
                                        Message localMessage = new Message();
                                        localMessage.obj = str;
                                        ForgetPassActivity.this.handler.sendMessage(localMessage);
                                        return;
                                    }
                                    catch (Exception localException)
                                    {
                                        localException.printStackTrace();
                                    }
                                }
                            }.start();
                            return;
                        }
                        Toast.makeText(ForgetPassActivity.this, "���������������������������", 1000).show();
                        return;
                    }
                    Toast.makeText(ForgetPassActivity.this, "������������������", 1000).show();
                    return;
                }
                Toast.makeText(ForgetPassActivity.this, "������������������������������", 1000).show();
            }
        });
    }

    public void getCodeClick()
    {
        this.mGetCode.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                ForgetPassActivity.this.code = "";
                if ((ForgetPassActivity.this.mPhone.getText().toString().equals("")) || (ForgetPassActivity.this.mPhone.getText().toString() == null))
                {
                    Toast.makeText(ForgetPassActivity.this, "���������������������������", 1000).show();
                    return;
                }
                Toast.makeText(ForgetPassActivity.this, "���������������������������������...", 1000).show();
                ForgetPassActivity.this.mGetCode.setClickable(false);
                ForgetPassActivity.this.phone = ForgetPassActivity.this.mPhone.getText().toString();
                ForgetPassActivity.this.CountDown();
                ForgetPassActivity.this.uploadPhone();
            }
        });
    }

    public void init()
    {
        new HeaderMenu(this, true, "������������", null).initHeaderBut();
        this.header = ((RelativeLayout)findViewById(2131361806));
        this.mGetCode = ((TextView)findViewById(2131361812));
        this.mPhone = ((EditText)findViewById(2131361810));
        this.et_forget_username = ((EditText)findViewById(2131361807));
        this.et_forget_newpass = ((EditText)findViewById(2131361808));
        this.et_forget_oknewpass = ((EditText)findViewById(2131361809));
        this.et_forget_code = ((EditText)findViewById(2131361811));
        this.but_forget_submit = ((Button)findViewById(2131361813));
        this.username = this.et_forget_username.getText().toString();
        this.newpass = this.et_forget_newpass.getText().toString();
        this.oknewpass = this.et_forget_oknewpass.getText().toString();
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(2130968580);
        init();
        getCodeClick();
        but_forget_submitClick();
        this.handler = new Handler()
        {
            public void handleMessage(Message paramAnonymousMessage)
            {
                Object localObject = paramAnonymousMessage.obj.toString();
                switch (((String)localObject).hashCode())
                {
                }
                for (;;)
                {
                    super.handleMessage(paramAnonymousMessage);
                    return;
                    if (((String)localObject).equals("0"))
                    {
                        ForgetPassActivity.this.waitingDialog.dismiss();
                        Toast.makeText(ForgetPassActivity.this, "���������������������������������������", 1000).show();
                        continue;
                        if (((String)localObject).equals("1"))
                        {
                            ForgetPassActivity.this.waitingDialog.dismiss();
                            new File("/data/data/" + ForgetPassActivity.this.getPackageName().toString() + "/shared_prefs", "remberPass.xml").delete();
                            Toast.makeText(ForgetPassActivity.this, "������������,������������������", 1000).show();
                            localObject = new Intent(ForgetPassActivity.this, LoginActivity.class);
                            ForgetPassActivity.this.startActivity((Intent)localObject);
                            ForgetPassActivity.this.finish();
                            continue;
                            if (((String)localObject).equals("-1"))
                            {
                                ForgetPassActivity.this.waitingDialog.dismiss();
                                Toast.makeText(ForgetPassActivity.this, "���������������������������������������", 1000).show();
                            }
                        }
                    }
                }
            }
        };
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

    public void setHeaderHeight()
    {
        this.header.measure(0, 0);
        this.header.setLayoutParams(new LinearLayout.LayoutParams(this.header.getWidth(), this.header.getHeight(), 0.0F));
    }

    public void uploadPhone()
    {
        new Thread()
        {
            public void run()
            {
                try
                {
                    String str = ConnWeb.ConnForJson(ForgetPassActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/register/TestCodeAjax", "phone=" + ForgetPassActivity.this.phone, "POST", 0, null, null);
                    System.out.println("================xxxxxxxx================");
                    ForgetPassActivity.this.code = str;
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
