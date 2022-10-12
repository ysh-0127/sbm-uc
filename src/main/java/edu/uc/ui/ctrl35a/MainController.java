package edu.uc.ui.ctrl35a;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(UIConst.AREAPATH)
public class MainController extends BaseController {

    public MainController() {
        super();
    }

    /**
     * 显示主页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/Main")
    protected ModelAndView mainView(HttpServletRequest
                                            request, HttpServletResponse response) {
        handleBase(request, response);
        ModelAndView mView = getMView("Main");
        //检测是否登录
//        if (!checkLogin(request, response)) {
//            String toURL = getRedirectLogin();
//            mView.setViewName(toURL);
//        }

        return mView;

    }

    /**
     * 显示欢迎页
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/Main_welcome")
    protected ModelAndView welcomeView(HttpServletRequest
                                               request, HttpServletResponse response) {
        handleBase(request, response);
        ModelAndView mView = getMView("Welcome");
//            检测是否登录
//            if(!checkLogin(request, response)) {
//                String toURL = getRedirectLogin();
//                mView.setViewName(toURL);
//            }

        return mView;
    }
}
