

package com.example.zhishui.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.LocationListener;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.UiSettings;
import com.baidu.mapapi.model.LatLng;
import com.baidu.nplatform.comapi.basestruct.GeoPoint;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.LogRecord;

//-encoding utf-8 -charset utf-8
/**
 * @author 应天成，杨知水，王晓达 <br><br><br><br>
 *
 * 说明：这个类是程序的主界面，负责对飞机的控制和飞机传回参数的展示
 */
public class MainActivity extends Activity implements BaiduMap.OnMapClickListener {

    public MapView mMapView;
    public UiSettings mUiSettings;
    public BaiduMap mBaiduMap;
    private LocationClient mLocationClient;
    private MyLocationListener myLocationListener;
 //   private boolean isFirstIn = true;
    private BitmapDescriptor mMarker;




    public boolean isClose;



    Socket socket = null;
    BufferedWriter writer = null;
    //BufferedReader reader = null;
    InputStream reader = null;
    public String sendData = "";
    public String receiveData = "";
    private final int PORT = 15810;
    private final String IP = "123.57.35.110";

    private final String ACTIVE="1";
    private final String ACCESS="2";
    private final String TAKEOFF="4";
    private final String DISCONNECT="3";
    private final String LANDING = "5";
    private final String HOMING = "6";

    private final String LEFT_UP="w";
    private final String LEFT_DOWN="s";
    private final String LEFT_LEFT="a";
    private final String LEFT_RIGHT="d";

    private final String RIGHT_UP="z";
    private final String RIGHT_DOWN="c";
    private final String RIGHT_LEFT="q";
    private final String RIGHT_RIGHT="e";


    TextView allData;
    TextView commandData;
    double longitude;
    double latitude;
    double altitude;
    float GPSHeight;
    int battery;
    int GPSHealth_flag;
    float groundVolocityVgx;
    float groundVolocityVgy;
    float groundVolocityVgz;
    short controlRoll;
    short controlPitch;
    short controlYaw;
    short controlThrottle;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //to change the button's text when switch the mode
        changeMode();

        //define the Marker
        mMarker = BitmapDescriptorFactory.fromResource(R.mipmap.liner);
        //thread flag
        isClose = false;
        //initialize the mapView
        initView();
        //init Location
        initLocation();

        //-----------------net----
        connect();

        allData = (TextView)findViewById(R.id.displayDataTextView);
        commandData = (TextView)findViewById(R.id.commandTextView_2);



