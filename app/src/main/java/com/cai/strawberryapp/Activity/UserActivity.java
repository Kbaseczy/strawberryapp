package com.cai.strawberryapp.Activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cai.strawberryapp.BaseClass.FooterMenu;
import com.cai.strawberryapp.BaseClass.HeaderMenu;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class UserActivity
        extends Activity {
    private Button but_exit_login;
    private String password;
    private RelativeLayout rl_about;
    private RelativeLayout rl_update_password;
    private RelativeLayout rl_user_que;
    private TextView tv_user_username;
    private Button user_but;
    private String username;




    public void init() {
        try {
            FileInputStream localFileInputStream = new FileInputStream(new File(getCacheDir(), "userInfo.properties"));
            Properties localProperties = new Properties();
            localProperties.load(localFileInputStream);
            localFileInputStream.close();
            this.username = localProperties.getProperty("username");
            this.password = localProperties.getProperty("password");
            this.rl_update_password = ((RelativeLayout) findViewById(2131361874));
            this.rl_user_que = ((RelativeLayout) findViewById(2131361876));
            this.rl_about = ((RelativeLayout) findViewById(2131361878));
            this.but_exit_login = ((Button) findViewById(2131361880));
            this.user_but = ((Button) findViewById(2131361805));
            this.user_but.setBackgroundResource(2130903079);
            this.tv_user_username = ((TextView) findViewById(2131361873));
            this.tv_user_username.setText(this.username);
            new HeaderMenu(this, false, "������������", null).initHeaderBut();
            new FooterMenu(this).initFooterBut();
            return;
        } catch (Exception localException) {
            for (; ; ) {
                localException.printStackTrace();
            }
        }
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_user);
        init();
        rl_update_passwordClick();
        rl_user_queClick();
        rl_aboutClick();
        but_exit_loginClick();
    }


    public void rl_aboutClick() {
        this.rl_about.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                paramAnonymousView = new Intent(UserActivity.this, AboutActivity.class);
                UserActivity.this.startActivity(paramAnonymousView);
            }
        });
    }

    public void rl_update_passwordClick() {
        this.rl_update_password.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                paramAnonymousView = new Intent(UserActivity.this, UpdatePassActivity.class);
                Bundle localBundle = new Bundle();
                localBundle.putString("username", UserActivity.this.username);
                localBundle.putString("password", UserActivity.this.password);
                paramAnonymousView.putExtras(localBundle);
                UserActivity.this.startActivity(paramAnonymousView);
            }
        });
    }

    public void rl_user_queClick() {
        this.rl_user_que.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                paramAnonymousView = new Intent(UserActivity.this, LeaveActivity.class);
                Bundle localBundle = new Bundle();
                localBundle.putString("username", UserActivity.this.username);
                paramAnonymousView.putExtras(localBundle);
                UserActivity.this.startActivity(paramAnonymousView);
                UserActivity.this.finish();
            }
        });
    }

    public void but_exit_loginClick() {
        this.but_exit_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                paramAnonymousView = new Intent(UserActivity.this, LoginActivity.class);
                try {
                    Thread.sleep(500L);
                    UserActivity.this.startActivity(paramAnonymousView);
                    UserActivity.this.finish();
                    return;
                } catch (InterruptedException localInterruptedException) {
                    for (; ; ) {
                        localInterruptedException.printStackTrace();
                    }
                }
            }
        });
    }
}
