package sold.monkeytech.com.sold_android.framework.Utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;

import sold.monkeytech.com.sold_android.R;


public class PermissionUtils {

    public static final String[] STORAGE_PERMISSIONS = new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
//    public static final String[] LOCATION_PERMISSIONS = new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    public static final String[] CAMERA_PERMISSIONS = new String[] {Manifest.permission.CAMERA};
    public static final String[] LOCATION_PERMISSIONS_1 = new String[] {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

    public static final String[] PHONE_PERMISSIONS = new String[] {Manifest.permission.CALL_PHONE};

    public static final int PERMISSION_STORAGE_REQUEST_CODE = 1;
    public static final int PERMISSION_LOCATION_1_REQUEST_CODE = 2;
    public static final int PERMISSION_PHONE_REQUEST_CODE = 3;

    public static boolean verifyPermissionResults(int[] grantResults) {
        // At least one result must be checked.
        if(grantResults.length < 1){
            return false;
        }

        // Verify that each required permission has been granted, otherwise return false.
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkPermissions(Context context, String[] permissionsToCheck){
        for (String permission : permissionsToCheck){
            if (ActivityCompat.checkSelfPermission(context, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }

        return true;
    }

    public static void askPermissions(final Activity activity, final String[] permissions, final int requestCode, String rationale){
        boolean showRationale = false;
        for (String permission : permissions){
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)){
                showRationale = true;
                break;
            }
        }

        if (showRationale){
            displayRationalDialog(activity, permissions, requestCode, rationale);
        }
        else{
            innerAskPermissions(activity, permissions, requestCode);
        }
    }

    public static void askPermissions(final Fragment fragment, final String[] permissions, final int requestCode, String rationale){
        boolean showRationale = false;
        for (String permission : permissions){
            if (fragment.shouldShowRequestPermissionRationale(permission)){
                showRationale = true;
                break;
            }
        }

        if (showRationale){
            displayRationalDialog(fragment.getActivity(), permissions, requestCode, rationale);
        }
        else{
            innerAskPermissions(fragment, permissions, requestCode);
        }
    }

    public static String[] concatPermissions(String[] a, String[] b) {
        int aLen = a.length;
        int bLen = b.length;
        String[] c= new String[aLen+bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }

    private static void innerAskPermissions(Activity activity, String[] permissions, int requestCode){
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    private static void innerAskPermissions(Fragment fragment, String[] permissions, int requestCode){
        fragment.requestPermissions(permissions, requestCode);
    }

    private static void displayRationalDialog(final Activity activity, final String[] permissions, final int requestCode, String rational){
        new AlertDialog.Builder(activity)
//                .setTitle(R.string.permissions_rational_title)
                .setMessage(rational)
                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        innerAskPermissions(activity, permissions, requestCode);
                    }
                })
                .show();
    }
}