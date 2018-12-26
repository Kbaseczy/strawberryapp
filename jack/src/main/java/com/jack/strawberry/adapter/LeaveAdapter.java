package com.jack.strawberry.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jack.strawberry.R;
import com.jack.strawberry.vo.KnowVO;
import com.jack.strawberry.vo.LeaveVO;

import java.util.List;

public class LeaveAdapter extends BaseAdapter {

    private Context mContext;
    private List<LeaveVO> mList;

    public LeaveAdapter(Context paramContext, List<LeaveVO> paramList) {
        this.mContext = paramContext;
        this.mList = paramList;
    }

    @Override
    public int getCount() {
        return this.mList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (contentView == null) {
            contentView = View.inflate(mContext, R.layout.list_leave, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_problem = contentView.findViewById(R.id.tv_problem);
            viewHolder.tv_answer = contentView.findViewById(R.id.tv_answer);

            contentView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) contentView.getTag();
        }

        LeaveVO leaveVO = mList.get(position);
        viewHolder.tv_problem.setText(leaveVO.getProblem());
        viewHolder.tv_answer.setText(leaveVO.getAnswer());

        return contentView;
    }


    class ViewHolder {
        private TextView tv_problem;
        private TextView tv_answer;

        ViewHolder() {
        }
    }
}
