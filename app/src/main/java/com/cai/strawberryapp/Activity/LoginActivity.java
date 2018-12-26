package com.cai.strawberryapp.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.cai.strawberryapp.Adapter.AutoCompleteAdapter;
import com.cai.strawberryapp.BaseClass.ConnWeb;
import com.cai.strawberryapp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class LoginActivity extends Activity {

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {

        public void handleMessage(Message paramAnonymousMessage) {

            switch (Integer.parseInt((String) paramAnonymousMessage.obj)) {
                default:
                    return;
                case 1:
                    Toast.makeText(LoginActivity.this, "���������������", Toast.LENGTH_SHORT).show();
                    LoginActivity.this.remberPass();
                    LoginActivity.this.saveUserInfo();
                    Intent indexIntent = new Intent(LoginActivity.this, IndexActivity.class);
                    LoginActivity.this.startActivity(indexIntent);
                    LoginActivity.this.finish();
                    return;
                case -1:
                    LoginActivity.this.mLoginBut.setClickable(true);
                    LoginActivity.this.mLoginBut.setText("������");
                    Toast.makeText(LoginActivity.this, "���������������", Toast.LENGTH_SHORT).show();
                    return;
                case 0:
                    LoginActivity.this.mLoginBut.setClickable(true);
                    LoginActivity.this.mLoginBut.setText("������");
                    Toast.makeText(LoginActivity.this, "���������������������", Toast.LENGTH_SHORT).show();
                    return;
            }

            LoginActivity.this.mLoginBut.setClickable(true);
            LoginActivity.this.mLoginBut.setText("������");
            Toast.makeText(LoginActivity.this, "���������������������������������", Toast.LENGTH_SHORT).show();
        }
    };
    private TextView mForgetPass;
    private Button mLoginBut = null;
    private EditText mPassEdit = null;
    private String mPassword = "";
    private TextView mRegister = null;
    private CheckBox mRemberPass = null;
    private AutoCompleteTextView mUserEdit = null;
    private String mUserName = "";
    private int remberPass = 0;
    private SharedPreferences sp = null;

    private void showExitDialog() {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        localBuilder.setMessage("���������������������");
        localBuilder.setPositiveButton("������", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                System.exit(0);
            }
        });
        localBuilder.setNeutralButton("������", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                paramAnonymousDialogInterface.cancel();
            }
        });
        localBuilder.show();
    }

    public void autoComplete() {
        Object localObject = new ArrayList();
        int i = 0;
        for (; ; ) {
            if (i >= this.sp.getInt("num", 0)) {
                localObject = new AutoCompleteAdapter(this, (List) localObject);
                this.mUserEdit.setAdapter((ListAdapter) localObject);
                return;
            }
            ((List) localObject).add(this.sp.getString("username" + i, "���������"));
            i += 1;
        }
    }

    public void autoCompleteItem() {
        this.mUserEdit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong) {
                paramAnonymousAdapterView = (String) paramAnonymousAdapterView.getItemAtPosition(paramAnonymousInt);
                paramAnonymousInt = 0;
                for (; ; ) {
                    if (paramAnonymousInt >= LoginActivity.this.sp.getInt("num", 0)) {
                        return;
                    }
                    if (LoginActivity.this.sp.getString("username" + paramAnonymousInt, "���������").equals(paramAnonymousAdapterView)) {
                        LoginActivity.this.mPassEdit.setText(LoginActivity.this.sp.getString("password" + paramAnonymousInt, "���������"));
                        return;
                    }
                    paramAnonymousInt += 1;
                }
            }
        });
    }

    public void clickRemberPass() {
        this.mRemberPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean) {
                LoginActivity.this.remberPass = 1;
                if (!LoginActivity.this.mRemberPass.isChecked()) {
                    LoginActivity.this.remberPass = 0;
                }
            }
        });
    }

    public void deleteCachaFile() {
        File[] arrayOfFile;
        int j;
        int i;
        if ((getCacheDir() != null) && (getCacheDir().exists()) && (getCacheDir().isDirectory())) {
            arrayOfFile = getCacheDir().listFiles();
            j = arrayOfFile.length;
            i = 0;
        }
        for (; ; ) {
            if (i >= j) {
                return;
            }
            File localFile = arrayOfFile[i];
            if (!localFile.getName().equals("userInfo.properties")) {
                localFile.delete();
            }
            i += 1;
        }
    }

    public void forgetButClick() {
        this.mForgetPass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Intent forgetIntent = new Intent(LoginActivity.this, ForgetPassActivity.class);
                LoginActivity.this.startActivity(forgetIntent);
            }
        });
    }

    public void login() {
        this.mLoginBut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                LoginActivity.this.mUserName = LoginActivity.this.mUserEdit.getText().toString().trim();
                LoginActivity.this.mPassword = LoginActivity.this.mPassEdit.getText().toString().trim();
                if ((LoginActivity.this.mUserName != "") && (LoginActivity.this.mPassword != "")) {
                    if (LoginActivity.this.mPassword.length() < 6) {
                        Toast.makeText(LoginActivity.this, "������������������6������", 1000).show();
                        return;
                    }
                    LoginActivity.this.mLoginBut.setClickable(false);
                    LoginActivity.this.mLoginBut.setText("���������������������...");
                    new Thread() {
                        public void run() {
                            try {
                                Message localMessage1 = ConnWeb.ConnForMsg("http://118.190.155.221:8098/StrawAdminWeb/login/LoginUserAjax", "username=" + LoginActivity.this.mUserName + "&password=" + LoginActivity.this.mPassword, "POST", 0, null, null);
                                LoginActivity.this.handler.sendMessage(localMessage1);
                                return;
                            } catch (IOException localIOException) {
                                Message localMessage2 = Message.obtain();
                                localMessage2.what = 0;
                                localMessage2.obj = "2";
                                LoginActivity.this.handler.sendMessage(localMessage2);
                                localIOException.printStackTrace();
                            }
                        }
                    }.start();
                    return;
                }
                Toast.makeText(LoginActivity.this, "���������������������", 1000).show();
            }
        });
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_login);

        this.mLoginBut = ((Button) findViewById(R.id.login_but));
        this.mUserEdit = ((AutoCompleteTextView) findViewById(R.id.username));
        this.mPassEdit = ((EditText) findViewById(R.id.password));
        this.mRegister = ((TextView) findViewById(R.id.register));
        this.mRemberPass = ((CheckBox) findViewById(R.id.rem_cbox));
        this.mForgetPass = ((TextView) findViewById(R.id.tv_notknow_pass));
        this.sp = getSharedPreferences("remberPass", 0);

        deleteCachaFile();
        forgetButClick();
        login();
        clickRemberPass();
        autoComplete();
        autoCompleteItem();
        register();
    }

    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
        if (paramInt == 4) {
            showExitDialog();
        }
        return super.onKeyDown(paramInt, paramKeyEvent);
    }

    public boolean onTouchEvent(MotionEvent paramMotionEvent) {
        if (paramMotionEvent.getAction() == 0) {
            InputMethodManager localInputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if ((getCurrentFocus() != null) && (getCurrentFocus().getWindowToken() != null)) {
                localInputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
            }
        }
        return super.onTouchEvent(paramMotionEvent);
    }

    public void register() {
        this.mRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
    }

    public void remberPass() {
        int j = 0;
        int k = 0;
        int i = 0;
        if (this.remberPass == 1) {
            j = 1;
            k = -1;
            i = 0;
        }
        for (; ; ) {
            if (i >= this.sp.getInt("num", 1)) {
                i = k;
            }
            for (; ; ) {
                SharedPreferences.Editor localEditor;
                if (i != -1) {
                    System.out.println("���������������������������");
                    localEditor = this.sp.edit();
                    localEditor.remove("username" + i);
                    localEditor.remove("password" + i);
                    localEditor.putString("username" + i, this.mUserName);
                    localEditor.putString("password" + i, this.mPassword);
                    localEditor.commit();
                }
                if (j == 0) {
                    System.out.println("������������");
                    localEditor = this.sp.edit();
                    localEditor.putString("username" + this.sp.getInt("num", 0), this.mUserName);
                    localEditor.putString("password" + this.sp.getInt("num", 0), this.mPassword);
                    localEditor.putInt("num", this.sp.getInt("num", 0) + 1);
                    localEditor.commit();
                }
                return;
                if (!this.mUserName.equals(this.sp.getString("username" + i, "���������"))) {
                    return;
                }
                System.out.println("������������");
                if (this.mPassword.equals(this.sp.getString("password" + i, "���������"))) {
                    System.out.println("���������������������������");
                    i = k;
                }
            }
            j = 0;
            i += 1;
        }
    }

    public void saveUserInfo() {
        try {
            FileOutputStream localFileOutputStream = new FileOutputStream(new File(getCacheDir(), "userInfo.properties"));
            Properties localProperties = new Properties();
            localProperties.setProperty("username", this.mUserName);
            localProperties.setProperty("password", this.mPassword);
            localProperties.store(localFileOutputStream, "userInfo.properties");
            localFileOutputStream.close();
            return;
        } catch (IOException localIOException) {
            localIOException.printStackTrace();
        }
    }
}
