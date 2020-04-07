package cn.cqu.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/*
重定义shiro successUrl
* */
public class LoginFormAuthenticationFilter extends FormAuthenticationFilter {
    //将原始的successUrl写死
    private final String SUCCESSURL = "/home";

    //重写[登录成功重定向地址]方法
    public void issueSuccessRedirect(ServletRequest request, ServletResponse response)throws Exception {

        WebUtils.issueRedirect(request, response, SUCCESSURL, null, true);

    }
}
