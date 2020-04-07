package cn.cqu.service;

import cn.cqu.pojo.Account;
import cn.cqu.pojo.dto.AccountDTO;

import java.util.List;

/**
 * 账户service接口
 */
public interface AccountService {

    /**
     * 查询所有账户
     * @return
     */
    List<AccountDTO> listAccount();

    /**
     * 添加账户
     * @param account
     * @return
     */
    int addAccount(Account account);

    /**
     * 根据uuid删除账户
     * @param uuid
     * @return
     */
    int deleteAccountById(String uuid);


    /**
     * 更改账户密码
     * @param uuid
     * @param password
     * @return
     */
    int updatePasswordByid(String uuid,String password);

    /**
     * 更改账户状态
     * @param uuid
     * @param status
     * @return
     */
    int updateStatusByid(String uuid,int status);
}
