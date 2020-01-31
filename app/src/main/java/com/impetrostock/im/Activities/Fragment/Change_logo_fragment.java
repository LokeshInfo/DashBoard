package com.impetrostock.im.Activities.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.impetrostock.im.View.Main_Activity;
import com.impetrostock.im.R;
import com.impetrostock.im.utility.Utilview;
import com.soundcloud.android.crop.Crop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.tajchert.nammu.Nammu;
import pl.tajchert.nammu.PermissionCallback;

import static android.app.Activity.RESULT_OK;

public class Change_logo_fragment extends Fragment
{
    @BindView(R.id.Selectimg)
    Button Selectimg;
    @BindView(R.id.Selectimg2)
    Button Selectimg2;
    @BindView(R.id.UploadLogo)
    Button UploadLogo;
    @BindView(R.id.UploadLogo2)
    Button UploadLogo2;
    @BindView(R.id.imgName)
    TextView imgName;
    @BindView(R.id.imgName2)
    TextView imgName2;


    private Bitmap bitmap;
    private Bitmap bitmap2;
    String strimgNm="";
    String strimgNm2="";
    String image="";
    String image2="";
    Toolbar toolbar;
    Activity activity;
    TextView txToolbar;
    ImageView imgToolbar;
    Change_logo_fragment change_logo_fragment;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity)context;
        change_logo_fragment = this;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.change_logo_fragment,container,false);

        ButterKnife.bind(this,view);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        txToolbar = (TextView) view.findViewById(R.id.txToolbar);
        imgToolbar = (ImageView) view.findViewById(R.id.imgToolbar);

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        txToolbar.setText("Change Site Logo");
        toolbar.setNavigationIcon(R.drawable.menu1);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Main_Activity)getActivity()).openDrawer();
            }
        });

        return view;
    }

    @OnClick({R.id.Selectimg,R.id.Selectimg2,R.id.UploadLogo,R.id.UploadLogo2})
    public void Click(View view)
    {
        switch (view.getId())
        {
            case R.id.Selectimg:
                        seleImage();
                        break;

            case R.id.Selectimg2:
                seleImage();
                break;
        }
    }


    // Selecting Image From Gallery //

    public void seleImage()
    {
        if (Build.VERSION.SDK_INT >= 23) {
            int permissionCheck = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                Nammu.askForPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE, new PermissionCallback() {
                    @Override
                    public void permissionGranted() {
                        //Nothing, this sample saves to Public gallery so it needs permission
                        Crop.pickImage(activity);
                    }

                    @Override
                    public void permissionRefused() {  activity.finish();      }
                });
            } else {
                Crop.pickImage(activity);
            }
        } else {
            Crop.pickImage(activity);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK){
                beginCrop(data.getData());
           Uri selectedImageUri = data.getData();
            String s = Utilview.getRealPathFromURI(selectedImageUri,activity);
            if (strimgNm.equals(""))
            {
            strimgNm = s.substring(s.lastIndexOf("/")+1);
            imgName.setText(""+strimgNm);}
            else
            {strimgNm2 = s.substring(s.lastIndexOf("/")+1);
             imgName2.setText(""+strimgNm2);       }
        } else if(requestCode == Crop.REQUEST_CROP) {
            handleCrop(resultCode,data);
        }
    }


    private void beginCrop(Uri source) {

        //Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
        Uri destination = Uri.fromFile(new File(activity.getCacheDir(),"cropped"));
        Crop.of(source, destination).asSquare().start(activity);
    }


    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {

            if (image.equals("")) {
                Uri abc = Crop.getOutput(result);
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), abc);
                    ByteArrayOutputStream bytess = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytess);

                    byte[] b = bytess.toByteArray();
                    image = Base64.encodeToString(b, Base64.DEFAULT);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.e("Image : ...", "" + image);
                //  imageviewProduct.setImageBitmap(bitmap);

            }
            else
            {
                Uri abc2 = Crop.getOutput(result);
                try {
                    bitmap2 = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), abc2);
                    ByteArrayOutputStream bytess = new ByteArrayOutputStream();
                    bitmap2.compress(Bitmap.CompressFormat.JPEG, 90, bytess);

                    byte[] b2 = bytess.toByteArray();
                    image2 = Base64.encodeToString(b2, Base64.DEFAULT);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.e("Image : ...", "" + image);

            }
        }else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(activity, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // end Selecting Image //

}

