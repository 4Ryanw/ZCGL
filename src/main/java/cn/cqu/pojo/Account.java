package cn.cqu.pojo;

import java.io.Serializable;

/**
 * 账户实体类
 */
public class Account implements Serializable {
    private String uuid ; //唯一标识
    private String userId ;//账户
    private String password ;//密码
    private String username ;//用户名
    private int userRole ;//角色id
    private int userStatus ;//账户状态标识

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return username;
    }
}
