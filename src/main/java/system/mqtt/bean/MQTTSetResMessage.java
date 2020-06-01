package system.mqtt.bean;

public class MQTTSetResMessage {

	private String type;//配置的参数类型，具体参数类型见3.3节参数类型
	private String value;//配置的参数值
	private String isok;//配置结果，1成功，0失败
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
	public String getIsok() {
		return isok;
	}
	public void setIsok(String isok) {
		this.isok = isok;
	}
	@Override
	public String toString() {
		return "MQQTSetResMessage [type=" + type + ", value=" + value + ", isok=" + isok + "]";
	}
	

}
