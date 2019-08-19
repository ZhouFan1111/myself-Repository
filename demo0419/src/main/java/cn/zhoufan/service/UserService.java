package cn.zhoufan.service;

import cn.zhoufan.entity.User;

import java.util.List;

public interface UserService {

    User getUser();

    User findUserByName(String name);

    int insertUser(String name,String password,int age);

    int insertTagProperty(String UUID,String TYPE,String FULL_CODE,String LEVEL1_CODE,String LEVEL1_NAME,
                         String LEVEL2_CODE,String LEVEL2_NAME,String LEVEL3_CODE,String LEVEL3_NAME,
                          String LEVEL4_CODE,String LEVEL4_NAME,String LEVEL);
}
