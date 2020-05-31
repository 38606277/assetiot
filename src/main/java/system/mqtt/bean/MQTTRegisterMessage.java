package system.mqtt.bean;

public class MQTTRegisterMessage {
	
	private String sn;//设备序列号，产品内部编号，编号为年月本批次产品序号，如18100001,18年10月，00001产品
	private String imei;//设备IMEI号
	private String iccid;//SIM卡ICCID号
	private String imsi;//SIM卡IMSI号
	private String pk;//本产品默认为001固定值
	private String version;//版本号
	private String update;//1为正在升级，0为正常工作状态；
	private String mode;//工作模式，低于0s为在线模式（-号代表app发起获取坐标操作，模块被动接收数据，平时不主动上传GPS坐标信息），0～300s为实时模式， 600s及以上为省电模式
	private String power;//0为sleep模式，1为正常运行模式
	private String call;//听音功能（手机拨号自动接听）是否打开；（可选,1为打开,0为关闭）
	
	
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getIccid() {
		return iccid;
	}
	public void setIccid(String iccid) {
		this.iccid = iccid;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public String getCall() {
		return call;
	}
	public void setCall(String call) {
		this.call = call;
	}
	@Override
	public String toString() {
		return "MQTTRegisterMessage [sn=" + sn + ", imei=" + imei + ", iccid=" + iccid + ", imsi=" + imsi + ", pk=" + pk
				+ ", version=" + version + ", update=" + update + ", mode=" + mode + ", power=" + power + ", call="
				+ call + "]";
	}
	
	

}
