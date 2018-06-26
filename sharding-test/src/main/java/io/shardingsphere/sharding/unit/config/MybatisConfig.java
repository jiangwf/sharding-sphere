package io.shardingsphere.sharding.unit.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * weifeng.jiang 2018-03-19 00:10
 */
@Configuration
@MapperScan(basePackages = {"io.shardingsphere.sharding.unit.dao"})
public class MybatisConfig {
}
