package com.impetrostock.im.Activities.Adapter2;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.impetrostock.im.Model.SPurchase_Data;
import com.impetrostock.im.R;
import com.impetrostock.im.listener.OnItemTouchListener;

import java.util.List;

public class SPurchase_Adapter extends RecyclerView.Adapter<SPurchase_Adapter.ViewHolder>
{
    private List<SPurchase_Data> Pdatalist;
    Activity mActivity;
    private OnItemTouchListener onItemTouchListener;

    public SPurchase_Adapter(Activity mActivity, List<SPurchase_Data> Pdatalist, OnItemTouchListener onItemTouchListener)
    {
        this.mActivity=mActivity;
        this.Pdatalist=Pdatalist;
        this.onItemTouchListener=onItemTouchListener;
    }

    @Override
    public SPurchase_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_purchases_list,parent,false);
        return new SPurchase_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SPurchase_Adapter.ViewHolder holder, final int position) {
        SPurchase_Data Pob = Pdatalist.get(position);
        if (holder!=null)
        {
            holder.SupplieNm.setText("Supplier Name : "+Pob.getSupplier());
            holder.RefNum.setText("Refrence No. : "+Pob.getRefrenceNum());
            holder.Date.setText(""+Pob.getDate());
            holder.Pay.setText("Total : "+Pob.getTotal());

        }
    }

    @Override
    public int getItemCount() {
        return Pdatalist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView SupplieNm;
        TextView Date;
        TextView RefNum;
        TextView Pay;

        public ViewHolder(View itemview)
        {
            super(itemview);
            SupplieNm = itemview.findViewById(R.id.tx_SupplierNm);
            Date = itemview.findViewById(R.id.tx_Date);
            RefNum = itemview.findViewById(R.id.tx_RefrenceNum);
            Pay = itemview.findViewById(R.id.tx_Pay);
        }
    }
}
