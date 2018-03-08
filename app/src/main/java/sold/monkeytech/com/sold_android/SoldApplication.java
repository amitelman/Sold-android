package sold.monkeytech.com.sold_android;

import android.content.Context;
import android.content.ContextWrapper;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.pixplicity.easyprefs.library.Prefs;

import java.io.File;

/**
 * Created by monkey on 08/06/2015.
 */
public class SoldApplication extends MultiDexApplication {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
       // Thread.setDefaultUncaughtExceptionHandler(new CrashHandler());
        context = getBaseContext();

        new Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(getPackageName())
                .setUseDefaultSharedPreference(true)
                .build();
        initImageLoader();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Context getContext(){
        return context;
    }


    public static void initImageLoader(){
        File cacheDir = StorageUtils.getCacheDirectory(context);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(1000,1000)
                .threadPoolSize(4)
                .threadPriority(Thread.NORM_PRIORITY - 1)
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(10 * 1024 * 1024))
                        //.memoryCacheSize(2 * 1024 * 1024)
                        //.memoryCacheSizePercentage(13)
                        //.diskCache(new UnlimitedDiscCache(cacheDir))
//                .diskCache(new UnlimitedDiscCache(cacheDir))
                        //.diskCacheSize(100 * 1024 * 1024)
                        //.diskCacheFileCount(150)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
//                .imageDownloader(new SecureImageDownloader(context,1000,1000))
                .imageDecoder(new BaseImageDecoder(false))
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);
    }




}

