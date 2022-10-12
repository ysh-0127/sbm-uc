package edu.uc.ui.ctrl35a;

import com.liuvei.common.SysFun;
import edu.uc.bean.Member;
import edu.uc.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录控制器
 */
@Controller
@RequestMapping(UIConst.AREAPATH)
public class LoginController extends BaseController {
    @Autowired
    private MemberService memberService;

    /**
     * 登录页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/Login")
    protected ModelAndView loginView(HttpServletRequest
                                             request, HttpServletResponse response) {

        handleBase(request, response);
        ModelAndView mView = getMView("Login");
//保存进入登录页面前的访问页面
        mView.addObject("redirectUrl", request.getParameter("redirectUrl"));
        return mView;
    }

    @RequestMapping("/Login loginDeal")
    protected ModelAndView loginDeal(HttpServletRequest request, HttpServletResponse response) {
        handleBase(request, response);
        ModelAndView mView = getMView("Login");
        HttpSession session = request.getSession();
//如果有登录前的页面。则登录成功后，要回到该页面
        String redirectUrl = request.getParameter("redirectUrl");
        if (!SysFun.isNullOrEmpty(redirectUrl)) {
            try {
                redirectUrl = java.net.URLDecoder.decode(redirectUrl, "UTF-8");
                request.setAttribute("redirectUrl", redirectUrl);
            } catch (java.io.UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        String userName = request.getParameter("userName");
        String userPass = request.getParameter("userPass");
        String validateCode = request.getParameter("validateCode");
        request.setAttribute("userName", userName);
        String vMsg = "";
        if (SysFun.isNullOrEmpty(userName)) {
            vMsg += "账号不能为空。";
        }
        if (SysFun.isNullOrEmpty(userPass)) {
            vMsg += "密码不能为空。";
        }
        if (SysFun.isNullOrEmpty(validateCode)) {
            vMsg += "验证码不能为空。";
        }
//如果验证失败,则将失败内容放到作用域变量，并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            mView.setViewName(getDispatcherPath("Login"));
            return mView;
        }
        if (!validateCode.equals(session.getAttribute(UIConst.BG_VALIDATE_CODE_KEY))) {
            vMsg += "验证码不正确。";
        }
//如果验证失败。则将失败内容放到作用域变量，并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            return mView;
        }
        Member bean = memberService.loadByName(userName);
        if (bean == null) {
            request.setAttribute("msg", "账号不存在。");
            System.out.println(vMsg);
            mView.setViewName(getDispatcherPath("Login"));
            return mView;
        }
        if (!bean.getUserPass().equals(userPass)) {
            request.setAttribute("msg", "密码错误。");
            System.out.println(vMsg);
            mView.setViewName(getDispatcherPath("Login"));
            return mView;
        }
        System.out.println("登录成功。");
//登录成功后，将当前登录用户放到session。
//其他页面,根据session是否存在来判断是否已经登录。
        request.getSession().setAttribute(UIConst.BG_LOGINUSER_KEY, bean);
        //登录成功后。就将是否管理员的标识保存在session
        if (bean.getUserName().equalsIgnoreCase("admin")) {
            request.getSession().setAttribute(UIConst.BG_ISADMIN_KEY, true);
        }
//登录成功后．如果跳转页面不为空，则重定向到跳转页面
        if (!SysFun.isNullOrEmpty(redirectUrl)) {
            mView.setViewName("redirect: " + redirectUrl);
            return mView;
        }
//之后，转发到main
//mView.setViewName (getDispatcherPath ( 'Main");//相当于请求转发怨，url不会变更
        mView.setViewName(getRedirectPath("Main"));//相当于响应重定向，url会变更
        return mView;
    }

}

