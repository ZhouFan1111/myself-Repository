package cn.zhoufan.zfutil;

import java.util.regex.Pattern;

/**
 * 项目名：portal
 * 包名：com.rshang.plantform.core.common.zfutil
 * 类名：
 * 描述：
 * web防火墙工具类 参照gus项目
 *
 * @author Suremotoo
 * @create 2018-08-26 13:38
 */
public class Waf {

    private static final String SCRIPT_PATTERN_ALL = "<script>(.*?)</script>";
    private static final String SCRIPT_PATTERN_SRC = "src[\r\n]*=[\r\n]*\\\"(.*?)\\\"";
    private static final String SCRIPT_PATTERN_END = "</script>";
    private static final String SCRIPT_PATTERN_BEGIN = "<script(.*?)>";
    private static final String SCRIPT_PATTERN_EVAL = "eval\\((.*?)\\)";
    private static final String SCRIPT_PATTERN_EXPRESSION = "expression\\((.*?)\\)";
    private static final String SCRIPT_PATTERN_JAVASCRIPT = "javascript:";
    private static final String SCRIPT_PATTERN_VBSCRIPT = "vbscript:";
    private static final String SCRIPT_PATTERN_LOAD = "onload(.*?)=";
    private static final String SQL_PATTERN = "('.+--)|(--)|(%7C)";

    /**
     * @param value 待处理内容
     * @return
     * @Description 过滤XSS脚本内容
     */
    public static String stripXSS(String value) {
        String rlt = null;

        if (null != value) {
            // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
            // avoid encoded attacks.
            // value = ESAPI.encoder().canonicalize(value);

            // Avoid null characters
            rlt = value.replaceAll("", "");

            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile(SCRIPT_PATTERN_ALL, Pattern.CASE_INSENSITIVE);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            // Avoid anything in a src='...' type of expression

            scriptPattern = Pattern.compile(SCRIPT_PATTERN_SRC, Pattern.CASE_INSENSITIVE
                    | Pattern.MULTILINE | Pattern.DOTALL);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile(SCRIPT_PATTERN_END, Pattern.CASE_INSENSITIVE);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile(SCRIPT_PATTERN_BEGIN, Pattern.CASE_INSENSITIVE
                    | Pattern.MULTILINE | Pattern.DOTALL);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            // Avoid eval(...) expressions
            scriptPattern = Pattern.compile(SCRIPT_PATTERN_EVAL, Pattern.CASE_INSENSITIVE
                    | Pattern.MULTILINE | Pattern.DOTALL);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            // Avoid expression(...) expressions
            scriptPattern = Pattern.compile(SCRIPT_PATTERN_EXPRESSION, Pattern.CASE_INSENSITIVE
                    | Pattern.MULTILINE | Pattern.DOTALL);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            // Avoid javascript:... expressions
            scriptPattern = Pattern.compile(SCRIPT_PATTERN_JAVASCRIPT, Pattern.CASE_INSENSITIVE);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            // Avoid vbscript:... expressions
            scriptPattern = Pattern.compile(SCRIPT_PATTERN_VBSCRIPT, Pattern.CASE_INSENSITIVE);
            rlt = scriptPattern.matcher(rlt).replaceAll("");

            // Avoid onload= expressions
            scriptPattern = Pattern.compile(SCRIPT_PATTERN_LOAD, Pattern.CASE_INSENSITIVE
                    | Pattern.MULTILINE | Pattern.DOTALL);
            rlt = scriptPattern.matcher(rlt).replaceAll("");
        }

        return rlt;
    }

    /**
     * @param value 待处理内容
     * @return
     * @Description 过滤SQL注入内容
     */
    public static String stripSqlInjection(String value) {
        return (null == value) ? null : value.replaceAll(SQL_PATTERN, "");
    }

    /**
     * @param value 待处理内容
     * @return
     * @Description 过滤SQL/XSS注入内容
     */
    public static String stripSqlXSS(String value) {
        return stripXSS(stripSqlInjection(value));
    }
}
