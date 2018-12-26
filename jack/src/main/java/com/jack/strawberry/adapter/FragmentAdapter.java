package com.jack.strawberry.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jack.strawberry.fragment.ProfessorFragment;

import java.util.List;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private List<ProfessorFragment> fragments;
    private String title[];
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setFragments(List<ProfessorFragment> fragments) {
        this.fragments = fragments;
    }
    public void setTitle(String[] title) {
        this.title = title;
    }
    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
