package com.kuaishou.raymond.algorithm.brand.material.enums;

import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.function.ToIntFunction;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;

import com.google.common.collect.Range;
import com.google.common.collect.Sets;
import com.kuaishou.raymond.algorithm.brand.material.annotation.Duration;
import com.kuaishou.raymond.algorithm.brand.material.annotation.Height;
import com.kuaishou.raymond.algorithm.brand.material.annotation.Size;
import com.kuaishou.raymond.algorithm.brand.material.annotation.Width;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: raymond
 * @Datetime: 2023/11/16 10:31
 * Description:
 */
@Getter
@AllArgsConstructor
public enum MaterialStandardEnum {

    @Width(min = 1124, max = 1124)
    @Height(min = 2436, max = 2436)
    @Size(max = 25 * 1024)
    @Duration(min = 15_000, max = 60_000)
    BRAND_MAX_VIDEO("BrandMax-视频素材规范", Sets.newHashSet()),

    ;

    private final String name;

    private final Set<Integer> productForms;

    /**
     * 根据产品形态寻找素材校验标准
     */
    @Nullable
    public static MaterialStandardEnum find(int productForm) {
        for (MaterialStandardEnum materialStandardEnum : MaterialStandardEnum.values()) {
            Set<Integer> productFormSet = materialStandardEnum.getProductForms();
            if (productFormSet.contains(productForm)) {
                return materialStandardEnum;
            }
        }
        return null;
    }

    /**
     * 寻找枚举上的注解
     *
     * @param annotationType 注解类型
     * @return {@link MaterialStandardEnum} 上类型为 annotationType 的注解
     */
    static <A extends Annotation> A findAnnotation(@NotNull Class<A> annotationType) {
        return AnnotationUtils.findAnnotation(MaterialStandardEnum.class, annotationType);
    }

    /**
     * 获取注解中的某个数值
     *
     * @param annotationType 注解类型
     * @param function       注解中方法的 Lambda
     * @param <A>            注解泛型
     * @return 注解中某方法的值
     */
    <A extends Annotation> int getValue(@NotNull Class<A> annotationType, ToIntFunction<A> function) {
        try {
            A annotation = AnnotatedElementUtils.getMergedAnnotation(MaterialStandardEnum.class.getField(this.name()), annotationType);
            if (annotation == null) {
                return 0;
            }
            return function.applyAsInt(annotation);
        } catch (Exception exception) {
            return 0;
        }
    }

    /**
     * 获取注解上的素材时长
     */
    public Range<Integer> getDuration() {
        int min = getValue(Duration.class, Duration::min);
        int max = getValue(Duration.class, Duration::max);
        return Range.closed(min, max);
    }

    /**
     * 获取注解上的素材体积
     */
    public Range<Integer> getSize() {
        int min = getValue(Size.class, Size::min);
        int max = getValue(Size.class, Size::max);
        return Range.closed(min, max);
    }

    /**
     * 获取注解上的素材高度
     */
    public Range<Integer> getHeight() {
        int min = getValue(Height.class, Height::min);
        int max = getValue(Height.class, Height::max);
        return Range.closed(min, max);
    }

    /**
     * 获取注解上的素材宽度
     */
    public Range<Integer> getWidth() {
        int min = getValue(Width.class, Width::min);
        int max = getValue(Width.class, Width::max);
        return Range.closed(min, max);
    }

}
