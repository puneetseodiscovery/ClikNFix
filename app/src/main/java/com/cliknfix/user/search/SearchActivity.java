package com.cliknfix.user.search;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.agrawalsuneet.dotsloader.loaders.CircularDotsLoader;
import com.cliknfix.user.R;
import com.cliknfix.user.technicianDetail.TechnicianDetailActivity;
import com.cliknfix.user.util.Utility;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.ll_circular_progress)
    LinearLayout linearLayout;
    CircularDotsLoader progressDialog;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_send_msg_text)
    TextView tvSendMsgText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        init();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                startNewActivity();
            }
        }, 3000);
    }

    private void init() {
        linearLayout.addView(Utility.circularLoader(this));
        TextView tvLoading = new TextView(this);
        tvLoading.setText("Please wait");
        tvLoading.setTextSize(15);
        tvLoading.setTextColor(getResources().getColor(R.color.blackColor));
        tvLoading.setGravity(Gravity.CENTER);
        tvLoading.setTypeface(Utility.typeFaceForText(this));
        linearLayout.addView(tvLoading);
        tvTitle.setTypeface(Utility.typeFaceForBoldText(this));
        tvSendMsgText.setTypeface(Utility.typeFaceForText(this));
    }

    public void startNewActivity(){
        startActivity(new Intent(this, TechnicianDetailActivity.class));
    }
}
