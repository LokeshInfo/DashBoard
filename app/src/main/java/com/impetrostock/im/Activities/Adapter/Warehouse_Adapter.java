package com.impetrostock.im.Activities.Adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.impetrostock.im.Model.Warehouse_Data;
import com.impetrostock.im.R;
import com.impetrostock.im.listener.OnItemTouchListener;

import java.util.List;

public class Warehouse_Adapter extends RecyclerView.Adapter<Warehouse_Adapter.ViewHolder>
{
    private List<Warehouse_Data> dataList;
    Activity mActivity;
    private OnItemTouchListener onItemTouchListener;

    public Warehouse_Adapter(Activity mActivity, List<Warehouse_Data> dataList, OnItemTouchListener onItemTouchListener)
    {
        this.mActivity = mActivity;
        this.dataList = dataList;
        this.onItemTouchListener = onItemTouchListener;
    }

    @Override
    public Warehouse_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_warehouse,parent,false);
        return new Warehouse_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final Warehouse_Adapter.ViewHolder holder, int position) {
        if (holder!=null)
        {
            Warehouse_Data wob = dataList.get(position);

            holder.name.setText(""+wob.getWName());
            holder.code.setText(""+wob.getWCode());
            holder.address.setText(""+wob.getWAddress());
            holder.city.setText(""+wob.getWCity());

            holder.imenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu pp = new PopupMenu(mActivity,holder.imenu);
                    pp.inflate(R.menu.list_options_two);
                    pp.getMenu().removeItem(R.id.Editsubcat);
                    pp.getMenu().add(R.id.group1,R.id.Editsubcat,0,"Update");
                    pp.getMenu().removeItem(R.id.Dltcat);
                    pp.getMenu().add(R.id.group1,R.id.Dltcat,1,"Delete");
                    pp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId())
                            {
                                case R.id.Editsubcat:
                                    break;
                                case R.id.Dltcat:
                                    break;
                            }
                            return false;
                        }
                    }); pp.show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView code;
        TextView name;
        TextView address;
        TextView city;
        ImageView imenu;

        public ViewHolder(View itemview)
        {
            super(itemview);
            code = itemview.findViewById(R.id.tx_wcode);
            name = itemview.findViewById(R.id.tx_wname);
            address = itemview.findViewById(R.id.tx_waddress);
            city = itemview.findViewById(R.id.tx_wcity);
            imenu = itemview.findViewById(R.id.waction_menu);
        }
    }
}
