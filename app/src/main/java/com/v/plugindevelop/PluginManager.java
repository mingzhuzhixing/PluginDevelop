package com.v.plugindevelop;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class PluginManager {
    /**
     * 类加载器 用于加载dex文件或者dex相关文件
     */
    private DexClassLoader dexClassLoader;

    /**
     * 插件apk的资源对象
     */
    private Resources resources;

    /**
     * 插件apk的包信息
     */
    private PackageInfo packageInfo;

    private Context context;

    private static PluginManager pluginManager=new PluginManager();

    private PluginManager(){}

    public static PluginManager getInstance(){
        return pluginManager;
    }


    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    public Resources getResources() {
        return resources;
    }

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }

    public void setContext(Context context) {
        this.context = context.getApplicationContext();
    }

    public void loadApk(String path){
        //获取当前应用的内部私有存储路径
        File dexOutFile=context.getDir("dex",Context.MODE_PRIVATE);

        //获取到包管理器
        PackageManager packageManager=context.getPackageManager();

        //通过包管理器获取到插件apk的包信息对象
        packageInfo=packageManager.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);

        //初始化类加载器
        dexClassLoader=new DexClassLoader(path,dexOutFile.getAbsolutePath(),null,context.getClassLoader());

        try {
            //获取到AssetManager对象
            AssetManager assetManager=AssetManager.class.newInstance();
            Method addAssetPath=AssetManager.class.getMethod("addAssetPath",String.class);
            addAssetPath.invoke(assetManager,path);

            //获取到了插件apk中的资源文件对象
            resources= new Resources(assetManager,context.getResources().getDisplayMetrics(),context.getResources().getConfiguration());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
