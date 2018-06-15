package com.example.nhdoan.doanapp.ui.playVideo;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.nhdoan.doanapp.R;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class ActivityPlayVideo extends AppCompatActivity {

    private static final String url1 = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    private VideoPlayerUtil videoPlayerUtil = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        //
        videoPlayerUtil = new VideoPlayerUtil(getApplicationContext());
        findViewById(R.id.posterContainer).setVisibility(View.VISIBLE);

        findViewById(R.id.ivPlay).setOnClickListener(v->{
            findViewById(R.id.posterContainer).setBackgroundColor(Color.BLACK);
            findViewById(R.id.ivPoster).setVisibility(View.GONE);
            videoPlayerUtil.initializePlayer(findViewById(R.id.exoPlayerView), url1);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoPlayerUtil.releasePlayer();
    }

    //
    private class VideoPlayerUtil {
        private Context context;
        private VideoPlayerUtil(Context context){
            this.context =context;
            initClass();
        }

        private SimpleExoPlayer player = null;
        private Timeline.Window window = null;
        private DataSource.Factory mediaDataSourceFactory = null;
        private DefaultTrackSelector trackSelector = null;
        private Boolean shouldAutoPlay = false;
        private BandwidthMeter bandwidthMeter = null;

        private void initClass() {
            shouldAutoPlay = true;
            bandwidthMeter = new DefaultBandwidthMeter();
            String agent = Util.getUserAgent(context, "videoFragment");
            mediaDataSourceFactory =  new DefaultDataSourceFactory(context, agent, (DefaultBandwidthMeter) bandwidthMeter);
            window = new Timeline.Window();
        }

        private void initializePlayer(SimpleExoPlayerView simpleExoPlayerView,  String url) {
            simpleExoPlayerView.setVisibility(View.VISIBLE);
            simpleExoPlayerView.requestFocus();

            AdaptiveTrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);

            trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

            player = ExoPlayerFactory.newSimpleInstance(context, trackSelector);

            simpleExoPlayerView.setPlayer(player);

            player.setPlayWhenReady(shouldAutoPlay);

            DefaultExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

            ExtractorMediaSource mediaSource = new ExtractorMediaSource(Uri.parse(url),
                    mediaDataSourceFactory, extractorsFactory, null, null);

            player.prepare(mediaSource);
            //
            Player.EventListener x = new Player.EventListener(){
                @Override
                public void onTimelineChanged(Timeline timeline, Object manifest) {

                }

                @Override
                public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

                }

                @Override
                public void onLoadingChanged(boolean isLoading) {

                }

                @Override
                public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                    if (playbackState == 4) {//4 means video end
                        findViewById(R.id.ivPoster).setVisibility(View.VISIBLE);
                        findViewById(R.id.exoPlayerView).setVisibility(View.INVISIBLE);
                        //exoPlayerView.hideController()
                    }
                }

                @Override
                public void onRepeatModeChanged(int repeatMode) {

                }

                @Override
                public void onPlayerError(ExoPlaybackException error) {

                }

                @Override
                public void onPositionDiscontinuity() {

                }

                @Override
                public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

                }
            };
            player.addListener(x);
        }


        private void releasePlayer() {
            shouldAutoPlay = player.getPlayWhenReady();
            player.release();
            player = null;
            trackSelector = null;
        }
    }
}
