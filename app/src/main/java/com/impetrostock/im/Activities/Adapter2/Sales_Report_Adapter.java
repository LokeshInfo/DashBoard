package com.impetrostock.im.Activities.Adapter2;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.impetrostock.im.Model.Sales_Data;
import com.impetrostock.im.R;
import com.impetrostock.im.listener.OnItemTouchListener;

import java.util.List;

public class Sales_Report_Adapter extends RecyclerView.Adapter<Sales_Report_Adapter.ViewHolder>
{
    private List<Sales_Data> Pdatalist;
    Activity mActivity;
    private OnItemTouchListener onItemTouchListener;

    public Sales_Report_Adapter(Activity mActivity, List<Sales_Data> Pdatalist, OnItemTouchListener onItemTouchListener)
    {
        this.mActivity=mActivity;
        this.Pdatalist=Pdatalist;
        this.onItemTouchListener=onItemTouchListener;
    }

    @Override
    public Sales_Report_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_ssales,parent,false);
        return new Sales_Report_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Sales_Report_Adapter.ViewHolder holder, final int position) {

        Sales_Data sob = Pdatalist.get(position);

        if (holder!=null)
        {
            holder.Biller.setText("Biller : "+sob.getBiller());
            holder.Refnum.setText("Refrence No. : "+sob.getRefrencenum());
            holder.date.setText(""+sob.getDate());
            holder.Customer.setText("Customer : "+sob.getCustomer());
            holder.PdTax.setText("Product Tax : "+sob.getProductTax());
            holder.InvoiceTax.setText("Invoice Tax : "+sob.getInvoiceTax());
            holder.Total.setText("Total : "+sob.getTotal());
        }
    }

    @Override
    public int getItemCount() {    return Pdatalist.size();    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView Biller;
        TextView Refnum;
        TextView Customer;
        TextView date;
        TextView Total;
        TextView PdTax;
        TextView InvoiceTax;

        public ViewHolder(View itemview)
        {
            super(itemview);
            Biller = itemview.findViewById(R.id.tx_BillerNm);
            Refnum = itemview.findViewById(R.id.tx_RefrenceNum);
            Customer = itemview.findViewById(R.id.tx_Customer);
            date = itemview.findViewById(R.id.tx_Dte);
            Total = itemview.findViewById(R.id.tx_total);
            PdTax = itemview.findViewById(R.id.tx_Ptax);
            InvoiceTax = itemview.findViewById(R.id.tx_PInvTax);
        }
    }
}
