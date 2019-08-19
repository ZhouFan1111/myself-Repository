package cn.zhoufan.configtest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName DemoApplicationTest
 * @Description TODO
 * @Author zhoufan
 * @Date 2019/6/3 14:10
 * @Version 1.0
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTest {

    @Autowired
    private TestProperties testProperties;


    @Test
    public void testProperties(){
        System.out.println(testProperties.toString());
    }

}
