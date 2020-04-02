package cn.edu.sdwu.android.classroom.sn170507180107;

import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.system.ErrnoException;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ch4_Activity extends AppCompatActivity implements View.OnFocusChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //加载界面不要写文件名，使用资源ID来引用资源
        setContentView(R.layout.layout_ch4_1);
        //1获取普通界面组件
        Button button=(Button) findViewById(R.id.button1);
        ImageView imageView=(ImageView)findViewById(R.id.ch4_iv);
        //调用事件源的setXXXLListener方法注册事件监听器
        button.setOnClickListener(new MyClickListener());
        //使用内部匿名类
        imageView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view){
                //设置壁纸
                WallpaperManager wallpaperManager=(WallpaperManager) getSystemService(WALLPAPER_SERVICE);
                try{
                    wallpaperManager.setResource(R.raw.timg);
                }catch(Exception e){
                    Log.e(Ch4_Activity.class.toString(),e.toString());

                }


                return true;
            }
        });
        EditText email=(EditText) findViewById(R.id.ch4_1_mail);
        email.setOnFocusChangeListener(this);
        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.ch4_1_11);
        linearLayout.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float x=motionEvent.getX();
                float y=motionEvent.getY();
                TextView textView=(TextView)findViewById(R.id.ch4_1_tv);
                textView.setText("x:"+x+",y:"+y);
                return true;
            }
        });
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        //参数b代表是否获取焦点
        //判断邮箱地址的合法性
        EditText editText=(EditText)view;
        if (!b) {
            String  content=editText.getText().toString();//得到EditText的内容;
            String regEx1="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern pattern=Pattern.compile(regEx1);
            Matcher matcher=pattern.matcher(content);
            TextView textView=(TextView)findViewById(R.id.ch4_1_tv);
            if(matcher.matches()){
                textView.setText("");
            }else{
                textView.setText("eamil invalidate");
            }
        }
    }


    //2实现事件监听类，该监听类是一个特殊的Java类，必须实现一个XXXListener接口

    class MyClickListener implements View.OnClickListener{
        @Override
        public  void onClick(View view){
           Log.i(Ch4_Activity.class.toString(),"button click");
        }
    }

    public void startMain(View view){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

    }


}
