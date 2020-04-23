package cn.cqu.controller;

import cn.cqu.pojo.Device;
import cn.cqu.pojo.dto.DeviceDTO;
import cn.cqu.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    /**
     * 获取所有设备信息
     * @param map
     * @return
     */
    @GetMapping("/list/{pageName}")
    public String listDevices(ModelMap map,@PathVariable("pageName") String pageName){
        List<DeviceDTO> deviceDTOList = deviceService.listDeviceDto();
        map.put("dtoList", deviceDTOList);
        return pageName+"::table-refresh";
    }
    /**
     * 根据id获取设备信息
     * @param map
     * @return
     */
    @GetMapping("/{devId}")
    public String getDeviceById(ModelMap map,@PathVariable("devId") String devId){
        DeviceDTO dto = deviceService.getDeviceDtoById(devId);
        map.put("detailDTO", dto);
        return "deviceList::modal-refresh";
    }

    /**
     * 新增设备
     * @param request
     * @return
     * @throws ParseException
     */
    @PostMapping
    public String addDevice(HttpServletRequest request) throws ParseException {
        Device device = new Device();
        device.setDevId(request.getParameter("devId"));
        device.setTypeId(request.getParameter("typeId"));
        device.setBrandId(request.getParameter("brandId"));
        device.setDevModel(request.getParameter("devModel"));
        device.setErpCode(request.getParameter("erpCode"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        device.setPurchaseTime(sdf.parse(request.getParameter("purchaseTime")));
        device.setLastUpate(new Date());
        //新增设备基本信息时,触发器会自动新增设备使用信息和运行信息
        deviceService.addDevice(device);
        return "deviceManage";
    }

    /**
     * 检查设备id是否重复
     * @param devId
     * @return
     */
    @GetMapping("/checkDevId")
    @ResponseBody
    public int checkDevId(String devId){
        DeviceDTO deviceDTO = deviceService.getDeviceDtoById(devId);
        if (deviceDTO != null )
            return 0;
        else
            return 1;
    }

    /**
     * 修改设备基础信息
     * @param request
     * @return
     * @throws ParseException
     */
    @PutMapping("/baseInfo")
    public String updateDevicveBaseInfo(HttpServletRequest request) throws ParseException {
        Device device = new Device();
        device.setDevId(request.getParameter("devId"));
        device.setTypeId(request.getParameter("typeId"));
        device.setBrandId(request.getParameter("brandId"));
        device.setDevModel(request.getParameter("devModel"));
        device.setErpCode(request.getParameter("erpCode"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        device.setPurchaseTime(sdf.parse(request.getParameter("purchaseTime")));
        device.setLastUpate(new Date());
        deviceService.updateDevice(device);
        return "deviceManage";
    }

    /**
     * 根据id删除设备
     * @param devId
     * @return
     */
    @DeleteMapping
    @ResponseBody
    public  int deleteDeviceById(@RequestBody  String devId){
        return deviceService.deleteDeviceById(devId);
    }

    /**
     * 修改设备状态
     * @param devId
     * @param status
     * @return
     */
    @PutMapping("/status")
    @ResponseBody
    public int updateStatus(String devId,int status){
        return deviceService.updateStatusByid(devId,status);
    }



}
