package com.cliknfix.user.paymentMethods;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.cliknfix.user.R;

public class AmountDetails extends AppCompatActivity {
    EditText phone, amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_details);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Button btn = (Button) findViewById(R.id.start_transaction);
        phone = (EditText) findViewById(R.id.phone);
        amount = (EditText) findViewById(R.id.amountid);
        phone.setText(getIntent().getStringExtra("phone"));
        amount.setText(getIntent().getStringExtra("amount"));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AmountDetails.this, StartPaymentActivity.class);
                intent.putExtra("phone", phone.getText().toString());
                intent.putExtra("amount", amount.getText().toString());
                startActivity(intent);
            }
        });

    }

}
