package com.feicui.videoplayercase.DemoF;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.feicui.videoplayercase.R;
import com.feicui.videoplayercase.VideoUrlRes;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class DemoFactivity extends AppCompatActivity {

    private VideoView videoView;
    // 缓存速度
    private TextView tvDownloadRate;
    // 缓存百分比
    private TextView tvLoadRate;
    // 缓存进度
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_f);

        videoView = (VideoView) findViewById(R.id.videoView);
        tvDownloadRate = (TextView) findViewById(R.id.tvDownloadRate);
        tvLoadRate = (TextView) findViewById(R.id.tvLoadRate);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        /**设置数据源*/
        videoView.setVideoPath(VideoUrlRes.getTestVideo2());
        /**设置控制器*/
        videoView.setMediaController(new MediaController(this));
        /**1.设置缓存大小*/
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.setBufferSize(512 * 1024);
            }
        });

        /**2.监听缓存的开始、结束、速率变化*/
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        startBuffer();
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        endBuffer();
                        break;
                    case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                        tvDownloadRate.setText(extra + "KB/S");
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        /**3.监听缓存进度，更新当前缓存百分比*/
        videoView.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                tvLoadRate.setText(percent + "%");
            }
        });

    }

    private void startBuffer() {
        /**若正在播放，则把视频暂停
         * progressbar、两个TV显示，两个TV重置*/
        if (videoView.isPlaying()) {
            videoView.pause();
            progressBar.setVisibility(View.VISIBLE);
            tvDownloadRate.setVisibility(View.VISIBLE);
            tvLoadRate.setVisibility(View.VISIBLE);
            tvDownloadRate.setText("");
            tvLoadRate.setText("");
        }
    }

    private void endBuffer() {
        /**视频播放，UI隐藏（progressbar、两个TV）*/
        videoView.start();
        progressBar.setVisibility(View.GONE);
        tvDownloadRate.setVisibility(View.GONE);
        tvLoadRate.setVisibility(View.GONE);
    }

}
