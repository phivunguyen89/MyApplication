package com.my.myapplication.activity;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;


import com.my.myapplication.R;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;


public class CustomMediaController extends MediaController {
    private static final int HIDEFRAM = 0;//Control the display of the prompt window
    private static final int SHOW_PROGRESS = 2;

    private GestureDetector mGestureDetector;
    private ImageButton img_back;//back button
    private TextView mFileName;//file name
    private VideoView videoView;
    private Activity activity;
    private Context context;
    private String videoname;
    private int controllerWidth = 0;//Set the mediaController height in order to make the horizontal display top displayed at the top of the screen


    private View mVolumeBrightnessLayout;//Prompt window
    private ImageView mOperationBg;
    private TextView mOperationTv;
    private AudioManager mAudioManager;
    private SeekBar progress;
    private boolean mDragging;
    private MediaPlayerControl player;

    private int mMaxVolume; //max volume

    private int mVolume = -1; // current volume

    private float mBrightness = -1f;

//Return monitor

    private OnClickListener backListener = new OnClickListener() {
        public void onClick(View v) {
            if (activity != null) {
                activity.finish();
            }
        }
    };

    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            long pos;
            switch (msg.what) {
                case HIDEFRAM: //Hidden prompt window
                    mVolumeBrightnessLayout.setVisibility(View.GONE);
                    mOperationTv.setVisibility(View.GONE);
                    break;
            }
        }
    };
