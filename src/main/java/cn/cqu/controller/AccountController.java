package cn.cqu.controller;

import cn.cqu.pojo.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import cn.cqu.pojo.Account;
import cn.cqu.service.AccountService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class AccountController {
    //自动注入服务类
    @Autowired
    private AccountService accountService;

    /**
     * 管理员账户信息
     * @return
     */
    @GetMapping("/admins")
    public String listAdmins(ModelMap map){
        List<AccountDTO> adminList = accountService.listAccountByLevel(2);
        List<AccountDTO> superAdminList = accountService.listAccountByLevel(1);
        adminList.addAll(superAdminList);
        map.put("adminsList", adminList);
        return "system::table-refresh";
    }

    /**
     * 获取所有账户信息
     * @param map
     * @return
     */
    @GetMapping("/list")
    public String listAccounts(ModelMap map){
       List<AccountDTO> accountList = accountService.listAccount();
        map.put("accountDTOList", accountList);
        return "account::table-refresh";
    }
    /**
     * 添加管理员页面
     * @param map
     * @return
     */
    @GetMapping("/users")
    public String listUsers(ModelMap map){
        List<AccountDTO> accountList = accountService.listAccountByLevel(3);
        map.put("usersDTOList", accountList);
        return "system::list-refresh";
    }


    /**
     * 获取设备使用人信息
     * @param map
     * @param devId
     * @return
     */
    @GetMapping("/ownerList")
    public String getOwnerList(ModelMap map, String devId){
        Map resMap  = accountService.listOwenrByDevId(devId);
        map.put("ownerMap", resMap);
        return "allotDevice::list-refresh";
    }

    /**
     * 添加账户
     * @param account
     * @return
     */
    @PostMapping
    @ResponseBody
    public int addAccount(Account account){
        return accountService.addAccount(account);
    }


    /**
     * 根据uuid删除账户
     * @param uuid
     * @return
     */
    @DeleteMapping("/{uuid}")
    @ResponseBody
    public int deleteAccount(@PathVariable("uuid")String uuid){
        return accountService.deleteAccountById(uuid);
    }

    /**
     * 修改账户密码
     * @param uuid
     * @param password
     * @return
     */
    @PutMapping("/password")
    @ResponseBody
    public int updatePassword(String uuid, String password){
        return accountService.updatePasswordByid(uuid,password);
    }

    /**
     * 修改账户状态
     * @param uuid
     * @param status
     * @return
     */
    @PutMapping("/status")
    @ResponseBody
    public int updateStatus(String uuid,int status){
        return accountService.updateStatusByid(uuid,status);
    }



    /**
     * 更改管理员
     * @return
     */
    @PutMapping("/admins")
    @ResponseBody
    public int updateDevOwner(HttpServletRequest request){
        String[] groups = request.getParameter("groups").split(",");
        int level = Integer.parseInt(request.getParameter("level"));
        return  accountService.updateAccountLevel(level,groups);
    };

}
