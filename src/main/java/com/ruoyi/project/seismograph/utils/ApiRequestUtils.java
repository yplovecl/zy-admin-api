package com.ruoyi.project.seismograph.utils;

import cn.zhxu.okhttps.HttpResult;
import cn.zhxu.okhttps.OkHttps;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.Threads;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiRequestUtils {

    private static final Logger logger = LoggerFactory.getLogger(Threads.class);

    private static String urlPrefix;

    public String getUrlPrefix() {
        return urlPrefix;
    }

    @Value("${ruoyi.apiUrlPrefix}")
    public void setUrlPrefix(String apiUrlPrefix) {
        this.urlPrefix = apiUrlPrefix;
    }

    /**
     * @param deviceId
     * @return
     */
    public static JSONObject get5gPayload(String deviceId) {
        String url = String.format("%s/mqttService/get5gPayload", urlPrefix);
        HttpResult httpResult = OkHttps.sync(url).addUrlPara("clientId", deviceId).get();
        String body = httpResult.getBody().toString();
        logger.debug("clientId: {}, get5gPayload result: {}", deviceId, body);
        JSONObject response = JSONObject.parseObject(body);
        if (StringUtils.isNotEmpty(response)) {
            return response.getJSONObject("data");
        }
        return null;
    }

    /**
     * @param deviceId
     * @param cmd
     * @return
     */
    public static boolean send5gRoutineCmd(String deviceId, int cmd) {
        String url = String.format("%s/mqttService/send5gRoutineCmd", urlPrefix);
        HttpResult httpResult = OkHttps.sync(url).addUrlPara("clientId", deviceId).addUrlPara("cmd", cmd).get();
        String body = httpResult.getBody().toString();
        logger.debug("clientId: {}, send5gRoutineCmd result: {}", deviceId, body);
        JSONObject response = JSONObject.parseObject(body);
        return StringUtils.isNotEmpty(response) && response.getIntValue("code") == 200;
    }

    public static boolean send5gRoutineCmd(String deviceId) {
        for (int i = 1; i < 9; i++) {
            send5gRoutineCmd(deviceId, i);
        }
        return true;
    }

    /**
     * 5g配置接口
     *
     * @param body
     * @return
     */
    public static boolean send5gConfigCmd(JSONObject data) {
        String url = String.format("%s/mqttService/send5gConfigCmd", urlPrefix);
        HttpResult httpResult = OkHttps.sync(url).addBodyPara(data).post();
        String body = httpResult.getBody().toString();
        logger.debug("send5gRoutineCmd, params: {}, result: {}", data.toString(), body);
        JSONObject response = JSONObject.parseObject(body);
        return StringUtils.isNotEmpty(response) && response.getIntValue("code") == 200;
    }
}
