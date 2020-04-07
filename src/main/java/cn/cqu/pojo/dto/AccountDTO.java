package cn.cqu.pojo.dto;

import cn.cqu.pojo.Account;
/*
账户类数据传输对象
 */
public class AccountDTO  extends  Account{
    private String role_name ;//角色

    //重写构造方法,转换账户状态
    /*public AccountDTO(Account account){
        this.account = account;
        userStatus = (account.getUserStatus()==0?"停用":"启用");
    }*/

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

}
