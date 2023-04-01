package com.kuaishou.raymond.algorithm.jdbc;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.stream.IntStream;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2022-12-22 17:16
 */
@Slf4j
public class JdbcTemplateTest {

    private static final String INSERT_TEMPLATE =
            "INSERT INTO single_table(id, key1, key2, key3, key_part1, key_part2, key_part3, common_field) VALUES "
                    + "(%s, 'key1_%s', %s, 'key3_%s', 'key_part1_%s', 'key_part2_%s', 'key_part3_%s', "
                    + "'common_field_%s');";

    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(localMySQLDataSource());
        IntStream.rangeClosed(3, 10000).forEach(number -> {
            String sql =
                    String.format(INSERT_TEMPLATE, number, number, number, number, number, number, number, number);
            jdbcTemplate.execute(sql);
        });
        Integer result = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM single_table", Integer.class);
        log.info("result = {}", result);
    }

    private static DataSource localMySQLDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/raymond");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }

    @Data
    public static class SingleTableEntity {
        private Integer id;
        private String key1;
        private Integer key2;
        private String key3;
        private String keyPart1;
        private String keyPart2;
        private String keyPart3;
        private String commonFields;
    }

}
