package com.bjw.togglebuttondemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity implements View
        .OnClickListener {


    private MyToggleButton mtb_new;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mtb_new = (MyToggleButton) findViewById(R.id.mtb_new);
/*        mtb_new.setBackground(R.drawable.slide_background);
        mtb_new.setIcon(R.drawable.slide_icon);*/
        //mtb_new.setState(false);
        mtb_new.setStateChangeListener(new MyToggleButton.onStateChangeListener() {

            @Override
            public void stateChanged(boolean state) {
                Logger.i("开关状态改变了: "+state);
            }
        });
        mtb_new.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mtb_new:

                break;
        }
    }
}
