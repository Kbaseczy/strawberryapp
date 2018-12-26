package com.cai.strawberryapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class AutoCompleteAdapter
        extends BaseAdapter
        implements Filterable
{
    private Context mContext;
    private ArrayFilter mFilter;
    private List<String> mList;
    private ArrayList<String> mUnfilteredData;

    public AutoCompleteAdapter(Context paramContext, List<String> paramList)
    {
        this.mContext = paramContext;
        this.mList = paramList;
    }

    public int getCount()
    {
        return this.mList.size();
    }

    public Filter getFilter()
    {
        if (this.mFilter == null) {
            this.mFilter = new ArrayFilter(null);
        }
        return this.mFilter;
    }

    public Object getItem(int paramInt)
    {
        return this.mList.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
        return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
        if (paramView == null)
        {
            paramView = LayoutInflater.from(this.mContext).inflate(2130968599, null);
            paramViewGroup = new ViewHolder();
            paramViewGroup.tv_autocomplete = ((TextView)paramView.findViewById(2131361906));
            paramView.setTag(paramViewGroup);
        }
        for (;;)
        {
            paramViewGroup.tv_autocomplete.setText((CharSequence)this.mList.get(paramInt));
            return paramView;
            paramViewGroup = (ViewHolder)paramView.getTag();
        }
    }

    private class ArrayFilter
            extends Filter
    {
        private ArrayFilter() {}

        protected Filter.FilterResults performFiltering(CharSequence paramCharSequence)
        {
            Filter.FilterResults localFilterResults = new Filter.FilterResults();
            if (AutoCompleteAdapter.this.mUnfilteredData == null) {
                AutoCompleteAdapter.this.mUnfilteredData = new ArrayList(AutoCompleteAdapter.this.mList);
            }
            if ((paramCharSequence == null) || (paramCharSequence.length() == 0))
            {
                paramCharSequence = new ArrayList(AutoCompleteAdapter.this.mUnfilteredData);
                localFilterResults.values = paramCharSequence;
                localFilterResults.count = paramCharSequence.size();
                return localFilterResults;
            }
            paramCharSequence = paramCharSequence.toString().toLowerCase();
            ArrayList localArrayList1 = AutoCompleteAdapter.this.mUnfilteredData;
            int j = localArrayList1.size();
            ArrayList localArrayList2 = new ArrayList(j);
            int i = 0;
            for (;;)
            {
                if (i >= j)
                {
                    localFilterResults.values = localArrayList2;
                    localFilterResults.count = localArrayList2.size();
                    return localFilterResults;
                }
                String str = (String)localArrayList1.get(i);
                if ((str != null) && (str != null) && (str.startsWith(paramCharSequence))) {
                    localArrayList2.add(str);
                }
                i += 1;
            }
        }

        protected void publishResults(CharSequence paramCharSequence, Filter.FilterResults paramFilterResults)
        {
            AutoCompleteAdapter.this.mList = ((List)paramFilterResults.values);
            if (paramFilterResults.count > 0)
            {
                AutoCompleteAdapter.this.notifyDataSetChanged();
                return;
            }
            AutoCompleteAdapter.this.notifyDataSetInvalidated();
        }
    }

    class ViewHolder
    {
        private TextView tv_autocomplete;

        ViewHolder() {}
    }
}
