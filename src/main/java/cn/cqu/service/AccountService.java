package cn.cqu.service;

import cn.cqu.pojo.Account;
import cn.cqu.pojo.dto.AccountDTO;

import java.util.List;
import java.util.Map;

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
     * 按权限等级查询账户
     * @return
     */
    List<AccountDTO> listAccountByLevel(int level);

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

    /**
     * 设备持有者列表
     * @return
     */
    Map listOwenrByDevId(String devId);

    /**
     * 更改账户权限级别
     * @param level
     * @param groups
     * @return
     */
    int updateAccountLevel(int level,String[] groups);

    /**
     * 根据账号查询账户DTO类
     * @param id
     * @return
     */
    AccountDTO getAccountDTOById(String id);

    /**
     * 根据用户名查询账户DTO类
     * @param userName
     * @return
     */
    List<AccountDTO> listAccountByName(String userName);




}
