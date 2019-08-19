package cn.zhoufan.zfutil;

import cn.zhoufan.zfutil.wrapper.WafRequestWrapper;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * 项目名：portal
 * 包名：com.rshang.plantform.core.common.zfutil
 * 类名：
 * 描述：
 * http 封装工具类
 *
 * @author Suremotoo
 * @create 2018-08-26 13:38
 */
public class H {

    /**
     * 获取经过包装防Xss Sql注入的HttpServletRequest
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return new WafRequestWrapper(request);
    }

    /**
     * 获取原始的HttpServletResponse
     *
     * @return HttpServletResponse
     */
    public static HttpServletResponse getResponse() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        return response;
    }


    /**
     * url编码
     *
     * @param url url地址
     * @return String
     */
    public static String urlEncoder(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
//
//    public static String redirect(HttpServletRequest request, SystemConfig systemConfig, String path) {
//        return "redirect:" + ("Y".equals(systemConfig.getIsTestProd()) ? systemConfig.getBaseClusterUrl() + request.getContextPath() + path : path);
//    }

}
