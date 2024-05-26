package com.ruoyi.project.seismograph.controller;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.interceptor.annotation.RepeatSubmit;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.seismograph.domain.Equipment;
import com.ruoyi.project.seismograph.domain.EquipmentSeconded;
import com.ruoyi.project.seismograph.service.IEquipmentSecondedService;
import com.ruoyi.project.seismograph.service.IEquipmentService;
import com.ruoyi.project.seismograph.utils.ApiRequestUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 设备Controller
 *
 * @author pange
 * @date 2023-04-18
 */
@RestController
@RequestMapping("/seismograph/equipment")
public class EquipmentController extends BaseController {
    private final IEquipmentService equipmentService;

    private final IEquipmentSecondedService equipmentSecondedService;

    public final RedisCache rs;

    public EquipmentController(IEquipmentService equipmentService, IEquipmentSecondedService equipmentSecondedService, RedisCache rs) {
        this.equipmentService = equipmentService;
        this.equipmentSecondedService = equipmentSecondedService;
        this.rs = rs;
    }

    /**
     * 查询设备列表
     */
    @PreAuthorize("@ss.hasPermi('seismograph:equipment:list')")
    @GetMapping("/list")
    public TableDataInfo list(Equipment equipment) {
        startPage();
        Long enterpriseId = SecurityUtils.getEnterpriseId();
        if (null != enterpriseId && enterpriseId.intValue() > 0) {
            equipment.setEnterpriseId(enterpriseId);
        }
        List<Equipment> list = equipmentService.selectEquipmentList(equipment);
        return getDataTable(list);
    }

    /**
     * 导出设备列表
     */
    @PreAuthorize("@ss.hasPermi('seismograph:equipment:export')")
    @Log(title = "设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Equipment equipment) {
        Long enterpriseId = SecurityUtils.getEnterpriseId();
        if (null != enterpriseId && enterpriseId.intValue() > 0) {
            equipment.setEnterpriseId(enterpriseId);
        }
        List<Equipment> list = equipmentService.selectEquipmentList(equipment);
        ExcelUtil<Equipment> util = new ExcelUtil<>(Equipment.class);
        util.exportExcel(response, list, "设备数据");
    }

    /**
     * 获取设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('seismograph:equipment:query')")
    @GetMapping(value = "/{equipmentId}")
    public AjaxResult getInfo(@PathVariable("equipmentId") Long equipmentId) {
        Equipment equipment = getEquipment(equipmentId);
        if (ObjectUtils.isEmpty(equipment)) {
            return error("设备不存在");
        }
        JSONObject jsonObject = JSONObject.from(equipment);
        EquipmentSeconded seconded = equipmentSecondedService.selectByEquipmentIdentity(equipment.getEquipmentIdentity());
        if (ObjectUtils.isNotEmpty(seconded)) {
            jsonObject.put("isSeconded", seconded.getIsSeconded());
            jsonObject.put("returnTime", new SimpleDateFormat("yyyy-MM-dd").format(seconded.getReturnTime()));
        }
        String pluckStateKey = String.format("device:state:%s:98", equipment.getEquipmentIdentity());
        if (rs.hasKey(pluckStateKey)) {
            jsonObject.put("pluckState", rs.getCacheObject(pluckStateKey));
        }
        JSONObject result = ApiRequestUtils.get5gPayload(equipment.getEquipmentIdentity());
        if (ObjectUtils.isNotEmpty(result)) {
            jsonObject.put("payload", result);
        }
        return success(jsonObject);
    }

    /**
     * 新增设备
     */
    @PreAuthorize("@ss.hasPermi('seismograph:equipment:add')")
    @Log(title = "设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Equipment equipment) {
        return toAjax(equipmentService.insertEquipment(equipment));
    }

    /**
     * 修改设备
     */
    @PreAuthorize("@ss.hasPermi('seismograph:equipment:edit')")
    @Log(title = "设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Equipment equipment) {
        return toAjax(equipmentService.updateEquipment(equipment));
    }

