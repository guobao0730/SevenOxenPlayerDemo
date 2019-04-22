package com.casic.sevenoxenplayerdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLOnCompletionListener;
import com.pili.pldroid.player.PLOnErrorListener;
import com.pili.pldroid.player.PLOnInfoListener;
import com.pili.pldroid.player.PLOnPreparedListener;
import com.pili.pldroid.player.PLOnVideoSizeChangedListener;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.pili.pldroid.player.widget.PLVideoView;

/*
* 七牛 播放器SDK引入
*
* */
public class MainActivity extends AppCompatActivity {

    private PLVideoTextureView mPLVideoTextureView;
    private MediaController mMediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }



    private void initView() {

        mPLVideoTextureView = findViewById(R.id.PLVideoTextureView);

        //关联播放控制器
        mMediaController = new MediaController(this,false,true);

        mPLVideoTextureView.setMediaController(mMediaController);

        //设置视频播放缓冲动画
        View loadingView = findViewById(R.id.LoadingView);
        mPLVideoTextureView.setBufferingIndicator(loadingView);


        //设置画面预览模式
        mPLVideoTextureView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_16_9);

        //设置画面旋转
        //mPLVideoTextureView.setDisplayOrientation(90); // 旋转90度,支持播放画面以 0度，90度，180度，270度旋转

        //设置播放地址
        mPLVideoTextureView.setVideoPath("http://vfx.mtime.cn/Video/2019/01/30/mp4/190130103623492184.mp4");

        //设置播放参数 setAVOptions
        AVOptions options = new AVOptions();
        // the unit of timeout is ms
        options.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
        options.setInteger(AVOptions.KEY_LIVE_STREAMING, 1);
        // 1 -> hw codec enable, 0 -> disable [recommended]
        //AVOptions.MEDIA_CODEC_SW_DECODE 表示实时直播
        options.setInteger(AVOptions.KEY_MEDIACODEC, AVOptions.MEDIA_CODEC_SW_DECODE);
        boolean disableLog = getIntent().getBooleanExtra("disable-log", false);
        options.setInteger(AVOptions.KEY_LOG_LEVEL, disableLog ? 5 : 0);
        boolean cache = getIntent().getBooleanExtra("cache", false);
        mPLVideoTextureView.setAVOptions(options);





    }

    private void initEvent() {
        mPLVideoTextureView.setOnPreparedListener(new PLOnPreparedListener() {
            @Override
            public void onPrepared(int i) {

            }
        });
        mPLVideoTextureView.setOnInfoListener(new PLOnInfoListener() {
            @Override
            public void onInfo(int i, int i1) {

            }
        });
        mPLVideoTextureView.setOnCompletionListener(new PLOnCompletionListener() {
            @Override
            public void onCompletion() {

            }
        });
        mPLVideoTextureView.setOnVideoSizeChangedListener(new PLOnVideoSizeChangedListener() {
            @Override
            public void onVideoSizeChanged(int i, int i1) {

            }
        });
        mPLVideoTextureView.setOnErrorListener(new PLOnErrorListener() {
            @Override
            public boolean onError(int i) {
                return false;
            }
        });


        mMediaController.setOnClickSpeedAdjustListener(new MediaController.OnClickSpeedAdjustListener() {
            @Override
            public void onClickNormal() {
                mPLVideoTextureView.setPlaySpeed(0X00010001);
            }

            @Override
            public void onClickFaster() {
                mPLVideoTextureView.setPlaySpeed(0X00020001);
            }

            @Override
            public void onClickSlower() {
                mPLVideoTextureView.setPlaySpeed(0X00010002);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPLVideoTextureView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPLVideoTextureView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPLVideoTextureView.stopPlayback();
    }
}
