package cn.cqu.service.impl;

import cn.cqu.dao.AccountDao;
import cn.cqu.dao.DeviceDao;
import cn.cqu.pojo.Account;
import cn.cqu.pojo.Device;
import cn.cqu.pojo.DeviceUseage;
import cn.cqu.pojo.dto.DeviceDTO;
import cn.cqu.service.DeviceService;
import cn.cqu.util.ExcelExportUtil;
import cn.cqu.util.MyLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceDao deviceDao;
    @Autowired
    private AccountDao accountDao;

    /**
     * 查询所有设备DTO
     *
     * @return
     */
    @Override
    public List<DeviceDTO> listDeviceDto() {
        List<DeviceDTO> resList = new ArrayList<>();
        //获取所有设备基础信息
        List<Device> deviceList = deviceDao.listDevice();
        for (Device device:deviceList
             ) {
            DeviceDTO deviceDTO = getDeviceDtoById(device.getDevId());
            resList.add(deviceDTO);
        }
        return resList;
    }

    /**
     * 根据id查询设备DTO
     *
     * @param deviceId
     * @return
     */
    @Override
    public DeviceDTO getDeviceDtoById(String deviceId) {
        DeviceDTO example = new DeviceDTO();
        example.setDevId(deviceId);
        List<DeviceDTO> deviceDTOList = deviceDao.listDeviceDtoByExample(example);
        if(deviceDTOList.size()>0){
            DeviceDTO deviceDTO = deviceDTOList.get(0);
            deviceDTO.setUserList(accountDao.listUserByDeviceId(deviceId));
            return deviceDTO;
        }
        else return null;

    }

    /**
     * 新增设备
     *
     * @param example
     * @return
     */
    @Override
    @MyLog(actionName = "新增设备")
    public int addDevice(Device example) {
        return deviceDao.insertDevice(example);
    }

    /**
     * 修改设备信息
     *
     * @param example
     * @return
     */
    @Override
    @MyLog(actionName = "修改设备信息")
    public int updateDevice(Device example) {
        example.setLastUpate(new Date());
        return deviceDao.updateDevice(example);
    }

    /**
     * 根据Id删除设备
     *
     * @param devId
     * @return
     */
    @Override
    @MyLog(actionName = "根据Id删除设备")
    public int deleteDeviceById(String devId) {
        return deviceDao.deleteDeviceById(devId);
    }

    /**
     * 修改设备状态
     *
     * @param devId
     * @param status
     * @return
     */
    @Override
    @MyLog(actionName = "修改设备状态")
    public int updateStatusByid(String devId, int status) {
        DeviceUseage deviceUseage = new DeviceUseage();
        deviceUseage.setDevId(devId);
        deviceUseage.setDevStatus(status);
        return deviceDao.updateDeviceUseage(deviceUseage);
    }

    /**
     * 更新设备持有者
     *
     * @param devId
     * @param groups
     * @return
     */
    @Override
    @MyLog(actionName = "更新设备持有者")
    public int updateDevOwnersByDevId(String devId, String[] groups) {
        //新数组转换为list方便接下来比较
        List<String> addAccountId;
        if(!StringUtils.isEmpty(groups[0])) //非空判断
            addAccountId = new ArrayList<>(Arrays.asList(groups));
        else
            addAccountId = new ArrayList<>();
        //获取旧的设备使用者id集合 oldAccountId
        List<Account> oldAccount = accountDao.listUserByDeviceId(devId);
        List<String> deleteAccountId = new ArrayList<>();
        List<String> deleteAccountId_coppy = new ArrayList<>();
        for (Account account:oldAccount
             ) {
            deleteAccountId.add(account.getUuid());
            deleteAccountId_coppy.add(account.getUuid());
        }

        //将两组id数组对比
        deleteAccountId.removeAll(addAccountId);//存在于旧的而不存在于新的,视为需要删除
        addAccountId.removeAll(deleteAccountId_coppy);//存在于新的而不存在于旧的,视为需要添加

        try {
            for (String uuid:deleteAccountId
            ) {
                deviceDao.deleteDeviceUserById(devId,uuid);
            }
            for (String uuid:addAccountId
            ) {
                deviceDao.insertDeviceUser(devId,uuid);
            }
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }

    /**
     * 更新设备部门
     *
     * @param devId
     * @param fri_org
     * @param sec_org
     * @param orgid_addr
     * @return
     */
    @Override
    @MyLog(actionName = "更新设备部门")
    public int updateDevOrgsByDevId(String devId, String fri_org, String sec_org, String orgid_addr) {
        DeviceUseage example = new DeviceUseage();
        example.setDevId(devId);
        example.setDepFri(fri_org);
        example.setDepSec(sec_org);
        example.setAddress(orgid_addr);
        return deviceDao.updateDeviceUseage(example);
    }

    /**
     * 根据条件动态查询设备
     *
     * @param example
     * @return
     */
    @Override
    public List<DeviceDTO> listDeviceDTOByexample(DeviceDTO example) {
        List<DeviceDTO> deviceDTOList = deviceDao.listDeviceDtoByExample(example);
        for (DeviceDTO deviceDTO: deviceDTOList
             ) {
            deviceDTO.setUserList(accountDao.listUserByDeviceId(deviceDTO.getDevId()));
        }
        return deviceDTOList;
    }

    /**
     * 导出Excel
     *
     * @param response
     * @param pageName
     */
    @Override
    @MyLog(actionName = "导出Excel")
    public void exportExcel(HttpServletResponse response, String pageName,String[] headers) {
            String sheetName = "工作表1";
            String fileName="" ;
            List<DeviceDTO> deviceList = listDeviceDto();
            List<String[]> dataList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            //根据pageName不同 填充不同的数据
        switch (pageName){
            case  "deviceManage":
                fileName = "设备管理报表";
                for (DeviceDTO deviceDTO:deviceList
                     ) {
                    List<String> arr = new ArrayList();
                    arr.add(deviceDTO.getDevId());
                    arr.add(deviceDTO.getType());
                    arr.add(deviceDTO.getBrand());
                    arr.add(deviceDTO.getDevModel());
                    arr.add(sdf.format(deviceDTO.getPurchaseTime()));
                    arr.add(deviceDTO.getErpCode());
                    arr.add(deviceDTO.getDevStatus()==1?"停用":"启用");
                    arr.add(deviceDTO.getDomainStatus()==1?"未加域":"已加域");
                    String[] arrStr = arr.toArray(new String[0]);
                    dataList.add(arrStr);
                };break;
            case  "deviceList":
                fileName = "设备台账报表";
                for (DeviceDTO deviceDTO:deviceList
            ) {
                List<String> arr = new ArrayList();
                arr.add(deviceDTO.getDevId());
                arr.add(deviceDTO.getType());
                arr.add(deviceDTO.getBrand());
                arr.add(deviceDTO.getDevModel());
                arr.add(sdf.format(deviceDTO.getPurchaseTime()));
                arr.add(deviceDTO.getDepFri());
                arr.add(deviceDTO.getDepSec());
                arr.add(deviceDTO.getAddress());
                arr.add(deviceDTO.getUserNameList());
                arr.add(deviceDTO.getNetwork()==1?"内网":"外网");
                arr.add(deviceDTO.getMacAddress());
                arr.add(sdf2.format(deviceDTO.getLastUpate()));
                String[] arrStr = arr.toArray(new String[0]);
                dataList.add(arrStr);
            };break;
        }
            ExcelExportUtil.downloadExcelFile(response,sheetName,headers,dataList,fileName);
        }

    /**
     * 按用户名筛选设备
     * @param dataList
     * @param userName
     * @return
     */
    public List<DeviceDTO> selectDeviceDTObyUserName(List<DeviceDTO> dataList,String userName) {
        if (dataList != null) {
            Iterator iterator = dataList.iterator();
            while (iterator.hasNext()) {
                DeviceDTO deviceDTO = (DeviceDTO) iterator.next();
                String userNameList = deviceDTO.getUserNameList();
                if (!userNameList.contains(userName)) {
                    iterator.remove();
                }
            }
        }
        return dataList;
    }
}
