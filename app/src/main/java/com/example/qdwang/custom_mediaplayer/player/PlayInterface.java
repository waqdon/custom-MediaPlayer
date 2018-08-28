package com.example.qdwang.custom_mediaplayer.player;

import android.view.SurfaceHolder;

import com.example.qdwang.custom_mediaplayer.player.impls.BaseMediaPlayer;

/**
 * @author qdwang$
 * @date 2018/8/29$
 * @describe  播放服务接口
 *            此接口是以MediaPlayer为原型设计的借口
 *            基本包含MediaPlayer的所有接口，我们做了在perpare上做了
 *            些修改使接口使用起来更加规范 就是去除了prepare 的同步调用只
 *            保留了异步调用
 *            <p/>
 *            等下一版在添加支持
 *
 *         我们把播放接口设计的更加简单抽象
 *         把不同播放器的差异都屏蔽掉 不再以mediaplayer为基准
 *         不再假设任何的实现 而是以业务需求为基准
 *         播放器应该把所有的错误异常都处理调不产生崩溃
 *         只有错误回调
 * @see BaseMediaPlayer
 */
public interface PlayInterface extends PlayerPropertyGetter {
    /**
     * 播放某个地址的视频或是音频
     * 支持传入多个地址。一般只是播放第一个地址
     *
     * @param url 文件地址 可以使网络地址 可以设置多个播放地址
     */
    void setDataSource(String... url);


    /**
     * 暂停
     *
     * @param goback 暂停后是否后台播放
     */
    void pause(boolean goback);

    /**
     * 设置显示的Holder
     *
     * @param holder
     */
    void setDisplay(SurfaceHolder holder);

    /**
     * @see BaseMediaPlayer#stop()
     * 停止
     */
    void stop();

    /**
     * @see BaseMediaPlayer#release()
     * 释放
     */
    void release();

    /**
     * @see BaseMediaPlayer#start()
     * 开始
     */
    void start();


    /**
     * @see BaseMediaPlayer#prepareAsync()
     * 异步准备
     */
    void prepareAsync();


    /**
     * 滑动到某个位置
     *
     * @param pos
     */
    void seekTo(int pos);


    /**
     * 重新播放
     */
    void rePlay();


    /**
     * 设置音量大小
     *
     * @param volue
     */
    void setVolume(int volue);

    /**
     * 设置音调
     *
     * @param pitch
     */
    void setPitch(int pitch);

    /**
     * 设置音频保存位置
     * 有时候需要采集播放的音视频数据
     *
     * @param loopBuffer
     */
    void setSaveMusicLoopBuffer(int loopBuffer);


    /**
     * 音轨信息类型
     */
    enum TRACKTYPE {
        /**
         * 视频
         */
        VEDIO,
        /**
         * 音频
         */
        AUDIO,
        /**
         * 文本
         */
        TEXT,
    }

    /**
     * 音轨信息
     */
    interface TrackInfo {
        /**
         * 获取Track类型
         *
         * @return
         */
        TRACKTYPE getType();

        /**
         * 获取Track位置
         *
         * @return
         */
        int getIndex();
    }

    /**
     * 获取轨道信息
     *
     * @param type
     * @return
     */
    TrackInfo[] getTrackInfo(TRACKTYPE type);

    /**
     * 切换轨道
     *
     * @param trackInfo 需要选择的音轨
     */
    void switchTrack(TrackInfo trackInfo);

    /**
     * 播放状态变化的观察者
     */

    interface PlayerStateObserver {

        /**
         * 开始准备
         */
        void onStartPrepareing();

        /**
         * 准备完成
         */
        void onPrepared();

        /**
         * 开始播放了
         */
        void onStarted();

        /**
         * 暂停了
         */
        void onPaused();

        /**
         * 停止了
         */
        void onStoped();

        /**
         * 滑动完成
         */
        void onSeekCompleted();

        /**
         * 开始缓冲
         */
        void onStartBuffering();

        /**
         * 缓冲完成
         */
        void onBuffered();

        /**
         * 缓冲百分比 更新
         * 此方法不能保证完却正确
         * 只是给用户的一个友好的提示
         *
         * @param persent 100 为缓冲完
         */
        void onBufferePersentChanged(int persent);


        /**
         * 视频大小发生改变
         *
         * @param videoWith
         * @param videoHeight
         */
        void onVideoSizeChanged(int videoWith, int videoHeight);

        /**
         * 播放完成
         */
        void onPlayCompleted();

        /**
         * 播放错误
         *
         * @param errorCode 错误代码 这个 怎么定义 0 源打不开 1 格式不支持 2 其他
         * @param error     错误消息
         */
        void onPlayError(int errorCode, String error);

        /**
         * 音调变化
         */
        void onPitchChanged();

        /**
         * 播放进度变化
         */
        void onPlayPostionChanged(int position);

        /**
         * 音频播放数据，返回exoplayer音频播放数据，用于录音（pcm格式）
         *
         * @param data
         */
        void onAudioOutputBuffer(byte[] data);
    }


    /**
     * 添加播放状态变化观察者
     *
     * @param observer
     */
    void setPlayerStateObserver(PlayerStateObserver observer);
}
