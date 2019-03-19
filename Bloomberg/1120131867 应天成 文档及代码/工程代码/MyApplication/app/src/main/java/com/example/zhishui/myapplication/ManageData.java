package com.example.zhishui.myapplication;


public class ManageData {

	private String rawData;
	private boolean flag = true;

	//1.时间戳
	Time time = new Time();

	//2.姿态四元素
	Gesture gesture =new Gesture();

	//3.Ground坐标系下的加速度
	GroundAccelerationAg groundAccelerationAg = new GroundAccelerationAg();

	//4.Ground坐标系下的速度
	GroundVolocityVg groundVolocityVg = new GroundVolocityVg();

	//5.Body坐标系下的叫速度
	BodyAngleW bodyAngleW = new BodyAngleW();

	//6.GPS位置，海拔，相对地面高度，信号健康度
	GPS gps = new GPS();

	//7.磁感器
	MagM magM = new MagM();

	//8.遥控器数据
	Control control = new Control();

	//9.云台状态数据
	Camera camera = new Camera();

	//10.飞行状态
	FlystateStatus flystateStatus = new FlystateStatus();

	//11.电量
	BatteryStatus batteryStatus = new BatteryStatus();

	//12.控制设备
	ControlDeviceStatus controlDeviceStatus = new ControlDeviceStatus();


	ManageData(String receiveData){
		this.rawData = receiveData;
	}
	
	private void fillindata(String[] temptemp,int i)
	{
		switch(i)
		{
		case 1:{
			if(temptemp.length != 0)
				time.setTime(Integer.parseInt(temptemp[0]));
			else
				flag = false;
			break;
		}
		case 2:{
			if(temptemp.length >= 4)
			{
				gesture.setGestureQ0(Float.parseFloat(temptemp[0]));
				gesture.setGestureQ1(Float.parseFloat(temptemp[1]));
				gesture.setGestureQ2(Float.parseFloat(temptemp[2]));
				gesture.setGestureQ3(Float.parseFloat(temptemp[3]));
			}
			else
				flag = false;
			break;
		}
		case 3:{
			if(temptemp.length >= 3)
			{
				groundAccelerationAg.setGroundAccelerationAgx(Float.parseFloat(temptemp[0]));
				groundAccelerationAg.setGroundAccelerationAgy(Float.parseFloat(temptemp[1]));
				groundAccelerationAg.setGroundAccelerationAgz(Float.parseFloat(temptemp[2]));
			}
			else
				flag = false;
			break;
		}
		case 4:{
			if(temptemp.length >= 3)
			{
				groundVolocityVg.setGroundVolocityVgx(Float.parseFloat(temptemp[0]));
				groundVolocityVg.setGroundVolocityVgy(Float.parseFloat(temptemp[1]));
				groundVolocityVg.setGroundVolocityVgz(Float.parseFloat(temptemp[2]));
			}
			else
				flag = false;
			break;
		}
		case 5:{
			if(temptemp.length >= 3)
			{
				bodyAngleW.setBodyAngleWx(Float.parseFloat(temptemp[0]));
				bodyAngleW.setBodyAngleWy(Float.parseFloat(temptemp[1]));
				bodyAngleW.setBodyAngleWz(Float.parseFloat(temptemp[2]));
			}
			else
				flag = false;
			break;
		}
		case 6:{
			if(temptemp.length >= 5)
			{
				gps.setGPSLongti(Double.parseDouble(temptemp[0]));
				gps.setGPSLati(Double.parseDouble(temptemp[1]));
				gps.setGPSAlti(Float.parseFloat(temptemp[2]));
				gps.setGPSHeight(Float.parseFloat(temptemp[3]));
				gps.setGPSHealth_flag(Integer.parseInt(temptemp[4]));
			}
			else
				flag = false;
			break;
		}
		case 7:{
			if(temptemp.length >= 3)
			{
				magM.setMagMx(Float.parseFloat(temptemp[0]));
				magM.setMagMy(Float.parseFloat(temptemp[1]));
				magM.setMagMz(Float.parseFloat(temptemp[2]));
			}
			else
				flag = false;
			break;
		}
		case 8:{
			if(temptemp.length >= 6)
			{
				control.setControlRoll(Short.parseShort(temptemp[0]));
				control.setControlPitch(Short.parseShort(temptemp[1]));
				control.setControlYaw(Short.parseShort(temptemp[2]));
				control.setControlThrottle(Short.parseShort(temptemp[3]));
				control.setControlMode(Short.parseShort(temptemp[4]));
				control.setControlGear(Short.parseShort(temptemp[5]));
			}
			else
				flag = false;
			break;
		}
		case 9:{
			if(temptemp.length >= 3)
			{
				camera.setCameraRoll(Float.parseFloat(temptemp[0]));
				camera.setCameraPitch(Float.parseFloat(temptemp[1]));
				camera.setCameraYaw(Float.parseFloat(temptemp[2]));
			}
			else
				flag = false;
			break;
		}
		case 10:{
			if(temptemp.length >= 1)
			{
				flystateStatus.setFlystateStatus(Integer.parseInt(temptemp[0]));
			}
			else
				flag = false;
			break;	
		}
		case 11:{
			if(temptemp.length >= 1)
			{
				if(temptemp[0].length() >= 1)
					batteryStatus.setBatteryStatus(Integer.parseInt(temptemp[0]));
			}
			else
				flag = false;
			break;
		}
		case 12:{
			if(temptemp.length >= 1)
			{
				controlDeviceStatus.setControlDeviceStatus(Integer.parseInt(temptemp[0]));
			}
			else
				flag = false;
			break;
		}
		default:{break;}
		}
	}

	public boolean myhandle()
	{
		int length = rawData.length();
		if(length > 0)
		{
			String[] temp = rawData.split("\\$[0-9a-f]\\$:");
			/*
			for(int i = 0;i<temp.length;i++)
			{
				System.out.println(i);
				System.out.println(temp[i]);
			}
			 
			int a = Integer.parseInt(temp[1].split(";")[0]);
			System.out.println(a);
			time.time = a;
			 */
			for(int i = 1; i<temp.length ; i++)
			{
				String[] temptemp = temp[i].split(";");
				//System.out.println(temptemp.length);
				/*
				System.out.print(temptemp[0] + " ");
				if(temptemp.length>=2)
					System.out.print(temptemp[1] + " ");
				if(temptemp.length>=3)
					System.out.print(temptemp[2] + " ");
				if(temptemp.length>=4)
					System.out.print(temptemp[3] + " ");
				if(temptemp.length>=5)
					System.out.print(temptemp[4] + " ");
				if(temptemp.length>=6)
					System.out.print(temptemp[5] + " ");
				System.out.println("");
				*/
				fillindata(temptemp,i);
			}
		}
		else
			flag = false;
	return flag;
}













}