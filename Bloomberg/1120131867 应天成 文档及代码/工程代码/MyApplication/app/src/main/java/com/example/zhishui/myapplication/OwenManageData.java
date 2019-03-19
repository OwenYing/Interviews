package com.example.zhishui.myapplication;

public class OwenManageData {
	
	private String rawData;
	OwenManageData(String str) {
		rawData = str;
		proceed();
	}
	
	//1.时间戳
	private int time;
	
	//2.姿态四元素
	private float gestureQ0;
	private float gestureQ1;
	private float gestureQ2;
	private float gestureQ3;

	//3.Ground坐标系下的加速度
	private float groundAccelerationAgx;
	private float groundAccelerationAgy;
	private float groundAccelerationAgz;
	
	//4.Ground坐标系下的速度
	private float groundVolocityVgx;
	private float groundVolocityVgy;
	private float groundVolocityVgz;
	
	//5.Body坐标系下的叫速度
	private float bodyAngleWx;
	private float bodyAngleWy;
	private float bodyAngleWz;
	
	//6.GPS位置，海拔，相对地面高度，信号健康度
	private double GPSLongti;
	private double GPSLati;
	private float GPSAlti;
	private float GPSHeight;
	private char GPSHealth_flag;
	
	//7.磁感器
	private float magMx;
	private float magMy;
	private float magMz;
	
	//8.遥控器数据
	private short controlRoll;
	private short controlPitch;
	private short controlYaw;
	private short controlThrottle;
	private short controlMode;
	private short controlGear;
	
	//9.云台状态数据
	private float cameraRoll;
	private float cameraPitch;
	private float cameraYaw;
	
	//10.飞行状态
	private char flystateStatus;
	
	//11.电量
	private int batteryStatus;
	
	//12.控制设备
	private char controldeviceStatus;

	//----------------处理－－－－－
	
	
	private void proceed() {
		//1.切大包
		String[] data = rawData.split("\\$[0-9a-z]\\$:");

		//2.切小包
		String[][] smallData = new String[data.length][];
		for(int i=1 ; i<data.length ; i++)
		{
			smallData[i] = data[i].split(";");
//			for(int j=0 ; j<smallData[i].length ; j++)
//				System.out.println(smallData[i][j]);
//			System.out.println("---------------------");
		}
		
		//3.赋值
		//1.时间戳
		time = Integer.parseInt(smallData[1][0]);
		
		//2.姿态四元素
		gestureQ0 = Float.parseFloat(smallData[2][0]);
		gestureQ1 = Float.parseFloat(smallData[2][1]);
		gestureQ2 = Float.parseFloat(smallData[2][2]);
		gestureQ3 = Float.parseFloat(smallData[2][3]);

		//3.Ground坐标系下的加速度
		groundAccelerationAgx = Float.parseFloat(smallData[3][0]);
		groundAccelerationAgy = Float.parseFloat(smallData[3][1]);
		groundAccelerationAgz = Float.parseFloat(smallData[3][2]);
		
		//4.Ground坐标系下的速度
		groundVolocityVgx = Float.parseFloat(smallData[4][0]);
		groundVolocityVgy = Float.parseFloat(smallData[4][1]);
		groundVolocityVgz = Float.parseFloat(smallData[4][2]);
		
		//5.Body坐标系下的叫速度
		bodyAngleWx = Float.parseFloat(smallData[5][0]);
		bodyAngleWy = Float.parseFloat(smallData[5][1]);
		bodyAngleWz = Float.parseFloat(smallData[5][2]);
		
		//6.GPS位置，海拔，相对地面高度，信号健康度
		GPSLongti = Double.parseDouble(smallData[6][0]);
		GPSLati = Double.parseDouble(smallData[6][1]);
		GPSAlti = Float.parseFloat(smallData[6][2]);
		GPSHeight = Float.parseFloat(smallData[6][3]);
		GPSHealth_flag = smallData[6][4].charAt(0);
		
		//7.磁感器
		magMx = Float.parseFloat(smallData[7][0]);
		magMy = Float.parseFloat(smallData[7][1]);
		magMz = Float.parseFloat(smallData[7][2]);
		
		//8.遥控器数据
		controlRoll = Short.parseShort(smallData[8][0]);
		controlPitch = Short.parseShort(smallData[8][1]);
		controlYaw = Short.parseShort(smallData[8][2]);
		controlThrottle = Short.parseShort(smallData[8][3]);
		controlMode = Short.parseShort(smallData[8][4]);
		controlGear = Short.parseShort(smallData[8][5]);
		
		//9.云台状态数据
		cameraRoll = Float.parseFloat(smallData[9][0]);
		cameraPitch = Float.parseFloat(smallData[9][0]);
		cameraYaw = Float.parseFloat(smallData[9][0]);
		
		//10.飞行状态
		flystateStatus = smallData[10][0].charAt(0);
		
		//11.电量
		batteryStatus = Integer.parseInt(smallData[11][0]);
		
		//12.控制设备
		controldeviceStatus = smallData[12][0].charAt(0);

		
		
	}
	
	
	
	
	
	
	
	
	
	//-----------------------------
	public String getRawData() {
		return rawData;
	}

	public void setRawData(String rawData) {
		this.rawData = rawData;
	}

	public int getTime() {
		return time;
	}

	public float getGestureQ0() {
		return gestureQ0;
	}


	public float getGestureQ1() {
		return gestureQ1;
	}


	public float getGestureQ2() {
		return gestureQ2;
	}



	public float getGestureQ3() {
		return gestureQ3;
	}



	public float getGroundAccelerationAgx() {
		return groundAccelerationAgx;
	}


	public float getGroundAccelerationAgy() {
		return groundAccelerationAgy;
	}


	public float getGroundAccelerationAgz() {
		return groundAccelerationAgz;
	}


	public float getGroundVolocityVgx() {
		return groundVolocityVgx;
	}



	public float getGroundVolocityVgy() {
		return groundVolocityVgy;
	}



	public float getGroundVolocityVgz() {
		return groundVolocityVgz;
	}

	public float getBodyAngleWx() {
		return bodyAngleWx;
	}


	public float getBodyAngleWy() {
		return bodyAngleWy;
	}


	public float getBodyAngleWz() {
		return bodyAngleWz;
	}

	public double getGPSLongti() {
		return GPSLongti;
	}


	public double getGPSLati() {
		return GPSLati;
	}

	public float getGPSAlti() {
		return GPSAlti;
	}

	public float getGPSHeight() {
		return GPSHeight;
	}


	public char getGPSHealth_flag() {
		return GPSHealth_flag;
	}


	public float getMagMx() {
		return magMx;
	}


	public float getMagMy() {
		return magMy;
	}


	public float getMagMz() {
		return magMz;
	}


	public short getControlRoll() {
		return controlRoll;
	}


	public short getControlPitch() {
		return controlPitch;
	}


	public short getControlYaw() {
		return controlYaw;
	}


	public short getControlThrottle() {
		return controlThrottle;
	}


	public short getControlMode() {
		return controlMode;
	}


	public short getControlGear() {
		return controlGear;
	}


	public float getCameraRoll() {
		return cameraRoll;
	}


	public float getCameraPitch() {
		return cameraPitch;
	}

	public float getCameraYaw() {
		return cameraYaw;
	}

	public char getFlystateStatus() {
		return flystateStatus;
	}


	public int getBatteryStatus() {
		return batteryStatus;
	}


	public char getControldeviceStatus() {
		return controldeviceStatus;
	}
}
