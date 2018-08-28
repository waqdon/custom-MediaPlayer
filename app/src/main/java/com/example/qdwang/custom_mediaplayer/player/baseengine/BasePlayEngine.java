package com.example.qdwang.custom_mediaplayer.player.baseengine;

import com.example.qdwang.custom_mediaplayer.player.PlayStatus;

/**
 * @author qdwang$
 * @date 2018/8/29$
 * @describe 基础引擎实现类
 */
public abstract class BasePlayEngine<T> implements IBasePlayEngine<T>{

    private T model;
    private PlayStatus playStatus = PlayStatus.stop;

    @Override
    public void setPlayModel(T model) {
        this.model = model;
    }

    @Override
    public T getCurModel() {
        return model;
    }

    @Override
    public PlayStatus getPlayStatus() {
        return playStatus;
    }

    protected void setPlayStatus(PlayStatus status) {
        playStatus = status;
    }
}
