package game.example.zzd.raise_iq;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @ViewInject(R.id.tv_now_iq)
    private TextView tv_now_iq;
    @ViewInject(R.id.tv_now_money)
    private TextView tv_now_money;
    @ViewInject(R.id.bt_study)
    private Button bt_study;
    @ViewInject(R.id.bt_study_up)
    private Button bt_study_up;
    @ViewInject(R.id.tv_now_company)
    private TextView tv_now_company;
    @ViewInject(R.id.tv_now_neediq)
    private TextView tv_now_neediq;
    @ViewInject(R.id.tv_now_salary)
    private TextView tv_now_salary;
    @ViewInject(R.id.tv_iq_speed)
    private TextView tv_iq_speed;
    @ViewInject(R.id.tv_next_company)
    private TextView tv_next_company;
    @ViewInject(R.id.tv_next_neediq)
    private TextView tv_next_neediq;
    @ViewInject(R.id.tv_next_salary)
    private TextView tv_next_salary;
    @ViewInject(R.id.bt_interview)
    private Button bt_interview;
    @ViewInject(R.id.pb_interview)
    private ProgressBar pb_interview;
    @ViewInject(R.id.tv_a_num)
    private TextView tv_a_num;
    @ViewInject(R.id.tv_a_speed)
    private TextView tv_a_speed;
    @ViewInject(R.id.bt_a)
    private Button bt_a;
    @ViewInject(R.id.tv_b_num)
    private TextView tv_b_num;
    @ViewInject(R.id.tv_b_speed)
    private TextView tv_b_speed;
    @ViewInject(R.id.bt_b)
    private Button bt_b;
    @ViewInject(R.id.tv_c_num)
    private TextView tv_c_num;
    @ViewInject(R.id.tv_c_speed)
    private TextView tv_c_speed;
    @ViewInject(R.id.bt_c)
    private Button bt_c;
    @ViewInject(R.id.tv_d_num)
    private TextView tv_d_num;
    @ViewInject(R.id.tv_d_speed)
    private TextView tv_d_speed;
    @ViewInject(R.id.bt_d)
    private Button bt_d;
    @ViewInject(R.id.tv_e_num)
    private TextView tv_e_num;
    @ViewInject(R.id.tv_e_speed)
    private TextView tv_e_speed;
    @ViewInject(R.id.bt_e)
    private Button bt_e;
    @ViewInject(R.id.tv_f_num)
    private TextView tv_f_num;
    @ViewInject(R.id.tv_f_speed)
    private TextView tv_f_speed;
    @ViewInject(R.id.bt_f)
    private Button bt_f;

    long now_iq, now_money;
    int study_lv;
    int company_lv;
    int a_num, b_num, c_num, d_num, e_num, f_num;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            initinfo();
            handler.sendEmptyMessageDelayed(0,1000);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        x.view().inject(this);
        //获取SharedPreferences对象
        sp = getSharedPreferences("SP", MODE_PRIVATE);
        editor = sp.edit();
        now_iq = sp.getLong("now_iq", 0);
        now_money = sp.getLong("now_money", 0);
        study_lv = sp.getInt("study_lv", 0);
        company_lv = sp.getInt("company_lv", 0);
        a_num = sp.getInt("a_num", 0);
        b_num = sp.getInt("b_num", 0);
        c_num = sp.getInt("c_num", 0);
        d_num = sp.getInt("d_num", 0);
        e_num = sp.getInt("e_num", 0);
        f_num = sp.getInt("f_num", 0);

        initinfo();
        handler.sendEmptyMessageDelayed(0, 1000);
        bt_study.setOnClickListener(this);
        bt_study_up.setOnClickListener(this);

    }

    static String[] companys = {"无","临时工", "小公司", "中型企业", "搜狐", "腾讯", "跨国大企", "银河系大财团", ""};
    static long[] neediqs = {0,20, 300, 4000, 60000, 800000, 12000000, 200000000, 1000000000, 0};
    static long[] salarys = {0,20, 300, 4000, 60000, 800000, 12000000, 200000000, 1000000000, 0};
    static long[] studyups = {1, 15, 200, 3000, 40000, 600000};
    static long[] speeds = {1, 15, 200, 3000, 40000, 600000};

    public void initinfo() {
        now_iq++;
        tv_now_iq.setText("当前智商：" + now_iq);
        tv_now_money.setText("当前金钱：" + now_money);
        bt_study.setText("书桌Lv" + study_lv);
        tv_now_company.setText("当前公司：" + companys[company_lv]);
        tv_now_neediq.setText("智商要求：" + neediqs[company_lv]);
        tv_now_salary.setText("薪水：" + salarys[company_lv]);
        tv_next_company.setText("目标公司：" + companys[company_lv + 1]);
        tv_next_neediq.setText("智商要求：" + neediqs[company_lv + 1]);
        tv_next_salary.setText("薪水：" + salarys[company_lv + 1]);
        tv_iq_speed.setText("智商涨幅：\n" + (a_num * speeds[0] + b_num * speeds[1] + c_num * speeds[2] +
                d_num * speeds[3] + e_num * speeds[4] + f_num * speeds[5]) + "/秒");

        tv_a_num.setText("草稿纸X" + a_num);
        tv_a_speed.setText(a_num * speeds[0] + "/秒");

        tv_b_num.setText("脑洞问题X" + b_num);
        tv_b_speed.setText(b_num * speeds[1] + "/秒");

        tv_c_num.setText("书本X" + c_num);
        tv_c_speed.setText(c_num * speeds[2] + "/秒");

        tv_d_num.setText("上网X" + d_num);
        tv_d_speed.setText(d_num * speeds[3] + "/秒");

        tv_e_num.setText("记忆芯片X" + e_num);
        tv_e_speed.setText(e_num * speeds[4] + "/秒");

        tv_f_num.setText("智能云存储X" + f_num);
        tv_f_speed.setText(f_num * speeds[5] + "/秒");
    }