        // Left Hand
        findViewById(R.id.leftControlUpImageButton_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData = LEFT_UP;
                send();
            }
        });
        findViewById(R.id.leftControlDownImageButton_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData = LEFT_DOWN;
                send();
            }
        });
        findViewById(R.id.leftControlLeftImageButton_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData = LEFT_LEFT;
                send();
            }
        });
        findViewById(R.id.leftControlRightImageButton_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData = LEFT_RIGHT;
                send();
            }
        });

        //Right Hand
        findViewById(R.id.rightControlUpImageButton_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData = RIGHT_UP;
                send();
            }
        });
        findViewById(R.id.rightControlDownImageButton_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData = RIGHT_DOWN;
                send();
            }
        });
        findViewById(R.id.rightControlLeftImageButton_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData = RIGHT_LEFT;
                send();
            }
        });
        findViewById(R.id.rightControlRightImageButton_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData = RIGHT_RIGHT;
                send();
            }
        });


        //Control Buttons
        findViewById(R.id.activeButton_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData = ACTIVE;
                send();
            }
        });
        findViewById(R.id.accessButton_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData = ACCESS;
                send();
            }
        });
        findViewById(R.id.takeoffButton_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData = TAKEOFF;
                send();
            }
        });
        findViewById(R.id.disconnectButton_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData = DISCONNECT;
                send();
            }
        });
        findViewById(R.id.landingImageButton_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData = LANDING;
                send();
            }
        });
        findViewById(R.id.homeButton_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData = HOMING;
                send();
            }
        });




    }






    //---------------------------------------------------   nettttt

    public Handler handler_net = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String data = bundle.getString("data");
            receiveData = data;
            //update The plane's state
            //allData.setText(data);

            //-----------------------
            switch(receiveData.charAt(0))
            {
                case 'D':
                    commandData.setText(receiveData);
                    break;
                case 'A':
                    commandData.setText(receiveData);
                    break;
                case 'C':
                    commandData.setText(receiveData);
                    break;
                case 'O':
                    String[] temp1 = receiveData.split("O");
                    Toast.makeText(getApplicationContext(), temp1[1], Toast.LENGTH_SHORT).show();
                    commandData.setText(temp1[1]);
                    break;
                case 'R':
                    String[] temp2 = receiveData.split("R");
                    commandData.setText("收到指令"+temp2[1]);
                    break;

                case 'I':

                    boolean flag = PackageProceed.checkstdPackage(receiveData);
                    if(flag==false)
                    {
                        receiveData = "";
                        break;
                    }


                    String[] afterRemoveI = receiveData.split("I");
                    ManageData manager = new ManageData(afterRemoveI[1]);
                    boolean canTheDataBeProceed = manager.myhandle();
                    if(canTheDataBeProceed) {
                        longitude = manager.gps.getGPSLongti();
                        latitude = manager.gps.getGPSLati();
                        battery = manager.batteryStatus.getBatteryStatus();
                        altitude = manager.gps.getGPSAlti();
                        GPSHeight = manager.gps.getGPSHeight();
                        GPSHealth_flag = manager.gps.getGPSHealth_flag();
                        groundVolocityVgx = manager.groundVolocityVg.getGroundVolocityVgx();
                        groundVolocityVgy = manager.groundVolocityVg.getGroundVolocityVgy();
                        groundVolocityVgz = manager.groundVolocityVg.getGroundVolocityVgz();
                        controlRoll = manager.control.getControlRoll();
                        controlPitch = manager.control.getControlPitch();
                        controlYaw = manager.control.getControlYaw();
                        controlThrottle = manager.control.getControlThrottle();

                        allData.setText("经度："+longitude+"\n");
                        allData.append("纬度：" + latitude + "\n");
                        allData.append("电量：" + battery + "%\n");
                        allData.append("海拔：" + altitude + "\n");
                        allData.append("高度：" + GPSHeight + "\n");
                        allData.append("GPS信号健康度：" + GPSHealth_flag + "\n");
                        allData.append("x轴速度：" + groundVolocityVgx + "\n");
                        allData.append("y轴速度：" + groundVolocityVgy + "\n");
                        allData.append("z轴速度："+groundVolocityVgz+"\n");
                        allData.append("controlRoll："+controlRoll+"\n");
                        allData.append("controlPitch："+controlPitch+"\n");
                        allData.append("controlYaw："+controlYaw+"\n");
                        allData.append("controlThrottle："+controlThrottle+"\n");

                    }
                    receiveData="";
                    break;

                default: commandData.setText("信息不能识别:\n\n");
            }
            //update Map
            float angle = (float)controlYaw;
            getMapLocationDate(longitude, latitude, angle);

