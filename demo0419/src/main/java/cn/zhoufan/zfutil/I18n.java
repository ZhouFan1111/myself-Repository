package cn.zhoufan.zfutil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * 项目名：portal
 * 包名：com.rshang.plantform.core.common.zfutil
 * 类名：I18n
 * 描述：
 * 国际化语言工具类
 *
 * @author Suremotoo
 * @create 2018-08-26 13:36
 */
@Component
public class I18n {

    @Autowired
    MessageSource messageSource;

    /**
     * 获取默认国际化语言信息
     *
     * @param key key名称
     * @return String
     */
    public String getMessage(String key) {
        return getMessage(key, null, LocaleContextHolder.getLocale());
    }

    /**
     * 获取默认国际化语言信息
     *
     * @param key  key名称
     * @param args 可变参数数组
     * @return String
     */
    public String getMessage(String key, Object... args) {
        return getMessage(key, args, LocaleContextHolder.getLocale());
    }

    /**
     * 获取默认国际化语言信息
     *
     * @param key  key名称
     * @param args 可变参数数组
     * @return String
     */
    public String getMessage(Object key, Object[] args) {
        return getMessage(String.valueOf(key), args, LocaleContextHolder.getLocale());
    }

    /**
     * 获取国际化语言信息
     *
     * @param key    key名称
     * @param args   参数数组
     * @param locale 语言类型
     * @return String
     */
    public String getMessage(String key, Object[] args, Locale locale) {
        return messageSource.getMessage(key, args, locale);
    }

    /**
     * 生成异常错误信息
     *
     * @param code    状态码
     * @param message 错误信息
     * @return String
     */
    public String errMessage(String code, String message) {
        return code + "-" + message;
    }

}
