package com.extra.demo;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.extra.demo.mptest.entity.User;
import com.extra.demo.mptest.mapper.UserMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author extralIl@1141517977
 * @date 2020/5/7 12:09
 */
@Data
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void insert(){
        // 插入一条记录
//        int insert(T entity);
        User user = new User();
        user.setAge(11);
        user.setName("jiweihao");
        user.setEmail("12312313");
        System.out.println(userMapper.insert(user));
    }

    @Test
    public void delete(){
        // 根据 entity 条件，删除记录
        //        int delete(@Param(Constants.WRAPPER) Wrapper<T> wrapper);
//        QueryWrapper<User> wrapper = new QueryWrapper<User>();
//        wrapper.lambda().eq(User::getId,27);
//        userMapper.delete(wrapper);
        // 删除（根据ID 批量删除）
//                int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
//        userMapper.deleteBatchIds(Arrays.asList(23,24,25,26));
        // 根据 ID 删除
        //        int deleteById(Serializable id);
        /**
         * 这里的根据id删除的方法,好像必须要有主键字段存在在实体类中才行.
         */
//        User user = new User();
//        userMapper.deleteById(22);
        // 根据 columnMap 条件，删除记录
        //        int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    }



    @Test
    public void update(){
        // 根据 whereEntity 条件，更新记录
//        int update(@Param(Constants.ENTITY) T entity, @Param(Constants.WRAPPER) Wrapper<T> updateWrapper);


// 根据 ID 修改
//        int updateById(@Param(Constants.ENTITY) T entity);

    }



    @Test
    public void select(){
        // 根据 ID 查询
//        T selectById(Serializable id);


// 根据 entity 条件，查询一条记录
//        T selectOne(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

// 查询（根据ID 批量查询）
//        List<T> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);


// 根据 entity 条件，查询全部记录
//        List<T> selectList(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);


// 查询（根据 columnMap 条件）
//        List<T> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);


// 根据 Wrapper 条件，查询全部记录
//        List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);


// 根据 Wrapper 条件，查询全部记录。注意： 只返回第一个字段的值
//        List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);


// 根据 entity 条件，查询全部记录（并翻页）
//        IPage<T> selectPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
        Page<User> page3 = new Page<>(1,2);
        QueryWrapper<User> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.lambda().isNull(User::getEmail);
        userMapper.selectPage(page3,queryWrapper2).getRecords().forEach(System.out::println);





        System.out.println("\n\n\n使用selectMapsPage");
// 根据 Wrapper 条件，查询全部记录（并翻页）
//        IPage<Map<String, Object>> selectMapsPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
        Page<Map<String,Object>> page2 = new Page<>(1,2);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().isNull(User::getEmail);
        userMapper.selectMapsPage(page2,queryWrapper).getRecords().forEach(System.out::println);



// 根据 Wrapper 条件，查询总记录数
//        Integer selectCount(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);


        System.out.println("\n\n\n自定义分页查询:\n");
        //自定义sql+wrapper+分页查询,${ew.customSqlSegment}默认是一个where语句. 并在语句最后拼上limit
        User user = new User();
        user.setName("jwh");
        Page<User> page = new Page<>(1,2);
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.lambda().isNull(User::getEmail);
        IPage<User> iPage = userMapper.selectUsersByPages(page,user,wrapper);
        System.out.println(iPage.getPages());
        System.out.println(iPage.getTotal());
        iPage.getRecords().forEach(System.out::println);
        page.setSearchCount(false);
        //递增分页查询,类似于翻页
        //原理就是第一次时isSearchCount=true,先查出count,放入total和pages属性,第二次查询之前把isSearchCount设为false,提高查询效率,并且一直用第一次查出的page和total
        //以后查询都先判断是否还有下一页,有的话再进行分页查询,并页数自增
        for(long i=page.getCurrent();page.hasNext();i++){
            page.setCurrent(i);
            userMapper.selectUsersByPages(page,user,wrapper).getRecords().forEach(System.out::println);
        }


    }


}
