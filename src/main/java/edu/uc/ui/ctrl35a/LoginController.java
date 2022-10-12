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
    protected ModelAndView loginView(HttpServletRequest request, HttpServletResponse response) {
        handleBase(request, response);
        ModelAndView mView = getMView("Login");
//保存进入登录页面前的访问页面
        mView.addObject("redirectUrl", request.getParameter("redirectUrl"));
        return mView;
    }

    @RequestMapping("/Login_loginDeal")
    protected ModelAndView loginDeal(HttpServletRequest request, HttpServletResponse response) {
        handleBase(request, response);
        ModelAndView mView = getMView("Login");
        HttpSession session = request.getSession();

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
            vMsg += "账号不能为空";
        }
        if (SysFun.isNullOrEmpty(userPass)) {
            vMsg += "密码不能为空";
        }
        if (SysFun.isNullOrEmpty(validateCode)) {
            vMsg += "验证码不能为空";
        }
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            mView.setViewName(getDispatcherPath("Login"));
            return mView;
        }
        if (!validateCode.equals(session.getAttribute(UIConst.BG_VALIDATE_CODE_KEY))) {
            vMsg += "验证码不正确";
        }
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            return mView;
        }

        Member bean = memberService.loadByName(userName);
        if (bean == null) {
            request.setAttribute("msg", "账号不存在");
            System.out.println(vMsg);
            mView.setViewName(getDispatcherPath("Login"));
            return mView;
        }
        if (!bean.getUserPass().equals(SysFun.md5(userPass))) {
            request.setAttribute("msg", "密码错误");
            System.out.println(vMsg);
            mView.setViewName(getDispatcherPath("Login"));
            return mView;

        }
        System.out.println("登录成功");
        request.getSession().setAttribute(UIConst.BG_LOGINUSER_KEY, bean);
        if (bean.getUserName().equalsIgnoreCase("admin")) {
            request.getSession().setAttribute(UIConst.BG_ISADMIN_KEY, true);
        }
        if (!SysFun.isNullOrEmpty(redirectUrl)) {
            mView.setViewName("redirect" + redirectUrl);
            return mView;
        }
        mView.setViewName(getDispatcherPath("Main"));
        return mView;
    }

    /**
     * 注销处理
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/Login_logoutDeal")
    protected ModelAndView logoutDeal(HttpServletRequest
                                              request, HttpServletResponse response) {
        handleBase(request, response);
        ModelAndView mView = getMView("Login");
//清空所有的会话数据
        request.getSession().invalidate();
//跳转到登录页面
        mView.setViewName(getRedirectLogin());
        return mView;
    }

}
