package cn.zhoufan.controller;

import cn.zhoufan.configtest.TestProperties;
import cn.zhoufan.entity.User;
import cn.zhoufan.mapper.UserMapper;
import cn.zhoufan.service.UserService;
import cn.zhoufan.zfutil.S;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author Administrator
 * @Date 2019/4/19 15:17
 * @Version 1.0
 **/
@Controller
@RequestMapping("/test")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TestProperties testProperties;

    @ResponseBody
    @RequestMapping("/testProperties")
    public TestProperties testProperties(){
        System.out.println(testProperties.toString());
        return testProperties;
    }













    @ResponseBody
    @RequestMapping("/index")
    public String index(){
       int a = userService.insertTagProperty(S.uuid(),"1","11010200","11","人口属性","01","身份属性","02","籍贯","","","3");
       if(a>0){
           return "添加成功";
       }else {
           return "添加失败";
       }
    }


    @ResponseBody
    @RequestMapping("/zhoufan")
    public String zhoufan(){
        String strs = "|（13）金融属性|（01）理财属性|（02）客户等级，\n" +
                "|（13）金融属性|（01）理财属性|（02）客户等级|（01）一标记，\n" +
                "|（13）金融属性|（01）理财属性|（02）客户等级|（02）二标记，\n" +
                "|（13）金融属性|（01）理财属性|（02）客户等级|（03）三标记，\n" +
                "|（13）金融属性|（01）理财属性|（02）客户等级|（04）四标记，\n" +
                "|（13）金融属性|（01）理财属性|（02）客户等级|（05）五标记，\n" +
                "|（13）金融属性|（01）理财属性|（03）风险等级，\n" +
                "|（13）金融属性|（01）理财属性|（03）风险等级|（01）R一标记谨慎型，\n" +
                "|（13）金融属性|（01）理财属性|（03）风险等级|（02）R二标记稳健型，\n" +
                "|（13）金融属性|（01）理财属性|（03）风险等级|（03）R三标记平衡型，\n" +
                "|（13）金融属性|（01）理财属性|（03）风险等级|（04）R四标记进取型，\n" +
                "|（13）金融属性|（01）理财属性|（03）风险等级|（05）R五标记激进型，\n" +
                "|（13）金融属性|（01）理财属性|（04）黑名单，\n" +
                "|（13）金融属性|（01）理财属性|（04）黑名单|（01）最高法失信";
        String[] mutls = strs.split("，");
        System.out.println(mutls.length);
        int count = 0;
        for (int i = 0; i < mutls.length; i++) {
            Map<String, String> map = strUtil(mutls[i]);
            int result = userService.insertTagProperty(map.get("uuid"),map.get("type"),map.get("full_code"),map.get("level1_Code"),map.get("level1_Name"),map.get("level2_Code"),map.get("level2_Name"),map.get("level3_Code"),map.get("level3_Name"),map.get("level4_Code"),map.get("level4_Name"),map.get("level"));
            if(result>0) count++;
        }
        return "执行完成,一共"+mutls.length+"条，成功了"+count+"条";
    }



    @ResponseBody
    @RequestMapping("/second")
    public String second(){
        String strs = "美食|海鲜餐厅," +
                "美食|西餐厅," +
                "美食|中餐厅," +
                "购物|珠宝首饰店/手表店," +
                "休闲娱乐|咖啡厅," +
                "休闲娱乐|夜总会," +
                "丽人|美容院," +
                "酒店|星级酒店," +
                "酒店|度假酒店," +
                "车辆|4S店/汽车销售," +
                "车辆|汽车改装/汽配维修," +
                "车辆|汽车美容/汽车装饰," +
                "生活服务|家政公司/保洁清洗," +
                "商务服务|公司注册/工商代理," +
                "商务服务|商标注册," +
                "商务服务|庆典公司/演出会展," +
                "医疗服务|整形医院/整形美容,";
        String[] mutls = strs.split(",");
        System.out.println(mutls.length);
        int count = 0;
        for (int i = 0; i < mutls.length; i++) {
            String[] strss = mutls[i].split("\\|");
            List<String> list = returnResult(strss);
            if(list.get(1)==null) continue;
            int result = userMapper.insertMerchantTagsRelation(S.uuid(), list.get(0), list.get(1), "6AEC8253238F4A37A6E023B1594D440A");
            if(result>0) count++;
        }
        return "执行完成,一共"+mutls.length+"条，成功了"+count+"条";
    }


/*
    *
     * @Description 传入几个数，就返回几个通过表查出来的list
     * @Author zhoufan
     * @Date 2019/4/22 11:26
     * @Param [strs]
     * @Return java.util.List<java.lang.String>
     */

    public List<String> returnResult(String[] strs){
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < strs.length; i++) {
            String name = userMapper.findName(strs[i].replaceAll("\\s*", ""));
            list.add(name);
        }
        return list;
    }




    @RequestMapping("/register")
    public String register(){
        return "register";
    }


    @RequestMapping("/create")
    public String create(HttpServletRequest request){
        request.setAttribute("name","周帆");
        System.err.println(userService.getUser());
        User user = userService.getUser();
        int x=userService.insertUser(user.getName(), user.getPassword(), user.getAge());
        if(x>0){

            logger.info("创建对象完成");
        }
        return "index";
    }

    @RequestMapping("/insertUser")
    @ResponseBody
    public User insertUser(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("userName");
        String password = request.getParameter("password");
        int age = Integer.valueOf(request.getParameter("age"));
        int x=userService.insertUser(name,password,age);

        if(x>0){

            logger.info("注册对象完成");
        }
        User user = new User(name,password,age);
        return user;
    }

    @RequestMapping("/findUser")
    public String findUser(){
        return "findUser";
    }

    @ResponseBody
    @RequestMapping("/findUserByName")
    public User findUserByName(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("userName");
        User user = userService.findUserByName(name);
        return user;
    }


    public Map<String, String> strUtil(String str){
        String[] strs = str.split("\\|");
        String uuid = S.uuid();
        String type = "";
        String full_code = returnAightChar(findNumber(str));      //returnAightChar(findNumber(str))   返回8个数字
        String level1_Code = "";    //
        String level1_Name = "";    //
        String level2_Code = "";    //
        String level2_Name = "";    //
        String level3_Code = "";    //
        String level3_Name = "";    //
        String level4_Code = "";    //
        String level4_Name = "";    //
        int level = strs.length-1 ;          //str.split("\\|").length-1             几级标签
        if(level>=1){
            level1_Code = findNumber(strs[1]);
            level1_Name = ClearBracket(strs[1]);
        }
        if(level>=2){
            level2_Code = findNumber(strs[2]);
            level2_Name = ClearBracket(strs[2]);
        }
        if(level>=3){
            level3_Code = findNumber(strs[3]);
            level3_Name = ClearBracket(strs[3]);
        }
        if(level>=4){
            level4_Code = findNumber(strs[4]);
            level4_Name = ClearBracket(strs[4]);
        }
        Map<String,String> map = new HashMap<String,String>();
        map.put("uuid",uuid);
        map.put("type",type);
        map.put("full_code",full_code);
        map.put("level1_Code",level1_Code);
        map.put("level1_Name",level1_Name);
        map.put("level2_Code",level2_Code);
        map.put("level2_Name",level2_Name);
        map.put("level3_Code",level3_Code);
        map.put("level3_Name",level3_Name);
        map.put("level4_Code",level4_Code);
        map.put("level4_Name",level4_Name);
        map.put("level",String.valueOf(level));

        return map;
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
    private String ClearBracket(String context) {
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
