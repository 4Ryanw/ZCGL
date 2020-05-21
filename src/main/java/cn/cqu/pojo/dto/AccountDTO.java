package cn.cqu.pojo.dto;

import cn.cqu.pojo.Account;
/*
账户类数据传输对象
 */
public class AccountDTO  extends  Account{
    private String roleName ;//角色

    //重写构造方法,转换账户状态
    /*public AccountDTO(Account account){
        this.account = account;
        userStatus = (account.getUserStatus()==0?"停用":"启用");
    }*/

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
