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

import com.impetrostock.im.Model.Billers_Data;
import com.impetrostock.im.Model.Category_Data;
import com.impetrostock.im.R;
import com.impetrostock.im.listener.OnItemTouchListener;

import java.util.List;

public class Categories_Adapter extends RecyclerView.Adapter<Categories_Adapter.ViewHolder>
{
    private List<Category_Data> dataList;
    Activity mActivity;
    private OnItemTouchListener onItemTouchListener;

    public Categories_Adapter(Activity mActivity, List<Category_Data> dataList, OnItemTouchListener onItemTouchListener)
    {
        this.mActivity = mActivity;
        this.dataList = dataList;
        this.onItemTouchListener = onItemTouchListener;
    }

    @NonNull
    @Override
    public Categories_Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_categories_list,viewGroup,false);
        return new Categories_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final Categories_Adapter.ViewHolder holder, int position) {
        if (holder!=null)
        {
            Category_Data dob = dataList.get(position);
            holder.Name.setText(""+dob.getCName());
            holder.Code.setText(""+dob.getCCode());

            holder.popup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu pp = new PopupMenu(mActivity,holder.popup);
                    pp.inflate(R.menu.list_options_menu);
                    pp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId())
                            {
                                case R.id.subcat:
                                    break;
                                case R.id.editcat:
                                    break;
                                case R.id.dltcat:
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
        TextView Code;
        ImageView popup;

        public ViewHolder(View itemview)
        {
            super(itemview);
            Name = itemview.findViewById(R.id.tx_Name);
            Code = itemview.findViewById(R.id.tx_Code);
            popup = itemview.findViewById(R.id.action_menu);
        }
    }



}
