package com.lrj.film.common;

/**
 * @author lrj
 */
public class StringUtil {
    public static final String NUMBER_REGEXP = "(\\d*)(\\.?)(\\d+)";

    public static boolean isEmpty(String str) {
        return !isNotEmpty(str);
    }

    public static boolean isNotEmpty(String str) {
        return str != null && str.trim().length() > 0;
    }

    public static boolean isNumber(String str) {
        return isNotEmpty(str) && str.trim().matches(NUMBER_REGEXP);
    }
}
