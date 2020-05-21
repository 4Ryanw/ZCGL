package cn.cqu.dao;

import cn.cqu.pojo.Account;
import cn.cqu.pojo.dto.AccountDTO;
import cn.cqu.util.MyLog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户Dao类
 */
@Repository
public interface AccountDao {
    //获取所有账户信息
     List<AccountDTO> listAccount();

     //插入一条账户数据
     @Insert("insert into t_account VALUES (UUID(),#{userId},#{password},#{username},#{userRole},#{userStatus})")
     int insertAccount(Account account) ;

     //根据uuid删除账户信息
     @Delete("delete from t_account where uuid = #{uuid}")
     int deleteAccountByUUId(String uuid);

     //根据账号查询账户
     @Select("select * from t_account where user_id = #{userId}")
    Account getAccountById(String userId);

     //根据uuid修改账户密码
     @Update("update t_account set password = #{password} where uuid = #{uuid}")
     int updatePasswordByUUId(@Param("uuid") String uuid, @Param("password") String password);

     @Update("update t_account set user_status = #{status} where uuid = #{uuid}")
     int updateStatusByUUId(@Param("uuid") String uuid, @Param("status") int status);

     /**
      * 按id查询设备所属人员
      * @param deviceId
      * @return
      */
     List<Account> listUserByDeviceId(String deviceId);

    /**
     * 删除该用户的设备关联信息
     * @param userId
     * @return
     */
    @Delete("delete from t_device_user where user_uuid = #{userId}")
    int deleteDeviceUserById(String userId);


    /**
     * 按等级查询用户
     * @param level
     * @return
     */
    List<AccountDTO> listAccountByRoleId(int level);

    /**
     * 根据id更改用户权限等级
     * @param id
     * @return
     */
    @Update("update t_account set user_role = #{level} where uuid = #{id} ")
    int updateLevelById(@Param("id") String id,@Param("level")int level);

}
