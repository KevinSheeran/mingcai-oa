package com.mingcai.edu.common.utils.TableUpdateLog;
/**
 * @author printsky
 * @since 2018/8/30
 */
public class Person extends Base {
    private String id;
    @LogCompar(name = "name")
    private String name;
    @LogCompar(name = "age")
    private Integer age;
    @LogCompar(name = "sex")
    private boolean sex;

    public Person() {
    }

    public Person(String id, String name, Integer age, boolean sex) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    @Override
    EnumTableName getTableName() {
        return EnumTableName.Person;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }


}