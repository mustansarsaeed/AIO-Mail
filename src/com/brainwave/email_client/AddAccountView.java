package com.brainwave.email_client;

import com.example.androidtablayout.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 
 * @author Mustansar Saeed
 *
 */
public class AddAccountView extends FrameLayout {
    public AddAccountView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public AddAccountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AddAccountView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        View view = inflate(getContext(), R.layout.add_account, null);
        addView(view);
    }
}