package system.mqtt.bean;

public class MQTTBtMessage {

	private String code; //编号
	private String signalIntensity;//信号强度
	private String electricity;//电量

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getSignalIntensity() {
		return signalIntensity;
	}
	public void setSignalIntensity(String signalIntensity) {
		this.signalIntensity = signalIntensity;
	}
	public String getElectricity() {
		return electricity;
	}
	public void setElectricity(String electricity) {
		this.electricity = electricity;
	}
	@Override
	public String toString() {
		return "MQTTBtMessage [code=" + code + ", signalIntensity=" + signalIntensity + ", electricity=" + electricity
				+ "]";
	}
	
	
	
}
