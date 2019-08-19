package cn.zhoufan.zfutil.wrapper;

import javax.servlet.http.Cookie;
import java.util.Map;

/**
 * request抽象封装类
 *
 * @author zhangjun
 * @date 2018-04-20 14:53
 **/
public abstract class AbstractRequestWrapper {

    /**
     * 获取请求参数转换为String 可设置默认值
     *
     * @param paramName  参数名称
     * @param defaultStr 默认值
     * @return String
     */
    public abstract String getPara(String paramName, String defaultStr);

    /**
     * 获取请求参数转换为String
     *
     * @param paramName 参数名称
     * @return String
     */
    public abstract String getPara(String paramName);

    /**
     * 获取请求参数转换为int 可设置默认值
     *
     * @param paramName    参数名称
     * @param defaultValue 默认值
     * @return int
     */
    public abstract int getParaToInt(String paramName, int defaultValue);

    /**
     * 获取请求参数转换为int 可设置默认值
     *
     * @param paramName 参数名称
     * @return int
     */
    public abstract int getParaToInt(String paramName);

    /**
     * 获取请求参数转换为double 可设置默认值
     *
     * @param paramName    参数名称
     * @param defaultValue 默认值
     * @return double
     */
    public abstract double getParaToDouble(String paramName, double defaultValue);

    /**
     * 获取请求参数转换为double 可设置默认值
     *
     * @param paramName 参数名称
     * @return double
     */
    public abstract double getParaToDouble(String paramName);

    /**
     * 获取请求参数数组值 用于checkbox获取
     *
     * @param paramName 参数名称
     * @return String[]
     */
    public abstract String[] getParas(String paramName);

    /**
     * 获取header值
     *
     * @param name 变量名
     * @return String
     */
    public abstract String getHeader(String name);

    /**
     * 获取session中的属性值 可设置默认值
     *
     * @param attrName   属性名
     * @param defaultStr 默认值
     * @return String
     */
    public abstract String getSessAttr(String attrName, String defaultStr);

    /**
     * 获取session中的属性值
     *
     * @param attrName 属性名
     * @return String
     */
    public abstract String getSessAttr(String attrName);

    /**
     * 获取session的属性对象
     *
     * @param attrName 属性名
     * @return Object
     */
    public abstract Object getSessObject(String attrName);

    /**
     * 删除session的属性
     *
     * @param attrName 属性名
     */
    public abstract void removeSessAttr(String attrName);

    /**
     * 设置request属性
     *
     * @param name 属性名
     * @param o    属性对象
     */
    public abstract void setReqAttr(String name, Object o);

    /**
     * 获取contextPath
     *
     * @return String
     */
    public abstract String getContextPath();

    /**
     * 删除request对象属性
     *
     * @param attrName 获取属性名
     */
    public abstract void removeReqAttr(String attrName);

    /**
     * 设置session失效
     */
    public abstract void sessionInvalidate();

    /**
     * 获取request中的全部cookie
     *
     * @return Cookie[]
     */
    public abstract Cookie[] getCookies();

    /**
     * 获取session id
     *
     * @return String
     */
    public abstract String getSessionId();

    /**
     * 获取表单请求参数map
     *
     * @return Map<String   ,       String   [   ]>
     */
    public abstract Map<String, String[]> getParameterMap();


    /**
     * 设置session中的属性值
     *
     * @param attrName  属性名
     * @param attrValue 属性值
     * @return String
     */
    public abstract void setSessAttr(String attrName, Object attrValue);
}
