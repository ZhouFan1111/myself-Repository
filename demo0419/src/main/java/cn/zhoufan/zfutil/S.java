package cn.zhoufan.zfutil;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
*  
 * @Description 
 * @Author zhoufan
 * @Date 2019/4/20 16:45
 * @Param   
 * @Return   
 */ 
public class S extends StrUtil {
    /**
     * uuid生成方法
     *
     * @return String
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }


    /**
     * 对象判断是否为空 为空设置默认字符串 不为空转换对象为字符
     *
     * @param obj        Object对象
     * @param defaultStr 默认字符串
     * @return String
     */
    public static String objIsNull(Object obj, String defaultStr) {
        if (obj == null) {
            return defaultStr;
        }
        return String.valueOf(obj);
    }

    /**
     * 对象判断是否为空 为空设置"" 不为空转换对象为字符
     *
     * @param obj Object对象
     * @return String
     */
    public static String objIsNull(Object obj) {
        return objIsNull(obj, EMPTY);
    }

    /**
     * 字符串加密
     */
    public static String login_encrypt(String id_temp, int flag) {
        String zff = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ=&";
        String zff1 = "0123456789abcdefghmnopqrstuxzBCDEFGHNQTUVZ=&";
        char mmid;
        byte zf, j;
        String id = id_temp;
        if (id.equals("") || id == null) {
            return id;
        }
        long i, ii, m, mm, mm1, cs;
        cs = 0;
        byte temp[], temp1[], temp0[];
        try {
            temp = id.getBytes("GBK");
        } catch (Exception e) {
            id = null;
            return id;
        }
        ii = temp.length;
        if (ii < 1) {
            return id;
        }
        temp1 = new byte[(int) (ii + 1)];//返回加密字符串
        temp0 = new byte[(int) (ii - 1)];//返回解密字符串
        if (flag == 1) {
            mm1 = (long) (Math.random() * 43);
//			mmid=zff.charAt((int)(mm-1));  修改前
            mmid = zff1.charAt((int) mm1);
            mm = (long) zff.indexOf(mmid);
            mm++; //修改后新添加的
            temp1[0] = (byte) mmid;
        } else {
//	        mm=zff.indexOf((char)temp[0],1)+1;  修改前
            mm = zff.indexOf((char) temp[0]) + 1;
//	        id=mid(id,2,id.length());
            for (int p = 0; p < temp.length - 1; p++) {
                temp[p] = temp[p + 1];
                temp[p + 1] = 0;
            }
            ii = ii - 1;
        }
        for (i = 0; i < ii; i++) {
            zf = temp[(int) i];
            j = zf;
            if (j < 0) {
                zf = (byte) (zf + 128);
            }
            if (j > 127) {
                zf = (byte) (zf - 128);
            }
//	        m=zff.indexOf((char)zf,1);  修改前
            m = zff.indexOf((char) zf);
            mm = mm * 127 + 13 + cs;
            while (mm > 62) {
                mm = mm - 63;
            }
            if (m >= 0 && m < 64) {
                m = f_add_mode2(m, mm, 8);
                zf = (byte) zff.charAt((int) m);
            }
            if (j < 0) {
//              zf=(char)((int)zf+128);
//				zf=(byte)(-zf);
                zf = (byte) (zf + 128);
            }
            if (j > 127) {
                zf = (byte) (zf + 128);
            }
            temp[(int) i] = zf;
//	         mmid=mmid+zf;
        }
        if (flag == 1) {
            for (int p = 0; p < temp.length; p++) {
                temp1[p + 1] = temp[p];
            }
            return (new String(temp1));
        } else {
            for (int p = 0; p < temp.length - 1; p++) {
                temp0[p] = temp[p];
            }
            return (new String(temp0));
        }
    }

    //整数1对整数2取模
    private static int mod(int int1, int int2) {
        if (int1 < int2) {
            return int1;
        }
        while (int1 >= int2) {
            int1 = int1 - int2;
        }
        return int1;
    }

