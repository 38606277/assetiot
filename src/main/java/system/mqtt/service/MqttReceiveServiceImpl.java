package system.mqtt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;


import system.mqtt.bean.MQTTBtMessage;
import system.mqtt.bean.MQTTAlarmMessage;
import system.mqtt.bean.MQTTRegisterMessage;
import system.mqtt.bean.MQTTSetResMessage;
import system.mqtt.bean.MQTTUpdateMessage;
import system.mqtt.util.HexUtils;

/**
 * 消息接收
 * @author cannon
 *
 */
@Service("mqttReceiveService")
public class MqttReceiveServiceImpl implements MqttReceiveService{

	@Override
	public void handlerMqttMessage(String topic, byte[] payload) {
		// TODO Auto-generated method stub
	
		System.out.println("topic：" + topic);
		
		String[] data  = topic.substring(1).split("/");
		
		if(data.length !=3) {
			System.out.println("非物联网订阅信息：" + topic);
			return ;
		}
		
		
		String prefix = data[0];//固定前缀
		long gatewayId = Long.parseLong(data[1]);//网关编号
		String type = data[2];//订阅类型
		
		
		if("bt".equals(type)){
			List<MQTTBtMessage> labelList = decodeLableList(payload);
			
			for(MQTTBtMessage label :labelList) {
				
			}
		
			System.out.println(labelList.toString());
		}else if("update".equals(type)){
			Map<String, Object> mapObj = JSON.parseObject(new String (payload));
			MQTTUpdateMessage mqqtUpdateMessage = new MQTTUpdateMessage(mapObj);
			System.out.println(mqqtUpdateMessage.toString());
		}else if("register".equals(type)) {
			MQTTRegisterMessage mqttRegisterMessageBean  = JSON.parseObject(payload, MQTTRegisterMessage.class);
			System.out.println(mqttRegisterMessageBean.toString());
		}else if("set_res".equals(type)){
			MQTTSetResMessage mqttSetRexMessageBean  = JSON.parseObject(payload, MQTTSetResMessage.class);
			System.out.println(mqttSetRexMessageBean.toString());
		}else if("alarm".equals(type)){
			MQTTAlarmMessage  mqttAlarmMessageBean = JSON.parseObject(payload, MQTTAlarmMessage.class);
			System.out.println(mqttAlarmMessageBean.toString());
		}else{
			System.out.println(new String (payload));
		}
	}
	
	/**
	 * 解析电子标签集合
	 * @param payload
	 * @return
	 */
	private List<MQTTBtMessage> decodeLableList(byte[] payload) {
		List<MQTTBtMessage> labelList = new ArrayList<MQTTBtMessage>();
		int span = 5;
		int count = payload.length/span;
		String hexBinary = DatatypeConverter.printHexBinary(payload);
		System.out.println("接收到" + count + "个标签数据 ---- 十六进制 ： " + hexBinary  );

		for(int i = 0 ; i <count ; i++) {
			byte [] bytes = new byte[span];
			for(int j = 0 ; j<span; j++) {
				bytes[j] = payload[i*span+j];
			}
			MQTTBtMessage label = decodeLabel(bytes);
			labelList.add(label);
		}
		return labelList;
	}

	
	/**
	 * 解析单个标签
	 * @param bytes
	 * @return
	 */
	public static MQTTBtMessage decodeLabel(byte[] bytes) {
		//byte数组转换为二进制字符串
		String bitStr =	HexUtils.byteArrToBinStr(bytes);
		//电量
		int electricityBit = Integer.parseInt(bitStr.substring(0,4),2);
		String electricityStr = ((electricityBit + 20)/10f) + "V";
		//编号
		int number  = Integer.parseInt(bitStr.substring(4,32),2);
		//信号强度
		int signalBit = Integer.parseInt(bitStr.substring(32),2);
		String signalStr =  ((byte)signalBit) + "dp";

		//decode(bitStr.substring(32));

		MQTTBtMessage label = new  MQTTBtMessage();
		label.setCode(String.valueOf(number));
		label.setElectricity(electricityStr);
		label.setSignalIntensity(signalStr);

		return label;
	}

}
