package com.my.myapplication.YoutubeModel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.my.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class YoutubeActivity extends YouTubeBaseActivity {
    YouTubePlayerView mYouTubePlayerView;
    Button btPlay;
    YouTubePlayer.OnInitializedListener mOnInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        btPlay = findViewById(R.id.btPlay);
        mYouTubePlayerView = findViewById(R.id.youtubePlay);

        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {


                youTubePlayer.loadPlaylist("PLS1QulWo1RIbfTjQvTdj8Y6yyq4R7g-Al");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mYouTubePlayerView.initialize(YoutubeConfig.getApiKey(),mOnInitializedListener);
            }
        });
    }
}
