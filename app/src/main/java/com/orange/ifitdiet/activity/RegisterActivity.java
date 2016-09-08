package com.orange.ifitdiet.activity;

import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.orange.ifitdiet.R;
import com.orange.ifitdiet.domain.UserBean;
import com.orange.ifitdiet.util.NetUtil;

import java.io.FileNotFoundException;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button btn_birth = (Button) findViewById(R.id.btn_birth);
        ImageView iv_avator = (ImageView) findViewById(R.id.iv_avatar);
        iv_avator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
                intent.setType("image/*");
                /* 使用Intent.ACTION_GET_CONTENT这个Action */
                intent.setAction(Intent.ACTION_GET_CONTENT);
                /* 取得相片后返回本画面 */
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            Log.e("uri", uri.toString());
            ContentResolver cr = this.getContentResolver();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                ImageView imageView = (ImageView) findViewById(R.id.iv_avatar);
                /* 将Bitmap设定到ImageView */
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(), e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected void chooseDate(View v) {
        final EditText et_birthday = (EditText) findViewById(R.id.et_birthday);
        final Calendar c = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                c.set(year, monthOfYear, dayOfMonth);
                et_birthday.setText(DateFormat.format("yyy-MM-dd", c));
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }


    protected void register(View v) {
        EditText et_userName = (EditText) findViewById(R.id.et_username);
        RadioButton rb_male = (RadioButton) findViewById(R.id.rb_male);
        RadioButton rb_female = (RadioButton) findViewById(R.id.rb_female);
        EditText et_psw = (EditText) findViewById(R.id.et_psw);
        EditText et_cfpsw = (EditText) findViewById(R.id.et_cfpsw);
        EditText et_phone = (EditText) findViewById(R.id.et_phone);
        EditText et_email = (EditText) findViewById(R.id.et_email);//可不填
        EditText et_province = (EditText) findViewById(R.id.et_province);//可不填
        EditText et_city = (EditText) findViewById(R.id.et_city);//可不填
        EditText et_birthday = (EditText) findViewById(R.id.et_birthday);

        String userName=et_userName.getText().toString().trim();
        String psw=et_psw.getText().toString().trim();
        String cfpsw=et_cfpsw.getText().toString().trim();
        String phone=et_phone.getText().toString().trim();
        String email=et_email.getText().toString().trim();
        String hometown=et_province.getText()+et_city.getText().toString().trim();
        String birthday=et_birthday.getText().toString();
        UserBean userBean = new UserBean();
        if(!userBean.equals("")) {
            userBean.setUserName(userName);
        }else{
            Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (rb_male.isSelected()) {
            userBean.setSex(0);
        } else if (rb_female.isSelected()) {
            userBean.setSex(1);
        }
        if(psw.equals(cfpsw)){
            userBean.setPsw(et_psw.getText().toString().trim());
        }else{
            Toast.makeText(RegisterActivity.this, "两次输入的密码不一致，请再次确认", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!phone.equals("")) {
            userBean.setPhone(phone);
        }else{
            Toast.makeText(RegisterActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!birthday.equals("")){
            userBean.setBirthday(birthday);
        }else{
            Toast.makeText(RegisterActivity.this, "生日不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        userBean.setEmail(email);
        userBean.setHometown(hometown);
        boolean success= new NetUtil().register(userBean);
        if(success){
            Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(RegisterActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();
        }
    }
}
