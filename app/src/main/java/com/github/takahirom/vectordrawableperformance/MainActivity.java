package com.github.takahirom.vectordrawableperformance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickBitmap(View view) {
        startActivity(new Intent(this, BitmapDrawableDupe.class));
    }

    public void onClickSupportVector(View view) {
        startActivity(new Intent(this, SupportVectorActivity.class));
    }

    public void onClickVector(View view) {
        startActivity(new Intent(this, VectorDrawablePerformance.class));
    }
}
