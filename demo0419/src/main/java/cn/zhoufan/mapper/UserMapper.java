package cn.zhoufan.mapper;

import cn.zhoufan.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user_zf where name=#{name}")
    User findUserByName(@Param("name")String name);

    @Insert("insert into user_zf(name,password,age) values(#{name},#{password},#{age})")
    int insertUser(@Param("name")String name,@Param("password")String password,@Param("age")int age);

    @Insert("insert into tb_tag_property values(#{UUID} , #{TYPE} , #{FULL_CODE} ,#{LEVEL1_CODE} , #{LEVEL1_NAME} ,#{LEVEL2_CODE} , #{LEVEL2_NAME} ,#{LEVEL3_CODE} , #{LEVEL3_NAME} ,#{LEVEL4_CODE} , #{LEVEL4_NAME} , #{LEVEL})")
    int insertTagProperty(@Param("UUID")String UUID,@Param("TYPE")String TYPE,@Param("FULL_CODE")String FULL_CODE,@Param("LEVEL1_CODE")String LEVEL1_CODE,@Param("LEVEL1_NAME")String LEVEL1_NAME,
                   @Param("LEVEL2_CODE")String LEVEL2_CODE,@Param("LEVEL2_NAME")String LEVEL2_NAME,@Param("LEVEL3_CODE")String LEVEL3_CODE,@Param("LEVEL3_NAME")String LEVEL3_NAME,
                   @Param("LEVEL4_CODE")String LEVEL4_CODE,@Param("LEVEL4_NAME")String LEVEL4_NAME,@Param("LEVEL")String LEVEL);

    @Select("SELECT enname from tb_bds_sys_mdict where name=#{NAME}")
    String findName(@Param("NAME")String name);

    @Insert("insert into tb_merchant_tags_relation values(#{UUID},#{ONE},#{TWO},#{UUID2})")
    int insertMerchantTagsRelation(@Param("UUID")String uuid,@Param("ONE")String one,@Param("TWO")String two,@Param("UUID2")String uuid2);

    @Select("SELECT tagid from tb_tag_property where level4_name like concat('%',#{NAME},'%')")
    String findUUIDByName(@Param("NAME")String level4_Name);

    @Insert("insert into tb_biz_type_tags_relation values(#{ID},#{NAME},#{TYPE},#{TAGID})")
    int insertBiz_type_tags_relation(@Param("ID")String id,@Param("NAME")String name,@Param("TYPE")String type,@Param("TAGID")String tagid);



}
