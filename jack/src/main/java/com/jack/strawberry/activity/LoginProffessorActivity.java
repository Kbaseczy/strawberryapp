package com.jack.strawberry.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jack.strawberry.R;

public class LoginProffessorActivity extends Activity {

    EditText editText, editText1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_proffessor);
        editText = findViewById(R.id.username_pro);
        editText1 = findViewById(R.id.password_pro);

        Button login = findViewById(R.id.login_but_professor);
        login.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(editText.getText().toString().trim()) && !TextUtils.isEmpty(editText.getText().toString().trim())) {
                startActivity(new Intent(LoginProffessorActivity.this, InputActivity.class));
                finish();
            } else
                Toast.makeText(this, "请完善用户名密码。", Toast.LENGTH_SHORT).show();
        });

    }
}
