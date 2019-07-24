package com.example.demo.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成器
 */
public class CodeGenerator {


    public static void main(String[] args) throws IOException {
        //作者
        String author = "wanghao";
        //项目路径
        String canonicalPath;
        //数据库连接信息
        String dbUrl = "jdbc:mysql://localhost:3306/wanghao?serverTimezone=UTC&characterEncoding=utf8";
        String driverName = "com.mysql.cj.jdbc.Driver";
        String userName = "root";
        String password = "password";

        /**
         * 获取项目路径
         */
        canonicalPath = System.getProperty("user.dir");
        System.out.println("获取项目路径:" + canonicalPath);

        //生成器对象
        AutoGenerator mpg = new AutoGenerator();

        //全局配置
        mpg.setGlobalConfig(new GlobalConfig()
                .setOutputDir(canonicalPath + "/src/main/java")//输出目录
                .setFileOverride(true)// 是否覆盖文件
                .setActiveRecord(true)// 开启 activeRecord 模式
                .setEnableCache(false)// XML 二级缓存
                .setBaseResultMap(true)// XML ResultMap
                .setBaseColumnList(true)// XML columList
                .setOpen(false)//生成后打开文件夹
                .setAuthor(author)
                // 自定义文件命名，注意 %s 会自动填充表实体属性！
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sController")
        );

        //数据源配置
        mpg.setDataSource(new DataSourceConfig()
                .setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setDriverName(driverName)
                .setUsername(userName)
                .setPassword(password));

        //包配置
        mpg.setPackageInfo(new PackageConfig()
                //.setModuleName("User")
                .setParent("com.example.demo")// 自定义包路径
                //.setController("controller")// 这里是控制器包名，默认 web
                .setEntity("model") // 设置Entity包名，默认entity
                .setMapper("mapper") // 设置Mapper包名，默认mapper
                //.setService("service") // 设置Service包名，默认service
                //.setServiceImpl("service.impl") // 设置Service Impl包名，默认service.impl
                .setXml("mapper") // 设置Mapper XML包名，默认mapper.xml
        );

        //策略配置
        mpg.setStrategy(new StrategyConfig()
                        // .setCapitalMode(true)// 全局大写命名
                        //.setDbColumnUnderline(true)//全局下划线命名
                        //.setTablePrefix(new String[]{prefix})// 此处可以修改为您的表前缀
                        .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                        .setInclude("test") // 需要生成的表
                        .setRestControllerStyle(true)
                        //.setExclude(new String[]{"test"}) // 排除生成的表
                        // 自定义实体父类
                        // .setSuperEntityClass("com.baomidou.demo.TestEntity")
                        // 自定义实体，公共字段
                        //.setSuperEntityColumns(new String[]{"test_id"})
                        //.setTableFillList(tableFillList)
                        // 自定义 mapper 父类 默认BaseMapper
                        //.setSuperMapperClass("com.baomidou.mybatisplus.mapper.BaseMapper")
                        // 自定义 service 父类 默认IService
                        // .setSuperServiceClass("com.baomidou.demo.TestService")
                        // 自定义 service 实现类父类 默认ServiceImpl
                        // .setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl")
                        // 自定义 controller 父类
                        //.setSuperControllerClass("com.kichun."+packageName+".controller.AbstractController")
                        // 【实体】是否生成字段常量（默认 false）
                        // public static final String ID = "test_id";
                        // .setEntityColumnConstant(true)
                        // 【实体】是否为构建者模型（默认 false）
                        // public User setName(String name) {this.name = name; return this;}
                        // .setEntityBuilderModel(true)
                        // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
                        .setEntityLombokModel(true)
                // Boolean类型字段是否移除is前缀处理
                // .setEntityBooleanColumnRemoveIsPrefix(true)
                // .setRestControllerStyle(true)
                // .setControllerMappingHyphenStyle(true)
        );

        /**
         * 注入自定义配置
         */
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                /*Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);*/
            }
        };

        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建,这里调用默认的方法
                checkDir(filePath);
                //对于已存在的文件，只需重复生成 entity 和 mapper.xml
                File file = new File(filePath);
                System.out.println(filePath);
                boolean exist = file.exists();
                if (exist) {
                    if (filePath.endsWith("Mapper.xml") || FileType.ENTITY == fileType) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    //不需要生成controller,service,serviceImpl类
                    if (FileType.CONTROLLER == fileType || FileType.SERVICE == fileType || FileType.SERVICE_IMPL == fileType) {
                        return false;
                    }
                }
                //不存在的文件都需要创建
                return true;
            }
        });

        //自定义文件输出位置（非必须）
        List<FileOutConfig> fileOutList = new ArrayList<FileOutConfig>();
        fileOutList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return canonicalPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(fileOutList);
        mpg.setCfg(cfg);


        /**
         * 模板配置
         */
        mpg.setTemplate(
                // 关闭默认 xml 生成，调整生成 至 根目录
                new TemplateConfig().setXml(null)
                // 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template 使用 copy
                // 至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
                // .setController("...");
                // .setEntity("...");
                // .setMapper("...");
                // .setXml("...");
                // .setService("...");
                // .setServiceImpl("...");
        );

        /*PackageConfig packageConfig,
        DataSourceConfig dataSourceConfig,
        StrategyConfig strategyConfig,
        TemplateConfig template,
        GlobalConfig globalConfig*/

        //自定义不生成目录
        /*mpg.setTemplateEngine(new VelocityTemplateEngine(){
            @Override
            public AbstractTemplateEngine mkdirs() {
                this.getConfigBuilder().getPathInfo().forEach((key, value) -> {
                    if(!value.endsWith("controller") && !value.endsWith("service") && !value.endsWith("impl")){
                        File dir = new File(value);
                        if (!dir.exists()) {
                            boolean result = dir.mkdirs();
                            if (result) {
                                logger.debug("创建目录： [" + value + "]");
                            }
                        }
                    }
                });
                return this;
            }
        });*/

        mpg.execute();
    }
}
