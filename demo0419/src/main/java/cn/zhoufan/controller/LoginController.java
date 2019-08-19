package cn.zhoufan.controller;

import cn.zhoufan.zfutil.wrapper.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author zhoufan
 * @Date 2019/6/3 13:54
 * @Version 1.0
 **/

@Controller
@RequestMapping("/login")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping("/login")
    public String login(RequestWrapper requestWrapper){
        requestWrapper.setReqAttr("username","周帆");
        requestWrapper.setReqAttr("zfTest","周帆测试");
        return "login";
    }


}
