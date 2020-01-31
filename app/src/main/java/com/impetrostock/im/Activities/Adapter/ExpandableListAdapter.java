package com.impetrostock.im.Activities.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.impetrostock.im.Model.Main_Model.Nav_Menu_List;
import com.impetrostock.im.R;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter
{
    private Context context;
    private List<Nav_Menu_List> listDataHeader;
    private HashMap<Nav_Menu_List, List<Nav_Menu_List>> listDataChild;
    private int sposition;
    private int cposition;

    public ExpandableListAdapter(Context context, List<Nav_Menu_List> listDataHeader,
                                 HashMap<Nav_Menu_List, List<Nav_Menu_List>> listChildData) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listChildData;
    }

    @Override
    public Nav_Menu_List getChild(int groupPosition, int childPosititon) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = getChild(groupPosition, childPosition).menuName;

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_child, null);
        }

        TextView txtListChild = convertView.findViewById(R.id.lblListItem);
        LinearLayout CLlayout = convertView.findViewById(R.id.CLlayout);

        txtListChild.setText(childText);

    /*     if (cposition !=-1)
        {
            if(cposition==childPosition)
            {
                if (cposition==0)
                {
                    txtListChild.setTextColor(Color.parseColor("#209688"));
                    CLlayout.setBackgroundColor(Color.parseColor("#ffffff"));
                }
                else if (cposition==1)
                {
                    txtListChild.setTextColor(Color.parseColor("#209688"));
                    CLlayout.setBackgroundColor(Color.parseColor("#ffffff"));
                }
                else if (cposition==2)
                {
                    txtListChild.setTextColor(Color.parseColor("#209688"));
                    CLlayout.setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }
            else
            {  txtListChild.setTextColor(Color.parseColor("#ffffff"));
               CLlayout.setBackgroundColor(Color.parseColor("#209688"));     }
        }
        else {      }
*/
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        if (this.listDataChild.get(this.listDataHeader.get(groupPosition)) == null)
            return 0;
        else
            return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                    .size();
    }

    @Override
    public Nav_Menu_List getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() { return this.listDataHeader.size();  }

    @Override
    public long getGroupId(int groupPosition) { return groupPosition; }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = getGroup(groupPosition).menuName;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_header, null);
        }

        TextView lblListHeader = convertView.findViewById(R.id.lblListHeader);
        ImageView expIcon = convertView.findViewById(R.id.exp_icon);
        LinearLayout Llayout = convertView.findViewById(R.id.Llayout);
        //lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        if (isExpanded)
        {   expIcon.setImageDrawable(null);
            expIcon.setBackgroundResource(R.drawable.ic_remove_black_24dp); }
        else if(isExpanded==false && sposition==groupPosition)
        {   expIcon.setImageDrawable(null);
            expIcon.setBackgroundResource(R.drawable.ic_add2_black_24dp);    }
        else
        {   expIcon.setImageDrawable(null);
            expIcon.setBackgroundResource(R.drawable.ic_add_black_24dp);   }


        if (sposition !=-1)
        {
            if(sposition==groupPosition)
            {
                lblListHeader.setTextColor(Color.parseColor("#209688"));
                Llayout.setBackgroundColor(Color.parseColor("#ffffff"));
                /*if (sposition==0)
                {
                    expIcon.setBackgroundResource(R.drawable.ic_remove_black_24dp);
                    lblListHeader.setTextColor(Color.parseColor("#209688"));
                    Llayout.setBackgroundColor(Color.parseColor("#ffffff"));
                }*/
            }
            else
            {   lblListHeader.setTextColor(Color.parseColor("#ffffff"));
                Llayout.setBackgroundColor(Color.parseColor("#209688"));     }
        }
        else {      }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public void sendPOsition(int position){
        sposition=position;
    }

    public void csendPOsition(int position){
        cposition=position;
    }

}
