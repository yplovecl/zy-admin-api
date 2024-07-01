package com.ruoyi.framework.task;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.Threads;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.sign.Base64;
import com.ruoyi.project.seismograph.domain.EmqxConfig;
import com.ruoyi.project.seismograph.service.IEmqxConfigService;
import com.ruoyi.project.seismograph.service.IEquipmentService;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定时任务调度测试
 *
 * @author ruoyi
 */
@Component("emqxTask")
@Configuration
public class EmqxTask {
    private static final Logger logger = LoggerFactory.getLogger(Threads.class);

    private final IEquipmentService equipmentService;

    private final IEmqxConfigService emqxConfigService;

    public EmqxTask(IEquipmentService equipmentService, IEmqxConfigService emqxConfigService) {
        this.equipmentService = equipmentService;
        this.emqxConfigService = emqxConfigService;
    }


    public void getClients(Integer page) {
        EmqxConfig emqxConfig = emqxConfigService.selectEmqxConfigById(1L);
        if (ObjectUtils.isEmpty(emqxConfig)) {
            logger.error("未配置emqx token");
            return;
        }

        if (StringUtils.isEmpty(emqxConfig.getEmqxBaseUrl()) || StringUtils.isEmpty(emqxConfig.getEmqxBaseUrl()) || StringUtils.isEmpty(emqxConfig.getEmqxBaseUrl())) {
            logger.error("emqx token配置错误: {}", emqxConfig.toString());
            return;
        }
//        logger.debug(emqxConfig.toString());
        String params = StringUtils.format("limit=1000&page={}", page);
        String emqx = StringUtils.format("{}:{}", emqxConfig.getEmqxUser(), emqxConfig.getEmqxPassword());
        emqx = Base64.encode(emqx.getBytes());
        String response = HttpUtils.sendGet(emqxConfig.getEmqxBaseUrl() + "/clients", params, Constants.UTF8, emqx);
        logger.debug(response);
        if (StringUtils.isEmpty(response)) {
            return;
        }
        if (page == 1) {
            equipmentService.updateEquipmentStatus("N", null);
        }
        JSONObject jsonObject = JSONObject.parseObject(response);
        List<JSONObject> data = jsonObject.getList("data", JSONObject.class);
        if (!data.isEmpty()) {
            for (JSONObject item : data) {
                if (item.getBooleanValue("connected"))
                    equipmentService.updateEquipmentStatus("Y", item.getString("clientid"));
            }
        }
        JSONObject meta = jsonObject.getJSONObject("meta");
        if (meta.getBooleanValue("hasnext")) {
            this.getClients(page + 1);
        }
    }
}
