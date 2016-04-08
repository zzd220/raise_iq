package game.example.zzd.raise_iq;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.umeng.fb.FeedbackAgent;
import com.umeng.message.PushAgent;

/**
 * Created by ZZD on 2016/2/24.
 */
public class StartActivity extends Activity {

    FeedbackAgent fb;
    private static final String TAG = StartActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this, MainActivity.class));
            }
        });
        findViewById(R.id.help).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(StartActivity.this);
                dialog.setTitle("游戏帮助");//窗口名
                dialog.setMessage("有好的想法可以在意见里发送反馈，我可以看到并回复");
                dialog.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
        findViewById(R.id.introduction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(StartActivity.this);
                dialog.setTitle("游戏介绍");//窗口名
                dialog.setMessage("hh毕业了，开始了她的求职之旅。她每天都在书桌上学习（点击书桌提高智商），不断的求职更好的工作，还买各种有帮助的物品充实自己，花不同的精力拓展视野。\n她的未来会怎样呢？");
                dialog.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
        findViewById(R.id.suggestion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StartActivity.this, CustomActivity.class));
            }
        });
        com.umeng.fb.util.Log.LOG = true;
        setUpUmengFeedback();
    }

    private void setUpUmengFeedback() {
        fb = new FeedbackAgent(this);
        // check if the app developer has replied to the feedback or not.
        fb.sync();
        fb.openAudioFeedback();
        fb.openFeedbackPush();
        PushAgent.getInstance(this).setDebugMode(true);
        PushAgent.getInstance(this).enable();

        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean result = fb.updateUserInfo();
            }
        }).start();
    }

    boolean isfirstback=false;


    @Override
    public void onBackPressed() {
        if (isfirstback){
           finish();
        }
        Toast.makeText(this, "再次点击返回退出", Toast.LENGTH_SHORT).show();
        isfirstback=true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    isfirstback=false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
