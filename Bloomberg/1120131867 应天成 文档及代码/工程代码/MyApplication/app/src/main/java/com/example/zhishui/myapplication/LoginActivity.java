package com.example.zhishui.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //set the LandScape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Camera

        ImageButton cameraButton = (ImageButton)findViewById(R.id.cameraButton_1);
        cameraButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent fromLoginWindowToCameraWindow = new Intent();
                fromLoginWindowToCameraWindow.setClass(LoginActivity.this,MainActivity.class);
                LoginActivity.this.startActivity(fromLoginWindowToCameraWindow);

            }
        });

        //Connect

        ImageButton connectButton = (ImageButton)findViewById(R.id.connectButton_1);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fromLoginWindowToConnectWindow = new Intent();
                fromLoginWindowToConnectWindow.setClass(LoginActivity.this, SocketClient.class);
                LoginActivity.this.startActivity(fromLoginWindowToConnectWindow);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
