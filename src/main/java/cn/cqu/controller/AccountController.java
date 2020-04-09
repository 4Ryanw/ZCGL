package cn.cqu.controller;

import cn.cqu.pojo.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import cn.cqu.pojo.Account;
import cn.cqu.service.AccountService;

import java.util.List;

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
    @RequestMapping("/admins")
    public String listAdmins(){
        System.out.println("访问管理员账户信息");
        return "system";
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

}
