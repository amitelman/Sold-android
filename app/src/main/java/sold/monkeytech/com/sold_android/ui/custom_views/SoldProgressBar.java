package sold.monkeytech.com.sold_android.ui.custom_views;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.SoldProgressBarBinding;

/**
 * Created by MonkeyFather on 21/08/2017.
 */

public class SoldProgressBar extends FrameLayout {

    private SoldProgressBarBinding mBinding;
    private int[] imgs = {R.drawable.ground_pressed_pb, R.drawable.villa_pressed_pb, R.drawable.double_pressed_pb,
            R.drawable.cottage_pressed_copy_pb, R.drawable.apartment_pressed_pb, R.drawable.penthiuse_pressed_pb,
            R.drawable.garden_pressed_pb, R.drawable.mini_pressed_pb};
    private int currentImg = 0;
    private ScrollView scrollView;

    public SoldProgressBar(Context context) {
        super(context);
        initUi(context);
    }

    public SoldProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUi(context);
    }

    public SoldProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUi(context);
    }

    private void initUi(Context context) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.sold_progress_bar, this, true);
        mBinding.soldProgressBarLayout.setVisibility(GONE);
    }

    public void show() {
        Log.d("wow", "pb - show");
        Animation animRotate = AnimationUtils.loadAnimation(getContext(), R.anim.rotate);

        mBinding.soldProgressBarLayout.setVisibility(VISIBLE);
        mBinding.soldProgressBarLoader.startAnimation(animRotate);
        startImageAnimation();
    }

    private void startImageAnimation() {
        final Handler handler = new Handler();
        final int delay = 1000;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mBinding.soldProgressBarImage.setImageResource(imgs[currentImg]);
                currentImg++;
                if (currentImg == imgs.length)
                    currentImg = 0;
                handler.postDelayed(this, delay);
            }
        }, 1000);
    }

    public void hide() {
        Log.d("wow", "pb - hide");
        mBinding.soldProgressBarLayout.setVisibility(GONE);
        Animation anim = mBinding.soldProgressBarLoader.getAnimation();
        if (anim != null)
            anim.cancel();
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    public SoldProgressBar isWithBkg(boolean isWithBkg) {
        if (!isWithBkg)
            findViewById(R.id.soldProgressBarLayout).setBackgroundResource(0);
        else {
            findViewById(R.id.soldProgressBarLayout).setBackgroundResource(R.drawable.photo_splash_pb);
        }
        return this;
    }

    public void showWithScroll(ScrollView scrollView) {
        this.scrollView = scrollView;
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        show();
    }
}
