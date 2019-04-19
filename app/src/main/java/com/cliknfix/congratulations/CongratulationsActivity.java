package com.cliknfix.congratulations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cliknfix.R;
import com.cliknfix.base.BaseClass;
import com.cliknfix.homeScreen.HomeScreenActivity;
import com.cliknfix.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CongratulationsActivity extends BaseClass {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_congrats)
    TextView tvCongrats;
    @BindView(R.id.tv_job_done)
    TextView tvJobDone;
    @BindView(R.id.btn_done)
    Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulations);
        ButterKnife.bind(this);
        init();
    }

    public void onDoneClicked(View view) {

        startActivity(new Intent(this, HomeScreenActivity.class));
    }

    public void init() {
        tvTitle.setTypeface(Utility.typeFaceForBoldText(this));
        tvCongrats.setTypeface(Utility.typeFaceForBoldText(this));
        tvJobDone.setTypeface(Utility.typeFaceForText(this));
        btnDone.setTypeface(Utility.typeFaceForBoldText(this));
    }
}
