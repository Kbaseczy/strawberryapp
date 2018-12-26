package com.jack.strawberry.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jack.strawberry.R;
import com.jack.strawberry.activity.TabActivity;
import com.jack.strawberry.adapter.LeaveAdapter;
import com.jack.strawberry.utils.PreferenceUtils;
import com.jack.strawberry.utils.ToastUtils;
import com.jack.strawberry.utils.Utils;
import com.jack.strawberry.vo.LeaveVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangdong on 18/9/26.
 * antony.huang@yeahmobi.com
 */
public class LeaveFragment extends Fragment {
    public static final String TYPE = "type";
    public static final int ZHONG_ZI = 0;
    public static final int JI_BING = 1;
    public static final int PE_YU = 2;
    public static final int YI_ZHI = 3;

    private View view;
    private Context context;
    private List<LeaveVO> leaveVOList;
    private EditText editText;
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_leave, null);
        context = getContext();
        getData();
        initView();
        return view;
    }

    private void getData() {

        try {
            leaveVOList = new ArrayList<>();

            String leaveJson = Utils.getStrFromAssets(context, "leave");
            JSONObject leaveObj = new JSONObject(leaveJson);

            JSONArray jsonArray = leaveObj.optJSONArray("leave");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject itemObj = jsonArray.optJSONObject(i);
                LeaveVO leaveVO = new LeaveVO();
                leaveVO.setProblem(itemObj.optString("problem"));
                leaveVO.setAnswer(itemObj.optString("answer"));
                leaveVOList.add(leaveVO);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void initView() {
        view.findViewById(R.id.back_but).setVisibility(View.GONE);
        ((TextView) view.findViewById(R.id.tv_header)).setText("咨询");

        button = view.findViewById(R.id.btn_professor);
        button.setOnClickListener(v -> button.showContextMenu());
        button.setOnCreateContextMenuListener((menu, v, menuInfo) -> {
            menu.add(0, 0, Menu.NONE, "种子专家");
            menu.add(0, 1, Menu.NONE, "疾病专家");
            menu.add(0, 2, Menu.NONE, "培育专家");
            menu.add(0, 3, Menu.NONE, "移植专家");
        });

        ListView leave_listview = view.findViewById(R.id.leave_listview);
        LeaveAdapter leaveAdapter = new LeaveAdapter(context, leaveVOList);
        leave_listview.setAdapter(leaveAdapter);


        editText = view.findViewById(R.id.et_problem);
        TextView tvSubmit = view.findViewById(R.id.tv_submit);

        tvSubmit.setOnClickListener(view -> submitProblem());

    }

    private void submitProblem() {

        String problem = editText.getText().toString();

        if (TextUtils.isEmpty(problem)) {
            ToastUtils.show(context, "没有提问任何问题.");
            return;
        }

        //写个dialog
        showDialog(context);

    }


    public void showDialog(Context context) {

        Dialog mDialog = new Dialog(context);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View dialogView = View.inflate(context, R.layout.view_dialog, null);
        mDialog.setContentView(dialogView);

        TextView tv_answer = dialogView.findViewById(R.id.tv_answer);
        tv_answer.setText("抱歉，未找到相关问题的答案！");

//        //3.设置指定的宽高,如果不设置的话，弹出的对话框可能不会显示全整个布局，当然在布局中写死宽高也可以
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        Window window = mDialog.getWindow();
//        lp.copyFrom(window.getAttributes());
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        //注意要在Dialog show之后，再将宽高属性设置进去，才有效果
        mDialog.show();
//        window.setAttributes(lp);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getGroupId() == 0) {
            switch (item.getItemId()) {
                case 0:
                    startActivity(new Intent(getActivity(), TabActivity.class).putExtra(TYPE, ZHONG_ZI));
//                    TabActivity.viewPager.setCurrentItem(0);
                    Toast.makeText(context, "种子专家", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    startActivity(new Intent(getActivity(), TabActivity.class).putExtra(TYPE, JI_BING));
//                    TabActivity.viewPager.setCurrentItem(1);
                    Toast.makeText(context, "疾病专家", Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    startActivity(new Intent(getActivity(), TabActivity.class).putExtra(TYPE, PE_YU));
//                    TabActivity.viewPager.setCurrentItem(2);
                    Toast.makeText(context, "培育专家", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    startActivity(new Intent(getActivity(), TabActivity.class).putExtra(TYPE, YI_ZHI));
//                    TabActivity.viewPager.setCurrentItem(3);
                    Toast.makeText(context, "移植专家", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        return super.onContextItemSelected(item);
    }
}
