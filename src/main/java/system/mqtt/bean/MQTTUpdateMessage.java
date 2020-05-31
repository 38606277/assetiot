package system.mqtt.bean;

import java.util.Map;

public class MQTTUpdateMessage {

	private String latitudeLBS;//"1792":LBS(纬度)
	private String longitudeLBS;//"087B":LBS(经度)；
	private String latitudeGPS;//"1480":GPS(纬度)；
	private String longitudeGPS;//"00EA":GPS(经度)；
	private String wifiMac;//"13DC":wifi mac值，最多上传5个；空时为大写NULL
	private String baseStationInfo;//"24ED":基站信息，“imei=xxxxx&bts=xxxxx&nearbts=xxxx|xxxx|xxxx”，其中bts字段拼接如下：mcc,mnc,lac,cellid,bdm
	private String electricQuantity;//"1E9D":电量百分比；
	private String runningTime;//"2557":运行时长,自模块上电开机算起；
	private String signalIntensity;//"023A":GMS信号强度
	private String satellitesCount;//"023B":GPS卫星数量；（可选）；
	private String speed;//"1082":速度；（可选： km/h）
	private String orientation;//"1182":方向 可选： 0~360度，0为正北，90为东，180为南，270为西）
	private String distancePrecision;//"1382":GPS坐标定位精度；（可选：定位精度，米）
	private String uploadInterval;//"0F82":"GPS数据上传间隔，单位秒，数值等同于工作模式；可选
	private String bluetoothInterval;//"22BC":蓝牙数据传输间隔，单位秒，默认60秒
	private String bluetoothWhiteList;//"22BD":蓝牙数据白名单开关，打开为1，关闭为0
	private String bluetoothWhiteListCount;//"22BE":蓝牙数据白名单数量
	
	
	public MQTTUpdateMessage(Map<String,Object> map){
		this.latitudeLBS = getValue(map,"1792");
		this.longitudeLBS = getValue(map,"087B");
		this.latitudeGPS = getValue(map,"1480");
		this.longitudeGPS = getValue(map,"00EA");
		
		this.wifiMac = getValue(map,"13DC");
		this.baseStationInfo = getValue(map,"24ED");
		this.electricQuantity = getValue(map,"1E9D");
		this.runningTime = getValue(map,"2557");
		this.signalIntensity = getValue(map,"023A");
		
		this.speed = getValue(map,"1082");
		this.orientation = getValue(map,"1182");
		this.distancePrecision = getValue(map,"1382");
		this.uploadInterval = getValue(map,"0F82");
		this.bluetoothInterval = getValue(map,"22BC");
		
		this.bluetoothWhiteList = getValue(map,"22BD");
		this.bluetoothWhiteListCount = getValue(map,"22BE");
	}
	
	private String getValue(Map<String,Object> map ,String key) {
		String value = "NULL";
		if(map.containsKey(key)) {
			return String.valueOf(map.get(key));
		}
		return value;
		
	}

	
	
	public String getLatitudeLBS() {
		return latitudeLBS;
	}
	public void setLatitudeLBS(String latitudeLBS) {
		this.latitudeLBS = latitudeLBS;
	}
	public String getLongitudeLBS() {
		return longitudeLBS;
	}
	public void setLongitudeLBS(String longitudeLBS) {
		this.longitudeLBS = longitudeLBS;
	}
	public String getLatitudeGPS() {
		return latitudeGPS;
	}
	public void setLatitudeGPS(String latitudeGPS) {
		this.latitudeGPS = latitudeGPS;
	}
	public String getLongitudeGPS() {
		return longitudeGPS;
	}
	public void setLongitudeGPS(String longitudeGPS) {
		this.longitudeGPS = longitudeGPS;
	}
	public String getWifiMac() {
		return wifiMac;
	}
	public void setWifiMac(String wifiMac) {
		this.wifiMac = wifiMac;
	}
	public String getBaseStationInfo() {
		return baseStationInfo;
	}
	public void setBaseStationInfo(String baseStationInfo) {
		this.baseStationInfo = baseStationInfo;
	}
	public String getElectricQuantity() {
		return electricQuantity;
	}
	public void setElectricQuantity(String electricQuantity) {
		this.electricQuantity = electricQuantity;
	}
	public String getRunningTime() {
		return runningTime;
	}
	public void setRunningTime(String runningTime) {
		this.runningTime = runningTime;
	}
	public String getSignalIntensity() {
		return signalIntensity;
	}
	public void setSignalIntensity(String signalIntensity) {
		this.signalIntensity = signalIntensity;
	}
	public String getSatellitesCount() {
		return satellitesCount;
	}
	public void setSatellitesCount(String satellitesCount) {
		this.satellitesCount = satellitesCount;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	public String getDistancePrecision() {
		return distancePrecision;
	}
	public void setDistancePrecision(String distancePrecision) {
		this.distancePrecision = distancePrecision;
	}
	public String getUploadInterval() {
		return uploadInterval;
	}
	public void setUploadInterval(String uploadInterval) {
		this.uploadInterval = uploadInterval;
	}
	public String getBluetoothInterval() {
		return bluetoothInterval;
	}
	public void setBluetoothInterval(String bluetoothInterval) {
		this.bluetoothInterval = bluetoothInterval;
	}
	public String getBluetoothWhiteList() {
		return bluetoothWhiteList;
	}
	public void setBluetoothWhiteList(String bluetoothWhiteList) {
		this.bluetoothWhiteList = bluetoothWhiteList;
	}
	public String getBluetoothWhiteListCount() {
		return bluetoothWhiteListCount;
	}
	public void setBluetoothWhiteListCount(String bluetoothWhiteListCount) {
		this.bluetoothWhiteListCount = bluetoothWhiteListCount;
	}

	@Override
	public String toString() {
		return "MQQTUpdateMessage [latitudeLBS=" + latitudeLBS + ", longitudeLBS=" + longitudeLBS + ", latitudeGPS="
				+ latitudeGPS + ", longitudeGPS=" + longitudeGPS + ", wifiMac=" + wifiMac + ", baseStationInfo="
				+ baseStationInfo + ", electricQuantity=" + electricQuantity + ", runningTime=" + runningTime
				+ ", signalIntensity=" + signalIntensity + ", satellitesCount=" + satellitesCount + ", speed=" + speed
				+ ", orientation=" + orientation + ", distancePrecision=" + distancePrecision + ", uploadInterval="
				+ uploadInterval + ", bluetoothInterval=" + bluetoothInterval + ", bluetoothWhiteList="
				+ bluetoothWhiteList + ", bluetoothWhiteListCount=" + bluetoothWhiteListCount + "]";
	}
	
	
	

	
	
}
