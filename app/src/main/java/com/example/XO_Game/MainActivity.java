package com.example.XO_Game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    EditText P1_name,P2_name;
    SeekBar sound_skb;
    Button play_btn;
    MediaPlayer musicPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        P1_name=findViewById(R.id.player1_id);
        P2_name=findViewById(R.id.player2_id);
        play_btn=findViewById(R.id.Play_btn);
        sound_skb=findViewById(R.id.soundskb);
        getSupportActionBar().hide();
        // btn listener :
        play_btn.setOnClickListener( this);

        musicPlayer=MediaPlayer.create(this,R.raw.voise1);
        musicPlayer.seekTo(0);
        musicPlayer.setVolume(0.5f,0.5f);
        // seekbar listener :
        sound_skb.setProgress(15);
        musicPlayer.start();
        sound_skb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float volume=i/100f;
                if(i==0){
                    musicPlayer.stop();
                }else{
                    musicPlayer.start();
                }
                musicPlayer.setVolume(volume,volume);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onClick(View view) {

        Intent intplay=new Intent(MainActivity.this,GameLayout.class);
        intplay.putExtra("p1name",P1_name.getText().toString());
        intplay.putExtra("p2name",P2_name.getText().toString());
        startActivity(intplay);
    }
}