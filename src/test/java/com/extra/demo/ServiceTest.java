package com.extra.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.extra.demo.mptest.entity.User;
import com.extra.demo.mptest.mapper.UserMapper;
import com.extra.demo.mptest.service.IUserService;
import com.extra.demo.mptest.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author extralIl@1141517977
 * @date 2020/5/13 12:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    IUserService userService;

    @Autowired
    UserMapper userMapper;

    @Test
    public void save(){
        /**
         * 当设置全局主键策略为雪花算法时,id是跟着对象走的.
         * 由于下面先save了一次user1,后面批量save又想save一次,所以会报主键重复错误
         */

        // 插入一条记录（选择字段，策略插入）
        User user1 = new User();
        user1.setName("save1");
        user1.setId(null);
        System.out.println(userService.save(user1));
        // 插入（批量）
//        boolean saveBatch(Collection<T> entityList);
        User user2 = new User();
        user2.setName("save2");

        System.out.println(userService.saveBatch(Arrays.asList(user1, user2)));

        // 插入（批量）  后面的batchSize指一次插入多少条数据
        // 一般是发生异常时事务回滚的代价
//        boolean saveBatch(Collection<T> entityList, int batchSize);
        System.out.println(userService.saveBatch(Arrays.asList(user1, user2),1));

    }

    @Test
    public void saveOrUpdate(){
        // TableId 注解存在更新记录，否插入一条记录
        //        boolean saveOrUpdate(T entity);
        // 根据updateWrapper尝试更新，否继续执行saveOrUpdate(T)方法
        //        boolean saveOrUpdate(T entity, Wrapper<T> updateWrapper);
        User user = new User();
        user.setName("ji");
        LambdaUpdateWrapper<User> updateWrapper = new UpdateWrapper<User>().lambda();
        updateWrapper.eq(User::getName,"jiweihao");
        System.out.println(userService.saveOrUpdate(user, updateWrapper));

        // 批量修改插入,判断有主键更新,无主键插入
        //        boolean saveOrUpdateBatch(Collection<T> entityList);

        // 批量修改插入
        //        boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize);

    }

    @Test
    public void remove(){
        // 根据 entity 条件，删除记录
    //        boolean remove(Wrapper<T> queryWrapper);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.lambda().eq(User::getName,"ji");
        userService.remove(queryWrapper);
    // 根据 ID 删除
    //        boolean removeById(Serializable id);
    // 根据 columnMap 条件，删除记录
//            boolean removeByMap(Map<String, Object> columnMap);
        HashMap<String,Object> map = new HashMap<>();
        map.put("name","jiweihao");
            userService.removeByMap(map);
    // 删除（根据ID 批量删除）
    //        boolean removeByIds(Collection<? extends Serializable> idList);

    }


    @Test
    public void update(){
        // 根据 UpdateWrapper 条件，更新记录 需要设置sqlset
        //        boolean update(Wrapper<T> updateWrapper);
        //// 根据 whereEntity 条件，更新记录
        //        boolean update(T entity, Wrapper<T> updateWrapper);
        //// 根据 ID 选择修改
        //        boolean updateById(T entity);
        //// 根据ID 批量更新
        //        boolean updateBatchById(Collection<T> entityList);
        //// 根据ID 批量更新
        //        boolean updateBatchById(Collection<T> entityList, int batchSize);
    }


    @Test
    public void get(){
        /**
         *      // 根据 ID 查询
         *     T getById(Serializable id);
         *     // 根据 Wrapper，查询一条记录。结果集，如果是多个会抛出异常，随机取一条加上限制条件 wrapper.last("LIMIT 1")
         *     T getOne(Wrapper<T> queryWrapper);
         *     // 根据 Wrapper，查询一条记录
         *     T getOne(Wrapper<T> queryWrapper, boolean throwEx);
         *     // 根据 Wrapper，查询一条记录
         *     Map<String, Object> getMap(Wrapper<T> queryWrapper);
         *     // 根据 Wrapper，查询一条记录
         *     <V> V getObj(Wrapper<T> queryWrapper, Function<? super Object, V> mapper);
         */
    }

    //还有获取多条记录的list


    @Test
    public void page(){

//        ArrayList<User> users = new ArrayList<>();
//        char a = 'a';
//        for(int i=0;a<'z';a++,i++){
//            User user = new User();
//            user.setName(a+"");
//            user.setAge(i);
//            users.add(user);
//        }
//
//        userService.saveBatch(users);


        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.isNull(User::getEmail);
        Page<User> page = new Page<>(1,2);
        page.addOrder(OrderItem.desc("age"));//倒序
        page = userService.page(page,queryWrapper);
        System.out.println(page.getPages());
        System.out.println(page.getTotal());
        List<User> users = page.getRecords();
        users.forEach(System.out::println);



//        System.out.println(userService.count());


    }





}
