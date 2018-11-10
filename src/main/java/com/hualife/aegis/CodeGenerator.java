package com.hualife.aegis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;


public class CodeGenerator {



    public static void main(String[] args) {

        //1. 全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true) // 是否支持AR模式
                .setAuthor("yangxudong") // 作者
                .setOutputDir("/Users/yangxudong/Documents/lh/aegis/src/main/java") // 生成路径
                .setFileOverride(true)  // 文件覆盖
                .setIdType(IdType.AUTO) // 主键策略
                .setServiceName("%sService")  // 设置生成的service接口的名字的首字母是否为I
                // IEmployeeService
                .setBaseResultMap(true)
                .setBaseColumnList(true);

        //2. 数据源配置
        DataSourceConfig  dsConfig  = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)  // 设置数据库类型
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/aegis?useUnicode=true&useSSL=false&characterEncoding=utf8")
                .setUsername("root")
                .setPassword("root");

        //3. 策略配置
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true) //全局大写命名
                //.setDbColumnUnderline(true)  // 指定表名 字段名是否使用下划线
                .setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                //.setTablePrefix("tbl_")
                .setInclude("user_organization");  // 生成的表

        //4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.hualife.aegis")
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("entity")
                .setXml("mapper");



        //5. 整合配置
        AutoGenerator  ag = new AutoGenerator();

        ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);

        //6. 执行
        ag.execute();
    }

}