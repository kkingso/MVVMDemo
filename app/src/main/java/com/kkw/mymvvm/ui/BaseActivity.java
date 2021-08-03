package com.kkw.mymvvm.ui;

import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        if (getIntent() != null && getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            initArgs(bundle);
        }
        initView();
        initData();
        register();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregister();
    }

    protected void findAndClick(@IdRes int viewId , View.OnClickListener clickListener){
        View view = findViewById(viewId);
        if (view != null){
            view.setOnClickListener(clickListener);
        }
    }

    protected void setVisibility(@IdRes int viewId, int visibility) {
        View view = findViewById(viewId);
        if (view != null) {
            view.setVisibility(visibility);
        }
    }

    protected void setText(@IdRes int viewId , String string){
        TextView view = findViewById(viewId);
        if (view != null){
            view.setText(string);
        }
    }

    protected void setText(@IdRes int viewId, SpannableString string) {
        TextView view = findViewById(viewId);
        if (view != null) {
            view.setText(string);
        }
    }

    // 默认实现的方法
    protected void register() {
    }

    protected void unregister() {
    }

    protected abstract @LayoutRes
    int getLayoutId();

    protected abstract void initView();

    protected void initArgs(@NonNull Bundle bundle) {

    }

    protected void initData() {

    }

}