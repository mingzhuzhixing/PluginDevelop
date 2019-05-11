package com.v.tiaopiaopiao;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.v.alipaystander.AlipayInterface;

public class BaseActivity extends Activity implements AlipayInterface {
    /**
     * 主apk的应用对象
     */
    protected Activity that;

    /**
     * 支付宝的上下文被注入进来了
     */
    @Override
    public void attach(Activity proxyActivity) {
        that=proxyActivity;
    }

    @Override
    public void setContentView(View view) {
        if(that==null){
            super.setContentView(view);
        }else{
            that.setContentView(view);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        if(that==null){
            super.setContentView(layoutResID);
        }else{
            that.setContentView(layoutResID);
        }
    }

    @Override
    public void startActivity(Intent intent) {
        Intent im=new Intent();
        im.putExtra("className",intent.getComponent().getClassName());
        that.startActivity(im);
    }

    @Override
    public <T extends View> T findViewById(int id) {
        if(that==null){
            return super.findViewById(id);
        }else{
            return that.findViewById(id);
        }
    }

    @Override
    public Intent getIntent() {
        if(that==null){
            return super.getIntent();
        }else{
            return that.getIntent();
        }

    }

    @Override
    public ClassLoader getClassLoader() {
        if(that==null){
            return super.getClassLoader();
        }else{
            return that.getClassLoader();
        }
    }

    @Override
    public LayoutInflater getLayoutInflater() {
        if(that==null){
            return super.getLayoutInflater();
        }else{
            return that.getLayoutInflater();
        }
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        if(that==null){
            return super.getApplicationInfo();
        }else{
            return that.getApplicationInfo();
        }
    }

    @Override
    public Resources getResources() {
        if(that==null){
            return super.getResources();
        }else {
            return that.getResources();
        }
    }

    @Override
    public Window getWindow() {
        if(that==null){
            return super.getWindow();
        }else{
            return that.getWindow();
        }
    }

    @Override
    public WindowManager getWindowManager() {
        if(that==null){
            return super.getWindowManager();
        }else {
            return that.getWindowManager();
        }
    }

    @Override
    public void finish() {
        if(that==null){
            super.finish();
        }else{
            that.finish();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {
    }

    @Override
    public void onResme() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestory() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public void onBackpressed() {

    }
}
