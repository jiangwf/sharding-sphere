package io.shardingsphere.sharding.unit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * weifeng.jiang 2018-06-26 11:07
 */
@SpringBootApplication
@ComponentScan(basePackages = "io.shardingsphere.sharding.unit")
public class UnitApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnitApplication.class,args);
    }
}
