package com.kuaishou.raymond.algorithm.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.google.common.collect.Sets;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;
import java.util.Set;

/**
 * @Author: raymond
 * @Datetime: 2023/12/7 12:55
 * @Description: TailNumber 作用规则：
 * 1. 标准格式是三个分号，如 A;B;C;D，其中 A, B, C, D 均可为空。
 * 2. A 代表取模的底，取值为任意正整数。
 * 3. B 代表取模的有效区间，取值为一个或多个区间，如果包含多个区间，区间之间使用英文逗号分隔，如果区间上下限不同，则需要用英文减号(-)做连字符，如 1-9,11,23-49。
 * 4. C 代表白名单，可为一个或多个任意正整数，如果包含多个值，值与值之间需要使用英文逗号(,)做分割。
 * 5. D 代表黑名单，可为一个或多个任意正整数，如果包含多个值，值与值之间需要使用英文逗号(,)做分割。
 * 匹配顺序为从右向左寻找英文分号(;)，并依次划分 A,B,C,D，请仔细阅读使用说明。
 * PS: 此方法与快手 KConf 的 TailNumber 使用姿势略有不同，请注意。
 * 以 ";;1,2,3,4" 为例：
 * - 在快手，是在为 1,2,3,4 加白。
 * - 在这里，是在为 1,2,3,4 加黑。
 * 具体原因是快手可能使用的是从左向右扫描分号，而这里是从右向左扫描分号，我个人更喜欢第二种方式。
 */
@Data
public class TailNumber {

    private static final String SEMICOLON = ";";

    private static final String DASH_MARK = "-";

    private static final String COMMA = ",";

    private Integer modBase;

    private final List<Range<Integer>> modRangeList = Lists.newArrayList();

    private final Set<String> whitelist = Sets.newHashSet();

    private final Set<String> blacklist = Sets.newHashSet();

    public TailNumber(String tailNumber) {
        parseTailNumber(tailNumber);
    }

    public static TailNumber from(String tailNumber) {
        return new TailNumber(tailNumber);
    }

    private void parseTailNumber(String tailNumber) {
        if (StringUtils.isBlank(tailNumber)) {
            return;
        }
        int blacklistIndex = tailNumber.lastIndexOf(SEMICOLON);

        // 取黑名单
        if (blacklistIndex + 1 <= tailNumber.length() - 1) {
            String blacklistString = tailNumber.substring(blacklistIndex + 1);
            initSpecialList(this.getBlacklist(), blacklistString);
        }

        int idx = blacklistIndex - 1;
        while (idx >= 0 && tailNumber.charAt(idx) != ';') {
            --idx;
        }

        // 取白名单
        int whitelistIdx = idx;
        if (idx >= 0) {
            String whitelistString = tailNumber.substring(idx + 1, blacklistIndex);
            initSpecialList(this.getWhitelist(), whitelistString);
        }

        --idx;
        while (idx >= 0 && tailNumber.charAt(idx) != ';') {
            --idx;
        }

        // 取模
        if (idx >= 0) {
            String modBaseString = tailNumber.substring(0, idx);
            initModBase(modBaseString);
            if (this.getModBase() != null) {
                // 取模区间
                String modRangeString = tailNumber.substring(idx + 1, whitelistIdx);
                initModRange(modRangeString);
            }
        }
    }

    /**
     * 初始化取模区间，可能是 0，可能是 0-9，也可能是 0-3,7-8
     *
     * @param modRangeString 取模区间
     */
    private void initModRange(String modRangeString) {
        String[] modRangeArray = modRangeString.split(COMMA);
        for (String modRangeSegment : modRangeArray) {
            if (StringUtils.isBlank(modRangeSegment)) {
                continue;
            }
            String[] modRangeSegmentArray = modRangeSegment.split(DASH_MARK);
            if (modRangeSegmentArray.length == 1) {
                // 如果只有一个数字，没有连字符 "-" 。
                String singleNumberString = modRangeSegmentArray[0].trim();
                if (NumberUtils.isDigits(singleNumberString)) {
                    int singleNumber = Integer.parseInt(singleNumberString);
                    modRangeList.add(Range.closed(singleNumber, singleNumber));
                }
            } else if (modRangeSegmentArray.length == 2) {
                String firstNumber = modRangeSegmentArray[0].trim();
                String secondNumber = modRangeSegmentArray[1].trim();
                if (NumberUtils.isDigits(firstNumber) && NumberUtils.isDigits(secondNumber)) {
                    modRangeList.add(Range.closed(Integer.parseInt(firstNumber), Integer.parseInt(secondNumber)));
                }
            }
        }
    }

    /**
     * 初始化取模的底
     *
     * @param modBaseString 取模的底
     */
    private void initModBase(String modBaseString) {
        modBaseString = modBaseString.trim();
        if (StringUtils.isNotBlank(modBaseString) && NumberUtils.isDigits(modBaseString)) {
            this.modBase = Integer.parseInt(modBaseString);
        }
    }

    /**
     * 初始化特殊名单
     *
     * @param specialList 要添加到的特殊名单
     * @param inputList   特殊名单
     */
    private void initSpecialList(Set<String> specialList, String inputList) {
        String[] idArray = inputList.split(COMMA);
        for (String id : idArray) {
            id = id.trim();
            if (!StringUtils.isBlank(id) && NumberUtils.isDigits(id)) {
                specialList.add(id);
            }
        }
    }

    public boolean isOnFor(long id) {
        String idString = String.valueOf(id);
        // 检查黑名单
        if (this.getBlacklist().contains(idString)) {
            return false;
        }
        // 检查白名单
        if (this.getWhitelist().contains(idString)) {
            return true;
        }
        // 检查取模结果
        if (CollectionUtils.isNotEmpty(this.getModRangeList())) {
            long realModResult = id % this.getModBase();
            for (Range<Integer> modRange : this.getModRangeList()) {
                if (modRange.lowerEndpoint() <= realModResult && modRange.upperEndpoint() >= realModResult) {
                    return true;
                }
            }
        }
        return false;
    }
}