    /**
     * 删除设备
     */
    @PreAuthorize("@ss.hasPermi('seismograph:equipment:remove')")
    @Log(title = "设备", businessType = BusinessType.DELETE)
    @DeleteMapping("/{equipmentIds}")
    public AjaxResult remove(@PathVariable Long[] equipmentIds) {
        return toAjax(equipmentService.deleteEquipmentByEquipmentIds(equipmentIds));
    }


    @PreAuthorize("@ss.hasPermi('seismograph:equipment:seconded')")
    @Log(title = "设备借调", businessType = BusinessType.OTHER)
    @PostMapping("/seconded")
    public AjaxResult seconded(@RequestBody EquipmentSeconded equipmentSeconded) {
        Long enterpriseId = SecurityUtils.getEnterpriseId();
        Equipment equipment = equipmentService.selectByEquipmentIdentity(equipmentSeconded.getEquipmentIdentity());
        if (ObjectUtils.isEmpty(equipment) || !Objects.equals(equipment.getEnterpriseId(), enterpriseId))
            return error("设备不存在");

        EquipmentSeconded seconded = equipmentSecondedService.selectByEquipmentIdentity(equipmentSeconded.getEquipmentIdentity());
        if (ObjectUtils.isNotEmpty(seconded) && "N".equals(seconded.getIsSeconded())) return error("设备未归还");

        equipmentSeconded.setFromEnterpriseId(equipment.getEnterpriseId());
        equipmentSeconded.setIsSeconded("N");
        int size = equipmentSecondedService.insertEquipmentSeconded(equipmentSeconded);
        if (size > 0) {
            equipment.setEnterpriseId(equipmentSeconded.getToEnterpriseId());
            equipmentService.updateEquipment(equipment);
            return success("设备借调成功");
        }
        return error("设备借调失败，请稍后再试。");
    }

    @PreAuthorize("@ss.hasPermi('seismograph:equipment:seconded')")
    @Log(title = "设备借调", businessType = BusinessType.OTHER)
    @PostMapping("/return")
    public AjaxResult returnEquipment(@RequestBody EquipmentSeconded equipmentSeconded) {
        Long enterpriseId = SecurityUtils.getEnterpriseId();
        Equipment equipment = equipmentService.selectByEquipmentIdentity(equipmentSeconded.getEquipmentIdentity());
        if (ObjectUtils.isEmpty(equipment) || !Objects.equals(equipment.getEnterpriseId(), enterpriseId))
            return error("设备不存在");

        EquipmentSeconded seconded = equipmentSecondedService.selectByEquipmentIdentity(equipmentSeconded.getEquipmentIdentity());
        if (ObjectUtils.isEmpty(seconded) || "Y".equals(seconded.getIsSeconded())) return error("该设备无需归还");

        seconded.setIsSeconded("Y");
        int size = equipmentSecondedService.updateEquipmentSeconded(seconded);
        if (size > 0) {
            equipment.setEnterpriseId(seconded.getFromEnterpriseId());
            equipmentService.updateEquipment(equipment);
            return success("设备归还成功");
        }
        return error("设备归还失败，请稍后再试。");
    }

    @RepeatSubmit(interval = 30000, message = "请求过于频繁")
    @PreAuthorize("@ss.hasPermi('seismograph:equipment:query')")
    @GetMapping(value = "/sync/{equipmentId}")
    public AjaxResult syncInfo(@PathVariable("equipmentId") Long equipmentId) {
        Equipment equipment = getEquipment(equipmentId);
        if (ObjectUtils.isEmpty(equipment)) {
            return error("设备不存在");
        }
        return ApiRequestUtils.send5gRoutineCmd(equipment.getEquipmentIdentity(), 100);
    }

    @PreAuthorize("@ss.hasPermi('seismograph:equipment:query')")
    @GetMapping(value = "/waveform/{equipmentId}")
    public AjaxResult waveform(@PathVariable("equipmentId") Long equipmentId) {
        Equipment equipment = getEquipment(equipmentId);
        if (ObjectUtils.isEmpty(equipment)) {
            return error("设备不存在");
        }
        List<?> data = rs.getCacheList("device:store:" + equipment.getEquipmentIdentity());
        return success(data);
    }

