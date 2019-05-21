package com.cliknfix.user.search;

import android.os.Bundle;
import android.widget.TextView;

import com.cliknfix.user.R;
import com.cliknfix.user.base.BaseClass;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoTechFoundActivity extends BaseClass {

    @BindView(R.id.tv_no_tech_found)
    TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_tech_found);
        ButterKnife.bind(this);
        tvMessage.setText(getIntent().getStringExtra("message"));
    }
}
