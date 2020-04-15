package cn.cqu.service.impl;

import cn.cqu.dao.AccountDao;
import cn.cqu.dao.DeviceDao;
import cn.cqu.pojo.dto.AccountDTO;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.cqu.pojo.Account;
import cn.cqu.service.AccountService;
import java.util.List;

/**
 * 账户service,处理业务逻辑
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDao accountDao;//自动注入dao层对象

    /**
     * 查询所有账户
     * @return
     */
    @Override
    public List<AccountDTO> listAccount() {
        return accountDao.listAccount();
    }


    /**
     * 添加账户
     * @param account
     * @return
     */
    @Override
    public int addAccount(Account account) {
        int res ;
        //先判断账号是否重复
        Account accountCheck = accountDao.getAccountById(account.getUserId());
        if(null == accountCheck){//若无该账号对应数据,则执行插入
             res =  accountDao.insertAccount(account);
        } else{//若该账户不为空则表明账号重复
            res = 0;
        }
        return res;
    }

    /**
     * 根据uuid删除账户
     * @param uuid
     * @return
     */
    @Override
    public int deleteAccountById(String uuid) {
        //删除前先删除其下绑定的设备
        accountDao.deleteDeviceUserById(uuid);
        //删除用户
        return accountDao.deleteAccountByUUId(uuid);
    }

    /**
     * 更改账户密码
     * @param uuid
     * @param password
     * @return
     */
    @Override
    public int updatePasswordByid(String uuid,String password) {
        int res;
        //对前端传递过来的数据进行简单地验证
        // 虽然前端已经做过了验证,但是我们作为专业后端还是需要二次验证
        if (StringUtils.isEmptyOrWhitespaceOnly(password)||password.length()<6)
            res = 0;//密码长度不符合要求
        else {
            res = accountDao.updatePasswordByUUId(uuid,password);
        }
        return res;
    }

    /**
     * 更改账户状态
     *
     * @param uuid
     * @param status
     * @return
     */
    @Override
    public int updateStatusByid(String uuid, int status) {
        return accountDao.updateStatusByUUId(uuid,status);
    }
}
