package com.impetrostock.im.Activities.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.impetrostock.im.Model.Suppliers_Data;
import com.impetrostock.im.R;
import com.impetrostock.im.listener.OnItemTouchListener;

import java.util.List;

public class Suppliers_Adapter extends RecyclerView.Adapter<Suppliers_Adapter.ViewHolder>
{
    private List<Suppliers_Data> dataList;
    Activity mActivity;
    private OnItemTouchListener onItemTouchListener;

    public Suppliers_Adapter(Activity mActivity, List<Suppliers_Data> dataList, OnItemTouchListener onItemTouchListener)
    {
        this.mActivity = mActivity;
        this.dataList = dataList;
        this.onItemTouchListener = onItemTouchListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_billers_list,parent,false);
        return new Suppliers_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder!=null)
        {
            Suppliers_Data bob = dataList.get(position);
            holder.aName.setText("Name : "+bob.getName());
            holder.Company.setText("Company : "+bob.getCompany());
            holder.Phone.setText("Phone : "+bob.getPhone());
            holder.Email.setText("Email : "+bob.getEmail());
            holder.City.setText("City : "+bob.getCity());
            holder.Country.setText("Country : "+bob.getCountry());

        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView aName;
        TextView Company;
        TextView Phone;
        TextView Email;
        TextView City;
        TextView Country;

        public ViewHolder(View itemview)
        {
            super(itemview);
            aName = itemview.findViewById(R.id.tx_name);
            Company = itemview.findViewById(R.id.tx_company);
            Phone = itemview.findViewById(R.id.tx_phone);
            Email = itemview.findViewById(R.id.tx_Email);
            City = itemview.findViewById(R.id.tx_City);
            Country = itemview.findViewById(R.id.tx_Country);
        }

    }

}
