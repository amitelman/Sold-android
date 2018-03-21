package sold.monkeytech.com.sold_android.ui.fragments.abs;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.View;


import sold.monkeytech.com.sold_android.SoldApplication;

public class BaseFragment extends Fragment {
    protected boolean started = false;

    public BaseFragment() {
    }

    public String getGeneratedTag() {
        return this.getClass().getName();
    }

    public void onResume() {
        super.onResume();
    }

    public void onStart() {
        super.onStart();
        if(!this.started) {
            this.started = true;
            this.onLoaded();
        }
    }

    public void onLoaded() {
    }

    public boolean onBackPreddes() {
        return false;
    }

    public View getView() {
        return (View)(super.getView() == null ? new BaseFragment.DummyView(SoldApplication.getContext()) : super.getView());
    }



    private class DummyView extends View {
        public DummyView(Context context) {
            super(context);
        }

        public DummyView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public DummyView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }
    }
}
