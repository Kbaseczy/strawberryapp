package com.jack.strawberry.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jack.strawberry.R;
import com.jack.strawberry.vo.ProfessorAnwser;

import java.util.List;

public class RecyclerAdapterProfessor extends RecyclerView.Adapter {

    private Context mContext;
    private List<ProfessorAnwser.LeaveBean> leaveBeans;

    public RecyclerAdapterProfessor(Context context) {
        this.mContext = context;
    }

    public void setLeaveBeans(List<ProfessorAnwser.LeaveBean> leaveBeans) {
        this.leaveBeans = leaveBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mContext == null) {
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_question, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ViewHolder) {
            ProfessorAnwser.LeaveBean leaveBean = leaveBeans.get(i);
            ((ViewHolder) viewHolder).textView.setText(leaveBean.getProblem());
            ((ViewHolder) viewHolder).textView.setOnClickListener(v -> showNormalDialog(leaveBean));
        }
    }

    @Override
    public int getItemCount() {
        return leaveBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.title_question);
        }
    }

    private void showNormalDialog(ProfessorAnwser.LeaveBean leaveBean) {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(mContext);
        normalDialog.setIcon(R.drawable.ic_launcher_background);
        normalDialog.setTitle(leaveBean.getProblem());
        normalDialog.setMessage(leaveBean.getAnswer());
        normalDialog.show();
    }
}
