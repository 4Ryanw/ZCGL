package cn.cqu.controller;

import cn.cqu.pojo.dto.DeviceDTO;
import cn.cqu.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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



}
