package com.cliknfix.user.paymentMethods;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cliknfix.user.R;
import com.cliknfix.user.base.BaseClass;
import com.cliknfix.user.homeScreen.HomeScreenActivity;
import com.cliknfix.user.paymentMethods.adapter.PaymentAdapter;
import com.cliknfix.user.paymentMethods.model.BeanPayment;
import com.cliknfix.user.technicianDetail.TechnicianDetailActivity;
import com.cliknfix.user.util.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentMethodsActivity extends BaseClass {

    @BindView(R.id.rv_payment)
    RecyclerView rvPayment;
    ArrayList<BeanPayment> paymentArrayList ;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    String phone,amount,techId,techName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_methods);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        phone = getIntent().getStringExtra("phone");
        amount = getIntent().getStringExtra("amount");
        techId = getIntent().getStringExtra("techId");
        techName = getIntent().getStringExtra("techName");

        tvTitle.setTypeface(Utility.typeFaceForBoldText(this));
        paymentArrayList=new ArrayList<>();

       // paymentArrayList.add(new BeanPayment(R.mipmap.paytm_icon,"Paytm"));
        paymentArrayList.add(new BeanPayment(R.mipmap.pay4u_icon,"Pay4u"));
       // paymentArrayList.add(new BeanPayment(R.mipmap.debit_icon,"Debit Card"));
       // paymentArrayList.add(new BeanPayment(R.mipmap.credit_icon,"Credit Card"));
        //paymentArrayList.add(new BeanPayment(R.mipmap.cash_icon,"Cash"));


        rvPayment.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        PaymentAdapter adapter = new PaymentAdapter(this, paymentArrayList,phone,amount,techId,techName);
        rvPayment.setNestedScrollingEnabled(false);
        rvPayment.setAdapter(adapter);
    }

    public void onBackClicked(View view) {
        //startActivity(new Intent(this, HomeScreenActivity.class));
        Intent intent = new Intent(this, HomeScreenActivity.class);
        intent.putExtra("DefaultTab", 1);
        startActivity(intent);
    }
}
