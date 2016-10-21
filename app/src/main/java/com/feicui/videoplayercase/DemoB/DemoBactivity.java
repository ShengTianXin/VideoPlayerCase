package com.feicui.videoplayercase.DemoB;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.feicui.videoplayercase.R;
import com.feicui.videoplayercase.VideoUrlRes;

import java.io.IOException;

import io.vov.vitamio.MediaPlayer;

public class DemoBactivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_b);

        surfaceView = (SurfaceView) findViewById(R.id.mainb_sv);
        surfaceHolder = surfaceView.getHolder();
        /**花屏进行处理*/
        surfaceHolder.setFormat(PixelFormat.RGBA_8888);

        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mediaPlayer = new MediaPlayer(DemoBactivity.this);
                mediaPlayer.setDisplay(surfaceHolder);
                try {
                    mediaPlayer.setDataSource(VideoUrlRes.getTestVideo1());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mediaPlayer.start();
                    }
                });
                /**黑屏没播放的原因：vitamio5.0之后
                 * 要进行audio处理，才能对在线视频进行播放
                 * */
                mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                    @Override
                    public boolean onInfo(MediaPlayer mp, int what, int extra) {
                        if (what == MediaPlayer.MEDIA_INFO_FILE_OPEN_OK) {
                            mediaPlayer.audioInitedOk(mediaPlayer.audioTrackInit());
                            return true;
                        }
                        return false;
                    }
                });
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                mediaPlayer.stop();
            }
        });
    }
}
