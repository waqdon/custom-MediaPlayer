package com.example.qdwang.custom_mediaplayer.player.impls;

import android.content.Context;
import android.net.Uri;
import android.view.SurfaceHolder;

import com.example.qdwang.custom_mediaplayer.player.PlayStatus;

import java.io.IOException;


/**
 * @author qdwang$
 * @date 2018/8/29$
 * @describe
 */
public class BaseMediaPlayer extends BasePlayerImp implements android.media.MediaPlayer.OnCompletionListener, android.media.MediaPlayer.OnPreparedListener, android.media.MediaPlayer.OnSeekCompleteListener, android.media.MediaPlayer.OnVideoSizeChangedListener, android.media.MediaPlayer.OnErrorListener, android.media.MediaPlayer.OnInfoListener, android.media.MediaPlayer.OnBufferingUpdateListener {

    Context context;
    android.media.MediaPlayer mediaPlayer;

    public BaseMediaPlayer(Context context) {
        this.context = context.getApplicationContext();
        if(mediaPlayer == null){
            mediaPlayer = new android.media.MediaPlayer();
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnSeekCompleteListener(this);
            mediaPlayer.setOnVideoSizeChangedListener(this);
            mediaPlayer.setOnErrorListener(this);
            mediaPlayer.setOnInfoListener(this);
            mediaPlayer.setOnBufferingUpdateListener(this);
        }
    }

    @Override
    public int getVideoWidth() {
        return mediaPlayer == null ? 0 : mediaPlayer.getVideoWidth();
    }

    @Override
    public int getVideoHeight() {
        return mediaPlayer == null ? 0 : mediaPlayer.getVideoHeight();
    }

    @Override
    public int getCurrentPosition() {
        int position = mediaPlayer == null ? 0 : mediaPlayer.getCurrentPosition();
//        Log.i("MediaPlayer", "getCurrentPosition position " + position);
        return position;
    }

    @Override
    public int getDuration() {
        return mediaPlayer == null ? 0 : mediaPlayer.getDuration();
    }

    @Override
    public PlayStatus getPlayStatus() {
        return playStatus;
    }

    @Override
    public void setDataSource(String... url) {
        if(url != null) {
            try {
                mediaPlayer.setDataSource(context, Uri.parse(url[0]));
            } catch (IOException e) {
                e.printStackTrace();
                if (observer != null)
                    observer.onPlayError(0, " ");
            }
        }
    }


    @Override
    public void pause(boolean goback) {
        mediaPlayer.pause();
    }

    @Override
    public void setDisplay(SurfaceHolder holder) {
        mediaPlayer.setDisplay(holder);
    }

    @Override
    public void stop() {
        mediaPlayer.stop();
    }

    @Override
    public void release() {
        mediaPlayer.release();
    }

    @Override
    public void start() {
        mediaPlayer.start();
    }

    @Override
    public void prepareAsync() {
        mediaPlayer.prepareAsync();
    }

    @Override
    public void seekTo(int pos) {
        mediaPlayer.seekTo(pos);
    }

    @Override
    public void rePlay() {

    }

    @Override
    public void onCompletion(android.media.MediaPlayer mp) {
        if (observer != null)
            observer.onPlayCompleted();
    }

    @Override
    public void onPrepared(android.media.MediaPlayer mp) {
        if (observer != null)
            observer.onPrepared();
    }

    @Override
    public void onSeekComplete(android.media.MediaPlayer mp) {
        if (observer != null) {
            observer.onSeekCompleted();
        }

    }

    @Override
    public void onVideoSizeChanged(android.media.MediaPlayer mp, int width, int height) {
        if (observer != null) {
            observer.onVideoSizeChanged(width, height);
        }
    }


    @Override
    public void onBufferingUpdate(android.media.MediaPlayer mediaPlayer, int i) {
//        if (observer != null) {
//            if (i == 0) {
//                observer.onStartBuffering();
//            } else if (i < 100) {
//                observer.onBufferePersentChanged(i);
//            } else if (i == 100) {
//                observer.onBuffered();
//            }
//        }
    }

    @Override
    public boolean onError(android.media.MediaPlayer mp, int what, int extra) {
        if (observer != null) {
            observer.onPlayError(0, "");
        }
        return false;
    }

    @Override
    public boolean onInfo(android.media.MediaPlayer mp, int what, int extra) {
        if(what== 701){
            observer.onStartBuffering();
        }else if(what==702){
            observer.onBuffered();
        }
        if (mp != null && mp.isPlaying()){
            playStatus = PlayStatus.play;
            if (observer != null){
                observer.onStarted();
            }
        }
        return false;
    }
}
