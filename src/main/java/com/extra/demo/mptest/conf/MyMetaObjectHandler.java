package com.extra.demo.mptest.conf;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author extralIl@1141517977
 * @date 2020/5/13 23:18
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Boolean hasSetter = metaObject.hasSetter("createTime");
        if(hasSetter){
            setInsertFieldValByName("createTime", LocalDateTime.now(),metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Boolean hasSetter = metaObject.hasSetter("updateTime");
        if(hasSetter){
            setInsertFieldValByName("updateTime", LocalDateTime.now(),metaObject);
        }
    }
}
