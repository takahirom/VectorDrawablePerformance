package com.github.takahirom.vectordrawableperformance;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.text.DecimalFormat;
public class SupportVectorActivity extends Activity {
    private static final String LOG_TAG = "SupportVectorActivity";
    private static final String LOGCAT = "VectorDrawable1";
    protected int[] icon = {
            R.drawable.ic_star_black_vector_18dp,
            R.drawable.ic_3d_rotation_black_vector_24dp,
            R.drawable.ic_android_black_vector_48dp
    };
    private static final int EXTRA_TESTS = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScrollView scrollView = new ScrollView(this);
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(container);
        container.setBackgroundColor(0xFF888888);
        DecimalFormat df = new DecimalFormat("#.##");
        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        Resources res = this.getResources();
        VectorDrawableCompat []d = new VectorDrawableCompat[icon.length];
        long time =  android.os.SystemClock.currentThreadTimeMillis();
        for (int i = 0; i < icon.length; i++) {
             d[i] = VectorDrawableCompat.create(res, icon[i], getTheme());
        }
        time =  android.os.SystemClock.currentThreadTimeMillis()-time;
        TextView t = new TextView(this);
        t.setText("avgL=" + df.format(time / (float)(icon.length)) + " ms");
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