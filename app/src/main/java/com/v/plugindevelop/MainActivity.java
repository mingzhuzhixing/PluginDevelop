package com.v.plugindevelop;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PluginManager.getInstance().setContext(this);
    }
    public void loadPlugin(View view){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        File file=new File(Environment.getExternalStorageDirectory(),"plugin.apk");
        PluginManager.getInstance().loadApk(file.getAbsolutePath());
    }

    public void entryActivity(View view){
        Toast.makeText(MainActivity.this,"fndasfjdf",Toast.LENGTH_SHORT).show();

        Intent intent=new Intent(MainActivity.this, ProxyActivity.class);
        intent.putExtra("className",PluginManager.getInstance().getPackageInfo().activities[0].name);
        startActivity(intent);
    }
}
