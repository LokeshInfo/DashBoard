package com.impetrostock.im.Activities.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.impetrostock.im.View.Main_Activity;
import com.impetrostock.im.Model.Main_Model.Function;
import com.impetrostock.im.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class Dashboard_fragment extends Fragment
{

    @BindView(R.id.graph)
    LineChart mChart;

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout SwipeRefresh;
    @BindView(R.id.txDay)
    TextView txDay;
    @BindView(R.id.txtemp)
    TextView txTemp;
    @BindView(R.id.txcloud)
    TextView txcloud;
    @BindView(R.id.txhumid)
    TextView txhumid;
    @BindView(R.id.txwind)
    TextView txwind;
    @BindView(R.id.imgcloud)
    TextView txcld;

    private String TAG=Dashboard_fragment.class.getSimpleName();

    private FusedLocationProviderClient client;

    private double Latitude;
    private double Longitude;
    private String query = "Qry";
    Typeface weatherFont;

    Toolbar toolbar;
    Activity activity;
    TextView txToolbar;
    TextView data1;
    TextView data2;
    TextView data3;
    ImageView imgToolbar;
    CalendarView calendarview;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        setHasOptionsMenu(true);
        activity = (Activity)context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard,container,false);

        ButterKnife.bind(this,view);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        txToolbar = (TextView) view.findViewById(R.id.txToolbar);
        imgToolbar = (ImageView) view.findViewById(R.id.imgToolbar);
        calendarview = (CalendarView) view.findViewById(R.id.calendarview);
        data1 = (TextView) view.findViewById(R.id.data);
        data2 = (TextView) view.findViewById(R.id.data2);
        data3 = (TextView) view.findViewById(R.id.data3);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        txToolbar.setText("Dashboard");
        toolbar.setNavigationIcon(R.drawable.menu1);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Main_Activity)getActivity()).openDrawer();
            }
        });


        requestPermission();
        client = LocationServices.getFusedLocationProviderClient(activity);


        weatherFont = Typeface.createFromAsset(activity.getAssets(), "fonts/weathericons-regular-webfont.ttf");
        txTemp.setTypeface(weatherFont);
        txcld.setTypeface(weatherFont);

        mChart.setPinchZoom(false);
        mChart.setScaleEnabled(false);
        //mChart.setDrawBarShadow(false);
        mChart.setDrawGridBackground(false);
        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        YAxis rightaxis = mChart.getAxisRight();
        rightaxis.setEnabled(false);

        LineDataSet lineset = new LineDataSet(dataValues(),null);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineset);

        lineset.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineset.setDrawFilled(true);

        LineDataSet lineset2 = new LineDataSet(dataValues2(),null);
        dataSets.add(lineset2);
        lineset2.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineset2.setDrawFilled(true);

        if (Utils.getSDKInt() >= 18) {
            Drawable drawable = ContextCompat.getDrawable(activity, R.drawable.fade_blue);
            Drawable drawable1 = ContextCompat.getDrawable(activity, R.drawable.fade_blue);
            lineset.setFillDrawable(drawable);
            lineset2.setFillDrawable(drawable1); }

        else { lineset.setFillColor(Color.BLACK);
            lineset2.setFillColor(Color.BLACK);  }

        LineData data = new LineData(dataSets);
        mChart.setData(data);
        mChart.animateXY(5000,4000, Easing.EaseInOutBounce,Easing.EaseInExpo);
        mChart.invalidate();

        taskLoadUp(query);

        SwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                taskLoadUp(query);
                SwipeRefresh.setRefreshing(false);
            }
        });

        //  Calendar View Listener
        calendarview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                    String clickDate =  i2+" / "+i1+" / "+i;
                    data1.setText(" "+clickDate);
                    data2.setText(" "+clickDate);
                    data3.setText(" "+clickDate);
                }
            });
        // end of Calendar View Listener

        return view;
    }

    //       Graph Values        //

    private List<Entry> dataValues()
    {
        ArrayList<Entry> dataVals = new ArrayList<Entry>();

        dataVals.add(new Entry(0,0));
        dataVals.add(new Entry(10,12));
        dataVals.add(new Entry(16,22));
        dataVals.add(new Entry(25,4));
        dataVals.add(new Entry(30,10));
        return dataVals;
    }

    private List<Entry> dataValues2()
    {
        ArrayList<Entry> dataVals2 = new ArrayList<Entry>();

        dataVals2.add(new Entry(0,0));
        dataVals2.add(new Entry(2,6));
        dataVals2.add(new Entry(4,6));
        dataVals2.add(new Entry(10,2));
        dataVals2.add(new Entry(12,8));
        dataVals2.add(new Entry(15,2));
        dataVals2.add(new Entry(18,6));
        dataVals2.add(new Entry(21,7));
        dataVals2.add(new Entry(24,3));
        dataVals2.add(new Entry(30,5));
        return dataVals2;
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(activity, new String[]{ACCESS_FINE_LOCATION}, 1);
    }

    //       end Graph Values      //

    public void taskLoadUp(String query) {
        if (Function.isNetworkAvailable(activity)) {
            latlon();
            DownloadWeather task = new DownloadWeather();
            task.execute(query);
        } else {
            SweetAlertDialog pDialog = new SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE);
            pDialog.setTitleText("Oops...");
            pDialog.setContentText("No Internet Connection !");
            pDialog.show();
            //Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }
    }

    //   GET WEATHER DETAILS     //

    private void latlon()
    {
        if (ActivityCompat.checkSelfPermission(activity, ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED )
        {
            return;
        }
        client.getLastLocation().addOnSuccessListener(activity, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location!=null)
                {
                    Latitude = location.getLatitude();
                    Longitude = location.getLongitude();

                    DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date date = new Date(location.getTime()) ;
                    String dTime = format.format(date) ;

                    DownloadWeather dweather = new DownloadWeather();
                    dweather.execute(query);
                    //etx.setText("Loction_T : "+location.getTime());
                }
            }
        });
    }

    class DownloadWeather extends AsyncTask< String, Void, String > {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //loader.setVisibility(View.VISIBLE);
        }
        protected String doInBackground(String...args) {

            String xml = Function.excuteGet("https://openweathermap.org/data/2.5/weather?"+
                    "lat="+Latitude+"&lon="+Longitude+"&appid=b6907d289e10d714a6e88b30761fae22");
            return xml;
        }
        @Override
        protected void onPostExecute(String xml) {

            try {
                JSONObject json = new JSONObject(xml);
                if (json != null) {
                    JSONObject details = json.getJSONArray("weather").getJSONObject(0);
                    JSONObject main = json.getJSONObject("main");
                    JSONObject wind = json.getJSONObject("wind");
                    SimpleDateFormat df = new SimpleDateFormat("EEEE");
                    Date date = new Date(json.getLong("dt")*1000);
                    String dayd = df.format(date);

                    String Temperature = String.format("%.2f", main.getDouble("temp")) + "°";

                    txDay.setText(dayd);
                    txTemp.setText(Html.fromHtml(Function.setWeatherIcon(details.getInt("id"),
                            json.getJSONObject("sys").getLong("sunrise") * 1000,
                            json.getJSONObject("sys").getLong("sunset") * 1000))+" "+Temperature);
                    txcld.setText(Html.fromHtml(Function.setWeatherIcon(details.getInt("id"),
                            json.getJSONObject("sys").getLong("sunrise") * 1000,
                            json.getJSONObject("sys").getLong("sunset") * 1000)));
                    txcloud.setText(String.format("%.2f", main.getDouble("temp")) + "°");
                    txhumid.setText(main.getString("humidity") + "%");
                    txwind.setText(wind.getDouble("speed")+"m/s");
                    Log.e("GET DATA",">>>____DOUBLE");
                    //Log.e("XML",">>>"+xml);
                }
            }                                   catch (JSONException e) {
                Toast.makeText(activity, "Error, Check City", Toast.LENGTH_SHORT).show();
            }
        }
    }
    //   end GET WEATHER DETAILS     //
}
