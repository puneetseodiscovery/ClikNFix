package com.cliknfix.user.paymentMethods.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cliknfix.user.R;
import com.cliknfix.user.paymentMethods.AmountDetails;
import com.cliknfix.user.paymentMethods.CardDetailActivity;
import com.cliknfix.user.paymentMethods.PaymentSuccessActivity;
import com.cliknfix.user.paymentMethods.StartPaymentActivity;
import com.cliknfix.user.paymentMethods.model.BeanPayment;
import com.cliknfix.user.util.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolder> {

    Context context;
    ArrayList<BeanPayment> list = new ArrayList<>();
    String amount,phone;

    public PaymentAdapter(Context context, ArrayList<BeanPayment> list,String phone, String amount) {
        this.context = context;
        this.list = list;
        this.phone = phone;
        this.amount = amount;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_payment_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.ivPayment.setImageResource(list.get(position).getPaymentImg());
        holder.tvPaymentMethod.setText(list.get(position).getPaymentMethodName());
        holder.llPaymentItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "" + list.get(position).getCatName(), Toast.LENGTH_SHORT).show();
                loadNewActivity(list.get(position).getPaymentMethodName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_payment_view_item)
        LinearLayout llPaymentItem;
        @BindView(R.id.iv_payment)
        ImageView ivPayment;
        @BindView(R.id.iv_next)
        ImageView ivNext;
        @BindView(R.id.tv_payment)
        TextView tvPaymentMethod;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            init();
        }

        public void init() {
            tvPaymentMethod.setTypeface(Utility.typeFaceForSemiBoldText(context));
        }
    }

    public void loadNewActivity(String paymentMethod) {
        if(paymentMethod.equalsIgnoreCase("Cash") || paymentMethod.equalsIgnoreCase("Paytm")
                || paymentMethod.equals("Pay4u")) {
            Intent intent = new Intent(context, StartPaymentActivity.class);
            intent.putExtra("phone", phone);
            intent.putExtra("amount", amount);
            context.startActivity(intent);
            //context.startActivity(new Intent(context, AmountDetails.class));
        }
        else if(paymentMethod.equalsIgnoreCase("Debit Card") || paymentMethod.equalsIgnoreCase("Credit Card")) {
            Intent intent = new Intent(context, CardDetailActivity.class);
            intent.putExtra("paymentMethod", paymentMethod);
            context.startActivity(intent);
        }
    }
}
