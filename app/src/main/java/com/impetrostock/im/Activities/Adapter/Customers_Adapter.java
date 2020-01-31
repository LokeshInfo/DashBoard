package com.impetrostock.im.Activities.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.impetrostock.im.Model.Customers_data;
import com.impetrostock.im.R;
import com.impetrostock.im.listener.OnItemTouchListener;

import java.util.List;

public class Customers_Adapter extends RecyclerView.Adapter<Customers_Adapter.ViewHolder>
{

    private List<Customers_data> Cdatalist;
    Activity mActivity;
    private OnItemTouchListener onItemTouchListener;

    public Customers_Adapter(Activity mactivity, List<Customers_data> cdatalist, OnItemTouchListener onItemTouchListener)
    {
     this.mActivity = mactivity;
     this.Cdatalist = cdatalist;
     this.onItemTouchListener = onItemTouchListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_customers_list,parent,false);
        return new Customers_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Customers_data cob = Cdatalist.get(position);
        if (holder!=null)
        {
            holder.CName.setText("Name : "+cob.getName());
            holder.CPhone.setText("Phone : "+cob.getPhone());
            holder.CAddress.setText("Address : "+cob.getAddress());
            holder.CMail.setText("Email : "+cob.getEmail());
        }
    }

    @Override
    public int getItemCount() {
        return Cdatalist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView CName;
        TextView CPhone;
        TextView CMail;
        TextView CAddress;

        public ViewHolder(View itemview)
        {
            super(itemview);
            CName = itemview.findViewById(R.id.tx_CName);
            CPhone = itemview.findViewById(R.id.tx_CPhone);
            CMail = itemview.findViewById(R.id.tx_CMail);
            CAddress = itemview.findViewById(R.id.tx_CAddress);
        }

    }

}