    @PreAuthorize("@ss.hasPermi('seismograph:equipment:query')")
    @PostMapping(value = "/sendCommandHex/{equipmentIds}")
    public AjaxResult sendCommandHex(@PathVariable Long[] equipmentIds, @RequestBody JSONObject params) {
        String hex = params.getString("hex");
        if (StringUtils.isEmpty(hex)) {
            return error("请输入要发送的指令");
        }
        if (ObjectUtils.isEmpty(equipmentIds)) return error("设备不存在");
        List<AjaxResult> results = new ArrayList<>();
        for (Long equipmentId : equipmentIds) {

            Equipment equipment = getEquipment(equipmentId);
            if (ObjectUtils.isEmpty(equipment)) {
                results.add(error("设备不存在"));
                continue;
            }
            results.add(ApiRequestUtils.sendCommandHex(equipment.getEquipmentIdentity(), hex));
        }
        if (results.size() == 1) return results.get(0);
        return success("指令已发送");
    }

    @PreAuthorize("@ss.hasPermi('seismograph:equipment:query')")
    @PostMapping(value = "/sendCmdConfig/{equipmentId}")
    public AjaxResult sendCmdConfig(@PathVariable("equipmentId") Long equipmentId, @RequestBody JSONObject params) {
        Equipment equipment = getEquipment(equipmentId);
        if (ObjectUtils.isEmpty(equipment)) {
            return error("设备不存在");
        }
        String wakeTimeDur = params.getString("wakeTimeDur");
        if (StringUtils.isEmpty(wakeTimeDur)) {
            return error("请输入持续时间");
        }
        String wakeTimeGap = params.getString("wakeTimeGap");
        if (StringUtils.isEmpty(wakeTimeGap)) {
            return error("请输入唤醒时间");
        }
        return ApiRequestUtils.sendCmdConfig(equipment.getEquipmentIdentity(), wakeTimeDur, wakeTimeGap);
    }

    @PreAuthorize("@ss.hasPermi('seismograph:equipment:wrConfig')")
    @PostMapping(value = "/send5gConfigCmd/{type}/{equipmentId}")
    public AjaxResult send5gConfigCmd(@PathVariable("type") int type, @PathVariable("equipmentId") Long equipmentId, @RequestBody JSONObject params) {
        Equipment equipment = getEquipment(equipmentId);
        if (ObjectUtils.isEmpty(equipment)) {
            return error("设备不存在");
        }
        if (!Arrays.asList(1, 2, 3, 4).contains(type))
            return error("参数类型错误");
        return ApiRequestUtils.send5gConfigCmd(type, equipment.getEquipmentIdentity(), params);
    }

    @PreAuthorize("@ss.hasPermi('seismograph:equipment:wrConfig')")
    @GetMapping(value = "/wrConfig/{equipmentId}")
    public AjaxResult wrConfig(@PathVariable("equipmentId") Long equipmentId) {
        Equipment equipment = getEquipment(equipmentId);
        if (ObjectUtils.isEmpty(equipment)) {
            return error("设备不存在");
        }
        JSONObject data = new JSONObject();
        String cellularKey = String.format("device:config:%s:/device/publish/%s/config/cellular", equipment.getEquipmentIdentity(), equipment.getEquipmentIdentity());
        JSONObject cellular = rs.getCacheObject(cellularKey);
        if (StringUtils.isNotNull(cellular) && !cellular.isEmpty()) {
            data.put("cellular", cellular.getJSONObject("cellular"));
        } else {
            String topic = String.format("/device/publish/%s/config/cellular/get", equipment.getEquipmentIdentity());
            ApiRequestUtils.send5gConfigCmd(5, equipment.getEquipmentIdentity(), JSONObject.of(), topic);
        }
        String wifiKey = String.format("device:config:%s:/device/publish/%s/config/wifi", equipment.getEquipmentIdentity(), equipment.getEquipmentIdentity());
        JSONObject wifi = rs.getCacheObject(wifiKey);
        if (StringUtils.isNotNull(wifi) && !wifi.isEmpty()) {
            data.put("wlanble", wifi.getJSONObject("wlanble"));
        } else {
            String topic = String.format("/device/publish/%s/config/wifi/get", equipment.getEquipmentIdentity());
            ApiRequestUtils.send5gConfigCmd(5, equipment.getEquipmentIdentity(), JSONObject.of(), topic);
        }
        AjaxResult result = success(data);
        String statusKey = String.format("device:config:%s:/device/publish/%s/config/status", equipment.getEquipmentIdentity(), equipment.getEquipmentIdentity());
        JSONObject status = rs.getCacheObject(statusKey);
        if (StringUtils.isNotNull(status) && !status.isEmpty()) {
            result.put("deviceStatus", status.getJSONObject("Status"));
        }
        return result;
    }

