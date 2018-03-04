package sold.monkeytech.com.sold_android.ui.custom_views;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.SoldProgressBarBinding;

/**
 * Created by MonkeyFather on 21/08/2017.
 */

public class SoldProgressBar extends FrameLayout {

    private SoldProgressBarBinding mBinding;

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
        mBinding.midProgressBarLayout.setVisibility(GONE);
    }

    public void show(){
        Log.d("wow","pb - show");
        Animation animRotate = AnimationUtils.loadAnimation(getContext(),R.anim.rotate);

        mBinding.midProgressBarLayout.setVisibility(VISIBLE);
        mBinding.midProgressBarLoader.startAnimation(animRotate);
    }

    public void hide(){
        Log.d("wow","pb - hide");
        mBinding.midProgressBarLayout.setVisibility(GONE);
        Animation anim = mBinding.midProgressBarLoader.getAnimation();
        if(anim != null)
            anim.cancel();
    }

    public SoldProgressBar isWithBkg(boolean isWithBkg){
        if(!isWithBkg)
            findViewById(R.id.midProgressBarLayout).setBackgroundResource(0);
        else{
            findViewById(R.id.midProgressBarLayout).setBackgroundResource(R.color.black_70);
        }
        return this;
    }
}
