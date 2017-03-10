package chubbycat_studio.com.helloworld.utils;

import android.util.Log;

/**
 * Created by xthewiz on 3/5/2017 AD.
 */

public class MyLog {
    private static boolean enable = true;
    public static void d (String tag, String msg) {
        if(enable) {
            Log.d(tag, msg);
        }
    }
}