//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    };


    /**
     * 说明：用于Socket初始连接
     */
    public void connect() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(IP,PORT);
                    writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                //    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    reader = socket.getInputStream();

                } catch (UnknownHostException e1) {

                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                try {
                    while(true) {
                        byte[] buffer = new byte[4096];
                        int len = reader.read(buffer);
                        Message message = new Message();
                        Bundle bundle = new Bundle();

                        //--------------------------


                        String tempLine = new String(buffer , 0 , len);
                        //Log.e(tempLine.length()+"","+++++++++++++++");

                        String line="";
                        if( tempLine.length()> 100 )
                        {
                            line = PackageProceed.GetStdPackage(buffer);
                            if(line == null)
                                line = "No Avaluable Data";
                        }
                        else
                        {
                            line = tempLine;
                        }

                        //--------------------------
                        bundle.putString("data", line);
                        message.setData(bundle);
                        handler_net.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    /**
     * 说明：用于通过Socket发送数据
     */
    public void send() {
        try {
            writer.write(sendData);
            writer.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //----------------------------------------------------end net

    //change the photoButton's text when changing mode

    /**
     * 说明：用于改变UI界面中Switch上的显示字体（照相或者录像）
     */
    private void changeMode() {
        final Switch cameraRecordSwitch = (Switch)findViewById(R.id.cameraOrRecordSwitch_2);
        final Button cameraModeButton = (Button)findViewById(R.id.photoButton_2);
        cameraRecordSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean mode = cameraRecordSwitch.isChecked();
                if (!mode) {
                    cameraModeButton.setText("拍照");
                } else if (mode) {
                    cameraModeButton.setText("录像");
                }
            }
        });

    }



    //----------------------------------Map

    //Get the Location Data
    private void getMapLocationDate(double update_latitude,double update_longitude,float update_rotate){

        LatLng update_latlng = new LatLng(update_latitude,update_longitude);
        mBaiduMap.clear();
        int currentMapType = mBaiduMap.getMapType();
        mBaiduMap.setMapType(currentMapType);
        OverlayOptions overlayOptions = new MarkerOptions().icon(mMarker)
                .position(update_latlng).rotate(update_rotate).anchor(0.5f,0.5f).zIndex(9);

        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(update_latlng);
        mBaiduMap.setMapStatus(msu);
      //  mBaiduMap.animateMapStatus(msu);
        mBaiduMap.addOverlay(overlayOptions);
    }

//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            Bundle bundle  = msg.getData();
//            Double update_latitude = bundle.getDouble("latitude");
//            Double update_longitude = bundle.getDouble("longitude");
//            Float update_rotate = bundle.getFloat("rotate");
//            LatLng update_latlng = new LatLng(update_latitude,update_longitude);
//            mBaiduMap.clear();
//            int currentMapType = mBaiduMap.getMapType();
//            mBaiduMap.setMapType(currentMapType);
//            OverlayOptions overlayOptions = new MarkerOptions().icon(mMarker)
//                    .position(update_latlng).rotate(update_rotate).anchor(0.5f,0.5f).zIndex(9);
//
//            MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(update_latlng);
//            mBaiduMap.setMapStatus(msu);
//            mBaiduMap.addOverlay(overlayOptions);
//        }
//    };

//
//    private Thread mapDataThread;
//    private void updateLocationData(){
//        mapDataThread =  new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(true){
//                    if (isClose == true){
//                        break;
//                    }
//                    //���̵߳����
//                    longitude -= 0.000001;
//                    latitude -= 0.000001;
//                    rotate += 5.0f;
//                    LatLng latLng = new LatLng(latitude,longitude);
//                    //������
//                    Message message = new Message();
//                    Bundle bundle = new Bundle();
//                    bundle.putDouble("latitude", latitude);
//                    bundle.putDouble("longitude", longitude);
//                    bundle.putFloat("rotate",rotate);
//                    message.setData(bundle);
//                    //������ݰ�����߳�
//                    handler.sendMessage(message);
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//        mapDataThread.start();
//    }

    private void initLocation() {
        mLocationClient = new LocationClient(this);
        myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);

        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setScanSpan(500);

        mLocationClient.setLocOption(option);
        //��ʼ��ͼ��
//        mIconLocation = BitmapDescriptorFactory.
//               fromResource(R.mipmap.fly_map);
    }

    private void initView() {
        mMapView = (MapView) findViewById(R.id.bmapView);
        mMapView.showZoomControls(false);


        mBaiduMap = mMapView.getMap();
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(18.0f);
        mBaiduMap.setMapStatus(msu);
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        mUiSettings = mBaiduMap.getUiSettings();

        mUiSettings.setCompassEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBaiduMap.setMyLocationEnabled(true);
        if(!mLocationClient.isStarted())
            mLocationClient.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mBaiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            socket.close();
        } catch (IOException e) {
            Log.e("关闭Socket异常","1111111111");
            e.printStackTrace();
        }
        isClose = true;
        mMapView.onDestroy();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.id_map_common:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            case R.id.id_map_site:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }

    private  class MyLocationListener implements BDLocationListener
    {
        @Override
        public void onReceiveLocation(BDLocation location)
        {
            MyLocationData data = new MyLocationData.Builder() //
                    .accuracy(location.getRadius()) //����
                    .latitude(location.getLatitude()) //γ��
                    .longitude(location.getLongitude()) //����
                   // .direction(location.getDirection()) //�����Լ��ӵ�
                    .build();

            mBaiduMap.setMyLocationData(data);

//            MyLocationConfiguration config = new
//                   MyLocationConfiguration(MyLocationConfiguration.LocationMode
//                    .NORMAL,true,mIconLocation); //��λͼ����ʾ��ʽ���Ƿ���ʾ������Ϣ���û��Զ���ͼ��λ��
//
//            mBaiduMap.setMyLocationConfigeration(config);
//            if(isFirstIn) //��һ��������ʱ��λ�����ĵ�
//            {
//                //��λ����
//                //LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude()); //���Ⱥ�γ��
//                LatLng latLng = new LatLng(latitude,longitude); //���Ⱥ�γ��
//                MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
//
//                //�Ӹ�����
//                OverlayOptions overlayOptions = new MarkerOptions().position(latLng)
//                        .icon(mMarker).zIndex(9);
//                mBaiduMap.addOverlay(overlayOptions);
//                mBaiduMap.animateMapStatus(msu);
//                isFirstIn = false;
//        }
        }
    }

    //--------------------end Map

}
