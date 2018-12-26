package com.jack.strawberry.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.jack.strawberry.R;
import com.jack.strawberry.fragment.ClassFragment;
import com.jack.strawberry.fragment.HomeFragment;
import com.jack.strawberry.fragment.LeaveFragment;
import com.jack.strawberry.fragment.MineFragment;


public class IndexActivity extends FragmentActivity {

    public FragmentTabHost tabHost;
    private LayoutInflater inflater;

    public static int lastSelectedIndex;

    private Class[] fragmentArray = {HomeFragment.class, ClassFragment.class, LeaveFragment.class, MineFragment.class};
    private int[] tabItemIds = {R.string.footer_home, R.string.footer_class, R.string.footer_leave, R.string.footer_user};
    private int[] tabDrawableIds = {R.mipmap.home_icon_unselected, R.mipmap.class_icon_unselected,
            R.mipmap.consult_icon_unselected, R.mipmap.mine_icon_unselected};
    private int[] tabDrawableSelectedIds = {R.mipmap.home_icon_selected, R.mipmap.class_icon_selected,
            R.mipmap.consult_icon_selected, R.mipmap.mine_icon_selected};

    private ImageView lastSelectTab;
    private TextView lastSelectTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        // 实例化布局对象
        inflater = LayoutInflater.from(this);

        // 实例化TabHost对象，得到TabHost
        tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        // 得到fragment的个数
        int count = fragmentArray.length;

        for (int i = 0; i < count; i++) {
            // 为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = tabHost.newTabSpec("tab" + i).setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中
            tabHost.addTab(tabSpec, fragmentArray[i], null);
        }

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String arg0) {
                // TODO Auto-generated method stub
                int tabIndex = 0;

                if (arg0.endsWith("tab0")) {
                    tabIndex = 0;
                } else if (arg0.endsWith("tab1")) {
                    tabIndex = 1;
                } else if (arg0.endsWith("tab2")) {
                    tabIndex = 2;
                } else if (arg0.endsWith("tab3")) {
                    tabIndex = 3;
                }

                ImageView content = tabHost.getTabWidget().getChildAt(tabIndex).findViewById(R.id.content);
                TextView tabText = tabHost.getTabWidget().getChildAt(tabIndex).findViewById(R.id.tab_tv);
                if (lastSelectTab != null && lastSelectTab != content) {
                    lastSelectTab.setImageResource(tabDrawableIds[lastSelectedIndex]);
                    lastSelectTV.setTextColor(getResources().getColor(R.color.white));
                }

                lastSelectedIndex = tabIndex;

                lastSelectTab = content;
                lastSelectTV = tabText;
                content.setImageResource(tabDrawableSelectedIds[tabIndex]);
                tabText.setTextColor(getResources().getColor(R.color.red));
            }
        });

        tabHost.setCurrentTab(lastSelectedIndex);
    }

    private View getTabItemView(int index) {
        LinearLayout tabView = (LinearLayout) inflater.inflate(R.layout.tab_layout, null);
        ImageView content = tabView.findViewById(R.id.content);
        TextView tabTV = tabView.findViewById(R.id.tab_tv);
        tabTV.setText(tabItemIds[index]);
        content.setImageResource(tabDrawableIds[index]);
        if (index == 0) {
            content.setImageResource(tabDrawableSelectedIds[index]);
            tabTV.setTextColor(getResources().getColor(R.color.red));
            lastSelectTab = content;
            lastSelectTV = tabTV;
        }
        return tabView;
    }

    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
        if (paramInt == 4) {
            showExitDialog();
        }
        return super.onKeyDown(paramInt, paramKeyEvent);
    }

    private void showExitDialog() {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        localBuilder.setMessage("是否退出？");
        localBuilder.setPositiveButton("是", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                System.exit(0);
            }
        });
        localBuilder.setNeutralButton("否", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                paramAnonymousDialogInterface.cancel();
            }
        });
        localBuilder.show();
    }

}
