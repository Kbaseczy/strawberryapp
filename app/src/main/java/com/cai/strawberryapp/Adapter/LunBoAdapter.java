package com.cai.strawberryapp.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class LunBoAdapter
        extends PagerAdapter {
    public int flag = 0;
    private List<View> viewList;

    public LunBoAdapter(List<View> paramList) {
        this.viewList = paramList;
    }

    public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject) {
        paramViewGroup.removeView((View) this.viewList.get(paramInt));
    }

    public int getCount() {
        return this.viewList.size();
    }

    public Object instantiateItem(ViewGroup paramViewGroup, int paramInt) {
        paramViewGroup.addView((View) this.viewList.get(paramInt));
        return this.viewList.get(paramInt);
    }

    public boolean isViewFromObject(View paramView, Object paramObject) {
        return paramView == paramObject;
    }
}
