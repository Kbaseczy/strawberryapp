package com.cai.strawberryapp.BaseClass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RefreshLayout
        extends SwipeRefreshLayout
        implements AbsListView.OnScrollListener
{
    private boolean isLoading = false;
    private int mLastY;
    private ListView mListView;
    private View mListViewFooter;
    private OnLoadListener mOnLoadListener;
    private int mTouchSlop;
    private int mYDown;

    public RefreshLayout(Context paramContext)
    {
        this(paramContext, null);
    }

    @SuppressLint({"InflateParams"})
    public RefreshLayout(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
        this.mTouchSlop = ViewConfiguration.get(paramContext).getScaledTouchSlop();
        this.mListViewFooter = LayoutInflater.from(paramContext).inflate(2130968615, null, false);
    }

    private boolean canLoad()
    {
        return (isBottom()) && (!this.isLoading) && (isPullUp());
    }

    private void getListView()
    {
        if (getChildCount() > 0)
        {
            View localView = getChildAt(0);
            if ((localView instanceof ListView))
            {
                this.mListView = ((ListView)localView);
                this.mListView.setOnScrollListener(this);
                Log.d("View", "### ������listview");
            }
        }
    }

    private boolean isBottom()
    {
        boolean bool2 = false;
        boolean bool1 = bool2;
        if (this.mListView != null)
        {
            bool1 = bool2;
            if (this.mListView.getAdapter() != null)
            {
                bool1 = bool2;
                if (this.mListView.getLastVisiblePosition() == this.mListView.getAdapter().getCount() - 1) {
                    bool1 = true;
                }
            }
        }
        return bool1;
    }

    private boolean isPullUp()
    {
        return this.mYDown - this.mLastY >= this.mTouchSlop;
    }

    private void loadData()
    {
        if (this.mOnLoadListener != null)
        {
            setLoading(true);
            this.mOnLoadListener.onLoad();
        }
    }

    public static void setRefreshing(SwipeRefreshLayout paramSwipeRefreshLayout, boolean paramBoolean1, boolean paramBoolean2)
    {
        Object localObject = paramSwipeRefreshLayout.getClass();
        if (localObject != null) {}
        try
        {
            localObject = ((Class)localObject).getDeclaredMethod("setRefreshing", new Class[] { Boolean.TYPE, Boolean.TYPE });
            ((Method)localObject).setAccessible(true);
            ((Method)localObject).invoke(paramSwipeRefreshLayout, new Object[] { Boolean.valueOf(paramBoolean1), Boolean.valueOf(paramBoolean2) });
            return;
        }
        catch (NoSuchMethodException paramSwipeRefreshLayout)
        {
            paramSwipeRefreshLayout.printStackTrace();
            return;
        }
        catch (IllegalAccessException paramSwipeRefreshLayout)
        {
            paramSwipeRefreshLayout.printStackTrace();
            return;
        }
        catch (InvocationTargetException paramSwipeRefreshLayout)
        {
            paramSwipeRefreshLayout.printStackTrace();
        }
    }

    public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
    {
        switch (paramMotionEvent.getAction())
        {
        }
        for (;;)
        {
            return super.dispatchTouchEvent(paramMotionEvent);
            this.mYDown = ((int)paramMotionEvent.getRawY());
            continue;
            this.mLastY = ((int)paramMotionEvent.getRawY());
            continue;
            if (canLoad()) {
                loadData();
            }
        }
    }

    protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
        super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
        if (this.mListView == null) {
            getListView();
        }
    }

    public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
    {
        if (canLoad()) {
            loadData();
        }
    }

    public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt) {}

    public void setLoading(boolean paramBoolean)
    {
        this.isLoading = paramBoolean;
        if (this.isLoading)
        {
            this.mListView.addFooterView(this.mListViewFooter);
            return;
        }
        this.mListView.removeFooterView(this.mListViewFooter);
        this.mYDown = 0;
        this.mLastY = 0;
    }

    public void setOnLoadListener(OnLoadListener paramOnLoadListener)
    {
        this.mOnLoadListener = paramOnLoadListener;
    }

    public static abstract interface OnLoadListener
    {
        public abstract void onLoad();
    }
}
