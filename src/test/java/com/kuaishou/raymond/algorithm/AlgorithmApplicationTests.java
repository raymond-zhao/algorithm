package com.kuaishou.raymond.algorithm;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kuaishou.raymond.algorithm.reading.design.patterns.demo.service.DemoService;
import com.kuaishou.raymond.algorithm.reading.design.patterns.demo.service.DemoService.DemoParam;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class AlgorithmApplicationTests {

    @Autowired
    private DemoService demoService;

    @Test
    void contextLoads() {
    }

    @Test
    void testVavrTry() {
        Try<Integer> result = Try.of(() -> 10 / 2);
        if (result.isFailure()) {
            Throwable cause = result.getCause();
            log.error("Get result error.", cause);
        }
        log.info("Get result success. result = {}", result.getOrNull());
    }

    @Test
    void testDivide() {
        int totalAmount = 998;
        int usedAmount = 1000;

        double percentage = Math.ceil((usedAmount * 100.0) / totalAmount);
        int roundedPercentage = Math.min((int) percentage, 100);

        System.out.println("Percentage: " + roundedPercentage + "%");

        String email = "zhaolei1@xiaohongshu.com";
        String[] split = email.split("@");
        System.out.println("Arrays.toString(split) = " + Arrays.toString(split));
    }

    @Test
    void testLocalDatetime() {
        String dateString = "2022-01-01 00:00:00";

        LocalDateTime dateTime = parseToLocalDateTime(dateString);
        long epochSeconds = dateTime.toEpochSecond(ZoneOffset.UTC);

        System.out.println(epochSeconds);
    }

    public static LocalDateTime parseToLocalDateTime(String str) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(str, df);
    }

    @Test
    void testRegExp() {
        String expression = "feature-topic-mvp-[a-z0-9]{8}";
        String str = "feature-topic-mvp-202309071550-b498e19e";
        System.out.println("str.matches(expression) = " + str.matches(expression));
    }

    @Test
    void testBigDecimal() {
        System.out.println("BigDecimal.valueOf(70).divide(new BigDecimal(100), 0, RoundingMode.UP).longValue() = "
                + BigDecimal.valueOf(70).divide(new BigDecimal(100), 0, RoundingMode.UP).longValue());
    }

    @Test
    void testDemoService() {
        DemoParam demoParam = new DemoParam();
        demoParam.setId(1L);
        demoService.createMaterial(demoParam);
    }

    @Test
    void testFunction() {
        System.out.println("function(1) = " + function(1));
        System.out.println("function(2) = " + function(2));
        System.out.println("function(3) = " + function(3));
        System.out.println("function(4) = " + function(4));
        System.out.println("function(5) = " + function(5));
    }

    String function(int y) {
        BigDecimal x = BigDecimal.valueOf(y);
        BigDecimal p18111 = BigDecimal.valueOf(18111);
        BigDecimal p2 = BigDecimal.valueOf(2);
        BigDecimal p90555 = BigDecimal.valueOf(90555);
        BigDecimal p633885 = BigDecimal.valueOf(633885);
        BigDecimal p452773 = BigDecimal.valueOf(452773);
        BigDecimal p217331 = BigDecimal.valueOf(217331);
        return p18111.divide(p2).multiply(x.pow(4)).subtract(p90555.multiply(x.pow(3)))
                .add(p633885.divide(p2).multiply(x.pow(2))).subtract(p452773.multiply(x)).add(p217331).toString();
    }

    @Test
    void testDouble() {
        double x = 2401578;
        double y = x / 1024 / 1024;
        BigDecimal z =
                BigDecimal.valueOf(x).divide(BigDecimal.valueOf(1024 * 1024), RoundingMode.HALF_UP).setScale(1, RoundingMode.HALF_UP);
        System.out.println("y = " + y);
        System.out.println("z = " + z);
        System.out.println("z.toString() = " + z.toString());
    }
}
