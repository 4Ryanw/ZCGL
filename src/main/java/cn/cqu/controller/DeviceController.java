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
        deviceService.addDevice(device);
        return "deviceManage";
    }

    @GetMapping("/checkDevId")
    @ResponseBody
    public int checkDevId(String devId){
        DeviceDTO deviceDTO = deviceService.getDeviceDtoById(devId);
        if (deviceDTO != null )
            return 0;
        else
            return 1;
    }

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



}
