package com.example.qdwang.custom_mediaplayer.player;

import android.content.Context;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.qdwang.custom_mediaplayer.player.factory.PlayerFactory;

/**
 * @author qdwang$
 * @date 2018/8/29$
 * @describe 播放器
 */
public class Player extends BasePlayer implements SurfaceHolder.Callback {

    private Context context;
    private SurfaceView surfaceView;
    private IPlayerListener listener;

    public void setILivePlayerListener(IPlayerListener listener) {
        this.listener = listener;
    }

    public Player(Context context, IBasePlayerListener listener) {
        super(context, listener);
        this.context = context;
        surfaceView = new SurfaceView(context);
        surfaceView.setZOrderMediaOverlay(false);
        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceView.getHolder().addCallback(this);
        this.listener = (IPlayerListener) listener;
        if (listener != null) {
            ((IPlayerListener) listener).onSurfaceViewCreated(surfaceView);
        }
    }

    @Override
    public void play(String url) {
        if(meidaPlayer == null){
            meidaPlayer = PlayerFactory.getInstance(context).getMediaPlayer();
            meidaPlayer.setPlayerStateObserver(this);
        }
        if (TextUtils.isEmpty(url)) {
            return;
        }
        meidaPlayer.setDataSource(url);
        meidaPlayer.setDisplay(surfaceView.getHolder());
        meidaPlayer.prepareAsync();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (listener != null) {
            listener.onPlayerCreated();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onVideoSizeChanged(int videoWith, int videoHeight) {
        super.onVideoSizeChanged(videoWith, videoHeight);
        if (listener != null) {
            listener.onVideoViewSizeChanged(surfaceView, videoWith, videoHeight);
        }
    }


    public interface IPlayerListener extends IBasePlayerListener {

        void onSurfaceViewCreated(SurfaceView view);

        void onVideoViewSizeChanged(SurfaceView view, int width, int height);
    }
}
