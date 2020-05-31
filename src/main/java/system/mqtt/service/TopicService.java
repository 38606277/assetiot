package system.mqtt.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import system.mqtt.configure.MyMqttPahoMessageDrivenChannelAdapter;

@Service
public class TopicService {
	
	@Resource
	private MyMqttPahoMessageDrivenChannelAdapter myMqttPahoMessageDrivenChannelAdapter;
	
	/**
	 * 按网关添加订阅
	 * @param gateway
	 */
	public void addTopicByGateway(String gateway) {
		myMqttPahoMessageDrivenChannelAdapter.addTopicByGateway(gateway);
	}

	/**
	 * 按网关移除订阅
	 * @param gateway
	 */
	public void removeTopicByGateway(String gateway) {
		myMqttPahoMessageDrivenChannelAdapter.removeTopicByGateway(gateway);
	}
}
