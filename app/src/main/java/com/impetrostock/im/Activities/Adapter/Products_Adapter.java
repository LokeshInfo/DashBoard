package com.impetrostock.im.Activities.Adapter;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.impetrostock.im.Model.Product_Data;
import com.impetrostock.im.R;
import com.impetrostock.im.listener.OnItemTouchListener;

import java.util.List;

public class Products_Adapter extends RecyclerView.Adapter<Products_Adapter.ViewHolder>
{
    private List<Product_Data> dataList;
    Activity mActivity;
    private OnItemTouchListener onItemTouchListener;

    public Products_Adapter(Activity mActivity, List<Product_Data> dataList,OnItemTouchListener onItemTouchListener)
    {
        this.mActivity = mActivity;
        this.dataList = dataList;
        this.onItemTouchListener = onItemTouchListener;
    }

    @NonNull
    @Override
    public Products_Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_product_list,viewGroup,false);
        return new Products_Adapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(Products_Adapter.ViewHolder viewHolder, final int i) {

        Product_Data dob = dataList.get(i);

        if (viewHolder!=null) {

            viewHolder.PdName.setText(dob.getName());
            viewHolder.Pdid.setText(dob.getId());
            viewHolder.PdPrice.setText("Product Price: "+dob.getPrice());
            viewHolder.PdCost.setText("Product Cost: "+dob.getCost());
            viewHolder.PdQty.setText("Qty: "+dob.getQuantity());
            viewHolder.PdCategory.setText("Category: "+dob.getCategory());

            viewHolder.imgInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemTouchListener.onViewTap(view,i);
                }
            });

            viewHolder.imgProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dlg = new Dialog(mActivity);
                    dlg.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    dlg.setContentView(R.layout.full_image_preview);
                    ImageView img = (ImageView) dlg.findViewById(R.id.preview_image);
                    img.setBackgroundResource(R.drawable.user_profile);
                    dlg.show();
                    dlg.setCanceledOnTouchOutside(true);
                }
            });

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemTouchListener.onClickTap(v,i);
                }
            });
        }
        else
        {
            viewHolder.PdName.setText("Nothing to Show");
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imgProfile;
        TextView PdName;
        TextView Pdid;
        TextView PdPrice;
        TextView PdCost;
        TextView PdQty;
        TextView PdCategory;
        TextView diff;
        ImageView imgInfo;

        public ViewHolder(View itemview)
        {
            super(itemview);

            imgProfile = itemview.findViewById(R.id.imgprofile);
            PdName = itemview.findViewById(R.id.tx_PdName);
            Pdid = itemview.findViewById(R.id.tx_Pdid);
            PdPrice = itemview.findViewById(R.id.tx_PdPrice);
            PdCost = itemview.findViewById(R.id.tx_PdCost);
            PdQty = itemview.findViewById(R.id.tx_PdQuantity);
            PdCategory = itemview.findViewById(R.id.tx_PdCategory);
            // diff = itemview.findViewById(R.id.diff);
            imgInfo = itemview.findViewById(R.id.imgInfo);
        }
    }
}
