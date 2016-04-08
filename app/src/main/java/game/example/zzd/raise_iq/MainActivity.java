package game.example.zzd.raise_iq;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

    @ViewInject(R.id.tsv_a)
    private ThingsView tsv_a;

    @ViewInject(R.id.tsv_b)
    private ThingsView tsv_b;

    @ViewInject(R.id.tsv_c)
    private ThingsView tsv_c;

    @ViewInject(R.id.tsv_d)
    private ThingsView tsv_d;

    @ViewInject(R.id.tsv_e)
    private ThingsView tsv_e;

    @ViewInject(R.id.tsv_f)
    private ThingsView tsv_f;


    long now_iq, now_money;
    int study_lv;
    int company_lv;
    int a_num, b_num, c_num, d_num, e_num, f_num;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (isonsc) {
                if (company_lv < 7) {
                    initinfo();
                    now_iq += +(a_num * speeds[0] + b_num * speeds[1] + c_num * speeds[2] +
                            d_num * speeds[3] + e_num * speeds[4] + f_num * speeds[5]);
                    now_money += salarys[company_lv];

                    editor.putLong("now_iq", now_iq);
                    editor.putLong("now_money", now_money);
                    editor.putInt("study_lv", study_lv);
                    editor.putInt("company_lv", company_lv);
                    editor.putInt("a_num", a_num);
                    editor.putInt("b_num", b_num);
                    editor.putInt("c_num", c_num);
                    editor.putInt("d_num", d_num);
                    editor.putInt("e_num", d_num);
                    editor.putInt("f_num", d_num);
                    editor.commit();
                } else {
                    showToast("恭喜你，已经通关了！");
                }
            }
            handler.sendEmptyMessageDelayed(0, 1000);
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
        bt_a.setOnClickListener(this);
        bt_b.setOnClickListener(this);
        bt_c.setOnClickListener(this);
        bt_d.setOnClickListener(this);
        bt_e.setOnClickListener(this);
        bt_f.setOnClickListener(this);
        bt_interview.setOnClickListener(this);


    }

    static String[] companys = {"无", "临时工", "小公司", "中型企业", "搜狐", "腾讯", "跨国大企", "银河系大财团", ""};
    static long[] neediqs = {0, 20, 300, 4000, 60000, 800000, 12000000, 200000000, 1000000000, 0};
    static long[] salarys = {0, 8, 64, 400, 3200, 25000, 200000, 1600000, 20000000, 0};
    static long[] studyups = {1, 3, 9, 30, 80, 240};
    static long[] speeds = {1, 3, 5, 9, 20, 400};

    public void initinfo() {
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
//        if (now_iq > neediqs[company_lv + 1]) {
//            bt_interview.setClickable(true);
//        } else {
//            bt_interview.setClickable(false);
//        }

        tv_a_num.setText((a_num > 0 ? "草稿纸X" : "？？？X") + a_num);
        tv_a_speed.setText(a_num * speeds[0] + "/秒");
        tsv_a.setThingtype(1);
        tsv_a.setThingsnum(a_num);
        tsv_a.invalidate();

        tv_b_num.setText((b_num > 0 ? "脑洞问题X" : "？？？？X") + b_num);
        tv_b_speed.setText(b_num * speeds[1] + "/秒");
        tsv_b.setThingtype(2);
        tsv_b.setThingsnum(b_num);
        tsv_b.invalidate();

        tv_c_num.setText((c_num > 0 ? "书本X" : "？？X") + c_num);
        tv_c_speed.setText(c_num * speeds[2] + "/秒");
        tsv_c.setThingtype(3);
        tsv_c.setThingsnum(c_num);
        tsv_c.invalidate();

        tv_d_num.setText((d_num > 0 ? "上网X" : "？？X") + d_num);
        tv_d_speed.setText(d_num * speeds[3] + "/秒");
        tsv_d.setThingtype(4);
        tsv_d.setThingsnum(d_num);
        tsv_d.invalidate();

        tv_e_num.setText((e_num > 0 ? "记忆芯片X" : "？？？？X") + e_num);
        tv_e_speed.setText(e_num * speeds[4] + "/秒");
        tsv_e.setThingtype(5);
        tsv_e.setThingsnum(e_num);
        tsv_e.invalidate();

        tv_f_num.setText((f_num > 0 ? "智能云存储X" : "？？？？？X") + f_num);
        tv_f_speed.setText(f_num * speeds[5] + "/秒");
        tsv_f.setThingtype(6);
        tsv_f.setThingsnum(f_num);
        tsv_f.invalidate();

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
                showToast("+" + studyups[study_lv]);
                now_iq += studyups[study_lv];
                editor.putLong("now_iq", now_iq);
                editor.commit();
                initinfo();
            }
            break;
            case R.id.bt_study_up: {
                showPopup(7);
            }
            break;
            case R.id.bt_a: {
                showPopup(1);
            }
            break;
            case R.id.bt_b: {
                showPopup(2);
            }
            break;
            case R.id.bt_c: {
                showPopup(3);
            }
            break;
            case R.id.bt_d: {
                showPopup(4);
            }
            break;
            case R.id.bt_e: {
                showPopup(5);
            }
            break;
            case R.id.bt_f: {
                showPopup(6);
            }
            break;
            case R.id.bt_interview: {
                if (now_iq >= neediqs[company_lv + 1]) {
                    now_iq -= neediqs[company_lv + 1];
                    showqs();
                } else {
                    showToast("不符合要求。");
                }
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
    Dialog alertDialog;
    Dialog qsDialog;
    TextView thing, needtingview, ordernum, jieshao;
    Button buynow;

    int redshow = 0;

    public void showPopup(final int typenum) {

        if (alertDialog == null) {
            alertDialog = new Dialog(MainActivity.this, R.style.dialog);
            View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.game_pop_layout, null);
            alertDialog.setContentView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            thing = (TextView) contentView.findViewById(R.id.tv_thing);
            needtingview = (TextView) contentView.findViewById(R.id.tv_need_thing);
            ordernum = (TextView) contentView.findViewById(R.id.tv_nowhave);
            jieshao = (TextView) contentView.findViewById(R.id.tv_thingjieshao);
            buynow = (Button) contentView.findViewById(R.id.bt_addnow);
        }
        needtingview.setTextColor(Color.BLACK);
        initalert(typenum);
        buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isiq && now_iq > needthing) {
                    now_iq -= needthing;
                    addtype(typenum);
                } else if (!isiq && now_money > needthing) {
                    now_money -= needthing;
                    addtype(typenum);
                } else {
                    needtingview.setTextColor(Color.RED);
                    needtingview.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            needtingview.setTextColor(Color.BLACK);
                        }
                    }, 500);
                }

            }
        });
        alertDialog.show();
    }

    TextView qsview;
    EditText ansview;
    Button commitbutton;

    long ansnum;

    public void showqs() {

        if (qsDialog == null) {
            qsDialog = new Dialog(MainActivity.this, R.style.dialog);
            View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.game_qs_layout, null);
            qsDialog.setContentView(contentView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            qsview = (TextView) contentView.findViewById(R.id.tv_qs);
            ansview = (EditText) contentView.findViewById(R.id.et_ans);
            commitbutton = (Button) contentView.findViewById(R.id.bt_addnow);
            commitbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ansview.getText().toString().equals(ansnum+"")) {
                        company_lv++;
                        initinfo();
                    }
                    else {
                        showToast("面试失败！");
                    }
                    qsDialog.dismiss();
                }
            });
        }
        ansview.setText("");
        int a,b;
        long c=neediqs[company_lv];
        if (c<100){
            c=100;
        }
        a= (int) (Math.random()*c);
        b= (int) (Math.random()*c);
        qsview.setText(a+"+"+b+"=");
        ansnum=a+b;
        qsDialog.setCancelable(false);
        qsDialog.setCanceledOnTouchOutside(false);
        qsDialog.show();
    }


    String st = null, jies = null;
    int needthing = 0, havenum = 0;
    boolean isiq = false;

    public void initalert(int typenum) {
        switch (typenum) {
            case 1: {
                st = "草稿纸";
                jies = "通过不断练习计算，得到更多成果";
                havenum = a_num;
                needthing = (int) (Math.pow(2, a_num) * 10);
                isiq = false;
            }
            break;
            case 2: {
                st = "脑洞问题";
                jies = "开拓思维，寻找研究问题";
                havenum = b_num;
                needthing = (int) (Math.pow(2, b_num) * 20);
                isiq = true;
            }
            break;
            case 3: {
                st = "书本";
                jies = "购买更多课程资料书工具书参考书……总有一天塞满房间！";
                havenum = c_num;
                needthing = (int) (Math.pow(2, c_num) * 60);
                isiq = false;
            }
            break;
            case 4: {
                st = "上网";
                jies = "消耗精力在无穷无尽的网络里，获取更多知识";
                havenum = d_num;
                needthing = (int) (Math.pow(2, d_num) * 190);
                isiq = true;
            }
            break;
            case 5: {
                st = "记忆芯片";
                jies = "高科技产品，植入体内可提高记忆力，过目不忘";
                havenum = e_num;
                needthing = (int) (Math.pow(2, e_num) * 800);
                isiq = false;
            }
            break;
            case 6: {
                st = "智能云存储";
                jies = "将人脑与云网络连接，扩散思维到电路所及之地";
                havenum = f_num;
                needthing = (int) (Math.pow(1.5, f_num) * 5000);
                isiq = false;
            }
            break;
            case 7: {
                st = "升级书桌";
                jies = "书桌是智力来源，更好更高级的书桌将带来更好的提升效果";
                havenum = study_lv;
                needthing = (int) (Math.pow(2, study_lv) * 200);
                isiq = false;
            }
            break;

        }
        if (havenum > 0 || typenum == 7) {
            thing.setText(st);
            jieshao.setText(jies);
        } else {
            thing.setText("？？？");
            jieshao.setText("？？？？？？？？？？？？");
        }
        needtingview.setText("需要消耗" + needthing + (isiq ? "智商" : "金钱"));
        ordernum.setText("你目前拥有的是" + havenum + (typenum == 7 ? "等级的书桌" : "个"));

    }

    public void addtype(int typenum) {
        tv_now_iq.setText("当前智商：" + now_iq);
        tv_now_money.setText("当前金钱：" + now_money);
        switch (typenum) {
            case 1: {
                a_num++;
            }
            break;
            case 2: {
                b_num++;
            }
            break;
            case 3: {
                c_num++;
            }
            break;
            case 4: {
                d_num++;
            }
            break;
            case 5: {
                e_num++;
            }
            break;
            case 6: {
                f_num++;
            }
            break;
            case 7: {
                if (study_lv < 5)
                    study_lv++;
                else {
                    study_lv = 5;
                    showToast("已经满级！");
                }

            }
            break;

        }
        initalert(typenum);
    }

    boolean isonsc;

    @Override
    protected void onResume() {
        isonsc = true;
        super.onResume();
    }

    @Override
    protected void onPause() {
        isonsc = false;
        super.onPause();
    }
}
