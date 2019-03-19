package com.example.zhishui.myapplication;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient extends Activity {

    EditText ip;
    EditText editText;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_client);

        ip = (EditText) findViewById(R.id.ip);
        editText = (EditText) findViewById(R.id.edit);
        text = (TextView) findViewById(R.id.text);

        findViewById(R.id.connect).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                connect();
            }
        });

        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                send();
            }
        });

        findViewById(R.id.disconnectButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                  //  writer.write("#");
                  //  writer.flush();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    //-------------------------------------

    Socket socket = null;
    BufferedWriter writer = null;
    BufferedReader reader = null;
    private final int PORT = 15810;




    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String data = bundle.getString("data");
            text.append("她说："+data+"\n");
        }
    };

    public void connect() {
        final String IP = ip.getText().toString();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    socket = new Socket(IP,PORT);
                    writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                } catch (UnknownHostException e1) {

                } catch (IOException e1) {
                    if(socket.isConnected())
                        Log.e("catch", "isConnected--------true");
                    else
                        Log.e("catch",  "isConnected--------false");
                }

                String line = "";
                try {
                    while((line = reader.readLine())!=null) {
                        Message message = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putString("data", line);
                        message.setData(bundle);
                        handler.sendMessage(message);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();


    }

    public void send() {
        try {
            text.append("我说："+editText.getText().toString()+"\n");
            writer.write(editText.getText().toString());
            writer.flush();
            editText.setText("");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



}
