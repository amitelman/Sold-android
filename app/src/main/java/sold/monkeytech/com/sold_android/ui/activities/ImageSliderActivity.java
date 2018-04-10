package sold.monkeytech.com.sold_android.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.BuildConfig;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.databinding.ActivityImageSliderBinding;
import sold.monkeytech.com.sold_android.ui.adapters.PicturesSliderAdapter;

public class ImageSliderActivity extends Activity {

    private ActivityImageSliderBinding mBinding;
    private PicturesSliderAdapter pagerAdapter;
    private int dotsCount;
    private ImageView[] dots;
    private int currentPage = 0;
    List<String> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_image_slider);

        initHeaderPager();
    }

    private void initHeaderPager() {
        pagerAdapter = new PicturesSliderAdapter(this, images);
        mBinding.imageSliderActPager.setAdapter(pagerAdapter);

        dotsCount = pagerAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.vp_circle_unselected));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            mBinding.imageSliderActTabDots.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.vp_circle_selected));

        mBinding.imageSliderActPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                Log.d("wowImageSlider","current: " + currentPage);
                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.vp_circle_unselected));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.vp_circle_selected));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBinding.imageSliderActShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareImage();
            }
        });
    }


    private void shareImage(){
        Bitmap bitmap = pagerAdapter.getCurrentBitmap(mBinding.imageSliderActPager.getCurrentItem());
        try {
            File file = new File(this.getExternalCacheDir(),"logicchip.png");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setType("image/png");
            startActivity(Intent.createChooser(intent, "Share image via"));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
