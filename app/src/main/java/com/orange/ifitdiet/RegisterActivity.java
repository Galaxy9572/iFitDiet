package com.orange.ifitdiet;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.PopupWindow;

import java.io.File;

public class RegisterActivity extends AppCompatActivity {
    private ImageView avator;
    private PopupWindow mPopupWindow;
    private View mpopview;
    private Bitmap photo;
    private File mAvatorFile;
    private int CAMERA_RESULT = 100;
    private int RESULT_LOAD_IMAGE = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button btn_birth = (Button) findViewById(R.id.btn_birth);
    }

    protected void chooseDate(View v) {
        new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            }
        }, 2016, 0, 1).show();
    }

}
