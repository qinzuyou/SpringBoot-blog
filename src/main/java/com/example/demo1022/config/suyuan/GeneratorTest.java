package com.example.demo1022.config.suyuan;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.config.po.TableFill;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;

import java.util.ArrayList;



public class GeneratorTest {

    public static void main(String[] args) {
        //1.代码生成器
        AutoGenerator mpg = new AutoGenerator();
        //2.全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        gc.setAuthor("user");//设置作者
        gc.setOpen(false);//生成后是否打开资源管理器
        gc.setFileOverride(false);//重新生成文件时是否覆盖
        gc.setServiceName("%sService");
        //gc.setSwagger2(true);//实体属性Swagger2注解
        mpg.setGlobalConfig(gc);
        //3.数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/st1024?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);
        //4.包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(null);
        pc.setParent("com.example.demo1022.comment");
        pc.setController("controller");
        pc.setEntity("entity");
        //只是对mapper中的方法进行了一些重新的封装 所以一般不进行使用
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);
        //5.策略配置
        StrategyConfig sc = new StrategyConfig();
        //对那些表进行生成代码
        sc.setInclude("t_comment");
        //数据库表映射到实体的命名策略
        sc.setNaming(NamingStrategy.underline_to_camel);
        //生成实体时去掉表前缀
        sc.setTablePrefix(pc.getModuleName() + "_");
        //数据库表字段映射到实体的命名策略
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        //lombox模型@Accessours(chain = true) setter链式操作

        sc.setEntityLombokModel(true);//自动lombok

        //设置自动填充配置
        TableFill gmt_create = new TableFill("create_time", FieldFill.INSERT);
        TableFill gmt_modified = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills=new ArrayList<>();
        tableFills.add(gmt_create);
        tableFills.add(gmt_modified);
        sc.setTableFillList(tableFills);



        //restful api 风格控制器
        sc.setRestControllerStyle(true);
        //url中驼峰转连字符
        sc.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(sc);
        //6.执行
        mpg.execute();
    }

}

