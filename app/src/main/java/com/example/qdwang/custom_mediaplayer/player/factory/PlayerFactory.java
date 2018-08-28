package com.example.qdwang.custom_mediaplayer.player.factory;

import android.content.Context;

import com.example.qdwang.custom_mediaplayer.player.PlayInterface;
import com.example.qdwang.custom_mediaplayer.player.impls.BaseMediaPlayer;
import com.example.qdwang.custom_mediaplayer.player.impls.PlayeProxy;

/**
 * @author qdwang$
 * @date 2018/8/29$
 * @describe 播放器工厂类
 *           我们的播放器对外不能主动实例 只能通过工厂来创建
 */
public class PlayerFactory {
    private static PlayerFactory instance = null;
    private Context context;

    private PlayerFactory(Context context) {
        this.context = context.getApplicationContext();
    }

    public static PlayerFactory  getInstance(Context context){
        if(instance == null){
            synchronized (PlayerFactory.class){
                if(instance == null){
                    instance = new PlayerFactory(context);
                }
            }
        }
        return instance;
    }


    public PlayInterface getMediaPlayer(){
        return PlayeProxy.obaintPlayer(new BaseMediaPlayer(context));
    }
}
