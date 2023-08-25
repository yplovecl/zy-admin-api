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
        logger.debug(url);
        HttpResult httpResult = OkHttps.sync(url).addUrlPara("clientId", deviceId).get();
        logger.debug("get5gPayload response body: {}", httpResult.getBody().toString());
        JSONObject response = JSONObject.parseObject(httpResult.getBody().toString());
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
        logger.info(url);
        HttpResult httpResult = OkHttps.sync(url).addUrlPara("clientId", deviceId).addUrlPara("cmd", cmd).get();
        logger.debug("send5gRoutineCmd response body: {}", httpResult.getBody().toString());
        JSONObject response = JSONObject.parseObject(httpResult.getBody().toString());
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
    public static boolean send5gConfigCmd(JSONObject body) {
        String url = String.format("%s/mqttService/send5gConfigCmd", urlPrefix);
        logger.info(url);
        HttpResult httpResult = OkHttps.sync(url).addBodyPara(body).post();
        logger.debug("send5gRoutineCmd response body: {}", httpResult.getBody().toString());
        JSONObject response = JSONObject.parseObject(httpResult.getBody().toString());
        return StringUtils.isNotEmpty(response) && response.getIntValue("code") == 200;
    }
}
