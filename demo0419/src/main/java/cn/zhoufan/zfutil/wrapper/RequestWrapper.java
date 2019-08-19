package cn.zhoufan.zfutil.wrapper;


import cn.zhoufan.zfutil.H;
import cn.zhoufan.zfutil.S;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Map;

/**
 * 请求封装类
 *
 * @author zhoufan
 * @date 2018-04-20 11:34
 **/
public class RequestWrapper extends AbstractRequestWrapper {

    protected HttpServletRequest request;
    protected HttpSession session;

    public RequestWrapper(HttpServletRequest request) throws UnsupportedEncodingException {
        this.request = new WafRequestWrapper(request);
        this.session = request.getSession();
        this.request.setCharacterEncoding("UTF-8");
    }

    public void setCharacterEncoding(String enc) throws UnsupportedEncodingException {
        this.request.setCharacterEncoding(enc);
    }

    public RequestWrapper() {
        this.request = H.getRequest();
        this.session = request.getSession();
    }

    public Enumeration<String> getAttributeNames(){
        return session.getAttributeNames();
    }

    @Override
    public String getPara(String paramName, String defaultStr) {
        return S.objIsNull(request.getParameter(paramName), defaultStr);
    }

    @Override
    public String getPara(String paramName) {
        return getPara(paramName, "");
    }

    @Override
    public int getParaToInt(String paramName, int defaultValue) {
        return request.getParameter(paramName) == null ? defaultValue : Integer.valueOf(request.getParameter(paramName).toString());
    }

    @Override
    public int getParaToInt(String paramName) {
        return getParaToInt(paramName, 0);
    }

    @Override
    public double getParaToDouble(String paramName, double defaultValue) {
        return request.getParameter(paramName) == null ? defaultValue : Double.valueOf(request.getParameter(paramName).toString());
    }

    @Override
    public double getParaToDouble(String paramName) {
        return getParaToDouble(paramName, 0.0);
    }

    @Override
    public String[] getParas(String paramName) {
        return request.getParameterValues(paramName);
    }

    @Override
    public String getHeader(String name) {
        return request.getHeader(name);
    }

    @Override
    public String getSessAttr(String attrName, String defaultStr) {
        return S.objIsNull(session.getAttribute(attrName), defaultStr);
    }

    @Override
    public String getSessAttr(String attrName) {
        return getSessAttr(attrName, "");
    }

    @Override
    public Object getSessObject(String attrName) {
        return session.getAttribute(attrName);
    }

    @Override
    public void removeSessAttr(String attrName) {
        session.removeAttribute(attrName);
    }

    @Override
    public void setReqAttr(String name, Object o) {
        request.setAttribute(name, o);
    }

    @Override
    public String getContextPath() {
        return request.getContextPath();
    }

    @Override
    public void removeReqAttr(String attrName) {
        request.removeAttribute(attrName);
        session.invalidate();
    }

    @Override
    public void sessionInvalidate() {
        session.invalidate();
    }

    @Override
    public Cookie[] getCookies() {

        return request.getCookies();
    }

    @Override
    public String getSessionId() {
        return session.getId();
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return request.getParameterMap();
    }

    @Override
    public void setSessAttr(String attrName, Object attrValue) {
        session.setAttribute(attrName, attrValue);
    }

    public HttpServletRequest getRequest() {
        return this.request;
    }
}
