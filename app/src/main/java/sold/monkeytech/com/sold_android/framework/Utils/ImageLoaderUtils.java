package sold.monkeytech.com.sold_android.framework.Utils;

/**
 * Created by MonkeyFather on 10/07/2017.
 */


import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.monkeytechy.commonutils.BitmapUtils;
import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.imageaware.NonViewAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;

import sold.monkeytech.com.sold_android.SoldApplication;

/**
 * Created by monkey on 28/05/2015.
 */
public class ImageLoaderUtils {

    private static DisplayImageOptions options = null;
    private static DisplayImageOptions roundedOptions = null;

    public static DisplayImageOptions getOptions() {
        if (options == null)
            options = new DisplayImageOptions.Builder()
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .resetViewBeforeLoading(true)
                    .considerExifParams(true)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .build();
        return options;
    }


    public static DisplayImageOptions getOptionsWithRoundedImage(final int pictureType) {

        roundedOptions = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .resetViewBeforeLoading(true)
                .considerExifParams(true)
                .cacheInMemory(true)
//                    .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .preProcessor(new BitmapProcessor() {
                    @Override
                    public Bitmap process(Bitmap bitmap) {
                        return BitmapUtils.getRoundedShape(SoldApplication.getContext(), bitmap, pictureType);
                    }
                })
                .build();
        return roundedOptions;
    }

    public static DisplayImageOptions getOptionsWithPolaroidStroke() {


        roundedOptions = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .resetViewBeforeLoading(true)
                .considerExifParams(true)
//                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .preProcessor(new BitmapProcessor() {
                    @Override
                    public Bitmap process(Bitmap bitmap) {
                        return BitmapUtils.getBitmapWithStroke(bitmap, 4, Color.parseColor("#fe75b2ad"));
                    }
                })
                .build();
        return roundedOptions;
    }

    public static void loadImageWithStroke(String url, ImageView pic){
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(url, pic, ImageLoaderUtils.getOptionsWithPolaroidStroke());
    }

    public static void loadRoundImage(String url, ImageView pic, int type){
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(url, pic, ImageLoaderUtils.getOptionsWithRoundedImage(type));
    }
    public static void loadPicture(final String url,ImageView pic){
        ImageLoader.getInstance().displayImage(url, pic, options);
    }

    public static void loadImageWithUrlVerifyUserImage(final String url, final ImageView reciever) {
        loadImageWithUrlVerifyUserImage(url,reciever,null,null);
    }
    public static void loadImageWithUrlVerifyUserImage(final String url, final ImageView reciever,final Action successHandler,final Action failHandler) {
        if (reciever.getAnimation()!=null)
            reciever.clearAnimation();
        NonViewAware imageAware = new NonViewAware(new ImageSize(1024, 1024), ViewScaleType.CROP);
        ImageLoader.getInstance().displayImage(url, imageAware, getOptions(), new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {
                //reciever.setImageResource(R.drawable.icon_user_placeholder_feed);
                reciever.setTag(url);
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
                if(failHandler!=null)
                    failHandler.execute();
            }

            @Override
            public void onLoadingComplete(String s, View view, final Bitmap bitmap) {
                if (reciever.getTag() == null || !reciever.getTag().equals(url))
                    return;
                if (bitmap != null)
                    reciever.setImageBitmap(bitmap);
                if(successHandler!=null)
                    successHandler.execute();
            }

            @Override
            public void onLoadingCancelled(String s, View view) {
            }
        });
    }

    public  void loadImageWithUrlVerify(final String url, final ImageView reciever) {
        if (reciever.getAnimation()!=null)
            reciever.clearAnimation();
        NonViewAware imageAware = new NonViewAware(new ImageSize(330, 330), ViewScaleType.CROP);
        ImageLoader.getInstance().displayImage(url, imageAware, getOptions(), new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {
                reciever.setImageBitmap(null);
                reciever.setTag(url);
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
                Log.d("imageLoader", "fail");
            }

            @Override
            public void onLoadingComplete(String s, View view, final Bitmap bitmap) {
                if (reciever.getTag() == null || !reciever.getTag().equals(url))
                    return;
                reciever.setImageBitmap(bitmap);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {
                Log.d("imageLoader", "cancel");
            }
        });
    }
    public void loadPicture(final String url,final TAction<Bitmap> actionComplete){
        Action action = new Action() {
            @Override
            public void execute() {
                NonViewAware imageAware = new NonViewAware(new ImageSize(720, 720), ViewScaleType.CROP);
                ImageLoader.getInstance().displayImage(url, imageAware, getOptions(), new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String s, View view) {
                    }

                    @Override
                    public void onLoadingFailed(String s, View view, FailReason failReason) {
                    }

                    @Override
                    public void onLoadingComplete(String s, View view, final Bitmap bitmap) {
                        actionComplete.execute(bitmap);
                    }

                    @Override
                    public void onLoadingCancelled(String s, View view) {

                    }
                });
            }
        };
        action.execute();
    }

    public static void loadHighResPicture(final String url, final TAction<Bitmap> actionComplete){
        Action action = new Action() {
            @Override
            public void execute() {
                NonViewAware imageAware = new NonViewAware(new ImageSize(1024, 1024), ViewScaleType.CROP);
                ImageLoader.getInstance().displayImage(url, imageAware, getOptions(), new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String s, View view) {
                    }

                    @Override
                    public void onLoadingFailed(String s, View view, FailReason failReason) {
                    }

                    @Override
                    public void onLoadingComplete(String s, View view, final Bitmap bitmap) {
                        actionComplete.execute(bitmap);
                    }

                    @Override
                    public void onLoadingCancelled(String s, View view) {

                    }
                });
            }
        };
        action.execute();
    }

    public static void loadBigPictureImage(String url, final ImageView reciever, final Action finishAction) {
        ImageLoader.getInstance().loadImage(url, new ImageSize(700, 700), getOptions(), new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
                alphaAnimation.setDuration(500);
                alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (finishAction!=null)
                            finishAction.execute();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
/*                    reciever.setDisplayType(ImageViewTouchBase.DisplayType.FIT_TO_SCREEN);
                    Matrix matrix = reciever.getDisplayMatrix();*/
                if(bitmap != null) {
                    if (bitmap.getWidth() > bitmap.getHeight()) {

                        bitmap = Bitmap.createBitmap(
                                bitmap,
                                bitmap.getWidth() / 2 - bitmap.getHeight() / 2,
                                0,
                                bitmap.getHeight(),
                                bitmap.getHeight()
                        );

                    } else if (bitmap.getWidth() < bitmap.getHeight()) {

                        bitmap = Bitmap.createBitmap(
                                bitmap,
                                0,
                                bitmap.getHeight() / 2 - bitmap.getWidth() / 2,
                                bitmap.getWidth(),
                                bitmap.getWidth()
                        );
                    }
                }
                reciever.setImageBitmap(bitmap);
                reciever.startAnimation(alphaAnimation);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {
            }
        });
    }
    public static void loadBigPictureWithCallback(String url, final ImageView reciever, final Action finishAction) {
        ImageLoader.getInstance().loadImage(url, new ImageSize(330, 330), getOptions(), new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {
            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                if (bitmap.getWidth() > bitmap.getHeight()) {

                    bitmap = Bitmap.createBitmap(
                            bitmap,
                            bitmap.getWidth() / 2 - bitmap.getHeight() / 2,
                            0,
                            bitmap.getHeight(),
                            bitmap.getHeight()
                    );

                } else if (bitmap.getWidth() < bitmap.getHeight()) {

                    bitmap = Bitmap.createBitmap(
                            bitmap,
                            0,
                            bitmap.getHeight() / 2 - bitmap.getWidth() / 2,
                            bitmap.getWidth(),
                            bitmap.getWidth()
                    );
                }
                reciever.setImageBitmap(bitmap);
                finishAction.execute();
            }

            @Override
            public void onLoadingCancelled(String s, View view) {
            }
        });
    }
}