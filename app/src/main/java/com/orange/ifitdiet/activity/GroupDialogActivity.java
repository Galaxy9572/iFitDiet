package com.orange.ifitdiet.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.orange.ifitdiet.R;
import com.orange.ifitdiet.common.BeanPool;
import com.orange.ifitdiet.domain.GroupBean;
import com.orange.ifitdiet.domain.UserBean;
import com.orange.ifitdiet.util.NetUtil;
import com.orange.ifitdiet.util.TimeUtil;

public class GroupDialogActivity extends Activity {
    private NetUtil netUtil= (NetUtil) MainActivity.getUtilPool().getUtilMap().get("netUtil");
    private TimeUtil timeUtil= (TimeUtil) MainActivity.getUtilPool().getUtilMap().get("timeUtil");
    private UserBean user= (UserBean) MainActivity.getBeanPool().getBeanMap().get("user");
    private BeanPool beanPool=MainActivity.getBeanPool();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//不显示activity标题
        setContentView(R.layout.activity_group_dialog);
        Button bt_confirm= (Button) findViewById(R.id.bt_confirm);
        Button bt_cancel= (Button) findViewById(R.id.bt_cancel);
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_groupName= (EditText) findViewById(R.id.et_groupName);
                final String groupName=et_groupName.getText().toString();
                String date=timeUtil.getCurrentDate();
                String userID=user.getId();
                int number=0;
                if(!"".equals(groupName)){
                    GroupBean groupBean=new GroupBean(groupName,userID,date,number);
                    netUtil.createGroup(groupBean);
                    new Thread(){
                        public void run(){
                            Looper.prepare();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(beanPool.getBeanMap().get(groupName)!=null){
                                Toast.makeText(GroupDialogActivity.this, "创建成功！", Toast.LENGTH_SHORT).show();
                                this.stop();
                                GroupDialogActivity.this.finish();
                            }else{
                                Toast.makeText(GroupDialogActivity.this, "创建失败！", Toast.LENGTH_SHORT).show();
                            }
                            Looper.loop();
                        }
                    }.start();
                }else{
                    Toast.makeText(GroupDialogActivity.this, "请输入群组名", Toast.LENGTH_SHORT).show();
                }

            }
        });

        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupDialogActivity.this.finish();
            }
        });
    }
}
