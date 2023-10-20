package com.ruoyi.framework.task;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.sign.Base64;
import com.ruoyi.project.seismograph.service.IEquipmentService;
import com.ruoyi.project.system.service.ISysConfigService;
import okhttp3.Credentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private final IEquipmentService equipmentService;

    private static final Logger log = LoggerFactory.getLogger(EmqxTask.class);

    @Value("${ruoyi.emqxUrl}")
    private String emqxUrl = "http://182.43.59.216:10003";

    private final ISysConfigService sysConfigService;

    public EmqxTask(IEquipmentService equipmentService, ISysConfigService sysConfigService) {
        this.equipmentService = equipmentService;
        this.sysConfigService = sysConfigService;
    }


    public void getClients(Integer page) {
//        35677a1611be3b7c 22TXOxW9BaGeUPjMRuwQdC168uZP9BUVd9C9CJ7UsNjFEHA
        String params = StringUtils.format("limit=1000&page={}", page);
        String emqx = sysConfigService.selectConfigByKey("emqx_user_info");
        System.out.println(emqx);
        emqx = Base64.encode(emqx.getBytes());
        System.out.println(emqx);
        String response = HttpUtils.sendGet(this.emqxUrl + "/api/v5/clients", params, Constants.UTF8, emqx);
        log.info(response);
        if (StringUtils.isNotEmpty(response)) {
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
}
