package com.ruoyi.project.seismograph.utils;

import cn.zhxu.okhttps.HttpResult;
import cn.zhxu.okhttps.OkHttps;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.Threads;
import com.ruoyi.framework.web.domain.AjaxResult;
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
     * get5gPayload
     *
     * @param deviceId 设备ID
     * @return JSONObject
     */
    public static JSONObject get5gPayload(String deviceId) {
        String url = String.format("%s/mqttService/get5gPayload", urlPrefix);
        HttpResult httpResult = OkHttps.sync(url).addUrlPara("clientId", deviceId).get();
        String body = httpResult.getBody().toString();
        logger.info("clientId: {}, get5gPayload result: {}", deviceId, body);
        JSONObject response = JSONObject.parseObject(body);
        if (StringUtils.isNotEmpty(response)) {
            return response.getJSONObject("data");
        }
        return null;
    }

    /**
     * send5gRoutineCmd
     *
     * @param deviceId 设备ID
     * @param cmd      cmd
     * @return AjaxResult
     */
    public static AjaxResult send5gRoutineCmd(String deviceId, int cmd) {
        String url = String.format("%s/mqttService/send5gRoutineCmd", urlPrefix);
        HttpResult.Body body = OkHttps.sync(url).addUrlPara("clientId", deviceId).addUrlPara("cmd", cmd).get().getBody().cache();
        logger.info("clientId: {}, send5gRoutineCmd result: {}", deviceId, body.toString());
        return body.toBean(AjaxResult.class);
    }

    /**
     * sendCommandHex
     *
     * @param deviceId 设备ID
     * @param hex
     * @return AjaxResult
     */
    public static AjaxResult sendCommandHex(String deviceId, String hex) {
        String url = String.format("%s/mqttService/send5gCmdHex", urlPrefix);
        JSONObject data = new JSONObject();
        data.put("clientId", deviceId);
        data.put("hex", hex);
        HttpResult.Body body = OkHttps.sync(url).addBodyPara(data).post().getBody().cache();
        logger.info("clientId: {}, send5gCmdHex: {}, result: {}", deviceId, hex, body.toString());
        return body.toBean(AjaxResult.class);
    }

    /**
     * 5g配置接口
     *
     * @param type
     * @param deviceId 设备ID
     * @param json
     * @return AjaxResult
     */
    public static AjaxResult send5gConfigCmd(int type, String deviceId, JSONObject json) {
        String url = String.format("%s/mqttService/send5gConfigCmd", urlPrefix);
        JSONObject data = new JSONObject();
        data.put("type", type);
        data.put("clientId", deviceId);
        data.put("json", json);
        HttpResult.Body body = OkHttps.sync(url).bodyType("application/json").addBodyPara(data).post().getBody().cache();
        logger.info("send5gConfigCmd, params: {}, result: {}", data.toJSONString(), body.toString());
        return body.toBean(AjaxResult.class);
    }

    /**
     * sendCmdConfig
     *
     * @param deviceId    设备ID
     * @param wakeTimeDur
     * @param wakeTimeGap
     * @return AjaxResult
     */
    public static AjaxResult sendCmdConfig(String deviceId, String wakeTimeDur, String wakeTimeGap) {
        String url = String.format("%s/mqttService/v1/cmd/config", urlPrefix);
        JSONObject data = new JSONObject();
        data.put("clientId", deviceId);
        data.put("wakeTimeDur", wakeTimeDur);
        data.put("wakeTimeGap", wakeTimeGap);
        HttpResult.Body body = OkHttps.sync(url).addBodyPara(data).post().getBody().cache();
        logger.info("url: {}, params: {}, result: {}", url, data.toJSONString(), body.toString());
        return body.toBean(AjaxResult.class);
    }

    /**
     * sendCmdControl
     *
     * @param deviceId 设备ID
     * @param type
     * @return AjaxResult
     */
    public static AjaxResult sendCmdControl(String deviceId, int type) {
        String url = String.format("%s/mqttService/v1/cmd/control", urlPrefix);
        HttpResult.Body body = OkHttps.sync(url).addUrlPara("clientId", deviceId).addUrlPara("type", type).get().getBody().cache();
        logger.info("clientId: {}, sendCmdControl result: {}", deviceId, body.toString());
        return body.toBean(AjaxResult.class);
    }

    /**
     * 5g 状态采样率状态指令接口
     *
     * @param deviceId 设备ID
     * @param retry    重试次数
     * @return AjaxResult
     */
    public static AjaxResult getDeviceSamplingRate(String deviceId, int retry) {
        String url = String.format("%s/mqttService/getDeviceSamplingRate", urlPrefix);
        HttpResult.Body body = OkHttps.sync(url).addUrlPara("clientId", deviceId).get().getBody().cache();
        logger.info("clientId: {}, getDeviceSamplingRate result: {}", deviceId, body);
        AjaxResult result = body.toBean(AjaxResult.class);
        if (result.isSuccess() || retry <= 0)
            return result;
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return getDeviceSamplingRate(deviceId, retry - 1);
    }

    /**
     * getDeviceSamplingRate
     *
     * @param deviceId 设备ID
     * @return AjaxResult
     */
    public static AjaxResult getDeviceSamplingRate(String deviceId) {
        return getDeviceSamplingRate(deviceId, 20);
    }
}
