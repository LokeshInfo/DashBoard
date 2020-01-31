package com.impetrostock.im.Activities.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.impetrostock.im.View.Main_Activity;
import com.impetrostock.im.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Reports_fragment extends Fragment
{
    @BindView(R.id.BarChart)
    BarChart chart;

    float barWidth;
    float barSpace;
    float groupSpace;

    Toolbar toolbar;
    Activity activity;
    TextView txToolbar;
    ImageView imgToolbar;
    Reports_fragment reports_fragment;

    ArrayList yVal;
    ArrayList yVals1;
    ArrayList yVals2;
    ArrayList yVals3;
    ArrayList xVals;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity)context;
        reports_fragment = this;          }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reports,container,false);

        ButterKnife.bind(this,view);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        txToolbar = (TextView) view.findViewById(R.id.txToolbar);
        imgToolbar = (ImageView) view.findViewById(R.id.imgToolbar);


        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        txToolbar.setText("Reports");
        toolbar.setNavigationIcon(R.drawable.menu1);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Main_Activity)getActivity()).openDrawer();
            }
        });

        barWidth = 1f;
        barSpace = 0.2f;
        groupSpace = 0.4f;

        chart.setDescription(null);
        chart.setPinchZoom(false);
        chart.setScaleEnabled(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);

        int groupCount = 6;

        preData();

        BarDataSet set1, set2, set3;
        set1 = new BarDataSet(yVals1, "Product Tax");
        set1.setColor(getResources().getColor(R.color.blue_btn_bg_color));
        set2 = new BarDataSet(yVals2, "Invoice Tax");
        set2.setColor(getResources().getColor(R.color.blue));
        set3 = new BarDataSet(yVals3,"Sales");
        set3.setColor(getResources().getColor(R.color.greenlight));
        BarData data = new BarData(set1, set2, set3);

        data.setValueFormatter(new LargeValueFormatter());
        chart.animateY(5000);
        chart.setData(data);
        chart.getBarData().setBarWidth(barWidth);
        chart.getXAxis().setAxisMinimum(0);
        chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        chart.groupBars(0, groupSpace, barSpace);
        //chart.setVisibleYRangeMaximum(50, YAxis.AxisDependency.LEFT);
        chart.getData().setHighlightEnabled(false);

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(true);
        l.setYOffset(0f);
        l.setXOffset(0f);
        l.setYEntrySpace(10f);
        l.setTextSize(8f);

        // X-Axis
        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(3.65f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMaximum(6);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));

        // Y-axis
        chart.getAxisRight().setEnabled(false);
        YAxis leftAxis = chart.getAxisLeft();
        //leftAxis.setValueFormatter(new IndexAxisValueFormatter(yVal));
        leftAxis.setDrawGridLines(true);
        //leftAxis.setLabelCount(6);
        leftAxis.setAxisMinValue(0);
        leftAxis.setAxisMaxValue(50);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f);

        chart.invalidate();

        return view;
    }

    private void preData()
    {
        xVals = new ArrayList();

        xVals.add("July-2019");

        yVals1 = new ArrayList();
        yVals2 = new ArrayList();
        yVals3 = new ArrayList();

      /*yVals1.add(new BarEntry(1, (float) 1));
        yVals2.add(new BarEntry(1, (float) 2));
        yVals1.add(new BarEntry(2, (float) 3));
        yVals2.add(new BarEntry(2, (float) 4));
        yVals1.add(new BarEntry(3, (float) 5));
        yVals2.add(new BarEntry(3, (float) 6));
        yVals1.add(new BarEntry(4, (float) 7));
        yVals2.add(new BarEntry(4, (float) 8));
        yVals1.add(new BarEntry(5, (float) 9));
        yVals2.add(new BarEntry(5, (float) 10));*/

        yVals1.add(new BarEntry(6, (float) 5));
        yVals2.add(new BarEntry(6, (float) 8));
        yVals3.add(new BarEntry(4, (float) 11));

        // y axis label values 0k to 50k

        yVal = new ArrayList();
        yVal.add("0k");
        yVal.add("5k");
        yVal.add("10k");
        yVal.add("15k");
        yVal.add("20k");
        yVal.add("25k");
        yVal.add("30k");
        yVal.add("35k");
        yVal.add("40k");
        yVal.add("45k");
        yVal.add("50k");
        yVal.add("55k");
    }
}
