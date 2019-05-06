package com.mingcai.edu.common.utils.TableUpdateLog;
/**
 * @author printsky
 * @since 2018/8/30
 */
public enum EnumTableName {
    Person("Person"),
    Province("Province"),
    Income("Income");

    EnumTableName(String tableName) {
        this.tableName = tableName;
    }

    private String tableName;

    public String getTableName() {
        return tableName;
    }
}