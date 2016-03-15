package com.github.takahirom.vectordrawableperformance;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ScrollView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.text.DecimalFormat;
@SuppressWarnings({"UnusedDeclaration"})
public class VectorDrawablePerformance extends Activity {
    private static final String LOGCAT = "VectorDrawable1";
    protected int[] icon = {
            R.drawable.ic_star_black_vector_18dp,
            R.drawable.ic_3d_rotation_black_vector_24dp,
            R.drawable.ic_android_black_vector_48dp
    };
    public static VectorDrawable create(Resources resources, int rid) {
        try {
            final XmlPullParser parser = resources.getXml(rid);
            final AttributeSet attrs = Xml.asAttributeSet(parser);
            int type;
            while ((type=parser.next()) != XmlPullParser.START_TAG &&
                    type != XmlPullParser.END_DOCUMENT) {
                // Empty loop
            }
            if (type != XmlPullParser.START_TAG) {
                throw new XmlPullParserException("No start tag found");
            }
            final VectorDrawable drawable = new VectorDrawable();
            drawable.inflate(resources, parser, attrs);
            return drawable;
        } catch (XmlPullParserException e) {
            Log.e(LOGCAT, "parser error", e);
        } catch (IOException e) {
            Log.e(LOGCAT, "parser error", e);
        }
        return null;
    }
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

        VectorDrawable []d = new VectorDrawable[icon.length];
        long time =  android.os.SystemClock.currentThreadTimeMillis();
        for (int i = 0; i < icon.length; i++) {
             d[i] = create(res,icon[i]);
        }
        time =  android.os.SystemClock.currentThreadTimeMillis()-time;
        TextView t = new TextView(this);
        t.setText("avgL=" + df.format(time / (float)(icon.length)) + " ms");
        container.addView(t);
        time =  android.os.SystemClock.currentThreadTimeMillis();
        for (int i = 0; i < icon.length; i++) {
            ImageView image = new AppCompatImageView(this);
            image.setBackgroundResource(icon[i]);
            container.addView(image,layoutParams);
        }
        setContentView(scrollView);
        time =  android.os.SystemClock.currentThreadTimeMillis()-time;
        t = new TextView(this);
        t.setText("avgS=" + df.format(time / (float)(icon.length)) + " ms");
        container.addView(t);
    }
}