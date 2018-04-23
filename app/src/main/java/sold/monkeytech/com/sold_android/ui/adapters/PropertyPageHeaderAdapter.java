package sold.monkeytech.com.sold_android.ui.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.monkeytechy.framework.interfaces.Action;
import com.monkeytechy.framework.interfaces.TAction;

import java.util.HashMap;
import java.util.List;

import sold.monkeytech.com.sold_android.R;
import sold.monkeytech.com.sold_android.framework.Utils.ImageLoaderUtils;

/**
 * Created by MonkeyFather on 11/03/2018.
 */

public class PropertyPageHeaderAdapter extends PagerAdapter {

    private Context context;
    private List<String> items;
    private final boolean isWith3d;
    private Action on3dTourClick;
    private LayoutInflater layoutInflater;
    private HashMap<Integer, Bitmap> currentBitmap;

    public PropertyPageHeaderAdapter(Context context, List<String> items, boolean isWith3d, Action on3dTourClick) {
        this.context = context;
        this.items = items;
        currentBitmap = new HashMap<>();
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.isWith3d = isWith3d;
        if(on3dTourClick != null)
            this.on3dTourClick = on3dTourClick;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View view = null;
        ViewPager vp = (ViewPager) container;
        Log.d("wowPager", "position : " + position);
        view = layoutInflater.inflate(R.layout.view_pager_slide, null);
        final ImageView imageView = (ImageView) view.findViewById(R.id.image);
        LinearLayout layout3D = view.findViewById(R.id.t3dLayout);

        ImageLoaderUtils.loadHighResPicture(items.get(position), new TAction<Bitmap>() {
            @Override
            public void execute(Bitmap bitmap) {
                Log.d("wowViewPager", "success");
                currentBitmap.put(position, bitmap);
                imageView.setImageBitmap(bitmap);
            }
        });

        if (position == 0 && isWith3d) {
            layout3D.setVisibility(View.VISIBLE);
            layout3D.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(on3dTourClick != null)
                        on3dTourClick.execute();
                }
            });
        } else {
            layout3D.setVisibility(View.GONE);
        }


        vp.addView(view, 0);

        return view;

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }

    public Bitmap getCurrentBitmap(int position) {
        return currentBitmap.get(position);
    }
}