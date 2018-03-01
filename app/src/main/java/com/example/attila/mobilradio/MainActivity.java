package com.example.attila.mobilradio;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
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
import java.io.IOException;
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

    private final static String stream = "http://stream.musicfm.hu:8000/musicfm.mp3";

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        startService();
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
                if (radioState) {
                    btn_toggle_switch.setBackgroundResource(R.drawable.switch_off);
                } else {
                    btn_toggle_switch.setBackgroundResource(R.drawable.switch_on);
                }
                radioState = !radioState;

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                }
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

    public void startService() {
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource("http://stream.musicfm.hu:8000/musicfm.mp3");
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    //mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}