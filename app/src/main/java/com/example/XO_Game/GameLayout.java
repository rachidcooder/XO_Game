package com.example.XO_Game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class GameLayout extends AppCompatActivity implements View.OnClickListener {
  private   TextView p1_name,p2_name,txtres_p1,txtres_p2;
  private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
  private int player=1;
  private int arraywiner[]=new int[]{0,0,0,0,0,0,0,0,0};
    String p1_n;
    String p2_n;
    int p1_res,p2_res;
    AdView mAdView;
    private InterstitialAd mInterstitialAd;
    boolean isAppRuning=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_layout);
        // Action Bar :
        getSupportActionBar().hide();
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        p1_name=findViewById(R.id.player1);
        p2_name=findViewById(R.id.player2);
        txtres_p1=findViewById(R.id.player1_res);
        txtres_p2=findViewById(R.id.player2_res);
        finviewButtons();
        Buttonslistener();

        //Ads
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();

        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        mAdView = findViewById(R.id.adView);

        mAdView.loadAd(adRequest);
        //get intent with parametrs :
        p1_n=getIntent().getStringExtra("p1name");
        p2_n =getIntent().getStringExtra("p2name");
        // set players name :
        p1_name.setText(p1_n+" :");
        p2_name.setText(p2_n+" :");
    }

    private void Buttonslistener() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
    }

    private void finviewButtons() {
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);
        btn7=findViewById(R.id.btn7);
        btn8=findViewById(R.id.btn8);
        btn9=findViewById(R.id.btn9);
    }

    @Override
    public void onClick(View v) {
        setTextChanged(v);
        switch (v.getId()){
            case R.id.btn1:
                if(arraywiner[0]==0)
                arraywiner[0]=player;
                checkWinner();
                break;
            case R.id.btn2:
                if(arraywiner[1]==0)
                arraywiner[1]=player;
                checkWinner();
                break;
            case R.id.btn3:
                if(arraywiner[2]==0)
                arraywiner[2]=player;
                checkWinner();
                break;
            case R.id.btn4:
                if(arraywiner[3]==0)
                arraywiner[3]=player;
                checkWinner();
                break;
            case R.id.btn5:
                if(arraywiner[4]==0)
                arraywiner[4]=player;
                checkWinner();
                break;
            case R.id.btn6:
                if(arraywiner[5]==0)
                arraywiner[5]=player;
                checkWinner();
                break;
            case R.id.btn7:
                if(arraywiner[6]==0)
                arraywiner[6]=player;
                checkWinner();
                break;
            case R.id.btn8:
                if(arraywiner[7]==0)
                arraywiner[7]=player;
                checkWinner();
                break;
            case R.id.btn9:
                if(arraywiner[8]==0)
                arraywiner[8]=player;
                checkWinner();
                break;


        }

    }

    private void checkWinner() {
        if (arraywiner[0]==arraywiner[1]&&arraywiner[1]==arraywiner[2]&&arraywiner[0]!=0
        ||arraywiner[0]==arraywiner[3]&&arraywiner[3]==arraywiner[6]&&arraywiner[0]!=0
        ||arraywiner[0]==arraywiner[4]&&arraywiner[4]==arraywiner[8]&&arraywiner[0]!=0
        ||arraywiner[1]==arraywiner[4]&&arraywiner[4]==arraywiner[7]&&arraywiner[1]!=0
        ||arraywiner[2]==arraywiner[5]&&arraywiner[5]==arraywiner[8]&&arraywiner[2]!=0
        ||arraywiner[2]==arraywiner[4]&&arraywiner[4]==arraywiner[6]&&arraywiner[2]!=0
     ){
            if(player==2){
                AlertDialog dialog;
                AlertDialog.Builder builder=new AlertDialog.Builder(GameLayout.this);
                View v=getLayoutInflater().inflate(R.layout.popup_dialogue,null);
                builder.setView(v);
                dialog= builder.create();
                dialog.show();
                TextView tv_yes=v.findViewById(R.id.btn_yes);
                TextView tv_no=v.findViewById(R.id.btn_no);
                TextView txtg_state=v.findViewById(R.id.text_stategame);
                txtg_state.setText("the winner is "+p1_n);
                tv_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        p1_res+=1;
                        txtres_p1.setText(p1_res+"");
                        clearFields();
                        player=1;
                        Arraytozero();
                        dialog.dismiss();
                    }
                });
                tv_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                        dialog.dismiss();
                    }
                });

        }
        else if(player==1){
               // Toast.makeText(getBaseContext(),"the winner is "+p2_n,Toast.LENGTH_LONG).show();
                AlertDialog dialog;
                AlertDialog.Builder builder=new AlertDialog.Builder(GameLayout.this);
                View v=getLayoutInflater().inflate(R.layout.popup_dialogue,null);
                builder.setView(v);
                dialog= builder.create();
                dialog.show();
                TextView tv_yes=v.findViewById(R.id.btn_yes);
                TextView tv_no=v.findViewById(R.id.btn_no);
                TextView txtg_state=v.findViewById(R.id.text_stategame);
                txtg_state.setText("the winner is "+p2_n);
                tv_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        p2_res+=1;
                        txtres_p2.setText(p2_res+"");
                        clearFields();
                        Arraytozero();
                        dialog.dismiss();
                    }
                });
                tv_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                        dialog.dismiss();
                    }
                });

        }
        }
        else if(checkdraw()){
            AlertDialog.Builder builder=new AlertDialog.Builder(GameLayout.this);
            builder.setTitle("no winner !")
                    .setMessage(" Do you want to play again!")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            recreate();
                        }
                    }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
            AlertDialog dialog=builder.create();
            dialog.show();

        }
        //To load Ads
        if(isAppRuning==true)
            LoadAdsInlies();
    }

    private void setTextChanged(View v) {
        Button btnclecked= (Button) v;
        String textbtn=btnclecked.getText().toString();
        if(player==1){
            if(textbtn.isEmpty()){
            btnclecked.setText("X");
            btnclecked.setTextColor(Color.parseColor("#FFF44336"));
            player=2;}
        }
        else if (player==2){
            if(textbtn.isEmpty()){
            btnclecked.setText("O");
            btnclecked.setTextColor(Color.parseColor("#FF0BF6E0"));
            player=1;
        }}
    }
    private void clearFields(){
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");
        btn9.setText("");

    }
   private void Arraytozero(){
        for(int i=0;i<arraywiner.length;i++){
            arraywiner[i]=0;
        }
   }
  private boolean checkdraw(){
        boolean chechdrawparameter=true;
 for(int i=0;i<arraywiner.length;i++){
     if(arraywiner[i]==0){
         chechdrawparameter=false;
     }
 }
   return chechdrawparameter;}

    private void LoadAdsInlies(){

        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        if(mInterstitialAd!=null)
                            mInterstitialAd.show(GameLayout.this);
                        Log.i("TAG", "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d("TAG", loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });

}

    @Override
    protected void onStart() {
        super.onStart();
        isAppRuning=true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isAppRuning=false;
    }
}