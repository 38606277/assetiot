package system.mqtt.bean;

public class MQTTAlarmMessage {

	private String type;//报警的参数类型
	private String value;//报警数据值
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
	@Override
	public String toString() {
		return "MQTTAlarmMessage [type=" + type + ", value=" + value + "]";
	}
	
	

}
