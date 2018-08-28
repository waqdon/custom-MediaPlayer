package com.example.qdwang.custom_mediaplayer.player.impls;

import android.view.SurfaceHolder;

import com.example.qdwang.custom_mediaplayer.player.PlayInterface;
import com.example.qdwang.custom_mediaplayer.player.PlayStatus;

/**
 * @author qdwang$
 * @date 2018/8/29$
 * @describe 播放器的代理
 *           此类主要是和PlayerManager打交道
 *           这样具体的Player就不用关心 PlayerManager的
 *           而只是关心功能的实现
 */
public class PlayeProxy implements PlayInterface {
    /**
     * 真正的播放器
     */
    PlayInterface player;
    public static PlayInterface obaintPlayer(PlayInterface player){
        return new PlayeProxy(player);
    }

    private PlayeProxy(PlayInterface player){
        this.player = player;
    }

    @Override
    public void setPitch(int pitch) {
        player.setPitch(pitch);
    }



    @Override
    public TrackInfo[] getTrackInfo(TRACKTYPE type) {
        if(player != null)
            return player.getTrackInfo(type);
        return null;
    }

    @Override
    public void switchTrack(TrackInfo trackInfo) {
        if(player != null)
            player.switchTrack(trackInfo);
    }


    @Override
    public void rePlay() {
        player.rePlay();
    }



    @Override
    public int getVideoWidth() {
        if(player != null)
            return player.getVideoWidth();
        return 0;
    }

    @Override
    public int getVideoHeight() {
        if(player != null)
            return player.getVideoHeight();
        return 0;
    }

    @Override
    public int getCurrentPosition() {
        if(player != null) {
            return player.getCurrentPosition();
        }
        return 0;
    }

    @Override
    public int getDuration() {
        if(player != null) {
            return player.getDuration();
        }
        return 0;
    }

    @Override
    public PlayStatus getPlayStatus() {
        if(player != null)
            return player.getPlayStatus();
        return null;
    }


    @Override
    public void setPlayerStateObserver(PlayerStateObserver observer) {
        player.setPlayerStateObserver(observer);
    }



    @Override
    public void setDataSource(String... url)  {
        if(player != null){
            /**
             * 启动当前的播放器
             */
//            PlayerManager.getInstance().startPlayService(this);
            player.setDataSource(url);
        }

    }



    @Override
    public void pause(boolean goback) {
        if(player != null){
            player.pause(goback);

        }
    }

    @Override
    public void setDisplay(SurfaceHolder holder) {
        if(player != null ) {
            player.setDisplay(holder);
        }
    }

    @Override
    public void stop() {
        if(player != null)
            player.stop();
    }

    @Override
    public void release() {
        if(player != null){
            player.release();
//            PlayerManager.getInstance().realsePlayer(this);
            player = null;
        }

    }

    @Override
    public void start() {
        if(player != null) {
//            PlayerManager.getInstance().startPlayService(this);
            player.start();
        }
    }

    @Override
    public void prepareAsync() {
        if(player != null) {
            player.prepareAsync();
        }
    }

    @Override
    public void seekTo(int pos) {
        if(player != null)
            player.seekTo(pos);
    }

    @Override
    public void setVolume(int volue) {
        if(player != null)
            player.setVolume(volue);
    }

    @Override
    public void setSaveMusicLoopBuffer(int loopBuffer) {
        if(player != null)
            player.setSaveMusicLoopBuffer(loopBuffer);
    }
}
