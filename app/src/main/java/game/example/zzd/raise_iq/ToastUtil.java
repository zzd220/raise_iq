package game.example.zzd.raise_iq;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by gigabud on 16-4-7.
 */
public class ToastUtil {
//    private static Handler handler = new Handler(Looper.getMainLooper());

    private static Toast toast = null;

    private static Object synObj = new Object();

    public static void showMessage(final Context act, final String msg) {
        showMessage(act, msg, Toast.LENGTH_SHORT);
    }

//    public static void showMessage(final Context act, final int msg) {
//        showMessage(act, msg, Toast.LENGTH_SHORT);
//    }

    public static void showMessage(final Context act, final String msg,
                                   final int len) {

    }


//    public static void showMessage(final Context act, final int msg,
//                                   final int len) {
//        new Thread(new Runnable() {
//            public void run() {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        synchronized (synObj) {
//                            if (toast != null) {
//                                toast.cancel();
//                                toast.setText(msg);
//                                toast.setDuration(len);
//                            } else {
//                                toast = Toast.makeText(act, msg, len);
//                            }
//                            toast.show();
//                        }
//                    }
//                });
//            }
//        }).start();
//    }

}
