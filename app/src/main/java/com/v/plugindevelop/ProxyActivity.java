package com.v.plugindevelop;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import com.v.alipaystander.AlipayInterface;

public class ProxyActivity extends Activity {

    //要跳转的activity
    private String className;

    private  AlipayInterface alipayInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //执行插件apk中的activity的加载


        //拿到需要启动的第三方插件的activity的名字
        className=getIntent().getStringExtra("className");

        try {
            //得到第三方插件的activity的类对象
            Class aClass=PluginManager.getInstance().getDexClassLoader().loadClass(className);
            Object newInstance = aClass.newInstance();

            //判断这个acitivity是否是PayInterface 标准接口的实现
            if(newInstance instanceof AlipayInterface){
                //面向接口
                alipayInterface= (AlipayInterface) newInstance;

                //将代理activity的实力传递给第三方activity（上下文）
                alipayInterface.attach(this);

                //传递参数
                Bundle bundle=new Bundle();

                //调用第三方插件apk中的OnCreate()方法
                alipayInterface.onCreate(bundle);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startActivity(Intent intent) {
        //接受 插件 传过来的 activity 全类名
        String className1=intent.getStringExtra("className");
        Intent intent1=new Intent(this,ProxyActivity.class);
        intent1.putExtra("className",className1);
        super.startActivity(intent1);
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }

    @Override
    protected void onStart() {
        super.onStart();
        alipayInterface.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        alipayInterface.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        alipayInterface.onResme();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        alipayInterface.onDestory();
    }
}
