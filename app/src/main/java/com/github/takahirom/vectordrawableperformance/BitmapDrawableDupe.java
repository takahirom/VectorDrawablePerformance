package com.github.takahirom.vectordrawableperformance;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ScrollView;
import java.text.DecimalFormat;
@SuppressWarnings({"UnusedDeclaration"})
public class BitmapDrawableDupe extends Activity {
    private static final String LOGCAT = "VectorDrawable1";
    protected int[] icon = {
            R.drawable.ic_star_black_18dp,
            R.drawable.ic_3d_rotation_black_24dp,
            R.drawable.ic_android_black_48dp,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scrollView = new ScrollView(this);
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(container);
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        container.setBackgroundColor(0xFF888888);
        DecimalFormat df = new DecimalFormat("#.##");
        TextView t = new TextView(this);


        BitmapDrawable []d = new BitmapDrawable[icon.length];
        final Resources res = getResources();
        long time;
        time =  android.os.SystemClock.currentThreadTimeMillis();
        for (int i = 0; i < icon.length; i++) {
             d[i] = new BitmapDrawable(res ,BitmapFactory.decodeResource(res, icon[i]));
        }
        time =  android.os.SystemClock.currentThreadTimeMillis()-time;
        t = new TextView(this);
        t.setText("avgV=" + df.format(time / (float)(icon.length)) + " ms");
        container.addView(t);

        time =  android.os.SystemClock.currentThreadTimeMillis();
        for (int i = 0; i < icon.length; i++) {
            ImageView image = new AppCompatImageView(this);
            image.setBackgroundResource(icon[i]);
            container.addView(image, layoutParams);
        }
        setContentView(scrollView);
        time =  android.os.SystemClock.currentThreadTimeMillis()-time;
        t = new TextView(this);
        t.setText("avgS=" + df.format(time / (float)(icon.length)) + " ms");
        container.addView(t);
    }
}