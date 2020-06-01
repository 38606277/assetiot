package system.mqtt.configure;

import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.stereotype.Component;

public class MyMqttPahoMessageDrivenChannelAdapter extends MqttPahoMessageDrivenChannelAdapter{

	public MyMqttPahoMessageDrivenChannelAdapter(String clientId, MqttPahoClientFactory clientFactory, String string) {
		super(clientId, clientFactory, string);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 添加订阅
	 * @param number
	 */
	public void addTopicByGateway(String gateway) {
		System.out.println("addTopicByGateway ：" + gateway);
		String[] topicArr = getTopic(gateway);
		for(String topic:topicArr) {
			this.addTopic(topic,1);
		} 
	}

	/**
	 * 删除订阅
	 * @param number
	 */
	public void removeTopicByGateway(String gateway) {
		System.out.println("removeTopicByGateway ：" + gateway);
		this.removeTopic(getTopic(gateway));
	}
	
	private String[] getTopic(String number) {
		return  new String[] {
				"/001/"+number+"/register",
				"/001/"+number+"/update",
				"/001/"+number+"/bt",
				"/001/"+number+"/set_res",
				"/001/"+number+"/alarm"
		};
	}
	
}
