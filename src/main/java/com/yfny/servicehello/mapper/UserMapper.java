package com.yfny.servicehello.mapper;

import com.yfny.servicepojo.enums.UserSexEnum;
import com.yfny.servicepojo.entity.UserEntity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * 示例用户Mapper
 * Created by jisongZhou on 2019/2/19.
 **/
public interface UserMapper extends BaseMapper<UserEntity> {

//    @Select("SELECT * FROM users")
//    @Results({
//            @Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
//            @Result(property = "nickName", column = "nick_name")
//    })
//    List<UserEntity> getAll();
//
//    @Select("SELECT * FROM users WHERE id = #{id}")
//    @Results({
//            @Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
//            @Result(property = "nickName", column = "nick_name")
//    })
//    UserEntity getOne(Long id);
//
//    @Insert("INSERT INTO users(userName,passWord,user_sex) VALUES(#{userName}, #{passWord}, #{userSex})")
//    void insert(UserEntity user);
//
//    @Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
//    void update(UserEntity user);
//
//    @Delete("DELETE FROM users WHERE id =#{id}")
//    void delete(Long id);


    /**
     * SpringBoot使用Mybatis注解进行一对多和多对多查询
     * 参考地址：
     *      https://blog.csdn.net/KingBoyWorld/article/details/78966789
     * @return
     */


    /**
     * 查询用户详细信息(一对一)
     *
     * @return
     */
    @Select("select * from user")
    @Results({
            //一定要加这句，要不然查询不到id的值
            @Result(id = true, column = "id", property = "id"),
            //属性字段对应数据库的列
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "passWord", column = "pass_word"),
            @Result(property = "permission", column = "permission"),
            //查询关联对象
            //property = “detailsEntity”, 表示要将返回的查询结果赋值给user的detailsEntity属性
            //column = “id” 是指将user表中的id作为com.yfny.servicehello.mapper.DetailsMapper.getDetailsByUserId的查询参数
            //one 表示这是一个一对一的查询
            //@One(select = "方法全路径) 表示我们调用的方法，fetchType表示查询类型是立即加载（EAGER）还是懒加载（LAZY）
            @Result(property = "detailsEntity",
                    column = "id",
                    one = @One(select = "com.yfny.servicehello.mapper.DetailsMapper.getDetailsByUserId", fetchType = FetchType.EAGER)),
//            property = “cars”, 表示要将返回的查询结果赋值给user的cars属性
//            column = “id” 是指将user表中的用户主键id作为com.yfny.servicehello.mapper.CarMapper.findCarByUserId的查询参数
//            many 表示这是一个一对多的查询
//            @Many(select = "方法全路径) 表示我们调用的方法, 方法参数userId就是上面column指定的列值
            @Result(property = "carEntities",
                    column = "id",
                    many = @Many(select = "com.yfny.servicehello.mapper.CarMapper.findCarByUserId", fetchType = FetchType.EAGER))
    })
    public List<UserEntity> selectAllUser();


    /**
     * 根据id查询用户详细信息(一对一)
     * 以及所拥有的车（一对多）
     */
    @Select("select * from user where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "passWord", column = "pass_word"),
            @Result(property = "permission", column = "permission"),
            @Result(property = "detailsEntity",
                    column = "id",
                    one = @One(select = "com.yfny.servicehello.mapper.DetailsMapper.getDetailsByUserId", fetchType = FetchType.EAGER)),
            @Result(property = "carEntities",
                    column = "id",
                    many = @Many(select = "com.yfny.servicehello.mapper.CarMapper.findCarByUserId", fetchType = FetchType.EAGER))
    })
    public UserEntity selectUserAllById(long id);


    /**
     * 登陆用户
     * 查询用户详细信息(一对一)
     * 以及所拥有的车（一对多）
     */
    @Select("select * from user where user_name = #{username} and pass_word = #{password}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "passWord", column = "pass_word"),
            @Result(property = "permission", column = "permission"),
            @Result(property = "detailsEntity", column = "id",
                    one = @One(select = "com.yfny.servicehello.mapper.DetailsMapper.getDetailsByUserId", fetchType = FetchType.EAGER)),
            @Result(property = "carEntities", column = "id",
                    many = @Many(select = "com.yfny.servicehello.mapper.CarMapper.findCarByUserId", fetchType = FetchType.EAGER))
    })
    public UserEntity isLogin(String username, String password);


}
