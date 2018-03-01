package com.example.attila.mobilradio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private TextView TextViewChannelName;
    private TextView TextViewChannelPanel;
    private Button btn_next;
    private Button btn_previous;
    private Button btn_toggle_switch;
    private ImageView favorite;
    private ProgressBar progressBar;
    private boolean radioState;
    private Button volume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        onClickListener();
    }

    public void init() {
        this.radioState = false;
        this.TextViewChannelPanel = (TextView) findViewById(R.id.TextViewChannelPanel);
        this.TextViewChannelName = (TextView) findViewById(R.id.TextViewChannelName);
        this.btn_toggle_switch = (Button) findViewById(R.id.btn_toggle_switch);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.btn_previous = (Button) findViewById(R.id.btn_previous);
        this.favorite = (ImageView) findViewById(R.id.favorite);
        this.btn_next = (Button) findViewById(R.id.btn_next);
        this.volume = (Button) findViewById(R.id.volume);
    }

    public void onClickListener() {

        btn_toggle_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.this.radioState) {
                    MainActivity.this.btn_toggle_switch.setBackgroundResource(R.drawable.switch_off);
                } else {
                    MainActivity.this.btn_toggle_switch.setBackgroundResource(R.drawable.switch_on);
                }
                MainActivity.this.radioState = !MainActivity.this.radioState;
            }
        });

        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat sf = new DecimalFormat("00.0");
                double value = Double.parseDouble(MainActivity.this.TextViewChannelPanel.getText().toString()) - 0.1;
                TextViewChannelPanel.setText(sf.format(value).replace(",", "."));
            }
        });

        btn_previous.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                DecimalFormat sf = new DecimalFormat("00.0");
                double value = Double.parseDouble(MainActivity.this.TextViewChannelPanel.getText().toString()) - 0.1;
                TextViewChannelPanel.setText(sf.format(value).replace(",", "."));
                return false;
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat sf = new DecimalFormat("00.0");
                double value = Double.parseDouble(MainActivity.this.TextViewChannelPanel.getText().toString()) + 0.1;
                TextViewChannelPanel.setText(sf.format(value).replace(",", "."));
            }
        });

        btn_next.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                DecimalFormat sf = new DecimalFormat("00.0");
                double value = Double.parseDouble(MainActivity.this.TextViewChannelPanel.getText().toString()) + 0.1;
                TextViewChannelPanel.setText(sf.format(value).replace(",", "."));
                return false;
            }
        });

    }
}
