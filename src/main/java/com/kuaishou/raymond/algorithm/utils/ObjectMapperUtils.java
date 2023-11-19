package com.kuaishou.raymond.algorithm.utils;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.vavr.control.Try;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: raymond
 * @Datetime: 2023/11/13 20:45
 * Description:
 */
@Slf4j
public class ObjectMapperUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    /**
     * 将对象转换为 JSON 格式，但需要手动制定变量命名策略。
     * @param propertyNamingStrategy 命名方式
     */
    public static String toJson(Object object, PropertyNamingStrategy propertyNamingStrategy) {
        if (object == null) {
            return null;
        }
        if (Objects.nonNull(propertyNamingStrategy)) {
            MAPPER.setPropertyNamingStrategy(propertyNamingStrategy);
        }
        Try<String> jsonString = Try.of(() -> MAPPER.writeValueAsString(object));
        if (jsonString.isFailure()) {
            log.error("object trans to str error", jsonString.getCause());
            return null;
        }
        return jsonString.get();
    }

    public static String toJson(Object object) {
        return toJson(object, null);
    }

    public static void main(String[] args) {
        RedLogoConfig redLogoConfig = new RedLogoConfig();
        redLogoConfig.setLogoTintColor("#ffffff");
        redLogoConfig.setLogoBackgroundColor("#ff0000");
        redLogoConfig.setShowLogoButton(true);
        redLogoConfig.setRedLogoStyleType(2);
        String json1 = toJson(redLogoConfig);
        System.out.println("json1 = " + json1);
    }

    @Data
    @JsonNaming(SnakeCaseStrategy.class)
    public static class RedLogoConfig implements Serializable {

        private static final long serialVersionUID = -3489112217326603795L;

        private boolean isShowLogoButton;

        private Integer redLogoStyleType;

        /**
         * Logo 颜色
         */
        private String logoTintColor;

        /**
         * Logo 背景颜色
         */
        private String logoBackgroundColor;
    }

}
