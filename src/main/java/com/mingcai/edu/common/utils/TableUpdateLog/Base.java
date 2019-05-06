package com.mingcai.edu.common.utils.TableUpdateLog;

import com.google.gson.Gson;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author printsky
 * @since 2018/8/30
 */
public abstract class Base {
    public abstract String getId();

    abstract EnumTableName getTableName();

    //region 生成数据修改日志
    public final static String ADD = "新增";
    public final static String UPDATE = "更新";
    public final static String DELETE = "删除";


    public <T extends Base> UnityLog createLog(String unityOperate, T oldObj) throws IllegalAccessException {
        //保存操作日志
        UnityLog unityLog = comparatorObject(unityOperate, oldObj);
        unityLog.setId(getId());
        return unityLog;
    }

    /**
     * 比较新老对象的差别
     *
     * @param unityOperate
     * @param oldObj
     * @return
     * @throws IllegalAccessException
     */
    private UnityLog comparatorObject(String unityOperate, Object oldObj) throws IllegalAccessException {
        StringBuilder matter = new StringBuilder();
        StringBuilder content = new StringBuilder();

        if (oldObj != null && UPDATE.equals(unityOperate)) {
            Map<String, Object> oldMap = changeValueToMap(oldObj);
            Map<String, Object> newMap = changeValueToMap(this);
            if (oldMap != null && !oldMap.isEmpty()) {
                for (Map.Entry<String, Object> entry : oldMap.entrySet()) {
                    Object oldValue = entry.getValue();
                    Object newValue = newMap.get(entry.getKey());
                    if (!oldValue.equals(newValue)) {
                        matter.append("[").append(entry.getKey()).append("]");
                        content.append("[").append(oldValue).append("->").append(newValue).append("]");
                    }
                }
            }
        } else {
            matter.append("-");
            content.append("-");
        }
        return UnityLog.builder()
                .unityOperate(unityOperate)
                .unityTag(getTableName().getTableName())
                .unityMatter(String.valueOf(matter))
                .unityContent(String.valueOf(content))
                .build();
    }

    /**
     * 将类对象转换成Map
     *
     * @param entity 原对象
     * @return Map
     * @throws IllegalAccessException 类型转换时报错
     */
    private static Map<String, Object> changeValueToMap(Object entity) throws IllegalAccessException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            if (PropertyUtils.isReadable(entity, name) && PropertyUtils.isWriteable(entity, name)) {
                if (field.isAnnotationPresent(LogCompar.class)) {
                    LogCompar anno = field.getAnnotation(LogCompar.class);
                    //获取private对象字段值
                    field.setAccessible(true);
                    resultMap.put(anno.name(), field.get(entity));
                }
            }
        }
        return resultMap;
    }
    //endregion
    public static void main(String[] args) {
        Gson gson=new Gson();
        Person user1=new Person("1","张三",21,true);
        System.out.println("user1 = " + gson.toJson(user1));
        Person user2=new Person("1","李四",24,false);
        System.out.println("user2 = " + gson.toJson(user2));
        try {
            UnityLog log = user2.createLog(Base.UPDATE, user1);
            System.out.println("log = " + gson.toJson(log));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
