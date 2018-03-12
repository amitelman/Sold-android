package sold.monkeytech.com.sold_android.ui.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.monkeytechy.framework.interfaces.Action;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.Utils.ImageLoaderUtils;

/**
 * Created by MonkeyFather on 11/03/2018.
 */

public class PicturesSliderAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private String [] images = {"https://static.birgun.net/resim/haber-detay-resim/2018/02/20/mezbahaya-goturulmeye-calisilan-inek-yuzerek-karsi-adaya-kacti-429916-5.jpg",
            "https://media.mnn.com/assets/images/2017/01/cow-in-pasture.jpg.838x0_q80.jpg","https://cdn.shopify.com/s/files/1/0032/7522/products/Awkward_Cow_1_1024x1024.jpg?v=1400623244",
            "https://i2-prod.mirror.co.uk/incoming/article11948636.ece/ALTERNATES/s615b/Cow-self-image.jpg"
    };

    public PicturesSliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.view_pager_slide, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);

        ImageLoaderUtils.loadImageWithUrlVerifyUserImage(images[position], imageView, new Action() {
            @Override
            public void execute() {
                Log.d("wowViewPager", "sucess");
            }
        }, new Action() {
            @Override
            public void execute() {
                Log.d("wowViewPager", "failed");
            }
        });

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}