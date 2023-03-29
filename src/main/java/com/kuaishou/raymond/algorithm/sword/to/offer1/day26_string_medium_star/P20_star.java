package com.kuaishou.raymond.algorithm.sword.to.offer1.day26_string_medium_star;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 剑指 Offer 20. 表示数值的字符串
 * 合法：["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
 * 非法："12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"
 */
public class P20_star {

    private static final Map<State, Map<CharType, State>> TRANSFER = new EnumMap<>(State.class);

    private static final Set<State> VALID_STATE = new HashSet<>();

    static {

        VALID_STATE.add(State.STATE_INTEGER);
        VALID_STATE.add(State.STATE_POINT);
        VALID_STATE.add(State.STATE_FRACTION);
        VALID_STATE.add(State.STATE_EXP_NUMBER);
        VALID_STATE.add(State.STATE_END);

        Map<CharType, State> initialMap = new EnumMap<>(CharType.class);
        initialMap.put(CharType.CHAR_SPACE, State.STATE_INITIAL);
        initialMap.put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
        initialMap.put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
        initialMap.put(CharType.CHAR_SIGN, State.STATE_INT_SIGN);
        TRANSFER.put(State.STATE_INITIAL, initialMap);

        Map<CharType, State> intSignMap = new EnumMap<>(CharType.class);
        intSignMap.put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
        intSignMap.put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
        TRANSFER.put(State.STATE_INT_SIGN, intSignMap);

        Map<CharType, State> integerMap = new EnumMap<>(CharType.class);
        integerMap.put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
        integerMap.put(CharType.CHAR_EXP, State.STATE_EXP);
        integerMap.put(CharType.CHAR_POINT, State.STATE_POINT);
        integerMap.put(CharType.CHAR_SPACE, State.STATE_END);
        TRANSFER.put(State.STATE_INTEGER, integerMap);

        Map<CharType, State> pointMap = new EnumMap<>(CharType.class);
        pointMap.put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
        pointMap.put(CharType.CHAR_EXP, State.STATE_EXP);
        pointMap.put(CharType.CHAR_SPACE, State.STATE_END);
        TRANSFER.put(State.STATE_POINT, pointMap);

        Map<CharType, State> pointWithoutIntMap = new EnumMap<>(CharType.class);
        pointWithoutIntMap.put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
        TRANSFER.put(State.STATE_POINT_WITHOUT_INT, pointWithoutIntMap);

        Map<CharType, State> fractionMap = new EnumMap<>(CharType.class);
        fractionMap.put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
        fractionMap.put(CharType.CHAR_EXP, State.STATE_EXP);
        fractionMap.put(CharType.CHAR_SPACE, State.STATE_END);
        TRANSFER.put(State.STATE_FRACTION, fractionMap);

        Map<CharType, State> expMap = new EnumMap<>(CharType.class);
        expMap.put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
        expMap.put(CharType.CHAR_SIGN, State.STATE_EXP_SIGN);
        TRANSFER.put(State.STATE_EXP, expMap);

        Map<CharType, State> expSignMap = new EnumMap<>(CharType.class);
        expSignMap.put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
        TRANSFER.put(State.STATE_EXP_SIGN, expSignMap);

        Map<CharType, State> expNumberMap = new EnumMap<>(CharType.class);
        expNumberMap.put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
        expNumberMap.put(CharType.CHAR_SPACE, State.STATE_END);
        TRANSFER.put(State.STATE_EXP_NUMBER, expNumberMap);

        Map<CharType, State> endMap = new EnumMap<>(CharType.class);
        endMap.put(CharType.CHAR_SPACE, State.STATE_END);
        TRANSFER.put(State.STATE_END, endMap);
    }

    public boolean isNumber(String s) {
        int length = s.length();
        State state = State.STATE_INITIAL;
        for (int i = 0; i < length; i++) {
            CharType charType = toCharType(s.charAt(i));
            if (!TRANSFER.get(state).containsKey(charType)) {
                return false;
            } else {
                state = TRANSFER.get(state).get(charType);
            }
        }
        return VALID_STATE.contains(state);
    }

    public CharType toCharType(char ch) {
        if (ch >= '0' && ch <= '9') {
            return CharType.CHAR_NUMBER;
        } else if (ch == 'e' || ch == 'E') {
            return CharType.CHAR_EXP;
        } else if (ch == '.') {
            return CharType.CHAR_POINT;
        } else if (ch == '+' || ch == '-') {
            return CharType.CHAR_SIGN;
        } else if (ch == ' ') {
            return CharType.CHAR_SPACE;
        } else {
            return CharType.CHAR_ILLEGAL;
        }
    }

}

enum State {
    STATE_INITIAL, // 初始状态
    STATE_INT_SIGN, // 正负号标记
    STATE_INTEGER, // 数字
    STATE_POINT, // 小数点
    STATE_POINT_WITHOUT_INT, // 有小数点但是没有数字
    STATE_FRACTION, // 分数/小数
    STATE_EXP, // 幂
    STATE_EXP_SIGN, // 幂符号
    STATE_EXP_NUMBER, // 带有数字的幂
    STATE_END, // 结束状态
}

enum CharType {
    CHAR_NUMBER, // 数字 0-9
    CHAR_EXP, // 指数符号 e | E
    CHAR_POINT, // 小数点 .
    CHAR_SIGN, // 正负号 + | -
    CHAR_SPACE, // 空格
    CHAR_ILLEGAL
}
