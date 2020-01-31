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

import com.impetrostock.im.Model.SubCategory_Data;
import com.impetrostock.im.R;
import com.impetrostock.im.listener.OnItemTouchListener;

import java.util.List;

public class SubCategory_Adapter extends RecyclerView.Adapter<SubCategory_Adapter.ViewHolder>
{
    private List<SubCategory_Data> dataList;
    Activity mActivity;
    private OnItemTouchListener onItemTouchListener;

    public SubCategory_Adapter(Activity mActivity, List<SubCategory_Data> dataList, OnItemTouchListener onItemTouchListener)
    {
        this.mActivity = mActivity;
        this.dataList = dataList;
        this.onItemTouchListener = onItemTouchListener;
    }

    @NonNull
    @Override
    public SubCategory_Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_subcategory_list,viewGroup,false);
        return new SubCategory_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final SubCategory_Adapter.ViewHolder holder, int position) {
        if (holder!=null)
        {
            SubCategory_Data sob = dataList.get(position);
            holder.SubCode.setText(""+sob.getSubCategoryCode());
            holder.SubName.setText(""+sob.getSubCategoryName());
            holder.MainCat.setText(""+sob.getMainCategory());

            holder.amenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu pp = new PopupMenu(mActivity,holder.amenu);
                    pp.inflate(R.menu.list_options_two);
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
        TextView SubCode;
        TextView SubName;
        TextView MainCat;
        ImageView amenu;

        public ViewHolder(View itemview)
        {
            super(itemview);
            SubCode = itemview.findViewById(R.id.tx_subcat);
            SubName = itemview.findViewById(R.id.tx_subname);
            MainCat = itemview.findViewById(R.id.tx_maincat);
            amenu = itemview.findViewById(R.id.action_menu);
        }
    }
}
