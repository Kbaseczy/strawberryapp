package com.jack.strawberry.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jack.strawberry.R;
import com.jack.strawberry.StrawberryApplication;
import com.jack.strawberry.greendao.QuestionVODao;
import com.jack.strawberry.vo.QuestionVO;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<QuestionVO> list;
    private Context mContext;

    public RecyclerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setList(List<QuestionVO> list) {
        this.list = list;
    }

    public void noti(List<QuestionVO> list) {
        this.list = list;
        this.list.notify();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mContext == null)
            mContext = viewGroup.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_question_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ViewHolder) {
            QuestionVO questionVO = list.get(position);
//            Log.v("recyclerAdapter",list.size()+"list 长度");
            Log.v("recyclerAdapter", questionVO.getContent_question() + "  item:" + position);
            ((ViewHolder) viewHolder).content.setText(questionVO.getContent_question());
            ((ViewHolder) viewHolder).anwser_a.setText(questionVO.getAnwser_a());
            ((ViewHolder) viewHolder).anwser_b.setText(questionVO.getAnwser_b());
            ((ViewHolder) viewHolder).anwser.setText(questionVO.getAnwser_real());
            QuestionVODao questionVODao = StrawberryApplication.getDaoSession().getQuestionVODao();
            ((ViewHolder) viewHolder).cardView.setOnLongClickListener(v -> {
                Toast.makeText(mContext, "delete", Toast.LENGTH_SHORT).show();
                questionVODao.delete(questionVO);
                setList(questionVODao.queryBuilder().list());
                notifyDataSetChanged();
                return false;
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView content, anwser_a, anwser_b, anwser;
        CardView cardView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            content = itemView.findViewById(R.id.question_content);
            anwser_a = itemView.findViewById(R.id.item_a);
            anwser_b = itemView.findViewById(R.id.item_b);
            anwser = itemView.findViewById(R.id.item_real);
        }
    }

   /* public interface OnLongClickListener {
        void onLongClick(View view, int position);
    }

    private OnLongClickListener mOnLongClickListener = null;

    public void setOnLongClickListener(OnLongClickListener listener) {
        mOnLongClickListener = listener;
    }

    @Override
    public boolean onLongClick(View view) {
        if (null != mOnLongClickListener) {
            mOnLongClickListener.onLongClick(view, (int) view.getTag());
        }
        return true;
    }*/
}
