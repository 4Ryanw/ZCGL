package shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import cn.cqu.dao.AccountDao;
import cn.cqu.pojo.Account;
import cn.cqu.pojo.dto.AccountDTO;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private AccountDao accountDao;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //利用登录用户的信息查询当前用户的角色
        Set<String> roles = new HashSet<>();
        String principal =  (String) principalCollection.getPrimaryPrincipal();
        String role  =  accountDao.getAccountDTOById(principal).getRoleName();
        roles.add(role);
        //创建SimpleAuthioriticationInfo 并设置reles属性
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        return info;
    }
    /**
     * 登录
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取输入的账号
        String userId = (String) authenticationToken.getPrincipal();
        //获取密码
        //String password = (String) authenticationToken.getCredentials();
        //根据账号查询数据库，如果查不到就认为账号不正确，返回null
        Account user = accountDao.getAccountById(userId);
        //判断账户是否存在
        if(null != user){
            //判断账户状态
            if(user.getUserStatus()==0){ //status为0 代表停用
                throw new LockedAccountException() ;
            }
            //交由shiro判断，是否与用户输入的匹配
            return new SimpleAuthenticationInfo(user.getUserId(),user.getPassword(),getName());
        }
        else
            return null;
    }
}