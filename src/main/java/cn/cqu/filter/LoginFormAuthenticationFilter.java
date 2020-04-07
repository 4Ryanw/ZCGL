package cn.cqu.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*
重定义Shiro successUrl
* */
public class LoginFormAuthenticationFilter extends FormAuthenticationFilter {

    //重写[登录成功重定向地址]方法
    public void issueSuccessRedirect(ServletRequest request, ServletResponse response)throws Exception {

        //将原始的successUrl写死
        String SUCCESSURL = "/home";
        WebUtils.issueRedirect(request, response, SUCCESSURL, null, true);

    }
}
