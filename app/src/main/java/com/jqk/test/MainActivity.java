package com.jqk.test;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static int mShortAnimationDuration = 500;

    private static final int ERROR = 0;
    private static final int CONTENT = 1;
    private static final int PROGRESS = 2;
    private static final int EMPTY = 3;

    private LinearLayout content, progress, error, empty;

    private LinearLayout showContent, showProgress, showError, showEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        showContent.setOnClickListener(this);
        showProgress.setOnClickListener(this);
        showError.setOnClickListener(this);
        showEmpty.setOnClickListener(this);

        showView(CONTENT);
    }

    public void init() {
        content = (LinearLayout) findViewById(R.id.content);
        progress = (LinearLayout) findViewById(R.id.progress);
        error = (LinearLayout) findViewById(R.id.error);
        empty = (LinearLayout) findViewById(R.id.empty);

        showContent = (LinearLayout) findViewById(R.id.showContent);
        showProgress = (LinearLayout) findViewById(R.id.showProgress);
        showError = (LinearLayout) findViewById(R.id.showError);
        showEmpty = (LinearLayout) findViewById(R.id.showEmpty);
    }

    public void showView(int type) {
        switch (type) {
            case PROGRESS:
                if (error.getVisibility() == View.VISIBLE) {
                    crossfade(progress, error);
                } else if (empty.getVisibility() == View.VISIBLE) {
                    crossfade(progress, empty);
                } else if (content.getVisibility() == View.VISIBLE) {
                    crossfade(progress, content);
                } else {
                    crossfade(progress, null);
                }
                break;
            case ERROR:
                content.setVisibility(View.GONE);
                empty.setVisibility(View.GONE);

                crossfade(error, progress);
                break;
            case CONTENT:
                error.setVisibility(View.GONE);
                empty.setVisibility(View.GONE);

                crossfade(content, progress);
                break;
            case EMPTY:
                error.setVisibility(View.GONE);
                content.setVisibility(View.GONE);

                crossfade(empty, progress);
                break;
        }
    }

    private void crossfade(View showView, final View hideView) {

        showView.setAlpha(0f);
        showView.setVisibility(View.VISIBLE);

        showView.animate()
                .alpha(1f)
                .setDuration(mShortAnimationDuration)
                .setListener(null);

        if (hideView != null) {
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.showContent:
                showView(CONTENT);
                break;
            case R.id.showProgress:
                showView(PROGRESS);
                break;
            case R.id.showError:
                showView(ERROR);
                break;
            case R.id.showEmpty:
                showView(EMPTY);
                break;
        }
    }
}
