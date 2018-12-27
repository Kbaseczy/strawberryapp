package com.jack.strawberry.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jack.strawberry.R;
import com.jack.strawberry.activity.IndexActivity;
import com.jack.strawberry.activity.InputActivity;
import com.jack.strawberry.activity.LoginActivity;
import com.jack.strawberry.activity.QuestionActivity;
import com.jack.strawberry.activity.ShopActivity;
import com.jack.strawberry.activity.StoreActivity;
import com.jack.strawberry.activity.UpdatePassActivity;
import com.jack.strawberry.utils.PreferenceUtils;

import java.util.Objects;

/**
 * Created by huangdong on 18/9/26.
 * antony.huang@yeahmobi.com
 */
public class MineFragment extends Fragment {

    private View view;
    private Context context;
    private IndexActivity indexActivity;
    TextView tv_user_score;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        indexActivity = (IndexActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine, null);
        context = getContext();
        initView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        String score = String.valueOf(PreferenceUtils.getInt(context, LoginActivity.SCORE));
        tv_user_score.setText(score);
    }

    private void initView() {
        view.findViewById(R.id.back_but).setVisibility(View.GONE);
        ((TextView) view.findViewById(R.id.tv_header)).setText("我");

        RelativeLayout rl_update_password = view.findViewById(R.id.rl_update_password);
        RelativeLayout rl_about = view.findViewById(R.id.rl_about);
        Button but_exit_login = view.findViewById(R.id.but_exit_login);
        TextView tv_user_username = view.findViewById(R.id.tv_user_username);
        tv_user_score = view.findViewById(R.id.score_question);

        String userName = PreferenceUtils.getString(context, LoginActivity.CURRENTACCOUNT);

        if (!TextUtils.isEmpty(userName)) {
            tv_user_username.setText(userName);
        }

        rl_update_password.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdatePassActivity.class);
            context.startActivity(intent);
        });

        rl_about.setOnClickListener(view -> {
            Intent intent = new Intent(context, QuestionActivity.class);
            context.startActivity(intent);
        });

        but_exit_login.setOnClickListener(view -> {
            PreferenceUtils.clearByKey(context, LoginActivity.CURRENTACCOUNT);
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
            indexActivity.finish();
        });

        view.findViewById(R.id.rl_professor).setOnClickListener(v -> {
                    if (Objects.requireNonNull(getActivity()).
                            getIntent().getIntExtra("zhuanjia", 1) == 1) {
                        startActivity(new Intent(getActivity(), InputActivity.class));
                    } else {
                        Toast.makeText(context, "没有权限", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        view.findViewById(R.id.rl_shop).setOnClickListener(v -> startActivity(new Intent(getActivity(), ShopActivity.class)));
        view.findViewById(R.id.rl_store).setOnClickListener(v -> startActivity(new Intent(getActivity(), StoreActivity.class)));
    }
}
