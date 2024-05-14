package com.mini.common.utils.str;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhl
 */
public class StrUtil extends StringUtils {

    public static String str(CharSequence cs) {
        return null == cs ? null : cs.toString();
    }

    public static String hide(CharSequence str, int startInclude, int endExclude) {
        return replace(str, startInclude, endExclude, '*');
    }

    public static String replace(CharSequence str, int startInclude, int endExclude, char replacedChar) {
        if (isEmpty(str)) {
            return str(str);
        } else {
            int strLength = str.length();
            if (startInclude > strLength) {
                return str(str);
            } else {
                if (endExclude > strLength) {
                    endExclude = strLength;
                }

                if (startInclude > endExclude) {
                    return str(str);
                } else {
                    char[] chars = new char[strLength];

                    for(int i = 0; i < strLength; ++i) {
                        if (i >= startInclude && i < endExclude) {
                            chars[i] = replacedChar;
                        } else {
                            chars[i] = str.charAt(i);
                        }
                    }

                    return new String(chars);
                }
            }
        }
    }

}
