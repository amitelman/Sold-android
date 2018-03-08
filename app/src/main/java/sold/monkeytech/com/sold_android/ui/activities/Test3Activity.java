package sold.monkeytech.com.sold_android.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.monkeytechy.ui.activities.BaseActivity;

import sold.monkeytech.com.sold_android.R;

public class Test3Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);

//        setSupportActionBar(myToolbar);
//
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        ctl.setTitle("Best Coupons Deals");


        TextView tv =(TextView)findViewById(R.id.coupons_lst);
        tv.setText("wowowowow");

    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
////        MenuInflater inflater = getMenuInflater();
////        inflater.inflate(R.menu.z_menu, menu);
////        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
////        switch (item.getItemId()) {
////            case R.id.latest_coupons:
////                Toast.makeText(this,"latest coupons action clicked", Toast.LENGTH_LONG).show();
////                return true;
////
////            case R.id.top_stores:
////                Toast.makeText(this,"top stores action clicked", Toast.LENGTH_LONG).show();
////                return true;
////
////            case R.id.action_settings:
////                Toast.makeText(this," action clicked", Toast.LENGTH_LONG).show();
////                return true;
////
////            default:
////                return super.onOptionsItemSelected(item);
////
////        }
//    }


}
