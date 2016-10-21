package com.feicui.videoplayercase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.feicui.videoplayercase.DemoA.DemoAactivity;
import com.feicui.videoplayercase.DemoB.DemoBactivity;
import com.feicui.videoplayercase.DemoC.DemoCactivity;
import com.feicui.videoplayercase.DemoD.DemoDactivity;
import com.feicui.videoplayercase.DemoE.DemoEactivity;
import com.feicui.videoplayercase.DemoF.DemoFactivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mLv;
    private ArrayAdapter<String> adapter;
    private String[] datas = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLv = (ListView) findViewById(R.id.main_lv);

        datas = new String[]{
                "A Andorid MedioPlayer+SurfaceView",
                "B Vitamio MediaPlayer+SurfaceView",
                "C Android VideoView",
                "D Vitamio VideoView",
                "E Vitamio MediaController",
                "F Vitamio Buffer",
        };

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
        mLv.setAdapter(adapter);
        mLv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        switch (position) {
            case 0:
                intent.setClass(this, DemoAactivity.class);
                startActivity(intent);
                break;
            case 1:
                intent.setClass(this, DemoBactivity.class);
                startActivity(intent);
                break;
            case 2:
                intent.setClass(this, DemoCactivity.class);
                startActivity(intent);
                break;
            case 3:
                intent.setClass(this, DemoDactivity.class);
                startActivity(intent);
                break;
            case 4:
                intent.setClass(this, DemoEactivity.class);
                startActivity(intent);
                break;
            case 5:
                intent.setClass(this, DemoFactivity.class);
                startActivity(intent);
                break;
        }
    }
}
