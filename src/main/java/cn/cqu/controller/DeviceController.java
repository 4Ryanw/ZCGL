package cn.cqu.controller;

import cn.cqu.pojo.Device;
import cn.cqu.pojo.dto.DeviceDTO;
import cn.cqu.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
     * 按条件查询设备
     * @param map
     * @param pageName
     * @return
     */
    @PostMapping("/list")
    public String listDeviceDTOByExample(ModelMap map,DeviceDTO example,String pageName,String userName){
      List<DeviceDTO> deviceDTOList = deviceService.listDeviceDTOByexample(example);
      if(!StringUtils.isEmpty(userName)){
          deviceDTOList = deviceService.selectDeviceDTObyUserName(deviceDTOList,userName);
      }
      map.put("dtoList", deviceDTOList);
      return pageName+"::table-refresh";
    };
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

    /**
     * 分配设备持有者
     * @return
     */
    @PutMapping("/owner")
    @ResponseBody
    public int updateDevOwner(HttpServletRequest request){
        String devId = request.getParameter("devId");
        String[] groups = request.getParameter("groups").split(",");
        return  deviceService.updateDevOwnersByDevId(devId,groups);
    };


    /**
     * 分配设备使用部门
     * @param request
     * @return
     */
    @PutMapping("/org")
    @ResponseBody
    public int updateDevOrg(HttpServletRequest request){
        String devId = request.getParameter("devId");
        String fri_org = request.getParameter("fri_org");
        String sec_org = request.getParameter("sec_org");
        String orgid_addr = request.getParameter("orgid_addr");
        return  deviceService.updateDevOrgsByDevId(devId,fri_org,sec_org,orgid_addr);
    };

    /**
     * 刷新使用信息模块
     * @param map
     * @param devId
     * @return
     */
    @GetMapping("/useage_info")
    public String allotDevice(ModelMap map,String devId){
        DeviceDTO deviceDTO = deviceService.getDeviceDtoById(devId);
        map.put("deviceDTO",deviceDTO);
        return "allotDevice::useage_info";
    }

    /**
     * 导出表格
     * @param response
     * @param request
     */
    @GetMapping("/exportExcel")
    public  void exportExcel(HttpServletRequest request,HttpServletResponse response){
        String pageName = request.getParameter("pageName");
        String[] headers = request.getParameter("headers").split(",");
        deviceService.exportExcel(response,pageName,headers);
    }



    @GetMapping("/template")
    public void DownloadServlet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        //设置ContentType字段值
        response.setContentType("text/html;charset=utf-8");
        //获取所要下载的文件名称
        String filename = request.getParameter("filename");
        //下载文件所在目录

        String folder = request.getServletContext().getRealPath("/WEB-INF/statics/file/");
        //通知浏览器以下载的方式打开
        response.addHeader("Content-type", "appllication/octet-stream");
        response.addHeader("Content-Disposition", "attachment;filename="+filename);
        //通知文件流读取文件
//        InputStream in = request.getServletContext().getResourceAsStream(folder+filename);
        InputStream in = new FileInputStream(folder+filename);
        //获取response对象的输出流
        OutputStream out = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        //循环取出流中的数据
        while((len = in.read(buffer)) != -1){
            out.write(buffer,0,len);
        }


    }

}
