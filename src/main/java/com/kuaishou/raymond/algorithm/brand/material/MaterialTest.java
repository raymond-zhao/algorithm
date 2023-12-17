package com.kuaishou.raymond.algorithm.brand.material;

import com.google.common.collect.Range;
import com.kuaishou.raymond.algorithm.brand.material.enums.MaterialStandardEnum;

/**
 * @Author: raymond
 * @Datetime: 2023/11/17 16:26
 * Description:
 */
public class MaterialTest {

    public static void main(String[] args) {
        MaterialStandardEnum brandMaxVideo = MaterialStandardEnum.BRAND_MAX_VIDEO;
        Range<Integer> width = brandMaxVideo.getWidth();
        System.out.println("width = " + width);
    }
}
