package com.kuaishou.raymond.algorithm.jdbc;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-12-22 17:19
 */
@Configuration
@ComponentScan("com.kuaishou.raymond")
public class SpringJdbcConfig {

    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://cluster01.proxysql.staging.internal:6032/gifshow");
        dataSource.setUsername("gifshow_48580_v1_rw");
        dataSource.setPassword("Kv1a5pP2TSk3K1IuQO6dXe7GtVxgLfFo");

        return dataSource;
    }

}
