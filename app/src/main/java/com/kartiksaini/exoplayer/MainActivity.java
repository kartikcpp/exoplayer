package com.kartiksaini.exoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.Util;

public class MainActivity extends AppCompatActivity {
        PlayerView playerView;
        private SimpleExoPlayer myexoplayer;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerView=findViewById(R.id.video_view);
    }

    @Override
    protected void onPause() {
        super.onPause();
        releasePlayer();
    }

    private void initializeplayer(){
        myexoplayer=new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(myexoplayer);
        MediaItem myimedia=MediaItem.fromUri("https://storage.googleapis.com/exoplayer-test-media-0/play.mp3");
        myexoplayer.setMediaItem(myimedia);
        myexoplayer.setPlayWhenReady(playWhenReady);
        myexoplayer.seekTo(currentWindow, playbackPosition);
        myexoplayer.prepare();
    }
    private void releasePlayer() {
        if (myexoplayer != null) {
            playWhenReady = myexoplayer.getPlayWhenReady();
            playbackPosition = myexoplayer.getCurrentPosition();
            currentWindow = myexoplayer.getCurrentWindowIndex();
            myexoplayer.release();
            myexoplayer = null;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
       initializeplayer();
    }
}