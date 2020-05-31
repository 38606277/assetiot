package system.mqtt.configure;

import javax.annotation.Resource;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import system.mqtt.service.MqttReceiveService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 接收者配置
 * @author cannon
 *
 */
@Configuration
@IntegrationComponentScan
public class MqttReceiveConfig {

	@Value("${mqtt.username}")
	private String username;

	@Value("${mqtt.password}")
	private String password;

	@Value("${mqtt.host}")
	private String hostUrl;

	@Value("${mqtt.clientinid}")
	private String clientId;

	@Value("${mqtt.topic}")
	private String defaultTopic;

	@Value("${mqtt.timeout}")
	private int completionTimeout ;   //连接超时

	@Resource
	private MqttReceiveService mqttReceiveService;
	
	@Bean
	public MqttConnectOptions getMqttConnectOptions(){
		MqttConnectOptions mqttConnectOptions=new MqttConnectOptions();
		mqttConnectOptions.setCleanSession(true);
		mqttConnectOptions.setConnectionTimeout(10);
		mqttConnectOptions.setKeepAliveInterval(90);
		mqttConnectOptions.setAutomaticReconnect(true);
		mqttConnectOptions.setUserName(username);
		mqttConnectOptions.setPassword(password.toCharArray());
		mqttConnectOptions.setServerURIs(new String[]{hostUrl});
		mqttConnectOptions.setKeepAliveInterval(2);
		return mqttConnectOptions;
	}
	
	@Bean
	public MqttPahoClientFactory mqttClientFactory() {
		DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
		factory.setConnectionOptions(getMqttConnectOptions());
		return factory;
	}

	//接收通道
	@Bean
	public MessageChannel mqttInputChannel() {
		return new DirectChannel();
	}
	
	//配置client,监听的topic /001/1319100003/bt
	@Bean
	public MyMqttPahoMessageDrivenChannelAdapter inbound() {
		MyMqttPahoMessageDrivenChannelAdapter adapter = new MyMqttPahoMessageDrivenChannelAdapter(clientId+"_inbound", mqttClientFactory(),
				"/init");

		adapter.setCompletionTimeout(completionTimeout);
		DefaultPahoMessageConverter converter = new DefaultPahoMessageConverter();
		converter.setPayloadAsBytes(true);
		adapter.setConverter(converter);
		adapter.setQos(1);
		adapter.setOutputChannel(mqttInputChannel());
		System.err.println("mqtt 适配器设置成功");
		return adapter;
	}

	//通过通道获取数据
	@Bean
	@ServiceActivator(inputChannel = "mqttInputChannel")
	public MessageHandler handler() {
		return new MessageHandler() {
			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				
				String topic = message.getHeaders().get("mqtt_receivedTopic").toString();
				if(topic!=null && 0 < topic.trim().length() ) {
					byte[] payload = (byte[])message.getPayload();
					mqttReceiveService.handlerMqttMessage(topic,  payload);
				}else {
					System.out.println("topic：" + topic);
				}	
			}
		};
	}

	

}