    //两个长整型变量之间的逻辑加
    private static long f_add_mode2(long id1, long id2, long cd) {
        long i, ii, i1, i2, k, kk;
        i1 = id1;
        i2 = id2;
        k = 0;
        kk = 1;
        for (i = 1; i <= cd; i++) {
            ii = i1 - (int) (i1 / 2) * 2 + i2 - (int) (i2 / 2) * 2;
            i1 = i1 / 2;
            i2 = i2 / 2;
            if (ii == 1) {
                k = k + kk;
            }
            kk = kk * 2;
        }
        return k;
    }

    /*
    *  
     * @Description 返回固定的随机的数字字符串
     * @Author zhoufan
     * @Date 2019/4/20 16:49
     * @Param [len]  
     * @Return java.lang.String  
     */ 
    public static String randomStr(int len){
        return RandomUtil.randomString("0123456789",len);
    }
    /*
    *  
     * @Description 对字符串进行加密
     * @Author zhoufan
     * @Date 2019/4/20 16:46
     * @Param [url]  
     * @Return java.lang.String  
     */ 
    public static String encodeurl(String url)
    {

        String checkcode = Integer.toString(generateCheckCode(url));
        String url_check = url+"|"+checkcode;
        String encode_url= login_encrypt(url_check,1);
        return encode_url;

    }
    //返回字符串的校验码
    public static int generateCheckCode (String s)
    {
        int checkcode=0;
        byte[] codes=s.getBytes();
        for (int i=0;i<codes.length ;i++ )
        {
            if (codes[i]<0)//如果字符中汉字的话，就需要与PB转化的规则相一致，即JAVA代码＋256
                checkcode=checkcode+(codes[i]+256)*(i+1);
            else
                checkcode=checkcode+codes[i]*(i+1);
        }
        checkcode=mod(checkcode,10000000);
        return checkcode;
    }


    /**
     * 高亮显示匹配成功的结果
     * @param origin
     * @param text
     * @return
     */
    public static String matchText(String origin, String text) {
        return  origin.replaceAll(text,"<hl>" + text + "</hl>");
    }




    /*
     *
     * @Description 传入一个字符串可以去除其中的数字
     * @Author zhoufan
     * @Date 2019/4/20 15:28
     * @Param [str]
     * @Return java.lang.String
     */
    public static String findNumber(String str){
        StringBuilder stringBuilder = new StringBuilder();
        Pattern p = Pattern.compile("\\d{1,}");
        Matcher m = p.matcher(str);
        while(m.find()) {
            stringBuilder.append(m.group());
        }
        return stringBuilder.toString();
    }

    /*
     *
     * @Description 传入一个字符，不足八位就用0补全
     * @Author zhoufan
     * @Date 2019/4/20 15:29
     * @Param []
     * @Return java.lang.String
     */
    public static String returnAightChar(String str){
        int length = str.length();
        for (int i = 0; i <8-length ; i++) {
            str = str + "0";
        }
        return str;
    }


    /*
     *
     * @Description 去除全部括号以及括号里面的值
     * @Author zhoufan
     * @Date 2019/4/20 16:00
     * @Param [context]
     * @Return java.lang.String
     */
    public static String ClearBracket(String context) {
//        String bracket = context.substring(context.indexOf("（"), context.indexOf("）") + 1);
//        context = context.replace(bracket, "");
//
//        context.substring(context.lastIndexOf())
//
//        return context;

        // 修改原来的逻辑，防止右括号出现在左括号前面的位置
        int head = context.indexOf('（'); // 标记第一个使用左括号的位置
        if (head == -1) {
            return context; // 如果context中不存在括号，什么也不做，直接跑到函数底端返回初值str
        } else {
            int next = head + 1; // 从head+1起检查每个字符
            int count = 1; // 记录括号情况
            do {
                if (context.charAt(next) == '（') {
                    count++;
                } else if (context.charAt(next) == '）') {
                    count--;
                }
                next++; // 更新即将读取的下一个字符的位置
                if (count == 0) { // 已经找到匹配的括号
                    String temp = context.substring(head, next);
                    context = context.replace(temp, ""); // 用空内容替换，复制给context
                    head = context.indexOf('（'); // 找寻下一个左括号
                    next = head + 1; // 标记下一个左括号后的字符位置
                    count = 1; // count的值还原成1
                }
            } while (head != -1); // 如果在该段落中找不到左括号了，就终止循环
        }
        return context; // 返回更新后的context
    }


}
