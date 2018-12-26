package com.cai.strawberryapp.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.text.SimpleDateFormat;

public class RefreshListView
        extends ListView
        implements AbsListView.OnScrollListener
{
    private static final String TAG = "RefreshListView";
    private final int DOWN_PULL_REFRESH = 0;
    private final int REFRESHING = 2;
    private final int RELEASE_REFRESH = 1;
    private int currentState = 0;
    private Animation downAnimation;
    private int downY;
    private int firstVisibleItemPosition;
    private View footerView;
    private int footerViewHeight;
    private View headerView;
    private int headerViewHeight;
    private boolean isLoadingMore = false;
    private boolean isScrollToBottom;
    private ImageView ivArrow;
    private OnRefreshListener mOnRefershListener;
    private TextView tvLastUpdateTime;
    private TextView tvState;
    private Animation upAnimation;

    public RefreshListView(Context paramContext)
    {
        this(paramContext, null);
    }

    public RefreshListView(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
        initHeaderView();
        initFooterView();
        setOnScrollListener(this);
    }

    public RefreshListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
    }

    private String getLastUpdateTime()
    {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(System.currentTimeMillis()));
    }

    private void initAnimation()
    {
        this.upAnimation = new RotateAnimation(0.0F, -180.0F, 1, 0.5F, 1, 0.5F);
        this.upAnimation.setDuration(500L);
        this.upAnimation.setFillAfter(true);
        this.downAnimation = new RotateAnimation(-180.0F, -360.0F, 1, 0.5F, 1, 0.5F);
        this.downAnimation.setDuration(500L);
        this.downAnimation.setFillAfter(true);
    }

    private void initFooterView()
    {
        this.footerView = LayoutInflater.from(getContext()).inflate(2130968615, null, false);
        this.footerView.measure(0, 0);
        this.footerViewHeight = this.footerView.getMeasuredHeight();
        this.footerView.setPadding(0, -this.footerViewHeight, 0, 0);
        addFooterView(this.footerView);
    }

    private void initHeaderView()
    {
        this.headerView = View.inflate(getContext(), 2130968616, null);
        this.ivArrow = ((ImageView)this.headerView.findViewById(2131361961));
        this.tvState = ((TextView)this.headerView.findViewById(2131361962));
        this.tvLastUpdateTime = ((TextView)this.headerView.findViewById(2131361963));
        this.tvLastUpdateTime.setText("������������������: " + getLastUpdateTime());
        this.headerView.measure(0, 0);
        this.headerViewHeight = this.headerView.getMeasuredHeight();
        this.headerView.setPadding(0, -this.headerViewHeight, 0, 0);
        addHeaderView(this.headerView);
        initAnimation();
    }

    private void refreshHeaderView()
    {
        switch (this.currentState)
        {
            default:
                return;
            case 0:
                this.tvState.setText("������������");
                this.ivArrow.startAnimation(this.downAnimation);
                return;
            case 1:
                this.tvState.setText("������������");
                this.ivArrow.startAnimation(this.upAnimation);
                return;
        }
        this.ivArrow.clearAnimation();
        this.ivArrow.setVisibility(8);
        this.tvState.setText("���������������...");
    }

    public void hideFooterView()
    {
        this.footerView.setPadding(0, -this.footerViewHeight, 0, 0);
        this.isLoadingMore = false;
    }

    public void hideHeaderView()
    {
        this.headerView.setPadding(0, -this.headerViewHeight, 0, 0);
        this.ivArrow.setVisibility(0);
        this.tvState.setText("������������");
        this.tvLastUpdateTime.setText("������������������: " + getLastUpdateTime());
        this.currentState = 0;
    }

    public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
    {
        this.firstVisibleItemPosition = paramInt1;
        if (getLastVisiblePosition() == paramInt3 - 1)
        {
            this.isScrollToBottom = true;
            return;
        }
        this.isScrollToBottom = false;
    }

    public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
    {
        if ((paramInt == 0) && (this.isScrollToBottom) && (!this.isLoadingMore))
        {
            this.isLoadingMore = true;
            Log.i("RefreshListView", "������������������");
            this.footerView.setPadding(0, 0, 0, 0);
            setSelection(getCount());
            if (this.mOnRefershListener != null) {
                this.mOnRefershListener.onLoadingMore();
            }
        }
    }

    public boolean onTouchEvent(MotionEvent paramMotionEvent)
    {
        switch (paramMotionEvent.getAction())
        {
        }
        for (;;)
        {
            return super.onTouchEvent(paramMotionEvent);
            this.downY = ((int)paramMotionEvent.getY());
            continue;
            int i = ((int)paramMotionEvent.getY() - this.downY) / 2;
            i = -this.headerViewHeight + i;
            if ((this.firstVisibleItemPosition == 0) && (-this.headerViewHeight < i))
            {
                if ((i > 0) && (this.currentState == 0))
                {
                    Log.i("RefreshListView", "������������");
                    this.currentState = 1;
                    refreshHeaderView();
                }
                for (;;)
                {
                    this.headerView.setPadding(0, i, 0, 0);
                    return false;
                    if ((i < 0) && (this.currentState == 1))
                    {
                        Log.i("RefreshListView", "������������");
                        this.currentState = 0;
                        refreshHeaderView();
                    }
                }
                if (this.currentState == 1)
                {
                    Log.i("RefreshListView", "������������.");
                    this.headerView.setPadding(0, 0, 0, 0);
                    this.currentState = 2;
                    refreshHeaderView();
                    if (this.mOnRefershListener != null)
                    {
                        this.mOnRefershListener.onDownPullRefresh();
                        return false;
                    }
                }
                else if (this.currentState == 0)
                {
                    this.headerView.setPadding(0, -this.headerViewHeight, 0, 0);
                }
            }
        }
    }

    public void setOnRefreshListener(OnRefreshListener paramOnRefreshListener)
    {
        this.mOnRefershListener = paramOnRefreshListener;
    }
}
