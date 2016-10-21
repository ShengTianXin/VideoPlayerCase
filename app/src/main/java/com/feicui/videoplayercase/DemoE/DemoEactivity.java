package com.feicui.videoplayercase.DemoE;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.feicui.videoplayercase.R;
import com.feicui.videoplayercase.VideoUrlRes;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class DemoEactivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_e);

        videoView= (VideoView) findViewById(R.id.maine_vv);
        videoView.setVideoPath(VideoUrlRes.getTestVideo1());
        /**添加控制器*/
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);

        videoView.start();
    }
}
