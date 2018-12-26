package com.cai.strawberryapp.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.cai.strawberryapp.BaseClass.ConnWeb;
import com.cai.strawberryapp.BaseClass.HeaderMenu;
import java.io.File;
import java.io.PrintStream;

public class UpdatePassActivity
        extends Activity
{
    private Button but_update_submit;
    private String code;
    private EditText et_update_newpass;
    private Handler handler;
    private EditText mCode;
    private TextView mGetCode;
    private EditText mPhone;
    private String newpass;
    private String phone;
    private TextView tv_update_username;
    private String username;
    private ProgressDialog waitingDialog;

    public void CountDown()
    {
        new CountDownTimer(60000L, 1000L)
        {
            public void onFinish()
            {
                UpdatePassActivity.this.mGetCode.setClickable(true);
                UpdatePassActivity.this.mGetCode.setText("���������������");
            }

            public void onTick(long paramAnonymousLong)
            {
                UpdatePassActivity.this.mGetCode.setText(paramAnonymousLong / 1000L + "���������������");
            }
        }.start();
    }

    public void getCodeClick()
    {
        this.mGetCode.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                UpdatePassActivity.this.code = "";
                if ((UpdatePassActivity.this.mPhone.getText().toString().equals("")) || (UpdatePassActivity.this.mPhone.getText().toString() == null))
                {
                    Toast.makeText(UpdatePassActivity.this, "���������������������������", 1000).show();
                    return;
                }
                UpdatePassActivity.this.mGetCode.setClickable(false);
                Toast.makeText(UpdatePassActivity.this, "���������������������������������...", 1000).show();
                UpdatePassActivity.this.phone = UpdatePassActivity.this.mPhone.getText().toString();
                UpdatePassActivity.this.CountDown();
                UpdatePassActivity.this.uploadPhone();
            }
        });
    }

    public void init()
    {
        new HeaderMenu(this, true, "������������", null).initHeaderBut();
        Bundle localBundle = getIntent().getExtras();
        this.et_update_newpass = ((EditText)findViewById(2131361868));
        this.tv_update_username = ((TextView)findViewById(2131361867));
        this.but_update_submit = ((Button)findViewById(2131361872));
        this.mPhone = ((EditText)findViewById(2131361869));
        this.mGetCode = ((TextView)findViewById(2131361871));
        this.mCode = ((EditText)findViewById(2131361870));
        this.tv_update_username.setText(localBundle.get("username").toString());
        this.username = localBundle.get("username").toString();
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        setContentView(2130968593);
        init();
        getCodeClick();
        submit();
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
                        UpdatePassActivity.this.waitingDialog.dismiss();
                        Toast.makeText(UpdatePassActivity.this, "���������������������������������������", 1000).show();
                        continue;
                        if (((String)localObject).equals("1"))
                        {
                            UpdatePassActivity.this.waitingDialog.dismiss();
                            new File("/data/data/" + UpdatePassActivity.this.getPackageName().toString() + "/shared_prefs", "remberPass.xml").delete();
                            Toast.makeText(UpdatePassActivity.this, "������������,������������������", 1000).show();
                            localObject = new Intent(UpdatePassActivity.this, LoginActivity.class);
                            UpdatePassActivity.this.startActivity((Intent)localObject);
                            UpdatePassActivity.this.finish();
                            continue;
                            if (((String)localObject).equals("-1"))
                            {
                                UpdatePassActivity.this.waitingDialog.dismiss();
                                Toast.makeText(UpdatePassActivity.this, "���������������������������������������", 1000).show();
                            }
                        }
                    }
                }
            }
        };
    }

    public void submit()
    {
        this.but_update_submit.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                UpdatePassActivity.this.newpass = UpdatePassActivity.this.et_update_newpass.getText().toString();
                if (!UpdatePassActivity.this.mCode.getText().toString().equals(UpdatePassActivity.this.code))
                {
                    Toast.makeText(UpdatePassActivity.this, "������������������������������", 1000).show();
                    return;
                }
                if ((UpdatePassActivity.this.et_update_newpass.getText().toString().equals("")) || (UpdatePassActivity.this.et_update_newpass.getText().toString() == null))
                {
                    Toast.makeText(UpdatePassActivity.this, "������������������", 1000).show();
                    return;
                }
                if (UpdatePassActivity.this.et_update_newpass.getText().toString().length() < 6)
                {
                    Toast.makeText(UpdatePassActivity.this, "������������������6������", 1000).show();
                    return;
                }
                UpdatePassActivity.this.waitingDialog = new ProgressDialog(UpdatePassActivity.this);
                UpdatePassActivity.this.waitingDialog.setTitle("������������...");
                UpdatePassActivity.this.waitingDialog.setMessage("���������...");
                UpdatePassActivity.this.waitingDialog.setIndeterminate(true);
                UpdatePassActivity.this.waitingDialog.setCancelable(false);
                UpdatePassActivity.this.waitingDialog.show();
                new Thread()
                {
                    public void run()
                    {
                        try
                        {
                            String str = ConnWeb.ConnForJson(UpdatePassActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/register/UpdateUserAjax", "phone=" + UpdatePassActivity.this.phone + "&username=" + UpdatePassActivity.this.username + "&newpass=" + UpdatePassActivity.this.newpass, "POST", 0, null, null);
                            System.out.println("================xxxxxxxx================");
                            Message localMessage = new Message();
                            localMessage.obj = str;
                            UpdatePassActivity.this.handler.sendMessage(localMessage);
                            return;
                        }
                        catch (Exception localException)
                        {
                            localException.printStackTrace();
                        }
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
                    String str = ConnWeb.ConnForJson(UpdatePassActivity.this, "http://118.190.155.221:8098/StrawAdminWeb/register/TestCodeAjax", "phone=" + UpdatePassActivity.this.phone, "POST", 0, null, null);
                    System.out.println("================xxxxxxxx================");
                    UpdatePassActivity.this.code = str;
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
