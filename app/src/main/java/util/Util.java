package util;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ideate.com.nandedgram.R;


/**
 * Created by iss on 17/3/16.
 */

public class Util {


    public static boolean checkPermission(Context context, String permission) {
        return (ContextCompat.checkSelfPermission(context,
                permission) == PackageManager.PERMISSION_GRANTED);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void requestPermissions(Activity activity, String requestedPermission, int REQUEST_WRITE_STORAGE) {
        ActivityCompat.requestPermissions(activity,
                new String[]{requestedPermission},
                REQUEST_WRITE_STORAGE);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void requestPermissionsForFragment(Fragment fragment, String requestedPermission, int REQUEST_WRITE_STORAGE) {
        fragment.requestPermissions(new String[]{Manifest.permission.CAMERA},
                REQUEST_WRITE_STORAGE);
    }

    public static String getDeviceId(Activity context) {
        // TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

        String deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
//        if (telephonyManager.getDeviceId() != null)
//            deviceId = telephonyManager.getDeviceId(); //*** use for mobiles
//        else{
//            deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
//        }

        return deviceId;
    }


    public static Snackbar showSnackBar(View coordinatorLayout, String message, int duration) {
        Snackbar snackbar = Snackbar.make(coordinatorLayout, message, duration);
        snackbar.getView().setBackgroundColor(coordinatorLayout.getContext().getResources().getColor(R.color.colorPrimary));
        snackbar.show();
        return snackbar;

    }


    public static void showSnackbarMessageDialog(CoordinatorLayout coordinatorLayout, String snackbarMessage) {

        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, snackbarMessage, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public static boolean validStr(String str) {
        if (str != null && str.trim().length() > 0 && !str.equalsIgnoreCase("null")) {
            return true;
        }
        return false;
    }

    public static String getCurrentDate() {
        Date date = new Date();
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

        return currentDate;

    }

    /* Method for detecting softkeyboard event*/


    public static float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public static float sp2px(Resources resources, float sp) {
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    public static int pxToSp(final Context context, final float px) {
        return Math.round(px / context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static int spToPx(final Context context, final float sp) {
        return Math.round(sp * context.getResources().getDisplayMetrics().scaledDensity);
    }

    public static boolean hasIceCreamSandwhich() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static boolean hasMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * This method gives the Device Screen height in Inches
     *
     * @param context Calling Activity context, needed for getting
     *                the DisplayMetrics.
     * @return Double value of actual screen height
     */
    public static double getScreenHeightInInches(Context context) {
        double result = 0;
        try {
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            int width = dm.widthPixels;
            int height = dm.heightPixels;
            int dens = dm.densityDpi;
            double wi = (double) width / (double) dens;
            double hi = (double) height / (double) dens;
            double x = Math.pow(wi, 2);
            double y = Math.pow(hi, 2);
            result = Math.sqrt(x + y);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void dumpIntoDb() {
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                String currentDBPath = "//data//com.iss247.guestlist//databases//GuestList_db";
                String backupDBPath = "GuestList_db";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
            }
        } catch (Exception e) {
        }
    }


    /*    public static SimpleDateFormat getAccountDateFormat(Pref mPref) {
            SimpleDateFormat mSimpleDateFormatToShow = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
            if (!mPref.getString(Pref.USER_DATE_FORMAT).trim().equals("")) {
                mSimpleDateFormatToShow = new SimpleDateFormat(mPref.getString(Pref.USER_DATE_FORMAT).trim(), Locale.US);
            }
            return mSimpleDateFormatToShow;
        }*/
    public static void openActivity(Activity source, Class<?> destination) {
        Intent openClass = new Intent(source, destination);
        source.startActivity(openClass);
        // source.overridePendingTransition(R.anim.right_in, R.anim.left_out);
        /*if (Util.isLollipop()) {
            ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(source, view, mTransitionName);
            source.startActivity(openClass, transitionActivityOptions.toBundle());
        } else {

        }*/
    }

    public static boolean validateDate(String date) {
        if (TextUtils.isEmpty(date) || TextUtils.equals("0000-00-00 00:00:00", date)) {
            return false;
        } else {
            return true;
        }
    }

//    public static String getDeviceApiMode() {
//        if (BuildConfig.DEBUG) return "production";
//        else return "development";
//    }

    /* method to set cursor color of search bar. cursor color uses controlColorActivated which is set to dark blue color in styles.xml */
    public static void setCursorDrawableColor(EditText editText, int color) {
        try {
            Field fCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            fCursorDrawableRes.setAccessible(true);
            int mCursorDrawableRes = fCursorDrawableRes.getInt(editText);
            Field fEditor = TextView.class.getDeclaredField("mEditor");
            fEditor.setAccessible(true);
            Object editor = fEditor.get(editText);
            Class<?> clazz = editor.getClass();
            Field fCursorDrawable = clazz.getDeclaredField("mCursorDrawable");
            fCursorDrawable.setAccessible(true);
            Drawable[] drawables = new Drawable[2];
            drawables[0] = ContextCompat.getDrawable(editText.getContext(), mCursorDrawableRes);
            drawables[1] = ContextCompat.getDrawable(editText.getContext(), mCursorDrawableRes);
            drawables[0].setColorFilter(color, PorterDuff.Mode.SRC_IN);
            drawables[1].setColorFilter(color, PorterDuff.Mode.SRC_IN);
            fCursorDrawable.set(editor, drawables);
        } catch (Throwable ignored) {
        }
    }

    /**
     * This method used to convert dp values into pixels value
     *
     * @param context Context of the activity
     * @return pixels value
     */
    public static int convertDpToPx(Context context, int dp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, context.getResources().getDisplayMetrics()));
    }

    /**
     * Added By Shree
     * For AGL-246
     *
     * @return default last part of the key
     */
    public static String getResourceKey() {
        String keyLast = "CZO";
        return keyLast;
    }


    /* method to change text color of search bar, as searchbar's textview uses
  colorPrimary as default color which is same as toolbar color*/

}
