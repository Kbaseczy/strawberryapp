package com.jack.strawberry.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jack.strawberry.R;
import com.jack.strawberry.adapter.FragmentAdapter;
import com.jack.strawberry.fragment.LeaveFragment;
import com.jack.strawberry.fragment.ProfessorFragment;
import com.jack.strawberry.utils.Utils;
import com.jack.strawberry.vo.ProfessorAnwser;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends FragmentActivity {
    public ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        int type = getIntent().getIntExtra(LeaveFragment.TYPE, 0);
        switch (type) {
            case LeaveFragment.ZHONG_ZI:
                viewPager.setCurrentItem(0);
                break;
            case LeaveFragment.JI_BING:
                viewPager.setCurrentItem(1);
                break;
            case LeaveFragment.PE_YU:
                viewPager.setCurrentItem(2);
                break;
            case LeaveFragment.YI_ZHI:
                viewPager.setCurrentItem(3);
                break;
        }
    }

    private void initView() {
        TextView header = findViewById(R.id.tv_header);
        Button button = findViewById(R.id.back_but);
        button.setOnClickListener(v -> finish());
        viewPager = findViewById(R.id.viewpager_professor);
        header.setText("专家答疑");
        TabLayout tabLayout = findViewById(R.id.tab_professor);
        String title[] = {"种子专家", "疾病专家", "培育专家", "移植专家"};

        for (String aTitle : title) {
            tabLayout.addTab(tabLayout.newTab().setText(aTitle));
        }

        String homeJson = Utils.getStrFromAssets(this, "leave");
        String jibingJson = Utils.getStrFromAssets(this, "jibing");
        String peiyuJson = Utils.getStrFromAssets(this, "peiyu");
        String yizhiJson = Utils.getStrFromAssets(this, "yizhi");
        Gson gson = new Gson();
        List<ProfessorAnwser.LeaveBean> list = gson.fromJson(homeJson, ProfessorAnwser.class).getLeave();
        List<ProfessorAnwser.LeaveBean> list1 = gson.fromJson(jibingJson, ProfessorAnwser.class).getLeave();
        List<ProfessorAnwser.LeaveBean> list2 = gson.fromJson(peiyuJson, ProfessorAnwser.class).getLeave();
        List<ProfessorAnwser.LeaveBean> list3 = gson.fromJson(yizhiJson, ProfessorAnwser.class).getLeave();

        Log.v("长度", list.size() + "<----长度");
        List<ProfessorFragment> fragments = new ArrayList<>();
        ProfessorFragment fragment1 = new ProfessorFragment();
        ProfessorFragment fragment2 = new ProfessorFragment();
        ProfessorFragment fragment3 = new ProfessorFragment();
        ProfessorFragment fragment4 = new ProfessorFragment();
        fragment1.setList(list);
        fragment2.setList(list1);
        fragment3.setList(list2);
        fragment4.setList(list3);
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);

        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        adapter.setTitle(title);

        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(viewPager);
    }
}
