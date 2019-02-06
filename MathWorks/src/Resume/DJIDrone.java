package Resume;

import java.io.*;
import java.net.Socket;

public class DJIDrone {
    public void socketShow(String IP, int PORT) throws IOException {
        Socket socket = new Socket(IP, PORT);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void multithreadShow() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("send socket here");
            }
        });
        thread.start();
    }

    public void androidEventShor() {
        findViewById(R.id.leftControlDownImgButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData = LEFT_DOWN;
                send();
            }
        });
    }
}


/*
OOD:
    |--Battery
        |--BatteryStatus
    |--BodyAngle
        |--x axis
        |--y axis
        |--z axis
    |--Camera
    |--ControlDeviceStatus
    |--FlyStateStatus
    |--Gesture
    |--GPS

Architecture: Client - Server
    1. Use TCP socket
    2. Use regex to process sticky packages.
    3. Package structure: $[0-9a-z]$:data  --> which have a problem
    4. Package structure should be: packageLen+$+data
    5. The interaction part is Event based

Road Blocks:
    1. Socket cannot be in the main thread
    2. Package structure design has big problems
    3. Team members have conflicts
    4. Used 10 days to develop this app, including learn Android Programming
    5. Send data among threads: Handler
    6. How to handle latency?
        |--DJI Drone has built in module to protect collision on the wall
        |--Achievement Reliable command transfer
        eg:
                Drone                                  Remote Controller
                            <---Command1+InitACK
                                 Status+ACK-->
                             <--Command2+ACK
              exe command1
 */

