package com.impetrostock.im.Activities.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.impetrostock.im.Model.Manufacturer_Data;
import com.impetrostock.im.R;
import com.impetrostock.im.listener.OnItemTouchListener;

import java.util.List;


public class Manufacturer_Adapter extends RecyclerView.Adapter<Manufacturer_Adapter.ViewHolder>
{
    private List<Manufacturer_Data> dataList;
    Activity mActivity;
    private OnItemTouchListener onItemTouchListener;

    public Manufacturer_Adapter(Activity mActivity, List<Manufacturer_Data> dataList, OnItemTouchListener onItemTouchListener)
    {
        this.mActivity = mActivity;
        this.dataList = dataList;
        this.onItemTouchListener = onItemTouchListener;
    }

    @NonNull
    @Override
    public Manufacturer_Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_single_item,viewGroup,false);
        return new Manufacturer_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final Manufacturer_Adapter.ViewHolder holder, int position) {
        if (holder!=null)
        {
            Manufacturer_Data mob = dataList.get(position);

            holder.Name.setText(""+mob.getManufacturer_name());

            holder.popup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu pp = new PopupMenu(mActivity,holder.popup);
                    pp.inflate(R.menu.list_options_two);
                    pp.getMenu().removeItem(R.id.Editsubcat);
                    pp.getMenu().add(R.id.group1,R.id.Editsubcat,0,"Driver Edit");
                    pp.getMenu().removeItem(R.id.Dltcat);
                    pp.getMenu().add(R.id.group1,R.id.Dltcat,1,"Delete");
                    pp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            switch (item.getItemId())
                            {
                                case R.id.Editsubcat:
                                    //Toast.makeText(mActivity,"Marvel",Toast.LENGTH_SHORT).show();
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
        TextView Name;
        ImageView popup;

        public ViewHolder(View itemview)
        {
            super(itemview);
            Name = itemview.findViewById(R.id.tx_Name);
            popup = itemview.findViewById(R.id.action_menu);
        }
    }
}
