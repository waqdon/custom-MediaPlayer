package com.example.qdwang.custom_mediaplayer.player.baseengine;

import com.example.qdwang.custom_mediaplayer.player.PlayStatus;

/**
 * @author qdwang$
 * @date 2018/8/29$
 * @describe 基础播放引擎，播放通用接口封装
 * @param <T> 播放Model类型
 */
public interface IBasePlayEngine<T> {

    /**
     * 设置播放Model，调用播放接口之前必须先设置
     */
    void setPlayModel(T model);

    /**
     * 获取当前播放Model
     */
    T getCurModel();

    /**
     * 开始播放接口
     */
    void startPlay();

    /**
     * 暂停播放接口
     */
    void pausePlay() ;

    /**
     * 设置播放进度，单位毫秒
     */
    void seekPlay(int pos);

    /**
     * 继续播放接口
     */
    void resumePlay();

    /**
     * 停止播放接口
     *
     */
    void stopPlay();

    /**
     * 获取播放时长
     */
    int getDuration();
    /**
     * 获取播放进度
     */
    int getPosition();
    /**
     * 获取播放状态
     */
    PlayStatus getPlayStatus();
}
