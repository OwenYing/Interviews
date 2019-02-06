package Resume;

public class RemoteSlidesController {
}

/*
OOD:
    |--Robot Class : used to manipulate computer
    |-- Mostly, this is event based application

Architecture: Point - Point
    1. Use UDP to broadcast: UDP fast, can tolerant package loss
    2. Control sending and receiving rate to prevent sticky packages
    3. IP free --> computer connected to phone's hotspot --> computer has IP 192.168.43.255
    4. Remote touch pad -- mapping algorithm from small screen to larger screen
    5. Use sensors to simulate remote laser pen effect

Road Blocks:
    1. Mapping algorithm
    2. How to achieve IP free
    3. Sensor algorithm -- failed
    4. How to get users to use this app?


 */