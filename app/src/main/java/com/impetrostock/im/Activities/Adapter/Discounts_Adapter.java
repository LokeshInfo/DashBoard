package com.impetrostock.im.Activities.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.impetrostock.im.Model.Billers_Data;
import com.impetrostock.im.Model.Discount_Data;
import com.impetrostock.im.R;
import com.impetrostock.im.listener.OnItemTouchListener;

import java.util.List;

public class Discounts_Adapter extends RecyclerView.Adapter<Discounts_Adapter.ViewHolder>
{
    private List<Discount_Data> dataList;
    Activity mActivity;
    private OnItemTouchListener onItemTouchListener;

    public Discounts_Adapter(Activity mActivity, List<Discount_Data> dataList, OnItemTouchListener onItemTouchListener)
    {
        this.mActivity = mActivity;
        this.dataList = dataList;
        this.onItemTouchListener = onItemTouchListener;
    }

    @NonNull
    @Override
    public Discounts_Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_tax_list,viewGroup,false);
        return new Discounts_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Discounts_Adapter.ViewHolder holder, int position) {
        if (holder!=null)
        {
            Discount_Data dob = dataList.get(position);
            holder.sno.setText(""+(position+1)+".");
            holder.title.setText("Title : "+dob.getTitle());
            holder.taxRate.setText("Discount : "+dob.getDiscount());
            holder.type.setText("Type : "+dob.getType());
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
