package com.example.qdwang.custom_mediaplayer.player.impls;

import com.example.qdwang.custom_mediaplayer.player.PlayInterface;
import com.example.qdwang.custom_mediaplayer.player.PlayStatus;

/**
 * @author qdwang$
 * @date 2018/8/29$
 * @describe 基础类
 */
public abstract class BasePlayerImp implements PlayInterface {


    PlayInterface.PlayerStateObserver observer;

    PlayStatus playStatus;


    @Override
    public void setPlayerStateObserver(PlayerStateObserver observer) {
        this.observer = observer;

    }

    @Override
    public void setPitch(int pitch) {

    }

    @Override
    public PlayStatus getPlayStatus() {
        return playStatus;
    }


    @Override
    public void prepareAsync() {
        playStatus = PlayStatus.prepare;
        if (observer != null)
            observer.onStartPrepareing();
    }

    @Override
    public void start() {
        playStatus = PlayStatus.play;
        if (observer != null)
            observer.onStarted();

    }

    @Override
    public void release() {
        playStatus = PlayStatus.error;

    }

    @Override
    public void stop() {
        playStatus = PlayStatus.stop;
        if (observer != null)
            observer.onStoped();
    }

    @Override
    public void pause(boolean goback) {
        playStatus = PlayStatus.pause;
        if (observer != null)
            observer.onPaused();
    }

    @Override
    public void setVolume(int volume) {

    }

    @Override
    public TrackInfo[] getTrackInfo(TRACKTYPE type) {
        return new TrackInfo[0];
    }

    @Override
    public void switchTrack(TrackInfo trackInfo) {

    }

    @Override
    public void setSaveMusicLoopBuffer(int loopBuffer) {

    }
}