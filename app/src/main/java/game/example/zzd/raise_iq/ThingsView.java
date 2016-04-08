package game.example.zzd.raise_iq;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;


import org.xutils.x;

/**
 * Created by gigabud on 16-4-8.
 */
public class ThingsView extends View {
    public ThingsView(Context context) {
        super(context);
        mContext = context;
    }

    Context mContext;

    public ThingsView(Context context, AttributeSet attrs) {

        super(context, attrs);
        mContext = context;
        paint.setAntiAlias(true);
    }

    int thingsnum = 0;
    int thingtype = 0;
    Paint paint = new Paint();

    @Override
    public void draw(Canvas canvas) {
        getdrawpng(thingtype);
        if (drawable != null)
            for (int i = thingsnum; i > 0; i--) {
                drawinnum(i, canvas);
            }
        super.draw(canvas);
    }

    public void drawinnum(int n, Canvas canvas) {

        if (n <= 6) {
            canvas.drawBitmap(getdrawpng(thingtype), new Rect(0, 0, drawable.getWidth(), drawable.getHeight()), new RectF(getMeasuredWidth() * (n - 1) / 6, 80, getMeasuredWidth() * (n) / 6, getMeasuredWidth() / 6 + 80), paint);
        } else if (n <= 14) {
            canvas.drawBitmap(getdrawpng(thingtype), new Rect(0, 0, drawable.getWidth(), drawable.getHeight()), new RectF(getMeasuredWidth() * (n - 7) / 8, 70, getMeasuredWidth() * (n - 6) / 8, getMeasuredWidth() / 8 + 70), paint);
        } else if (n <= 26) {
            canvas.drawBitmap(getdrawpng(thingtype), new Rect(0, 0, drawable.getWidth(), drawable.getHeight()), new RectF(getMeasuredWidth() * (n - 15) / 12, 60, getMeasuredWidth() * (n - 14) / 12, getMeasuredWidth() / 12 + 60), paint);

        }
    }

    Bitmap drawable;

    public Bitmap getdrawpng(int type) {
        if (drawable == null && type > 0)
            switch (type) {
                case 1: {

                    drawable = decodeResource(mContext.getResources(), R.drawable.a);
                }
                break;
                case 2: {
                    drawable = decodeResource(mContext.getResources(), R.drawable.b);
                }
                break;
                case 3: {
                    drawable = decodeResource(mContext.getResources(), R.drawable.c);
                }
                break;
                case 4: {
                    drawable = decodeResource(mContext.getResources(), R.drawable.d);
                }
                break;
                case 5: {
                    drawable = decodeResource(mContext.getResources(), R.drawable.e);
                }
                break;
                case 6: {
                    drawable = decodeResource(mContext.getResources(), R.drawable.f);
                }
                break;
            }
        return drawable;
    }

    public int getThingsnum() {
        return thingsnum;
    }

    public void setThingsnum(int thingsnum) {
        this.thingsnum = thingsnum;
    }


    public int getThingtype() {
        return thingtype;
    }

    public void setThingtype(int thingtype) {
        this.thingtype = thingtype;
    }

    private Bitmap decodeResource(Resources resources, int id) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        opts.inPurgeable = true;
        opts.inInputShareable = true;
        opts.inDensity = resources.getDisplayMetrics().densityDpi;
        opts.inTargetDensity = resources.getDisplayMetrics().densityDpi;
        return BitmapFactory.decodeResource(resources, id, opts);
    }
}
