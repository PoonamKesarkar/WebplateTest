package com.webplate.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.webplate.R;
import com.webplate.util.CommonMethods;

import static com.webplate.util.Constant.INTERNET_REQUEST_CODE;

public class InternetActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.try_again_imageview})
    public void onClick(View view) {
        if (view.getId() == R.id.try_again_imageview) {
            if (CommonMethods.isInternetConnection(this)) {
                setResult(Activity.RESULT_OK);
                finish();
            }
        }
    }
}