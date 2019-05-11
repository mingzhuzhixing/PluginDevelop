package com.v.alipaystander;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * 所有外置apk中的app页面必须要实现的接口类 这个类是规范所有外置apk的activity的标准
 */
public interface AlipayInterface {

    /**
     * 注入上下文
     * @param proxyActivity
     */
    public void attach(Activity proxyActivity);

    public void onCreate(Bundle savedInstanceState);

    public void onStart();

    public void onResme();

    public void onPause();

    public void onStop();

    public void onDestory();

    public void onSaveInstanceState(Bundle outState);

    public boolean onTouchEvent(MotionEvent event);

    public void onBackpressed();

}
