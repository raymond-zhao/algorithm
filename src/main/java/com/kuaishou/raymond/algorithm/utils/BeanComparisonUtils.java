package com.kuaishou.raymond.algorithm.utils;

import java.beans.PropertyDescriptor;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import com.google.common.collect.Sets;

import lombok.Data;

/**
 * @Author: raymond
 * @Datetime: 2023/9/22 15:53
 * Description:
 */
public class BeanComparisonUtils {

    /**
     * 判断两个 Bean 的哪些属性发生了改变
     * @param dbBean DB 对象
     * @param newBean 新对象
     * @return 发生了属性改变的字段
     */
    public static Set<String> getChangedProperties(Object dbBean, Object newBean) {
        BeanWrapper oldWrapper = PropertyAccessorFactory.forBeanPropertyAccess(dbBean);
        BeanWrapper newWrapper = PropertyAccessorFactory.forBeanPropertyAccess(newBean);

        Set<String> changedProperties = Sets.newHashSet();
        PropertyDescriptor[] propertyDescriptors = oldWrapper.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();
            Object oldValue = oldWrapper.getPropertyValue(propertyName);
            Object newValue = newWrapper.getPropertyValue(propertyName);

            if (oldValue != null && !oldValue.equals(newValue)) {
                changedProperties.add(propertyName);
            }
        }

        return changedProperties;
    }

    /**
     * 检查新旧 Bean 是否有属性发生了改变
     */
    public static boolean propertiesChanged(Object dbBean, Object newBean) {
        BeanWrapper oldWrapper = PropertyAccessorFactory.forBeanPropertyAccess(dbBean);
        BeanWrapper newWrapper = PropertyAccessorFactory.forBeanPropertyAccess(newBean);

        PropertyDescriptor[] propertyDescriptors = oldWrapper.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();
            Object oldValue = oldWrapper.getPropertyValue(propertyName);
            Object newValue = newWrapper.getPropertyValue(propertyName);

            if (oldValue != null && !oldValue.equals(newValue)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // 创建两个示例对象
        Person person1 = new Person("John Snow", 25);
        Person person2 = new Person("John", 30);

        // 获取属性发生改变的名称
        Set<String> changedProperties = getChangedProperties(person1, person2);

        // 输出结果
        System.out.println("Changed properties: " + changedProperties);
        boolean propertiesChanged = propertiesChanged(person1, person2);
        System.out.println("propertiesChanged = " + propertiesChanged);
    }

    @Data
    private static class Person {

        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
