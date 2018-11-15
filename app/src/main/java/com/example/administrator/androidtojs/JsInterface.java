package com.example.administrator.androidtojs;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/11/16/016.
 */

public class JsInterface {
    private static final String TAG = "JsInterface";
    private Context mContext;

    public JsInterface(Context context) {
        this.mContext = context;
    }

    //在js中调用window.AndroidWebView.showInfoFromJs(name)，便会触发此方法。
    @JavascriptInterface
    public void showInfoFromJs(String name) {
        Log.d(TAG, "showInfoFromJs: ");
        Toast.makeText(mContext, name, Toast.LENGTH_SHORT).show();
    }

}
