package app.map.shades.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import app.map.shades.R;


public class Slider_activity extends AppCompatActivity {
//    SimpleExoPlayer newExoPlayer;
//    PlayerView mainExoPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);


//        mainExoPlayer = findViewById(R.id.mainExoPlayer);
//        final ImageView playImage  = findViewById(R.id.playImage);
//
//        playImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                playImage.setVisibility(View.GONE);
//                newExoPlayer = new SimpleExoPlayer.Builder(getApplicationContext()).build();
//                newExoPlayer.prepare(new ProgressiveMediaSource.Factory(new DefaultDataSourceFactory(getApplicationContext(), Util.getUserAgent(getApplicationContext(), "StoreApp")))
//                        .createMediaSource(Uri.parse("https://dm0qx8t0i9gc9.cloudfront.net/watermarks/video/eeIr1Cv/autumn-pov-driving-shot-into-asheville-nc-at-sunset_ekloq-xge__acdd60bf102163c19777827364b9454b__P360.mp4")));
//                newExoPlayer.setPlayWhenReady(true);
//                mainExoPlayer.setPlayer(newExoPlayer);
//
//                mainExoPlayer.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        if(newExoPlayer.getPlayWhenReady()){
//                            newExoPlayer.setPlayWhenReady(false);
//                            playImage.setVisibility(View.VISIBLE);
//                        }else{
//                            playImage.setVisibility(View.GONE);
//                            newExoPlayer.setPlayWhenReady(true);
//                        }
//
//                    }
//                });
//            }
//        });


    findViewById(R.id.sliderNextBtn).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(Slider_activity.this,LocationActivity.class));
            finish();
        }
    });
    }
}