package com.example.cc.dialog;

import android.Manifest;
import android.accessibilityservice.GestureDescription;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
   private Button btn_dialog1;
   private Button btn_dialog2;
   private Button btn_dialog3;
   private Button btn_dialog4;

   private String[] str=new String[]{"安全","系统设置","网络","我的文档","导航服务","我的音乐"};

   private String[] items=new String[]{"安全","系统设置","网络","我的文档","导航服务","我的音乐"};
   private String content;    //存储单选选中的内容
    //setMultiChoiceItems的第三个变量，标志哪些项是被选中的
    private boolean[]flag=new boolean[]{false,false,false,false,false,false};
    private String temp="";
    //自定义对话框
    private Button btn_dialog5;
    private SimpleAdapter simpleAdapter;
    String[] name=new String[]{"安全","系统设置","网络","我的文档","导航服务","我的音乐"};
    int[] images=new int[]{R.drawable.img01,R.drawable.img02,R.drawable.img03,
            R.drawable.img04,R.drawable.img05,R.drawable.img06};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_dialog1=findViewById(R.id.button1);
        btn_dialog2=findViewById(R.id.button2);
        btn_dialog3=findViewById(R.id.button3);
        btn_dialog4=findViewById(R.id.button4);
        btn_dialog5=findViewById(R.id.button5);

        btn_dialog1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.ic_launcher_background);
                builder.setTitle("提示");
                builder.setMessage("简单消息提示对话框");
                //setPositiveButton()方法的第二个参数是DialogInterFace类中的监听事件而不是View类中的监听事件
                builder.setPositiveButton("确定",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface arg0,int arg1) {
                        Toast.makeText(MainActivity.this,"您单击了确定",0).show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0,int arg1) {
                        Toast.makeText(MainActivity.this,"您单击了取消",0).show();
                    }
                });
                builder.show();
            }
        });
    btn_dialog2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            builder.setIcon(R.drawable.ic_launcher_background);
            builder.setTitle("提示");
            builder.setItems(str, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(MainActivity.this,"您单击了【"+str[i]+"】",0).show();
                }
            }); //弹框内容是列表
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(MainActivity.this,"您单击了确认",0).show();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {      //int i表示您单击的是那一项
                    Toast.makeText(MainActivity.this,"您单击了取消",0).show();
                }
            });
            builder.show();
        }
    });
    btn_dialog3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            builder.setIcon(R.drawable.ic_launcher_background);
            builder.setTitle("提示");
            builder.setSingleChoiceItems(str, 1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    content=str[i];
                }
            });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(MainActivity.this,content,0).show();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(MainActivity.this,"您单击了取消",0).show();
                }
            });
            builder.show();
        }
    });
    btn_dialog4.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            builder.setIcon(R.drawable.ic_launcher_background);
            builder.setTitle("提示");
            builder.setMultiChoiceItems(items, flag, new DialogInterface.OnMultiChoiceClickListener() {   //注意这里的单击事件是OnMultiChoiceClickListener()
                @Override
                //i:第i项， b:获取页面选择信息，选中为true,b不选为false
                public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                    flag[i]=b;
                }
            });
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int arg1) {
                    for(int i=0;i<flag.length;i++){
                        if(flag[i]==true){
                            temp+=items[i]+" ; ";
                        }
                    }
                    if(temp==""){
                        Toast.makeText(MainActivity.this,"您未选中任何选项",0).show();
                        return;
                    }
                    temp=temp.substring(0,temp.length()-2);
                    Toast.makeText(MainActivity.this,temp,0).show();
                    temp="";
                }
            });
            builder.setNegativeButton("取消",null);
            builder.show();
        }
    });
    btn_dialog5.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            builder.setIcon(R.drawable.ic_launcher_background);
            builder.setTitle("自定义对话框");
           /**初始化map，list
            注意必须在这里构造list对象，如果在btn_dialog5的单击事件前构造list，则每执行一次单击，都会重新构造一次list对象
            造成程序出错**/
            List<Map<String,Object>>  list=new ArrayList<>();
            for(int i=0;i<images.length;i++){
                Map<String,Object> map=new HashMap<>();
                map.put("001",images[i]);
                map.put("002",name[i]);
                list.add(map);
            }
            //不知道怎么设置弹窗的行间距
            simpleAdapter=new SimpleAdapter(MainActivity.this,list,R.layout.item1,new String[]{"001","002"},new  int[]{R.id.imageView,R.id.textView});
            builder.setAdapter(simpleAdapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {    //单击第项
                    Toast.makeText(MainActivity.this,name[i],0).show();
                }
            });
            builder.show();
        }
    });
    }
}
