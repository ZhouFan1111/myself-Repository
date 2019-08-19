package cn.zhoufan.controller;

import cn.zhoufan.entity.User;
import cn.zhoufan.mapper.UserMapper;
import cn.zhoufan.service.UserService;
import cn.zhoufan.zfutil.S;
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
import java.util.logging.Logger;
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
@RequestMapping("/third")
public class ThirdController {

    private final Logger logger = (Logger) Logger.getLogger(String.valueOf(ThirdController.class));

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    protected final static List<String> list = new ArrayList<String>();



    @ResponseBody
    @RequestMapping("/one")
    public String one(){
        String strs = "|（12）行为属性|（02）消费属性|（06）商户偏好| （001） 其他旅游出行，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （002） 健身房，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （003） 茶馆，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （004） 婚纱礼服，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （005） 桑拿洗浴中心，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （006） 旅馆客栈，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （007） 夜总会，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （008） 其他休闲会所，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （009） 中餐厅，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （010） 旅行社/旅游团，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （011） 茶叶酒食品店，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （012） 电脑培训，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （013） 药店/保健品店，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （014） 物流公司/货运专线，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （015） KTV，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （016） 干洗店/洗衣店，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （017） 书店/书刊音像店，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （018） 英语培训/外语培训，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （019） 文体培训/艺术培训，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （020） 小吃，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （021） 度假酒店，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （022） 美发店/化妆造型，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （023） 野味馆，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （024） 四S店/汽车销售，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （025） 布艺床品，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （026） 电动车行，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （027） 摄影摄像/宣传片制作，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （028） 咨询公司，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （029） 礼品店/艺术品收藏，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （030） 户外运动，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （031） 其他运动健身场馆，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （032） 其他丽人，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （033） 宠物鲜花/花鸟鱼虫，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （034） 石材管材，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （035） 酒吧，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （036） 招待所，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （037） 橱柜卫浴，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （038） 商场超市，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （039） 其他培训，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （040） 整形医院/整形美容，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （041） 公司注册/工商代理，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （042） 其他美食店，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （043） 婚纱摄影，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （044） 门窗楼梯，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （045） 自助餐厅，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （046） 舞蹈，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （047） 装修装饰/家装设计，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （048） 海鲜餐厅，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （049） 快餐简餐，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （050） 家常菜馆，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （051） 厨房电器，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （052） 电玩店/游戏厅，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （053） 游泳馆，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （054） 防水保温，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （055） 熟食店，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （056） 停车场，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （057） 美容院，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （058） 家政公司/保洁清洗，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （059） 家电维修清洗，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （060） 眼镜店，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （061） 体育场馆，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （062） 油漆涂料，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （063） 搬家公司，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （064） 农家乐/采摘园，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （065） 火锅店，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （066） 律师事务所/法律咨询，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （067） 宠物医院，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （068） 棋牌室，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （069） 灯具照明，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （070） 办公用品/文具乐器，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （071） 家纺日用，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （072） 商标注册，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （073） 办公家具/家居卖场，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （074） 母婴店/儿童用品，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （075） 食堂，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （076） 其他医疗机构，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （077） 蛋糕店/饮品店，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （078） 喷绘招牌/打字复印，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （079） 广告公司/设计公司，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （080） 商务(其他)，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （081） 减肥中心/瘦身，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （082） 汽车美容/汽车装饰，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （083） 手机电脑/家电数码，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （084） 其他购物场所，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （085） 珠宝首饰店/手表店，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （086） 自行车店，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （087） 台球厅，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （088） 五金工具，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （089） 公寓酒店，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （090） 瑜伽馆，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （091） 快捷酒店，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （092） 其他建材，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （093） 电子电料，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （094） 麻辣烫店，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （095） 粉面粥店，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （096） 家居饰品，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （097） 板材吊顶，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （098） 民宿，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （099） 庆典公司/演出会展，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （100） 婚庆公司/婚礼策划，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （101） 便利店，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （102） 美甲店，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （103） 汽车改装/汽配维修，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （104） 其他宾馆，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （105） 加油站，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （106） 其他生活便利，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （107） 公园/游乐场，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （108） 咖啡厅，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （109） 印刷厂/包装厂，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （110） 洗车店，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （111） 其他车辆服务，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （112） 摩托车行，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （113） 电影院/演出/票务，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （114） 按摩保健，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （115） 口腔医院/牙科，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （116） 服装服饰店，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （117） 西餐厅，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （118） 儿童摄影，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （119） 租车公司/汽车租赁，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （120） 化妆品店，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （121） 烧烤店，" +
                "|（12）行为属性|（02）消费属性|（06）商户偏好| （122） 星级酒店";
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



    @ResponseBody
    @RequestMapping("/third")
    public String third(){
        String strs = "生活类客户|生活用品类,"+
                        "生活类客户|家纺日用,"+
                        "生活类客户|便利店,"+
                        "生活类客户|茶叶烟酒食品店,"+
                        "旅游出行类客户|旅游出行类,"+
                        "旅游出行类客户|旅行社/旅游团,"+
                        "旅游出行类客户|农家乐/采摘园,"+
                        "旅游出行类客户|快捷酒店,"+
                        "旅游出行类客户|星级酒店,"+
                        "旅游出行类客户|公寓酒店,"+
                        "旅游出行类客户|度假酒店,"+
                        "旅游出行类客户|旅馆客栈,"+
                        "旅游出行类客户|招待所,"+
                        "旅游出行类客户|民宿,"+
                        "美食类客户|餐饮美食类,"+
                        "美食类客户|火锅店,"+
                        "美食类客户|蛋糕店/饮品店,"+
                        "美食类客户|烧烤店,"+
                        "美食类客户|粉面粥店,"+
                        "美食类客户|麻辣烫店,"+
                        "美食类客户|熟食店”,"+
                        "美食类客户|快餐简餐,"+
                        "美食类客户|自助餐厅,"+
                        "美食类客户|家常菜馆,"+
                        "美食类客户|海鲜餐厅,"+
                        "美食类客户|西餐厅,"+
                        "美食类客户|中餐厅,"+
                        "办公类客户|办公用品类,"+
                        "办公类客户|办公用品/文具乐器,"+
                        "母婴类客户|母婴用品类,"+
                        "母婴类客户|母婴店/儿童用品,"+
                        "休闲娱乐类客户|休闲娱乐类”；,"+
                        "休闲娱乐类客户|KTV,"+
                        "休闲娱乐类客户|咖啡厅,"+
                        "休闲娱乐类客户|茶馆,"+
                        "休闲娱乐类客户|酒吧,"+
                        "休闲娱乐类客户|网吧,"+
                        "休闲娱乐类客户|电玩店/游戏厅,"+
                        "休闲娱乐类客户|夜总会,"+
                        "休闲娱乐类客户|桑拿洗浴中心,"+
                        "休闲娱乐类客户|按摩保健,"+
                        "休闲娱乐类客户|电影院/演出/票务,"+
                        "休闲娱乐类客户|图书馆/文化艺术,"+
                        "休闲娱乐类客户|公园/游乐场,"+
                        "休闲娱乐类客户|棋牌室,"+
                        "健身类客户|健身类,"+
                        "健身类客户|健身房,"+
                        "健身类客户|瑜伽馆,"+
                        "健身类客户|游泳馆,"+
                        "健身类客户|体育场馆,"+
                        "健身类客户|台球厅,"+
                        "医疗保健类客户|医疗保健类,"+
                        "医疗保健类客户|口腔医院/牙科,"+
                        "医疗保健类客户|药店/保健品店,"+
                        "医疗保健类客户|宠物医院," +
                         "其他类|健身类";
        String[] mutls = strs.split(",");
        System.out.println(mutls.length);
        int count = 0;
        for (int i = 0; i < mutls.length; i++) {
            String[] strss = mutls[i].split("\\|");
            String uuid = userMapper.findUUIDByName(strss[1]);
            if("".equals(uuid) || uuid == null) {
                list.add(strss[1]);
                continue;
            }
            int result = userMapper.insertBiz_type_tags_relation(S.uuid(), strss[0], "2", uuid);
            if(result>0) count++;
        }
        return "执行完成,一共"+mutls.length+"条，成功了"+count+"条";
    }


    @RequestMapping("/testList")
    @ResponseBody
    public String testList(){
        System.out.println("尽力啊啦啦");
        return "测试成功";
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



    public Map<String, String> strUtil(String str){
        String[] strs = str.split("\\|");
        String uuid = S.uuid();
        String type = "2";
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
