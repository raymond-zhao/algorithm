package com.kuaishou.raymond.algorithm.leetcode.all;

import java.util.ArrayList;
import java.util.List;

/**
 * @author raymond <zhaolei09@kuaishou.com>
 * created on 2023-04-12 15:06
 * 2023-06-07 19:00 美团一面
 */
public class P93RestoreIPAddress {

    public static void main(String[] args) {
        String s = "25525511135";
        P93RestoreIPAddress p93 = new P93RestoreIPAddress();
        System.out.println("p93.restoreIpAddresses(s) = " + p93.restoreIpAddresses(s));
        String s1 = "172162541";
        System.out.println("p93.restoreIpAddresses(s1) = " + p93.restoreIpAddresses(s1));
    }

    private static final int THREE_DOTS = 3;

    /**
     * 存储所有复原后的 IP 地址
     */
    private List<String> ipAddresses;

    /**
     * 存储复原后的 IP 地址
     */
    private StringBuilder ipAddress;

    public List<String> restoreIpAddresses(String s) {
        ipAddresses = new ArrayList<>();
        ipAddress = new StringBuilder();

        // 先加入源字符串，在回溯的过程中插入 . 分隔符进行切割。
        ipAddress.append(s);
        backtrack(0, 0);

        return ipAddresses;
    }

    /**
     * 回溯搜索所有的 IP 地址
     *
     * @param startIdx 本轮搜索的起始位置
     * @param dotCount 搜索过程中已使用的「.」的个数
     */
    private void backtrack(int startIdx, int dotCount) {
        if (dotCount == THREE_DOTS) {
            // 如果 IP 地址中已经插入了三个「点分隔符」，则检查最后一 IP 段是否合法。
            String fourthIpSegment = ipAddress.substring(startIdx, ipAddress.length());
            if (isValidIpSegment(fourthIpSegment)) {
                ipAddresses.add(ipAddress.toString());
            }
            return;
        }

        // 罗列所有选择：同层横向遍历
        for (int i = startIdx; i < ipAddress.length(); i++) {
            // 截取 s[startIdx, i] 作为本次回溯的 IP 段。
            String ipSegment = ipAddress.substring(startIdx, i + 1);
            if (!isValidIpSegment(ipSegment)) {
                break;
            }
            // 做出选择：插入一个「点分隔符」
            ipAddress.insert(i + 1, '.');
            // 回溯
            backtrack(i + 2, dotCount + 1);
            // 撤销选择
            ipAddress.deleteCharAt(i + 1);
        }
    }

    /**
     * 判断 s 是否是合法的 IP 段
     */
    private boolean isValidIpSegment(String s) {
        // 校验 IP 段的长度
        if (s == null || s.length() == 0 || s.length() > 3) {
            return false;
        }

        // 校验 IP 是否是 '0XX' 格式
        if (s.length() > 1 && s.charAt(0) == '0') {
            return false;
        }

        int ipSegment = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch < '0' || ch > '9') {
                return false;
            }
            ipSegment = ipSegment * 10 + ch - '0';
            if (ipSegment > 255) {
                return false;
            }
        }
        return true;
    }
}
