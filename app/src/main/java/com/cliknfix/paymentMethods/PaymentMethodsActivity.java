package com.cliknfix.paymentMethods;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cliknfix.R;
import com.cliknfix.paymentMethods.adapter.PaymentAdapter;
import com.cliknfix.paymentMethods.model.BeanPayment;
import com.cliknfix.technicianDetail.TechnicianDetailActivity;
import com.cliknfix.util.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentMethodsActivity extends AppCompatActivity {

    @BindView(R.id.rv_payment)
    RecyclerView rvPayment;
    ArrayList<BeanPayment> paymentArrayList ;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_methods);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tvTitle.setTypeface(Utility.typeFaceForBoldText(this));
        paymentArrayList=new ArrayList<>();

        paymentArrayList.add(new BeanPayment(R.mipmap.paytm_icon,"Paytm"));
        paymentArrayList.add(new BeanPayment(R.mipmap.pay4u_icon    ,"Pay4u"));
        paymentArrayList.add(new BeanPayment(R.mipmap.debit_icon,"Debit Card"));
        paymentArrayList.add(new BeanPayment(R.mipmap.credit_icon,"Credit Card"));
        paymentArrayList.add(new BeanPayment(R.mipmap.cash_icon,"Cash"));


        rvPayment.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        PaymentAdapter adapter = new PaymentAdapter(this, paymentArrayList);
        rvPayment.setNestedScrollingEnabled(false);
        rvPayment.setAdapter(adapter);
    }

    public void onBackClicked(View view) {
        startActivity(new Intent(this, TechnicianDetailActivity.class));
    }
}
