package com.zf.config;


/**
 * @author zhufang
 * @date 2020/11/30 1:19 下午
 */
public class DataSourceContextHolder {

    private static ThreadLocal<String> dataSourceHolder = new ThreadLocal<>();

    public static void setDataSource(String dataSource) {
        dataSourceHolder.set(dataSource);
    }

    public static String getDataSource() {
        return dataSourceHolder.get();
    }

    public static void removeDataSource() {
        dataSourceHolder.remove();
    }
}
