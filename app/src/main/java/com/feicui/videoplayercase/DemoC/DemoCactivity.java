package com.feicui.videoplayercase.DemoC;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import com.feicui.videoplayercase.R;
import com.feicui.videoplayercase.VideoUrlRes;

public class DemoCactivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_c);

        videoView= (VideoView) findViewById(R.id.mainc_vv);
        videoView.setVideoPath(VideoUrlRes.getTestVideo1());
        /**添加控制器*/
        MediaController mediaController = new MediaController(this);
        /**显示上一首和下一首按钮*/
        mediaController.setPrevNextListeners(null,null);

        videoView.setMediaController(mediaController);

        videoView.start();
    }
}
