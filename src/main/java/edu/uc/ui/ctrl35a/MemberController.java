package edu.uc.ui.ctrl35a;

import com.liuvei.common.PagerItem;
import com.liuvei.common.SysFun;
import edu.uc.bean.Member;
import edu.uc.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author night
 * @date 2022/11/2 16:12
 */
@Controller
@RequestMapping(UIConst.AREAPATH)
public class MemberController extends CrudController {
    @Resource
    private MemberService memberService;

    public MemberController() {
        super();
    }

    /**
     * 各方法的路径的Bean的前缀
     */
    public final static String BEAN_PREFIX = "/Member_";

    @Override
    @RequestMapping(BEAN_PREFIX + "list")
    protected ModelAndView listView(HttpServletRequest request,
                                    HttpServletResponse response) {
        handleBase(request, response);
        ModelAndView mView = getMView("Member", "list");
        List<Member> vDataList;
// ---------------------------------------------------------------------

// 分页步骤1. 创建PagerIter对象, 处理url传过来的pageSize和pageIndex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
// 分页步骤2.1. 定义记录数变量
        Long rowCount;
// 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其它

        rowCount = memberService.count();
// 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
        pagerItem.changeRowCount(rowCount);
// 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
        vDataList = memberService.pager(pagerItem.getPageNum(),
                pagerItem.getPageSize());
// 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(),
                request.getQueryString()));
// 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);
// ---------------------------------------------------------------------
// 转发到列表页面
        mView.setViewName(getDispatcherPath("Member", "list"));
        return mView;
    }

    @Override
    @RequestMapping(BEAN_PREFIX + "listDeal")
    protected ModelAndView listDeal(HttpServletRequest request,
                                    HttpServletResponse response) {
        ModelAndView modelAndView = getMView("Member", "list");
        String name = request.getParameter("userName");
        request.setAttribute("userName", name);
        if (!SysFun.isNullOrEmpty(name)) {
            name = name.trim();
        } else {
            name = "";
        }
        List<Member> vDataList;

        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
        Long rowCount;
        if (!SysFun.isNullOrEmpty(name)) {
            rowCount = memberService.countByName(name);
            pagerItem.changeRowCount(rowCount);
            vDataList = memberService.pagerByName(name, pagerItem.getPageNum(), pagerItem.getPageSize());
        } else {
            rowCount = memberService.count();
            pagerItem.changeRowCount(rowCount);
            vDataList = memberService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
        }

        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);

        modelAndView.setViewName(getDispatcherPath("Member", "list"));
        return modelAndView;

    }

    @Override
    @RequestMapping(BEAN_PREFIX + "insert")
    protected ModelAndView insertView(HttpServletRequest request,
                                      HttpServletResponse response) {
        handleBase(request,response);
        return  getMView("Member","insert");
    }

    @Override
    @RequestMapping(BEAN_PREFIX + "insertDeal")
    protected ModelAndView insertDeal(HttpServletRequest request,
                                      HttpServletResponse response) {
        handleBase(request, response);
        ModelAndView modelAndView = getMView("Member", "insert");
        String userName = request.getParameter("userName");
        String userPass = request.getParameter("userPass");
        String userPass2 = request.getParameter("userPass2");
        String nickName = request.getParameter("nickName");
        String email = request.getParameter("email");
//为了在输入页面回显原来的旧值,需要将旧值放到作用域,页面中进行获取request.setAttribute ( s: "userName" , userName) ;
        request.setAttribute("userPass", userPass);
        request.setAttribute("userPass2", userPass2);
        request.setAttribute("nickName", nickName);
        request.setAttribute("email", email);
//(1)服务端验证
        String vMsg = "";
        if (SysFun.isNullOrEmpty(userName)) {
            vMsg += "账号不能为空。";
        }

        if (SysFun.isNullOrEmpty(userPass)) {
            vMsg += "密码不能为空。";
        }

        if (SysFun.isNullOrEmpty(userPass2)) {
            vMsg += "密码不能为空。";
        }
        if (SysFun.isNullOrEmpty(nickName)) {
            vMsg += "昵称不能为空。";
        }
        if (SysFun.isNullOrEmpty(email)) {
            vMsg += "邮箱不能为空。";
        }
        Member member = memberService.loadByName(userName);
        if (member != null) {
            vMsg += "该用户已存在。";
        }
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            return modelAndView;

        }

        Member bean = new Member();
        bean.setUserName(userName);
        bean.setUserPass(userPass2);
        bean.setNickName(nickName);
        bean.setEmail(email);
        Long result = 0L;
        try {
            result = memberService.insert(bean);
        } catch (Exception e) {
            vMsg += "添加失败，原因："+e.getMessage();
        }
        if (result > 0){
            modelAndView.setViewName(getDispatcherPath("Go","preload"));
        }else {
            request.setAttribute("msg",vMsg);
        }
        return  modelAndView;

    }
    @Override
    protected ModelAndView updateView(HttpServletRequest request,
                                      HttpServletResponse response) {
        return null;
    }

    @Override
    protected ModelAndView updateDeal(HttpServletRequest request,
                                      HttpServletResponse response) {
        return null;
    }

    @Override
    protected ModelAndView detailView(HttpServletRequest request,
                                      HttpServletResponse response) {
        return null;
    }

    @Override
    protected ModelAndView detailDeal(HttpServletRequest request,
                                      HttpServletResponse response) {
        return null;
    }

    @Override
    protected ModelAndView deleteView(HttpServletRequest request,
                                      HttpServletResponse response) {
        return null;
    }

    @Override
    protected ModelAndView deleteDeal(HttpServletRequest request,
                                      HttpServletResponse response) {
        return null;
    }
}
