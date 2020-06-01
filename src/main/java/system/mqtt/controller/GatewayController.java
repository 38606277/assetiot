package system.mqtt.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import system.common.RO;
import system.db.DbFactory;
import system.mqtt.configure.MqttSendMessage;
import system.mqtt.service.TopicService;

/**
 * 测试网关 1319100003
 * @author cannon
 */
@RestController
@RequestMapping(value = "/Gateway")
public class GatewayController extends RO{
	
	@Resource
	private TopicService topicService;
	
	@Resource
	private MqttSendMessage mqttSendMessage;
	
	/**
	 * 添加网关
	 * @return
	 */
    @RequestMapping(value="/addGateway",produces = "text/plain;charset=UTF-8")
    public String addGateway(@RequestBody JSONObject pJson) throws UnsupportedEncodingException{
    	topicService.addTopicByGateway(pJson.getString("gatewayNumber"));
        return SuccessMsg("添加成功", "");
    }
    
    /**
	 * 删除网关
	 * @return
	 */
    @RequestMapping(value="/deleteGateway",produces = "text/plain;charset=UTF-8")
    public String deleteGateway(@RequestBody JSONObject pJson) throws UnsupportedEncodingException{
    	topicService.removeTopicByGateway(pJson.getString("gatewayNumber"));
        return SuccessMsg("删除成功", "");
    }
    
    /**
	 * 修改网关配置
	 * @return
	 */
    @RequestMapping(value="/updateGatewayConfig",produces = "text/plain;charset=UTF-8")
    public String updateGatewayConfig(@RequestBody JSONObject pJson) throws UnsupportedEncodingException{
    	String gatewayNumber = pJson.getString("gatewayNumber");
    	String topic = "/001/"+ gatewayNumber + "/set";
    	pJson.remove("gatewayNumber");
    	mqttSendMessage.sendMessage(pJson.toJSONString(), topic);
        return SuccessMsg("修改成功", "");
    }
    
}
