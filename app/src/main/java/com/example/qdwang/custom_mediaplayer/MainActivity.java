package com.example.qdwang.custom_mediaplayer;

import android.text.TextUtils;
import android.view.SurfaceView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.qdwang.custom_mediaplayer.player.PlayStatus;
import com.example.qdwang.custom_mediaplayer.player.PlayerEngine;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.container)RelativeLayout container;

    private PlayerEngine playerEngine;

    String uri;

    @Override
    protected void createView() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        createPlayEngine();
    }

    private void createPlayEngine() {
        playerEngine = new PlayerEngine(this);
        playerEngine.setILivePlayerEngienListerner(new PlayerEngine.IPlayerEngienListerner() {
            @Override
            public void onDimissLoading() {

            }

            @Override
            public void onShowLoading() {

            }

            @Override
            public void onPlayDurationChanged() {

            }

            @Override
            public void onPlayComplete() {

            }

            @Override
            public void onPlayPositionChanged() {

            }

            @Override
            public void onPlayErro() {

            }

            @Override
            public void addPlayView(SurfaceView surfaceView) {
                container.removeAllViews();
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                container.addView(surfaceView, params);
            }

            @Override
            public void onViewSizeChanged(SurfaceView view, int width, int height) {

            }
        });

        uri = "android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.guide;
        if(TextUtils.isEmpty(uri)){
            return;
        }
        playerEngine.setDataSource(uri);
        playerEngine.onPlayerPrepared();
        playerEngine.startPlay();
    }

    @Override
    protected void requestPermissions() {
        super.requestPermissions();
        uri = "android.resource://" + getApplicationContext().getPackageName() + "/" + R.raw.guide;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (playerEngine != null && playerEngine.getPlayStatus() == PlayStatus.pause) {
            playerEngine.resumePlay();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (playerEngine != null) {
            playerEngine.pausePlay();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (playerEngine != null) {
            playerEngine.stopPlay();
        }
    }
}