//    {
        //存入数据
//
//        editor.putString("STRING_KEY", "string");
//        editor.putInt("INT_KEY", 0);
//
//    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_study: {
                showToast("+"+studyups[study_lv]);
                now_iq+=studyups[study_lv];
                editor.putLong("now_iq", now_iq);
                editor.commit();
                initinfo();
            }
            break;
            case R.id.bt_study_up:{
                showPopup();
            }
            break;
//            case R.id.tv_a_num:{
//
//            }
//            break;
//            case R.id.tv_a_num:{
//
//            }
//            break;
//            case R.id.tv_a_num:{
//
//            }
//            break;
//            case R.id.tv_a_num:{
//
//            }
//            break;
//            case R.id.tv_a_num:{
//
//            }
//            break;
//            case R.id.tv_a_num:{
//
//            }
//            break;
//            case R.id.tv_a_num:{
//
//            }
//            break;
//            case R.id.tv_a_num:{
//
//            }
//            break;
//            case R.id.tv_a_num:{
//
//            }
//            break;
            default:
                break;

        }

    }

    Toast toast;

    public void showToast(final String string) {
        runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                              if (toast != null) {
                                  toast.cancel();
                                  toast = Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT);

                              } else {
                                  toast = Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT);
                              }
                              toast.show();
                          }
                      }
        );

    }

    PopupWindow popupWindow;

    public void showPopup() {

        Dialog alertDialog=new Dialog(MainActivity.this,R.style.dialog);
        View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.game_pop_layout, null);
        alertDialog.setContentView(contentView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        alertDialog.show();
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        if (popupWindow == null) {
//            View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.game_pop_layout, null);
//            popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//            popupWindow.setOutsideTouchable(true);
//            popupWindow.setFocusable(true);
//            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#88000000")));
//                    popupWindow.showAtLocation((ViewGroup) tv_now_iq.getParent(), Gravity.CENTER, 0, 0);
//
//        }
//
//        else     popupWindow.showAtLocation((ViewGroup) tv_now_iq.getParent(), Gravity.CENTER, 0, 0);
    }
}
