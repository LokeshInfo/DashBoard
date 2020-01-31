package com.impetrostock.im.Activities.Adapter2;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.impetrostock.im.Model.Transfers_Data;
import com.impetrostock.im.R;
import com.impetrostock.im.listener.OnItemTouchListener;

import java.util.List;

public class Transfers_Adapter extends RecyclerView.Adapter<Transfers_Adapter.ViewHolder>
{
    List<Transfers_Data> dataList;
    Activity mactivity;
    private OnItemTouchListener onItemTouchListener;

    public Transfers_Adapter(Activity mactivity, List<Transfers_Data> dataList, OnItemTouchListener onItemTouchListener)
    {
        this.mactivity=mactivity;
        this.dataList=dataList;
        this.onItemTouchListener=onItemTouchListener;
    }


    @NonNull
    @Override
    public Transfers_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_transfers,viewGroup,false);
        return new Transfers_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Transfers_Adapter.ViewHolder viewHolder, int i) {
        if (viewHolder!=null)
        {
            Transfers_Data tob = dataList.get(i);

            viewHolder.TransfrNum.setText(""+tob.getTranfer_Num());
            viewHolder.Date.setText(""+tob.getDate());
            viewHolder.From.setText("From - "+tob.getWFrom());
            viewHolder.To.setText("To - "+tob.getWto());
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView TransfrNum;
        TextView Date;
        TextView From;
        TextView To;

        public ViewHolder(View itemview)
        {
            super(itemview);
            TransfrNum = itemview.findViewById(R.id.tx_transfrnum);
            Date = itemview.findViewById(R.id.tx_date);
            From = itemview.findViewById(R.id.tx_from);
            To = itemview.findViewById(R.id.tx_to);
        }
    }
}
