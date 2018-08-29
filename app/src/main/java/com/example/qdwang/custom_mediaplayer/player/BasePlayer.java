package com.example.qdwang.custom_mediaplayer.player;

import android.content.Context;
import android.os.Handler;

import com.example.qdwang.custom_mediaplayer.player.factory.PlayerFactory;

/**
 * @author qdwang$
 * @date 2018/8/29$
 * @describe 播放器基类
 */
public abstract class BasePlayer implements PlayInterface.PlayerStateObserver {

    protected PlayInterface meidaPlayer;
    protected IBasePlayerListener listener;
    protected Context context;

    public BasePlayer(Context context, IBasePlayerListener listener){
        this.context = context;
        this.listener = listener;
        meidaPlayer = PlayerFactory.getInstance(context).getMediaPlayer();
        meidaPlayer.setPlayerStateObserver(this);
    }

    abstract public void play(String url);

    public void start() {
        if (meidaPlayer != null){
            meidaPlayer.start();
        }
        if (handler != null){
            handler.post(updateThread);
        }
    }

    public void stop() {
        if (meidaPlayer != null) {
            meidaPlayer.stop();
            meidaPlayer.release();
            meidaPlayer = null;
        }
        handler.removeCallbacks(updateThread);
    }

    public void pause() {
        if (meidaPlayer != null ){
            meidaPlayer.pause(false);
        }
    }

    public void seek(int pos) {
        if (meidaPlayer != null){
            meidaPlayer.seekTo(pos);
        }

    }

    public int getDuration() {
        if (meidaPlayer != null) {
            return meidaPlayer.getDuration();
        }
        return 0;
    }

    public int getPosition() {
        if (meidaPlayer != null) {
            return meidaPlayer.getCurrentPosition();
        }
        return 0;
    }

    Handler handler = new Handler();
    Runnable updateThread = new Runnable() {
        public void run() {
            // 获得歌曲现在播放位置并设置成播放进度条的值
            // 每次延迟2000毫秒再启动线程
            if (listener != null) {
                listener.updatePosition();
            }
            handler.postDelayed(updateThread, 1000);
        }
    };

    public boolean isPlaying() {
        return meidaPlayer != null && meidaPlayer.getPlayStatus() == PlayStatus.play;
    }

    @Override
    public void onStartPrepareing() {

    }

    @Override
    public void onPrepared() {
        if (listener != null) {
            listener.onPlayerPrepared();
        }
//        if(meidaPlayer != null)
//            meidaPlayer.start();
    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onStoped() {

    }

    @Override
    public void onSeekCompleted() {

    }

    @Override
    public void onStartBuffering() {

    }

    @Override
    public void onBuffered() {

    }

    @Override
    public void onBufferePersentChanged(int persent) {

    }

    @Override
    public void onVideoSizeChanged(int videoWith, int videoHeight) {

    }

    @Override
    public void onPlayCompleted() {
        if (listener != null) {
            listener.onPlayerComplete();
        }
    }

    @Override
    public void onPlayError(int errorCode, String error) {
        if (listener != null){
            listener.onExoPlayerErro();
        }
    }

    @Override
    public void onPitchChanged() {

    }

    @Override
    public void onPlayPostionChanged(int position) {

    }

    @Override
    public void onAudioOutputBuffer(byte[] data) {

    }


    interface IBasePlayerListener {
        void onPlayerCreated();

        void onPlayerPrepared();

        void onPlayerComplete();

        void updatePosition();

        void onUpdateBufferProgress(int percent);

        void onExoPlayerErro();
    }
}
