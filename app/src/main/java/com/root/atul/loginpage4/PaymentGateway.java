package com.root.atul.loginpage4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.sasidhar.smaps.payumoney.PayUMoney_Constants;
import com.sasidhar.smaps.payumoney.Utils;

import java.util.HashMap;

public class PaymentGateway extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway);


        HashMap params = new HashMap<>();
        params.put(PayUMoney_Constants.KEY, "merchant_key>"); // Get merchant key from PayU Money Account
        params.put(PayUMoney_Constants.TXN_ID, "transaction_id");
        params.put(PayUMoney_Constants.AMOUNT, "amount");
        params.put(PayUMoney_Constants.PRODUCT_INFO, "product_info");
        params.put(PayUMoney_Constants.FIRST_NAME, "first_name");
        params.put(PayUMoney_Constants.EMAIL, "email");
        params.put(PayUMoney_Constants.PHONE, "phone_number");
        params.put(PayUMoney_Constants.SURL, "success_url");
        params.put(PayUMoney_Constants.FURL, "failure_url");


// User defined fields are optional (pass empty string)
        params.put(PayUMoney_Constants.UDF1, "");
        params.put(PayUMoney_Constants.UDF2, "");
        params.put(PayUMoney_Constants.UDF3, "");
        params.put(PayUMoney_Constants.UDF4, "");
        params.put(PayUMoney_Constants.UDF5, "");


// generate hash by passing params and salt
        String hash = Utils.generateHash(params,"salt"); // Get Salt from PayU Money Account
        params.put(PayUMoney_Constants.HASH, hash);


// SERVICE PROVIDER VALUE IS ALWAYS "payu_paisa".
        params.put(PayUMoney_Constants.SERVICE_PROVIDER, "payu_paisa");
        Intent intent = new Intent(this, MakePaymentActivity.class);
        intent.putExtra(PayUMoney_Constants.ENVIRONMENT, PayUMoney_Constants.ENV_DEV);
        intent.putExtra(PayUMoney_Constants.PARAMS, params);
        startActivityForResult(intent, PayUMoney_Constants.PAYMENT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PayUMoney_Constants.PAYMENT_REQUEST) {
            switch (resultCode) {
                case RESULT_OK:
                    Toast.makeText(PaymentGateway.this, "Payment Success.", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case RESULT_CANCELED:
                    Toast.makeText(PaymentGateway.this, "Payment Cancelled | Failed.", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
            }

        }
    }
}

