package com.impetrostock.im.View;

/**
 *   Created by Lokesh
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import com.impetrostock.im.Activities.Adapter.ExpandableListAdapter;
import com.impetrostock.im.Model.Main_Model.Nav_Menu_List;
import com.impetrostock.im.R;
import com.impetrostock.im.utility.Utilview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main_Activity extends AppCompatActivity {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    @BindView(R.id.expandableListView)
    ExpandableListView expandableListView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.fragment_layout)
    LinearLayout content;
    private Drawable empty;

    long lastPressedTime = 0;
    final int PERIOD = 2000;

    AlertDialog.Builder builder;
    FragmentManager fragmentmanager;
    FragmentTransaction fragmentTransaction;
    Fragment fragment;
    Activity activity;
    com.impetrostock.im.Activities.Adapter.ExpandableListAdapter expandableListAdapter;
    List<Nav_Menu_List> headerList = new ArrayList<>();
    HashMap<Nav_Menu_List, List<Nav_Menu_List>> childList = new HashMap<>();
    Boolean sc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_nav);
        ButterKnife.bind(this);
        empty = expandableListView.getSelector();

        activity = this;

        initview();
        prepareMenuData();
        populateExpandableList();

        builder = new AlertDialog.Builder(this);
        fragment = new com.impetrostock.im.Activities.Fragment.Dashboard_fragment();
        fragmentmanager = getSupportFragmentManager();
        fragmentTransaction =fragmentmanager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout,fragment);
        fragmentTransaction.commit();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));  }

    private void initview() {
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

       /* ActionBarDrawerToggle ttogle = new ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                float slideX = drawerView.getWidth()*slideOffset;
                content.setTranslationX(slideX);
            }     };         drawer.addDrawerListener(ttogle);*/
    }


    public void openDrawer() {
        Utilview.hidekeyboard(Main_Activity.this);
        drawer.openDrawer(GravityCompat.START);    }

    public void closeDrawer() {
        drawer.closeDrawers();
    }

    public void lockDrawer(){  drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);  }

    public void unlockDrawer(){ drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED); }

    /*@Override
    public void onBackPressed() {
        if (Utilview.isAppBack(fragment.getActivity())){
            builder.setMessage("Do you want to close Application")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Main_Activity.super.onBackPressed();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.setTitle("Exit ?");
            alertDialog.show();
        }
        else
        {
            super.onBackPressed();
        }
    }*///////

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        unlockDrawer();         }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        unlockDrawer();

        if (Utilview.isAppBack(this)) {

        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
        {
            switch (event.getAction()) {

                case KeyEvent.ACTION_DOWN:
                    if (event.getDownTime() - lastPressedTime < PERIOD) {
                        finish();
                        return true;
                    } else {
                        Toast.makeText(getApplicationContext(), "Press again to exit.",
                                Toast.LENGTH_SHORT).show();
                        lastPressedTime = event.getEventTime();
                    }
                    break;
            }
        }   }
        else if (event.getKeyCode() == KeyEvent.KEYCODE_DEL)
        {
            lockDrawer();
            return false;
        }
        else
        {
            super.onBackPressed();
        }
        return false;
    }

    private void prepareMenuData()
    {
        // Dashboard
        Nav_Menu_List menuModel = new Nav_Menu_List("Dashboard",true,false);
        headerList.add(menuModel);

        if (!menuModel.hasChildren)   {   childList.put(menuModel,null);   }

        // Inventory
        menuModel = new Nav_Menu_List("Inventory",true,true);
        headerList.add(menuModel);

        List<Nav_Menu_List> childModelsList = new ArrayList<>();
        childModelsList.add(new Nav_Menu_List("Categories",false,false));

        childModelsList.add(new Nav_Menu_List("Subcategories",false,false));

        childModelsList.add(new Nav_Menu_List("Manufacturer",false,false));

        childModelsList.add(new Nav_Menu_List("Brands",false,false));

        childModelsList.add(new Nav_Menu_List("Variants",false,false));

        childModelsList.add(new Nav_Menu_List("Attributes",false,false));

        childModelsList.add(new Nav_Menu_List("Products", false, false));

        childModelsList.add(new Nav_Menu_List("Add Product By CSV",false,false));

        childModelsList.add(new Nav_Menu_List("Damage Products",false,false));

        childModelsList.add(new Nav_Menu_List("Print Barcode",false,false));

        if (menuModel.hasChildren)
        {   childList.put(menuModel,childModelsList);        }

        // Stock
        menuModel = new Nav_Menu_List("Stock",true,true);
        headerList.add(menuModel);

        childModelsList = new ArrayList<>();
        childModelsList.add(new Nav_Menu_List("Purchases",false,false));
        childModelsList.add(new Nav_Menu_List("Add Purchases",false,false));
        childModelsList.add(new Nav_Menu_List("Invoices",false,false));
        childModelsList.add(new Nav_Menu_List("Add Invoice",false,false));
        childModelsList.add(new Nav_Menu_List("Stock Chart",false,false));
        childModelsList.add(new Nav_Menu_List("Purchase Report",false,false));
        childModelsList.add(new Nav_Menu_List("Sales Report",false,false));
        childModelsList.add(new Nav_Menu_List("Add Sales By CSV",false,false));
        childModelsList.add(new Nav_Menu_List("Add Purchase By CSV",false,false));

        if (menuModel.hasChildren)    {   childList.put(menuModel,childModelsList);    }

        //Sales
        menuModel = new Nav_Menu_List("Sales",true,true);
        headerList.add(menuModel);

        childModelsList = new ArrayList<>();
        Nav_Menu_List childModel = new Nav_Menu_List("List Sales", false, false);
        childModelsList.add(childModel);


        if (menuModel.hasChildren) {   childList.put(menuModel, childModelsList);   }


        childModelsList = new ArrayList<>();

        // Warehouse
        menuModel = new Nav_Menu_List("Warehouse",true,true);
        headerList.add(menuModel);
        childModelsList.add(new Nav_Menu_List("Warehouse",false,false));
        childModelsList.add(new Nav_Menu_List("Add Warehouse",false,false));
        childModelsList.add(new Nav_Menu_List("Transfer Product",false,false));

        if (menuModel.hasChildren) {  childList.put(menuModel, childModelsList);       }

        childModelsList = new ArrayList<>();

        // Orders
        menuModel = new Nav_Menu_List("Orders",true,true);
        headerList.add(menuModel);
        childModelsList.add(new Nav_Menu_List("Online Order",false,false));
        childModelsList.add(new Nav_Menu_List("Create An Order",false,false));

        if (menuModel.hasChildren) {  childList.put(menuModel, childModelsList);       }

        childModelsList = new ArrayList<>();

        // Service & Support
        menuModel = new Nav_Menu_List("Service & Support",true,true);
        headerList.add(menuModel);
        childModelsList.add(new Nav_Menu_List("Return",false,false));
        childModelsList.add(new Nav_Menu_List("Replacement",false,false));
        childModelsList.add(new Nav_Menu_List("Repair",false,false));

        if (menuModel.hasChildren) {  childList.put(menuModel, childModelsList);       }

        childModelsList = new ArrayList<>();

        // Account
        menuModel = new Nav_Menu_List("Account",true,true);
        headerList.add(menuModel);
        childModelsList.add(new Nav_Menu_List("Expenses List",false,false));
        childModelsList.add(new Nav_Menu_List("Add Expenses",false,false));
        childModelsList.add(new Nav_Menu_List("Employee Salary",false,false));
        childModelsList.add(new Nav_Menu_List("Bank Transactions List",false,false));
        childModelsList.add(new Nav_Menu_List("Bank Detail",false,false));
        childModelsList.add(new Nav_Menu_List("Add Withdrawal",false,false));
        childModelsList.add(new Nav_Menu_List("Add Deposit",false,false));
        childModelsList.add(new Nav_Menu_List("Add AgentsBroker ship",false,false));

        if (menuModel.hasChildren) {  childList.put(menuModel, childModelsList); }

        childModelsList = new ArrayList<>();

        // People
        menuModel = new Nav_Menu_List("People",true,true);
        headerList.add(menuModel);

        childModel = new Nav_Menu_List("Billers", false, false);
        childModelsList.add(childModel);

        childModel = new Nav_Menu_List("Customers",false,false);
        childModelsList.add(childModel);

        childModel = new Nav_Menu_List("Suppliers",false,false);
        childModelsList.add(childModel);

        childModel = new Nav_Menu_List("Dealer",false,false);
        childModelsList.add(childModel);

        childModel = new Nav_Menu_List("Agent/Brokers",false,false);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {     childList.put(menuModel, childModelsList);      }

        childModelsList = new ArrayList<>();

        // POS System
        menuModel = new Nav_Menu_List("POS System",true,true);
        headerList.add(menuModel);
        childModelsList.add(new Nav_Menu_List("Billing Desk for Biller",false,false));
        childModelsList.add(new Nav_Menu_List("Suspense Billing",false,false));

        if (menuModel.hasChildren) {  childList.put(menuModel, childModelsList);       }

        childModelsList = new ArrayList<>();

        // Shipping & Transport
        menuModel = new Nav_Menu_List("Shipping & Transport",true,true);
        headerList.add(menuModel);
        childModelsList.add(new Nav_Menu_List("Deliveries",false,false));
        childModelsList.add(new Nav_Menu_List("Assign Vehicle",false,false));
        childModelsList.add(new Nav_Menu_List("Driver",false,false));

        if (menuModel.hasChildren) {  childList.put(menuModel, childModelsList);       }

        // Website Setting
        menuModel = new Nav_Menu_List("Website Setting",true,false);
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {  childList.put(menuModel, null);   }

        childModelsList = new ArrayList<>();

        //  Settings
        menuModel = new Nav_Menu_List("Settings",true,true);
        headerList.add(menuModel);
        childModel = new Nav_Menu_List("Tax", false, false);
        childModelsList.add(childModel);

        childModel = new Nav_Menu_List("Discounts",false,false);
        childModelsList.add(childModel);

        childModel = new Nav_Menu_List("Coupons",false,false);
        childModelsList.add(childModel);

        childModel = new Nav_Menu_List("Contact",false,false);
        childModelsList.add(childModel);

        childModel = new Nav_Menu_List("Invoice Logo",false,false);
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {     childList.put(menuModel, childModelsList);      }

        // Reports
        menuModel = new Nav_Menu_List("Reports",true,false);
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {    childList.put(menuModel, null);     }
    }

    private void populateExpandableList() {

        expandableListAdapter = new ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        final int[] prevExpandPosition = {-1};
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (prevExpandPosition[0] >= 0 && prevExpandPosition[0] != groupPosition) {
                    expandableListView.collapseGroup(prevExpandPosition[0]);
                }
                prevExpandPosition[0] = groupPosition;
            }
        });

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

              int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForGroup(groupPosition));
              parent.setItemChecked(index,true);

                if (headerList.get(groupPosition).isGroup) {

                    String menuName = headerList.get(groupPosition).menuName;

                    if (!headerList.get(groupPosition).hasChildren) {
                        fragment = null;
                        switch (menuName)
                        {
                            case "Dashboard":
                                closeDrawer();
                                expandableListAdapter.sendPOsition(groupPosition);
                                expandableListAdapter.notifyDataSetChanged();
                                fragment = new com.impetrostock.im.Activities.Fragment.Dashboard_fragment();
                                break;

                            case "Reports":
                                drawer.closeDrawers();
                                expandableListAdapter.sendPOsition(groupPosition);
                                expandableListAdapter.notifyDataSetChanged();
                                fragment = new com.impetrostock.im.Activities.Fragment.Reports_fragment();
                                break;

                            case "Website Setting":
                                expandableListAdapter.sendPOsition(groupPosition);
                                expandableListAdapter.notifyDataSetChanged();
                                break;

                            default:
                                    break;
                        }
                        if (fragment !=null)
                        {
                            fragmentmanager = getSupportFragmentManager();
                            fragmentTransaction = fragmentmanager.beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_layout,fragment);
                            fragmentTransaction.addToBackStack("my_fragment");
                            fragmentTransaction.commit();
                        }
                    }
                    else {
                        for (int s = 0; s < headerList.size(); s++) {
                            if (menuName.equals(headerList.get(s).menuName))
                            {
                                expandableListAdapter.sendPOsition(groupPosition);
                                expandableListAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPosition,childPosition));
                parent.setItemChecked(index,true);

                if (childList.get(headerList.get(groupPosition)) != null) {
                    Nav_Menu_List model = childList.get(headerList.get(groupPosition)).get(childPosition);
                    fragment = null;
                    String menuName = childList.get(headerList.get(groupPosition)).get(childPosition).menuName;
                    switch (menuName)
                    {
                        case "Products":
                            drawer.closeDrawers();
                            expandableListAdapter.sendPOsition(groupPosition);
                            expandableListAdapter.notifyDataSetChanged();
                            fragment = new com.impetrostock.im.Activities.Fragment.Products_fragment();
                            break;

                        case "Purchases":
                             drawer.closeDrawers();
                             expandableListAdapter.sendPOsition(groupPosition);
                             expandableListAdapter.notifyDataSetChanged();
                             sc=true;
                             fragment = new com.impetrostock.im.Activities.Fragment.Purchases_fragment();
                             break;

                        case "List Sales":
                            drawer.closeDrawers();
                            expandableListAdapter.sendPOsition(groupPosition);
                            expandableListAdapter.notifyDataSetChanged();
                            fragment = new com.impetrostock.im.Activities.Fragment.Sales_fragment();
                            break;

                        case "Billers":
                            drawer.closeDrawers();
                            expandableListAdapter.sendPOsition(groupPosition);
                            expandableListAdapter.notifyDataSetChanged();
                            fragment = new com.impetrostock.im.Activities.Fragment.Billers_fragment();
                            break;

                        case "Customers":
                            drawer.closeDrawers();
                            expandableListAdapter.sendPOsition(groupPosition);
                            expandableListAdapter.notifyDataSetChanged();
                            fragment = new com.impetrostock.im.Activities.Fragment.Customers_fragment();
                            break;

                        case "Suppliers":
                            drawer.closeDrawers();
                            expandableListAdapter.sendPOsition(groupPosition);
                            expandableListAdapter.notifyDataSetChanged();
                            fragment = new com.impetrostock.im.Activities.Fragment.Suppliers_fragment();
                            break;

                        case "Tax":
                            drawer.closeDrawers();
                            expandableListAdapter.sendPOsition(groupPosition);
                            expandableListAdapter.notifyDataSetChanged();
                            fragment = new com.impetrostock.im.Activities.Fragment.Tax_Fragment();
                            break;

                        case "Discounts":
                            drawer.closeDrawers();
                            expandableListAdapter.sendPOsition(groupPosition);
                            expandableListAdapter.notifyDataSetChanged();
                            fragment = new com.impetrostock.im.Activities.Fragment.Discount_fragment();
                            break;

                        case "Contact":
                            drawer.closeDrawers();
                            expandableListAdapter.sendPOsition(groupPosition);
                            expandableListAdapter.notifyDataSetChanged();
                            fragment = new com.impetrostock.im.Activities.Fragment.Contact_Fragment();
                            break;

                        case "Invoice Logo":
                            drawer.closeDrawers();
                            expandableListAdapter.sendPOsition(groupPosition);
                            expandableListAdapter.notifyDataSetChanged();
                            fragment = new com.impetrostock.im.Activities.Fragment.Change_logo_fragment();
                            break;

                        case "Categories":
                            drawer.closeDrawers();
                            expandableListAdapter.sendPOsition(groupPosition);
                            expandableListAdapter.notifyDataSetChanged();
                            fragment = new com.impetrostock.im.Activities.Fragment.Categories_fragment();
                            break;

                        case "Subcategories":
                            drawer.closeDrawers();
                            expandableListAdapter.sendPOsition(groupPosition);
                            expandableListAdapter.notifyDataSetChanged();
                            fragment = new com.impetrostock.im.Activities.Fragment.SubCategory_fragment();
                            break;

                        case "Manufacturer":
                            drawer.closeDrawers();
                            expandableListAdapter.sendPOsition(groupPosition);
                            expandableListAdapter.notifyDataSetChanged();
                            fragment = new com.impetrostock.im.Activities.Fragment.Manufacturer_fragment();
                            break;

                        case "Brands":
                            drawer.closeDrawers();
                            expandableListAdapter.sendPOsition(groupPosition);
                            expandableListAdapter.notifyDataSetChanged();
                            fragment = new com.impetrostock.im.Activities.Fragment.Brands_fragment();
                            break;

                        case "Variants":
                            drawer.closeDrawers();
                            expandableListAdapter.sendPOsition(groupPosition);
                            expandableListAdapter.notifyDataSetChanged();
                            fragment = new com.impetrostock.im.Activities.Fragment.Variant_fragment();
                            break;

                        case "Attributes":
                            drawer.closeDrawers();
                            expandableListAdapter.sendPOsition(groupPosition);
                            expandableListAdapter.notifyDataSetChanged();
                            fragment = new com.impetrostock.im.Activities.Fragment.Attributes_fragment();
                            break;

                        case "Add Product By CSV":
                            drawer.closeDrawers();
                            expandableListAdapter.sendPOsition(groupPosition);
                            expandableListAdapter.notifyDataSetChanged();
                            fragment = new com.impetrostock.im.Activities.Fragment.Update_Product_Price_fragment();
                            break;

                        case "Damage Products":
                            drawer.closeDrawers();
                            expandableListAdapter.sendPOsition(groupPosition);
                            expandableListAdapter.notifyDataSetChanged();
                            fragment = new com.impetrostock.im.Activities.Fragment.Damage_Products_fragment();
                            break;

                        case "Add Purchases":
                            drawer.closeDrawers();
                            expandableListAdapter.sendPOsition(groupPosition);
                            expandableListAdapter.notifyDataSetChanged();
                            sc=false;
                            fragment = new com.impetrostock.im.Activities.Fragment.Add_Purchase();
                            break;

                        case "Purchase Report":
                            drawer.closeDrawers();
                            expandableListAdapter.sendPOsition(groupPosition);
                            expandableListAdapter.notifyDataSetChanged();
                            fragment = new com.impetrostock.im.Activities.Fragment.Purchase_Report_fragment();
                            break;

                        case "Sales Report":
                            drawer.closeDrawers();
                            expandableListAdapter.sendPOsition(groupPosition);
                            expandableListAdapter.notifyDataSetChanged();
                            fragment = new com.impetrostock.im.Activities.Fragment.Sales_Report_Fragment();
                            break;

                        case "Warehouse":
                            drawer.closeDrawers();
                            expandableListAdapter.sendPOsition(groupPosition);
                            expandableListAdapter.notifyDataSetChanged();
                            sc=true;
                            fragment = new com.impetrostock.im.Activities.Fragment.Warehouse();
                            break;

                        case "Add Warehouse":
                            drawer.closeDrawers();
                            expandableListAdapter.sendPOsition(groupPosition);
                            expandableListAdapter.notifyDataSetChanged();
                            sc=false;
                            fragment = new com.impetrostock.im.Activities.Fragment.Add_Warehouse();
                            break;

                        case "Transfer Product":
                            drawer.closeDrawers();
                            expandableListAdapter.sendPOsition(groupPosition);
                            expandableListAdapter.notifyDataSetChanged();
                            fragment = new com.impetrostock.im.Activities.Fragment2.Tranfers_Fragment();
                            break;

                        default:
                            break;
                    }
                    if (fragment !=null)
                    {
                        fragmentmanager = getSupportFragmentManager();
                        fragmentTransaction = fragmentmanager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_layout,fragment);
                        fragmentTransaction.commit();
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment frag = getSupportFragmentManager().findFragmentById(R.id.fragment_layout);
        frag.onActivityResult(requestCode,resultCode,data);
    }

    // For Add_Purchase Fragment Navigation or Back icon...
    public Boolean screenNav()
    {
        if (sc)
        {  return true;   }
        else
        {  return  false; }
    }
}