//Videoview is used to control the video, etc., activity is to exit


    public CustomMediaController(Context context, VideoView videoView, Activity activity) {
        super(context);
        this.context = context;
        this.videoView = videoView;
        this.activity = activity;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        controllerWidth = wm.getDefaultDisplay().getWidth();
        mGestureDetector = new GestureDetector(context, new MyGestureListener());

    }

    @Override
    protected View makeControllerView() {
//here mymediacontroller for our custom controller layout file name
        View v = ((LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(getResources().getIdentifier("mymediacontroller", "layout", getContext().getPackageName()), this);
        v.setMinimumHeight(controllerWidth);
//Get control
        img_back = (ImageButton) v.findViewById(getResources().getIdentifier("mediacontroller_top_back", "id", context.getPackageName()));
        mFileName = (TextView) v.findViewById(getResources().getIdentifier("mediacontroller_filename", "id", context.getPackageName()));

        if (mFileName != null) {
            mFileName.setText(videoname);
        }

        mVolumeBrightnessLayout = (RelativeLayout) v.findViewById(R.id.operation_volume_brightness);
        mOperationBg = (ImageView) v.findViewById(R.id.operation_bg);
        mOperationTv = (TextView) v.findViewById(R.id.operation_tv);
        mOperationTv.setVisibility(View.GONE);
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mMaxVolume = mAudioManager
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//Register event listener
        img_back.setOnClickListener(backListener);
        return v;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        System.out.println("MYApp-MyMediaController-dispatchKeyEvent");
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mGestureDetector.onTouchEvent(event)) return true;
//Processing gesture end
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                endGesture();
                break;
        }
        return super.onTouchEvent(event);
    }


    private void endGesture() {
        mVolume = -1;
        mBrightness = -1f;

        myHandler.removeMessages(HIDEFRAM);
        myHandler.sendEmptyMessageDelayed(HIDEFRAM, 1);
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

/*Because the custom mediaController is used, when displayed, the mediaController will fill the screen.
         * So the VideoView click event will be intercepted, so rewrite the controller's gesture event,
         * Write all the operations in the controller,
         * Because the click event is intercepted by the controller and cannot be passed to the underlying VideoView,
         * So the original stand-alone hidden will be invalid, as an alternative,
         * Add custom hidden/display to onSingleTapConfirmed() in gesture monitoring.*/
        public boolean onSingleTapConfirmed(MotionEvent e) {
//When the gesture ends and the click is over, the controller hides/displays
            toggleMediaControlsVisiblity();
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

//Sliding event listener

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            float mOldX = e1.getX(), mOldY = e1.getY();
            int y = (int) e2.getRawY();
            int x = (int) e2.getRawX();
            Display disp = activity.getWindowManager().getDefaultDisplay();
            int windowWidth = disp.getWidth();
            int windowHeight = disp.getHeight();
            if (mOldX > windowWidth * 3.0 / 4.0) {//Swipe right screen 3/4
                onVolumeSlide((mOldY - y) / windowHeight);
            } else if (mOldX < windowWidth * 1.0 / 4.0) {
                onBrightnessSlide((mOldY - y) / windowHeight);
            }
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            playOrPause();
            return true;
        }


        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }


    private void onVolumeSlide(float percent) {
        if (mVolume == -1) {
            mVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            if (mVolume < 0)
                mVolume = 0;


            mVolumeBrightnessLayout.setVisibility(View.VISIBLE);
            mOperationTv.setVisibility(VISIBLE);
        }

        int index = (int) (percent * mMaxVolume) + mVolume;
        if (index > mMaxVolume)
            index = mMaxVolume;
        else if (index < 0)
            index = 0;
        if (index >= 10) {
            mOperationBg.setImageResource(R.drawable.volmn_100);
        } else if (index >= 5 && index < 10) {
            mOperationBg.setImageResource(R.drawable.volmn_60);
        } else if (index > 0 && index < 5) {
            mOperationBg.setImageResource(R.drawable.volmn_30);
        } else {
            mOperationBg.setImageResource(R.drawable.volmn_no);
        }
        //DecimalFormat    df   = new DecimalFormat("######0.00");
        mOperationTv.setText((int) (((double) index / mMaxVolume) * 100) + "%");//change volume
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, index, 0);

    }


    private void onBrightnessSlide(float percent) {
        if (mBrightness < 0) {
            mBrightness = activity.getWindow().getAttributes().screenBrightness;
            if (mBrightness <= 0.00f)
                mBrightness = 0.50f;
            if (mBrightness < 0.01f)
                mBrightness = 0.01f;


            mVolumeBrightnessLayout.setVisibility(View.VISIBLE);
            mOperationTv.setVisibility(VISIBLE);

        }


        WindowManager.LayoutParams lpa = activity.getWindow().getAttributes();
        lpa.screenBrightness = mBrightness + percent;
        if (lpa.screenBrightness > 1.0f)
            lpa.screenBrightness = 1.0f;
        else if (lpa.screenBrightness < 0.01f)
            lpa.screenBrightness = 0.01f;
        activity.getWindow().setAttributes(lpa);

        mOperationTv.setText((int) (lpa.screenBrightness * 100) + "%");
        if (lpa.screenBrightness * 100 >= 90) {
            mOperationBg.setImageResource(R.drawable.light_100);
        } else if (lpa.screenBrightness * 100 >= 80 && lpa.screenBrightness * 100 < 90) {
            mOperationBg.setImageResource(R.drawable.light_90);
        } else if (lpa.screenBrightness * 100 >= 70 && lpa.screenBrightness * 100 < 80) {
            mOperationBg.setImageResource(R.drawable.light_80);
        } else if (lpa.screenBrightness * 100 >= 60 && lpa.screenBrightness * 100 < 70) {
            mOperationBg.setImageResource(R.drawable.light_70);
        } else if (lpa.screenBrightness * 100 >= 50 && lpa.screenBrightness * 100 < 60) {
            mOperationBg.setImageResource(R.drawable.light_60);
        } else if (lpa.screenBrightness * 100 >= 40 && lpa.screenBrightness * 100 < 50) {
            mOperationBg.setImageResource(R.drawable.light_50);
        } else if (lpa.screenBrightness * 100 >= 30 && lpa.screenBrightness * 100 < 40) {
            mOperationBg.setImageResource(R.drawable.light_40);
        } else if (lpa.screenBrightness * 100 >= 20 && lpa.screenBrightness * 100 < 20) {
            mOperationBg.setImageResource(R.drawable.light_30);
        } else if (lpa.screenBrightness * 100 >= 10 && lpa.screenBrightness * 100 < 20) {
            mOperationBg.setImageResource(R.drawable.light_20);
        }

    }


    public void setVideoName(String name) {
        videoname = name;
        if (mFileName != null) {
            mFileName.setText(name);
        }
    }


    private void toggleMediaControlsVisiblity() {
        if (isShowing()) {
            hide();
        } else {
            show();
        }
    } //hide or show


    private void playOrPause() {
        if (videoView != null)
            if (videoView.isPlaying()) {
                videoView.pause();
            } else {
                videoView.start();
            }
    }//play or pause
}