    @PreAuthorize("@ss.hasPermi('seismograph:equipment:query')")
    @GetMapping(value = "/sendCmdControl/{equipmentIds}/{type}")
    public AjaxResult sendCmdControl(@PathVariable Long[] equipmentIds, @PathVariable("type") Integer type) {
        if (ObjectUtils.isEmpty(equipmentIds)) return error("设备不存在");
        List<AjaxResult> results = new ArrayList<>();
        for (Long equipmentId : equipmentIds) {
            Equipment equipment = getEquipment(equipmentId);
            if (ObjectUtils.isEmpty(equipment)) {
                results.add(error("设备不存在"));
                continue;
            }
            results.add(ApiRequestUtils.sendCmdControl(equipment.getEquipmentIdentity(), type));
        }
        if (results.size() == 1) return results.get(0);
        return success("指令已发送");
    }

    /**
     * 获取设备采样率
     *
     * @param equipmentId
     * @return
     */
    @PreAuthorize("@ss.hasPermi('seismograph:equipment:query')")
    @GetMapping(value = "/getDeviceSamplingRate/{equipmentId}")
    public AjaxResult getDeviceSamplingRate(@PathVariable("equipmentId") Long equipmentId) throws InterruptedException {
        Equipment equipment = getEquipment(equipmentId);
        if (ObjectUtils.isEmpty(equipment)) {
            return error("设备不存在");
        }
        return ApiRequestUtils.getDeviceSamplingRate(equipment.getEquipmentIdentity());
    }

    @PreAuthorize("@ss.hasPermi('seismograph:equipment:add')")
    @Log(title = "批量添加设备", businessType = BusinessType.INSERT)
    @PostMapping("/batchAdd")
    public AjaxResult batchAdd(@RequestBody JSONObject params) {
        String idTpl = params.getString("idTpl");
        if (StringUtils.isEmpty(idTpl))
            return error("请选择设备编号模板");
        int start = params.getIntValue("start");
        int end = params.getIntValue("end");
        if (!(start > 0 && end > 0 && start < end))
            return error("设备编号起始/结束位置错误");
        int size = 0;
        for (int i = start; i <= end; i++) {
            String equipmentIdentity = idTpl.replace("XXXXXX", String.format("%06d", i));
            Equipment equipment = equipmentService.selectByEquipmentIdentity(equipmentIdentity);
            if (StringUtils.isNotNull(equipment))
                continue;
            equipment = new Equipment();
            equipment.setOnline("N");
            equipment.setEquipmentIdentity(equipmentIdentity);
            equipment.setHave5g(params.getString("have5g"));
            equipment.setEnterpriseId(params.getLong("enterpriseId"));
            equipment.setPacketTime(params.getInteger("packetTime"));
            equipment.setWorkMode(params.getString("workMode"));
            equipmentService.insertEquipment(equipment);
            size++;
        }
        return success(String.format("成功添加%s台设备", size));
    }

    private Equipment getEquipment(Long equipmentId) {
        Equipment equipment = equipmentService.selectEquipmentByEquipmentId(equipmentId);
        if (ObjectUtils.isEmpty(equipment)) {
            return null;
        }
        Long enterpriseId = SecurityUtils.getEnterpriseId();
        if (null != enterpriseId && enterpriseId > 0 && !enterpriseId.equals(equipment.getEnterpriseId())) {
            return null;
        }
        return equipment;
    }
}
