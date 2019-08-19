package cn.zhoufan.zfutil.wrapper;


import cn.zhoufan.zfutil.Waf;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 项目名：portal
 * 包名：com.rshang.plantform.core.common.wrapper
 * 类名：WafRequestWrapper
 * 描述：
 * request请求过滤封装
 *
 * @author Suremotoo
 * @create 2018-08-26 14:10
 */
public class WafRequestWrapper extends HttpServletRequestWrapper {

    private boolean filterXSS = true;

    private boolean filterSQL = true;


    public WafRequestWrapper(HttpServletRequest request, boolean filterXSS, boolean filterSQL) {
        super(request);
        this.filterXSS = filterXSS;
        this.filterSQL = filterSQL;
    }


    @Override
    public void setCharacterEncoding(String enc) throws UnsupportedEncodingException {
        super.setCharacterEncoding(enc);
    }

    public WafRequestWrapper(HttpServletRequest request) {
        this(request, true, true);
    }


    /**
     * @param parameter 过滤参数
     * @return
     * @Description 数组参数过滤
     */
    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }

        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = filterParamString(values[i]);
        }

        return encodedValues;
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Map getParameterMap() {
        Map<String, String[]> primary = super.getParameterMap();
        Map<String, String[]> result = new HashMap<String, String[]>(primary.size());
        for (Map.Entry<String, String[]> entry : primary.entrySet()) {
            result.put(entry.getKey(), filterEntryString(entry.getValue()));
        }
        return result;

    }

    protected String[] filterEntryString(String[] rawValue) {
        for (int i = 0; i < rawValue.length; i++) {
            rawValue[i] = filterParamString(rawValue[i]);
        }
        return rawValue;
    }

    /**
     * @param parameter 过滤参数
     * @return
     * @Description 参数过滤
     */
    @Override
    public String getParameter(String parameter) {
        return filterParamString(super.getParameter(parameter));
    }


    /**
     * @param name 过滤内容
     * @return
     * @Description 请求头过滤
     */
    @Override
    public String getHeader(String name) {
        return filterParamString(super.getHeader(name));
    }


    /**
     * @return
     * @Description Cookie内容过滤
     */
    @Override
    public Cookie[] getCookies() {
        Cookie[] existingCookies = super.getCookies();
        if (existingCookies != null) {
            for (int i = 0; i < existingCookies.length; ++i) {
                Cookie cookie = existingCookies[i];
                cookie.setValue(filterParamString(cookie.getValue()));
            }
        }
        return existingCookies;
    }

    /**
     * @param rawValue 待处理内容
     * @return
     * @Description 过滤字符串内容
     */
    protected String filterParamString(String rawValue) {
        if (null == rawValue) {
            return null;
        }
        String tmpStr = rawValue;
        if (this.filterXSS) {
            tmpStr = Waf.stripXSS(rawValue);
        }
        if (this.filterSQL) {
            tmpStr = Waf.stripSqlInjection(tmpStr);
        }
        return tmpStr;
    }

}
