package com.kuaishou.raymond.algorithm.swordtooffer;

public class P5ReplaceSpace {

    public String replaceSpace1(String s) {
        if (s == null) {
            return null;
        }
        return s.replace(" ", "%20");
    }

    /**
     * s = "We are happy."
     * @param s
     * @return
     */
    public String replaceSpace2(String s) {
        if (s == null) {
            return null;
        }
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                builder.append(s.charAt(i));
            } else {
                builder.append("%20");
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        P5ReplaceSpace p5 = new P5ReplaceSpace();
        System.out.println("p5.replaceSpace2(\"We are happy.\") = " + p5.replaceSpace2("We are happy."));
    }

}
