package com.kuaishou.raymond.algorithm.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * Created on 2023-06-24 11:46
 */
public class WhatsForLunch {

    private static Random random;

    static {
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println("whatsForLunch() = " + whatsForLunch());
    }

    private static String whatsForLunch() {
        List<String> menu = new ArrayList<>();
        menu.add("KFC");
        menu.add("麦当劳");
        menu.add("食堂");
        menu.add("麻辣香锅");
        int idx = random.nextInt(menu.size());
        return menu.get(idx);
    }
}
