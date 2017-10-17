package com.jqk.test;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/10/17 0017.
 */

public class SencodActrivity extends AppCompatActivity {
    private static int mShortAnimationDuration = 2000;
    private LinearLayout progress, content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        progress = (LinearLayout) findViewById(R.id.progress);
        content = (LinearLayout) findViewById(R.id.content);

        content.setVisibility(View.GONE);

        crossfade(content, progress);
    }

    private void crossfade(View showView, final View hideView) {

        showView.setAlpha(0f);
        showView.setVisibility(View.VISIBLE);

        showView.animate()
                .alpha(1f)
                .setDuration(mShortAnimationDuration)
                .setListener(null);
        hideView.animate()
                .alpha(0f)
                .setDuration(mShortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        hideView.setVisibility(View.GONE);
                    }
                });
    }
}
