package cn.cqu.controller;

import cn.cqu.pojo.Device;
import cn.cqu.pojo.dto.DeviceDTO;
import cn.cqu.service.DeviceService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
     * 导出报表
     * @param response
     * @param request
     */
    @GetMapping("/exportExcel")
    public  void exportExcel(HttpServletRequest request,HttpServletResponse response){
        String pageName = request.getParameter("pageName");
        String[] headers = request.getParameter("headers").split(",");
        deviceService.exportExcel(response,pageName,headers);
    }


    /**
     * 下载模板
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @GetMapping("/template")
    public void downloadServlet(HttpServletRequest request, HttpServletResponse response)
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

    /**
     * 获取图表统计数据
     * @param request
     * @return
     * @throws ParseException
     */
    @GetMapping("/statistics")
    @ResponseBody
    public Map<String, Object> reloadChart(HttpServletRequest request) throws ParseException {
        String monthStr = request.getParameter("monthStr");
         monthStr = subMonth(monthStr);
        Map resultMap = new HashMap();
        //设备类型统计
         Map typeMap =  deviceService.staDeviceByType(monthStr);
         resultMap.put("typeMap",typeMap);
         //设备品牌统计
         Map brandMap = deviceService.staDeviceByBrand(monthStr);
         resultMap.put("brandMap",brandMap);
         //设备部门统计
         Map orgMap = deviceService.staDeviceByOrg(monthStr);
         resultMap.put("orgMap",orgMap);
         //设备状态统计
        Map statusMap = deviceService.staDeviceByStatus(monthStr);
        resultMap.put("statusMap",statusMap);
        return resultMap;
    }

    /**
     * 传入具体日期 ，返回具体日期增加一个月。
     * @param date 日期
     * @return 2017-05-13
     * @throws ParseException
     */
    private  String subMonth(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date dt = sdf.parse(date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.MONTH, 1);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        return reStr;
    }

    @PostMapping("/file")
    @ResponseBody
    public Map uploadFile(HttpServletRequest request) throws Exception {
        System.out.println("文件上传");
        //文件上传的位置
        String path =  request.getSession().getServletContext().getRealPath("/upload");
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        //解析request对象,获取文件上传项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        //解析
        List<FileItem> list =  fileUpload.parseRequest(request);
        //遍历
        for (FileItem item:list
             ) {
            if(item.isFormField()){//普通表单项

          }   else{
                String fileName = item.getName()+UUID.randomUUID();
                //完成文件上传
                item.write(new File(path,fileName));
                //删除临时文件
                item.delete();
                return   deviceService.fileRead(path+"/"+fileName);
            }
        }
        return null;
    }

}
