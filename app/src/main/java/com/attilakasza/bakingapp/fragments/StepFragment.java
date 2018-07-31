package com.attilakasza.bakingapp.fragments;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.attilakasza.bakingapp.R;
import com.attilakasza.bakingapp.models.Step;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.attilakasza.bakingapp.fragments.StepsFragment.RECIPE_STEP;

public class StepFragment extends Fragment {

    @BindView(R.id.player_view) SimpleExoPlayerView mExoPlayerView;
    @BindView(R.id.main_media_frame) FrameLayout mFrame;
    @BindView(R.id.tv_step) TextView mDescription;
    @BindView(R.id.iv_thumbnail) ImageView mThumbnail;
    public static String CURRENT_POSITION = "Current_Position";
    private Step mSteps = new Step();
    private SimpleExoPlayer mExoPlayer;
    private BandwidthMeter mBandwidthMeter;
    private Dialog mFullScreenDialog;
    private boolean mExoPlayerFullscreen = false;
    private long mPosition = C.TIME_UNSET;
    private String mVideoUrl;


    public StepFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step, container, false);
        ButterKnife.bind(this, rootView);

        if(savedInstanceState != null) {
            mSteps = savedInstanceState.getParcelable(RECIPE_STEP);
            mPosition = savedInstanceState.getLong(CURRENT_POSITION, C.TIME_UNSET);
        }
        else {
            mSteps = getArguments().getParcelable(RECIPE_STEP);
        }

        mDescription.setText(mSteps.getmDescription());

        mVideoUrl = mSteps.getmVideoUrl();
        String thumbnailUrl = mSteps.getmThumbnailUrl();
        if (mVideoUrl != null && !mVideoUrl.isEmpty()) {
            mThumbnail.setVisibility(View.GONE);
            initializeExoPlayer(Uri.parse(mVideoUrl));
        } else {
            mThumbnail.setVisibility(View.VISIBLE);
            mExoPlayerView.setVisibility(View.GONE);
            mFrame.setVisibility(View.GONE);
            if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
                Picasso.with(getContext())
                        .load(Uri.parse(thumbnailUrl))
                        .placeholder(R.drawable.ic_videocam_off)
                        .error(R.drawable.ic_error)
                        .into(mThumbnail);
            } else {
                Picasso.with(getContext())
                        .load(R.drawable.ic_videocam_off)
                        .placeholder(R.drawable.ic_videocam_off)
                        .error(R.drawable.ic_error)
                        .into(mThumbnail);
            }
        }
        return rootView;
    }

    private void initializeExoPlayer(Uri uri) {
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveVideoTrackSelection.Factory(mBandwidthMeter);
        DefaultTrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        LoadControl loadControl = new DefaultLoadControl();

        mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
        mExoPlayerView.setPlayer(mExoPlayer);

        String userAgent = Util.getUserAgent(getContext(), "Baking App");
        MediaSource mediaSource = new ExtractorMediaSource(uri, new DefaultDataSourceFactory(getContext(),
                userAgent), new DefaultExtractorsFactory(), null, null);
        if (mPosition != C.TIME_UNSET) mExoPlayer.seekTo(mPosition);
        mExoPlayer.prepare(mediaSource);
        mExoPlayer.setPlayWhenReady(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mVideoUrl != null) {
            if (mExoPlayer == null) {
                initializeExoPlayer(Uri.parse(mVideoUrl));
            }
            if (mPosition != C.TIME_UNSET) mExoPlayer.seekTo(mPosition);
        }
        if (!mExoPlayerFullscreen) {
            mFullScreenDialog = new Dialog(getContext(), android.R.style.Theme_Black_NoTitleBar_Fullscreen);
            openFullscreenDialog();
        }
        if(mExoPlayerFullscreen){
            closeFullscreenDialog();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mExoPlayer != null) {
            mPosition = mExoPlayer.getCurrentPosition();
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    private void openFullscreenDialog() {
        ((ViewGroup) mExoPlayerView.getParent()).removeView(mExoPlayerView);
        mFullScreenDialog.addContentView(mExoPlayerView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mExoPlayerFullscreen = true;
        mFullScreenDialog.show();
    }

    private void closeFullscreenDialog() {
        ((ViewGroup) mExoPlayerView.getParent()).removeView(mExoPlayerView);
        mFrame.addView(mExoPlayerView);
        mExoPlayerFullscreen = false;
        mFullScreenDialog.dismiss();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(RECIPE_STEP, mSteps);
        outState.putLong(CURRENT_POSITION, mPosition);
    }
}