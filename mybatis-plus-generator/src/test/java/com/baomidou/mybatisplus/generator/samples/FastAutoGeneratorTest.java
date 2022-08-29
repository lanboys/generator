package com.baomidou.mybatisplus.generator.samples;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.engine.EnjoyTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.sql.SQLException;
import java.util.Collections;
import java.util.function.Consumer;

/**
 * <p>
 * 快速生成
 * </p>
 *
 * @author lanjerry
 * @since 2021-09-16
 */
public class FastAutoGeneratorTest extends BaseGeneratorTest {

    /**
     * 数据源配置
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
        .Builder("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;CASE_INSENSITIVE_IDENTIFIERS=TRUE;MODE=MYSQL", "sa", "");

    /**
     * 执行 run
     */
    public static void main1(String[] args) throws SQLException {
        // 初始化数据库脚本
        initDataSource(DATA_SOURCE_CONFIG.build());
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
            // 全局配置
            .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称")))
            // 包配置
            .packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入包名")))
            // 策略配置
            .strategyConfig((scanner, builder) -> builder.addInclude(scanner.apply("请输入表名，多个表名用,隔开")))
            /*
                模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker 或 Enjoy
               .templateEngine(new BeetlTemplateEngine())
               .templateEngine(new FreemarkerTemplateEngine())
               .templateEngine(new EnjoyTemplateEngine())
             */
            .execute();
    }

    private static final DataSourceConfig.Builder MYSQL_DATA_SOURCE_CONFIG =
        new DataSourceConfig
            .Builder("jdbc:mysql://localhost:3306/baomidou?serverTimezone=Asia/Shanghai", "root", "admin")
            .schema("baomidou");

    public static void main(String[] args) {
        FastAutoGenerator.create(MYSQL_DATA_SOURCE_CONFIG)
                         .globalConfig(builder -> {
                             builder.author("baomidou") // 设置作者
                                    // .enableSwagger() // 开启 swagger 模式
                                    .outputDir("E:\\workspace\\wisdom-workspace\\short-video\\short-video-parent\\video-dao\\src\\test\\java"); // 指定输出目录
                         })
                         .packageConfig(builder -> {
                             builder.parent("com.bing.lan.samples") // 设置父包名
                                    .moduleName("") // 设置父包模块名
                                    .pathInfo(Collections.singletonMap(OutputFile.xml, "E:\\workspace\\wisdom-workspace\\short-video\\short-video-parent\\video-dao\\src\\test\\java")); // 设置mapperXml生成路径
                         })
                         .strategyConfig(builder -> {
                             builder.addInclude("t_simple") // 设置需要生成的表名
                                    .addTablePrefix("t_", "c_"); // 设置过滤表前缀

                             builder.entityBuilder().enableLombok().enableFileOverride().build();

                         })
            .injectionConfig(new Consumer<InjectionConfig.Builder>() {
                @Override
                public void accept(InjectionConfig.Builder builder) {
                    builder.customFile(new Consumer<CustomFile.Builder>() {
                        @Override
                        public void accept(CustomFile.Builder builder) {
                            builder.templatePath("/templates/entityDto.java.ftl");
                            // builder.filePath("entity");
                            builder.packageName("com.bing.lan.samples.domain.dto");
                            builder.fileName("Dto.java");

                        }
                    });
                }
            })
            .templateConfig(new Consumer<TemplateConfig.Builder>() {
                @Override
                public void accept(TemplateConfig.Builder builder) {
                    // builder.entity("/sss");








                }
            })



                         .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                         .execute();
    }
}
