package cn.zhoufan.service.impl;

import cn.zhoufan.entity.User;
import cn.zhoufan.mapper.UserMapper;
import cn.zhoufan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser() {
        return new User("周帆123214214","1234",new Random().nextInt(1000)+1);
    }

    @Override
    public User findUserByName(String name) {
        return userMapper.findUserByName(name);
    }

    @Override
    public int insertUser(String name,String password,int age) {
        return userMapper.insertUser(name,password,age);
    }

    @Override
    public int insertTagProperty(String UUID, String TYPE, String FULL_CODE, String LEVEL1_CODE, String LEVEL1_NAME, String LEVEL2_CODE, String LEVEL2_NAME, String LEVEL3_CODE, String LEVEL3_NAME, String LEVEL4_CODE, String LEVEL4_NAME, String LEVEL) {
        return userMapper.insertTagProperty(UUID,TYPE,FULL_CODE,LEVEL1_CODE,LEVEL1_NAME,LEVEL2_CODE,LEVEL2_NAME,LEVEL3_CODE,LEVEL3_NAME,LEVEL4_CODE,LEVEL4_NAME,LEVEL);
    }


}