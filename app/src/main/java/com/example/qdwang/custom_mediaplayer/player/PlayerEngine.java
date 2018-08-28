package com.example.qdwang.custom_mediaplayer.player;

import android.content.Context;
import android.text.TextUtils;
import android.view.SurfaceView;

import com.example.qdwang.custom_mediaplayer.player.baseengine.BasePlayEngine;

/**
 * @author qdwang$
 * @date 2018/8/29$
 * @describe 播放器引擎
 */
public class PlayerEngine extends BasePlayEngine implements Player.IPlayerListener {

    private IPlayerEngienListerner listener;
    private Player player;
    private Context context;
    private String videoUrl;

    public void setILivePlayerEngienListerner(IPlayerEngienListerner listener) {
        this.listener = listener;
    }

    public PlayerEngine(Context context){
        this.context = context;
    }

    /**
     * 设置播放视频的数据
     * @param videoUrl 带播放地址的数据
     */
    public void setDataSource(String videoUrl){
        this.videoUrl = videoUrl;
    }

    @Override
    public void startPlay() {
        setPlayStatus(PlayStatus.prepare);
        if (!TextUtils.isEmpty(videoUrl)) {
            playVedio();
        }else {
            //TODA---后期业务处理
        }
    }

    /**
     * 播放视频
     */
    private void playVedio() {
        if (listener != null) {
            listener.onShowLoading();
        }
        if(player == null && !(player instanceof BasePlayer)){
            player = new Player(context, this);
        }
    }

    @Override
    public void pausePlay() {
        if (player == null) {
            return;
        }
        player.pause();
        setPlayStatus(PlayStatus.pause);
    }

    @Override
    public void seekPlay(int pos) {
        if (player == null) {
            return;
        }
        player.seek(pos);
    }

    @Override
    public void resumePlay() {
        if (player == null) {
            return;
        }
        player.start();
        setPlayStatus(PlayStatus.play);
    }

    @Override
    public void stopPlay() {
        if (player == null) {
            return;
        }
        player.stop();
        setPlayStatus(PlayStatus.stop);
    }

    @Override
    public int getDuration() {
        if (player == null) {
            return 0;
        }
        return player.getDuration();
    }

    @Override
    public int getPosition() {
        if (player == null) {
            return 0;
        }
        return player.getPosition();
    }

    @Override
    public void onPlayerCreated() {
        if (player == null) {
            return;
        }
        if (TextUtils.isEmpty(videoUrl)) {
            return;
        }
        player.play(videoUrl);
    }

    @Override
    public void onPlayerPrepared() {
        if (player == null) {
            return;
        }

        if (listener != null) {
            listener.onDimissLoading();
        }

        player.start();

        setPlayStatus(PlayStatus.play);
        if (listener != null) {
            listener.onPlayDurationChanged();
        }
    }

    @Override
    public void onPlayerComplete() {
        if (player != null) {
            player.stop();
        }

        if (listener != null) {
            listener.onPlayComplete();
        }
    }

    @Override
    public void updatePosition() {
        if (listener != null) {
            listener.onPlayPositionChanged();
        }
    }

    @Override
    public void onUpdateBufferProgress(int percent) {
        if (percent == 100) {
            listener.onDimissLoading();
        } else {
            listener.onShowLoading();
        }
    }

    @Override
    public void onExoPlayerErro() {
        if (listener != null) {
            listener.onPlayErro();
        }
    }

    @Override
    public void onSurfaceViewCreated(SurfaceView surfaceView) {
        if (listener != null) {
            listener.addPlayView(surfaceView);
        }
    }

    @Override
    public void onVideoViewSizeChanged(SurfaceView view, int width, int height) {
        if (listener != null) {
            listener.onViewSizeChanged(view, width, height);
        }
    }

    public interface IPlayerEngienListerner {

        /**
         * 隐藏进度框
         */
        void onDimissLoading();

        /**
         * 显示进度框
         */
        void onShowLoading();

        /**
         * 播放时间改变回调
         */
        void onPlayDurationChanged();

        /**
         * 播放完成回调
         */
        void onPlayComplete();

        /**
         * 播放位置改变回调--处理播放进度条
         */
        void onPlayPositionChanged();

        /**
         * 播放错误回调
         */
        void onPlayErro();

        /**
         * 把surfaceview加到页面上
         * @param surfaceView 播放的view
         */
        void addPlayView(SurfaceView surfaceView);

        /**
         * 播放画面尺寸变化回调
         * @param view
         * @param width
         * @param height
         */
        void onViewSizeChanged(SurfaceView view, int width, int height);
    }
}
