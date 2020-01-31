package com.impetrostock.im.Activities.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.impetrostock.im.Model.Billers_Data;
import com.impetrostock.im.Model.Tax_Data;
import com.impetrostock.im.R;
import com.impetrostock.im.listener.OnItemTouchListener;

import java.util.List;

public class Tax_Adapter extends RecyclerView.Adapter<Tax_Adapter.ViewHolder>
{
    private List<Tax_Data> dataList;
    Activity mActivity;
    private OnItemTouchListener onItemTouchListener;

    public Tax_Adapter(Activity mActivity, List<Tax_Data> dataList, OnItemTouchListener onItemTouchListener)
    {
        this.mActivity = mActivity;
        this.dataList = dataList;
        this.onItemTouchListener = onItemTouchListener;
    }

    @Override
    public Tax_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_tax_list,parent,false);
        return new Tax_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Tax_Adapter.ViewHolder holder, int position) {
        if (holder!=null)
        {
            Tax_Data tob = dataList.get(position);
            holder.sno.setText(""+(position+1)+".");
            holder.title.setText(" Title : "+tob.getTitle());
            holder.taxRate.setText("Tax Rate : "+tob.getTaxRate());
            holder.type.setText("Type : "+tob.getType());
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        TextView type;
        TextView taxRate;
        TextView sno;

        public ViewHolder(View itemview)
        {
            super(itemview);
            title = itemview.findViewById(R.id.tx_title);
            type = itemview.findViewById(R.id.tx_type);
            taxRate = itemview.findViewById(R.id.tx_taxrate);
            sno = itemview.findViewById(R.id.tx_num);
        }
    }

}
