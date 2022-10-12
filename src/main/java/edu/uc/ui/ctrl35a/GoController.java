package edu.uc.ui.ctrl35a;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(UIConst.AREAPATH)
public class GoController extends BaseController {
    public GoController() {
        super();
    }

    @RequestMapping("/Go_ok")
    protected ModelAndView ok(HttpServletRequest request, HttpServletResponse
            response) {
        super.handleBase(request, response);
        return getMView("Go", "ok");
    }

    @RequestMapping("/Go_no")
    protected ModelAndView no(HttpServletRequest request, HttpServletResponse
            response) {
        super.handleBase(request, response);
        return getMView("Go", "no");
    }

    @RequestMapping("/Go_err")
    protected ModelAndView err(HttpServletRequest request, HttpServletResponse
            response) {
        super.handleBase(request, response);
        return getMView("Go", "err");
    }

    @RequestMapping("/Go_noright")
    protected ModelAndView noright(HttpServletRequest request,
                                   HttpServletResponse response) {
        super.handleBase(request, response);
        return getMView("Go", "noright");
    }

    @RequestMapping("/Go_preload")
    protected ModelAndView preload(HttpServletRequest request,
                                   HttpServletResponse response) {
        super.handleBase(request, response);
        return getMView("Go", "preload");
    }

    @RequestMapping("/Go_reload")
    protected ModelAndView reload(HttpServletRequest request,
                                  HttpServletResponse response) {
        super.handleBase(request, response);
        return getMView("Go", "reload");
    }

    @RequestMapping("/Go_blank")
    protected ModelAndView blank(HttpServletRequest request, HttpServletResponse
            response) {
        super.handleBase(request, response);
        return getMView("Go", "blank");
    }
}
