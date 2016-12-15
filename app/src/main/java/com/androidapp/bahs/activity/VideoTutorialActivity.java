package com.androidapp.bahs.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.androidapp.bahs.R;
import com.androidapp.bahs.activity.base.BaseActivity;

import java.io.IOException;

/**
 * Created by Mobikasa Night on 12/16/2016.
 */
public class VideoTutorialActivity extends BaseActivity {


    private RelativeLayout bannerLayout;
    private TextureVideoViewLoadHome mCustomVideoView;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_vedio_tutorial);
        initView();
    }

    private void initView() {
        bannerLayout = (RelativeLayout)findViewById(R.id.bannerLayout);
        addVideo();
    }


    private void addVideo() {
        try {

            bannerLayout.removeAllViews();
            bannerLayout.setVisibility(View.VISIBLE);
            mCustomVideoView = new TextureVideoViewLoadHome(this);
            mCustomVideoView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            bannerLayout.addView(mCustomVideoView);
        } catch (Exception e) {

        }
    }


    public class TextureVideoViewLoadHome extends TextureView implements TextureView.SurfaceTextureListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnVideoSizeChangedListener {

        private final String TAG = TextureVideoViewLoadHome.class.getName();
        private MediaPlayer mMediaPlayer;
        private float mVideoHeight;
        private float mVideoWidth;
        private String FILE_NAME = "home_video.mp4";

        public TextureVideoViewLoadHome(Context context) {
            super(context);
            initView();
        }

        public TextureVideoViewLoadHome(Context context, AttributeSet attrs) {
            super(context, attrs);
            initView();
        }

        public TextureVideoViewLoadHome(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            initView();
        }

        private void initView() {
            calculateVideoSize();
            setSurfaceTextureListener(this);
        }

        public MediaPlayer getMediaPlayer() {
            return mMediaPlayer;
        }

        private void updateTextureViewSize() {
            float viewWidth = getWidth();
            float viewHeight = getHeight();

            float scaleX = 1.0f;
            float scaleY = 1.0f;
            if (mVideoWidth > 0 && mVideoHeight > 0) {
                if (mVideoWidth > viewWidth && mVideoHeight > viewHeight) {
                    scaleX = mVideoWidth / viewWidth;
                    scaleY = mVideoHeight / viewHeight;
                } else if (mVideoWidth < viewWidth && mVideoHeight < viewHeight) {
                    scaleY = viewWidth / mVideoWidth;
                    scaleX = viewHeight / mVideoHeight;
                } else if (viewWidth > mVideoWidth) {
                    scaleY = (viewWidth / mVideoWidth) / (viewHeight / mVideoHeight);
                } else if (viewHeight > mVideoHeight) {
                    scaleX = (viewHeight / mVideoHeight) / (viewWidth / mVideoWidth);
                }
            }
        }

        private void calculateVideoSize() {
            try {
                AssetFileDescriptor afd = getContext().getAssets().openFd(FILE_NAME);
                MediaMetadataRetriever metaRetriever = new MediaMetadataRetriever();
                metaRetriever.setDataSource(
                        afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                String height = metaRetriever
                        .extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT);
                String width = metaRetriever
                        .extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH);
                mVideoHeight = Float.parseFloat(height);
                mVideoWidth = Float.parseFloat(width);

            } catch (IOException e) {
            } catch (NumberFormatException e) {
            } catch (Exception e) {
            }
        }


        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int width, int height) {
            Surface surface = new Surface(surfaceTexture);

            try {
                AssetFileDescriptor afd = getContext().getAssets().openFd(FILE_NAME);
                mMediaPlayer = new MediaPlayer();
                mMediaPlayer
                        .setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mMediaPlayer.setSurface(surface);
                mMediaPlayer.setLooping(false);

                // don't forget to call MediaPlayer.prepareAsync() method when you use constructor for
                // creating MediaPlayer
                mMediaPlayer.prepareAsync();
                mMediaPlayer.setOnVideoSizeChangedListener(this);
                mMediaPlayer.setOnCompletionListener(this);
                // Play video when the media source is ready for playback.
                mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mediaPlayer.start();
                    }
                });

            } catch (IllegalArgumentException e) {
            } catch (SecurityException e) {
            } catch (IllegalStateException e) {
            } catch (IOException e) {
            }
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {

        }

        @Override
        public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
            updateTextureViewSize();
        }

        @Override
        public void onCompletion(MediaPlayer mp) {
            Intent in=new Intent(((VideoTutorialActivity.this)),HomeActivity.class);
            mContext.startActivity(in);
        }
    }
}
