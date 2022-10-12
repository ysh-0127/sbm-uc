package edu.uc.ui.ctrl35a;

import com.liuvei.common.RandFun;
import com.liuvei.common.ValidateCodeFun;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.*;
import javax.servlet.http.*;

@Controller
@RequestMapping((UIConst.AREAPATH))
public class ValidateCodeController extends BaseController {
    @GetMapping("ValidateCode")
    protected ModelAndView ValidateCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mView = super.handleBase(request,response);
        HttpSession session = request.getSession();



        String strCode = RandFun.rand4Num().toString();
        session.setAttribute(UIConst.BG_VALIDATE_CODE_KEY,strCode);
        java.awt.image.BufferedImage image = ValidateCodeFun.generalImage(strCode);

        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",0);
        response.setContentType("image/jpeg");

        ServletOutputStream outStream= response.getOutputStream();
        javax.imageio.ImageIO.write(image,"jpeg",outStream);
        outStream.close();
        response.flushBuffer();
        return null;
    }


}
