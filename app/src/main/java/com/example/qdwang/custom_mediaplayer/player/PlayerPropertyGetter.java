package com.example.qdwang.custom_mediaplayer.player;

import com.example.qdwang.custom_mediaplayer.player.impls.BaseMediaPlayer;

/**
 * @author qdwang$
 * @date 2018/8/29$
 * @describe
 */
public interface PlayerPropertyGetter {

    /**
     * 获取视频宽度
     *
     * @return
     */
    int getVideoWidth();

    /**
     * @return
     * @see BaseMediaPlayer#getVideoHeight()
     * 获取视频高度
     */
    int getVideoHeight();

    /**
     * @return
     * @see BaseMediaPlayer#getCurrentPosition()
     * 获取播放位置
     */
    int getCurrentPosition();

    /**
     * @return
     * @see BaseMediaPlayer#getDuration()
     * 获取时间长度
     */
    int getDuration();

    /**
     * 获取播放状态
     *
     * @return
     */
    PlayStatus getPlayStatus();
}
