package com.mini.common.utils;

import com.mini.common.utils.str.StrUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhl
 * @source_auth hutool
 */
public class DesensitizedUtil {

    private static final int CAR_LICENSE_LENGTH_7 = 7;

    private static final int CAR_LICENSE_LENGTH_8 = 8;

    private static final int BANK_CARD_NO_LENGTH = 9;

    public static final Long USER_ID = 0L;

    public DesensitizedUtil() {
        // 无参构造
    }

    public static String desensitized(CharSequence str, DesensitizedType desensitizedType) {
        if (StringUtils.isBlank(str)) {
            return "";
        } else {
            String newStr = String.valueOf(str);
            switch (desensitizedType) {
                case USER_ID:
                    newStr = String.valueOf(USER_ID);
                    break;
                case CHINESE_NAME:
                    newStr = chineseName(String.valueOf(str));
                    break;
                case ID_CARD:
                    newStr = idCardNum(String.valueOf(str), 1, 2);
                    break;
                case FIXED_PHONE:
                    newStr = fixedPhone(String.valueOf(str));
                    break;
                case MOBILE_PHONE:
                    newStr = mobilePhone(String.valueOf(str));
                    break;
                case ADDRESS:
                    newStr = address(String.valueOf(str), 8);
                    break;
                case EMAIL:
                    newStr = email(String.valueOf(str));
                    break;
                case PASSWORD:
                    newStr = password(String.valueOf(str));
                    break;
                case CAR_LICENSE:
                    newStr = carLicense(String.valueOf(str));
                    break;
                case BANK_CARD:
                    newStr = bankCard(String.valueOf(str));
                    break;
                default:
            }

            return newStr;
        }
    }

    public static String chineseName(String fullName) {
        return StringUtils.isBlank(fullName) ? "" : StrUtil.hide(fullName, 1, fullName.length());
    }

    public static String idCardNum(String idCardNum, int front, int end) {
        if (StringUtils.isBlank(idCardNum)) {
            return "";
        } else if (front + end > idCardNum.length()) {
            return "";
        } else {
            return front >= 0 && end >= 0 ? StrUtil.hide(idCardNum, front, idCardNum.length() - end) : "";
        }
    }

    public static String fixedPhone(String num) {
        return StringUtils.isBlank(num) ? "" : StrUtil.hide(num, 4, num.length() - 2);
    }

    public static String mobilePhone(String num) {
        return StringUtils.isBlank(num) ? "" : StrUtil.hide(num, 3, num.length() - 4);
    }

    public static String address(String address, int sensitiveSize) {
        if (StringUtils.isBlank(address)) {
            return "";
        } else {
            int length = address.length();
            return StrUtil.hide(address, length - sensitiveSize, length);
        }
    }

    public static String email(String email) {
        if (StringUtils.isBlank(email)) {
            return "";
        } else {
            int index = StringUtils.indexOf(email, '@');
            return index <= 1 ? email : StrUtil.hide(email, 1, index);
        }
    }

    public static String password(String password) {
        return StringUtils.isBlank(password) ? "" : StringUtils.repeat('*', password.length());
    }

    public static String carLicense(String carLicense) {
        if (StringUtils.isBlank(carLicense)) {
            return "";
        } else {
            if (carLicense.length() == CAR_LICENSE_LENGTH_7) {
                carLicense = StrUtil.hide(carLicense, 3, 6);
            } else if (carLicense.length() == CAR_LICENSE_LENGTH_8) {
                carLicense = StrUtil.hide(carLicense, 3, 7);
            }

            return carLicense;
        }
    }

    public static String bankCard(String bankCardNo) {
        if (StringUtils.isBlank(bankCardNo)) {
            return bankCardNo;
        } else {
            bankCardNo = StringUtils.trim(bankCardNo);
            if (bankCardNo.length() < BANK_CARD_NO_LENGTH) {
                return bankCardNo;
            } else {
                int length = bankCardNo.length();
                int midLength = length - 8;
                StringBuilder buf = new StringBuilder();
                buf.append(bankCardNo, 0, 4);

                for (int i = 0; i < midLength; ++i) {
                    if (i % 4 == 0) {
                        buf.append(' ');
                    }

                    buf.append('*');
                }

                buf.append(' ').append(bankCardNo, length - 4, length);
                return buf.toString();
            }
        }
    }

    public enum DesensitizedType {
        /**
         * 用户id
         */
        USER_ID,
        /**
         * 中文名
         */
        CHINESE_NAME,
        /**
         * 身份证
         */
        ID_CARD,
        /**
         * 手机号
         */
        FIXED_PHONE,
        /**
         * 电话
         */
        MOBILE_PHONE,
        /**
         * 地址
         */
        ADDRESS,
        /**
         * 邮箱
         */
        EMAIL,
        /**
         * 密码
         */
        PASSWORD,
        /**
         * 车牌号
         */
        CAR_LICENSE,
        /**
         * 银行卡号
         */
        BANK_CARD;

        private DesensitizedType() {
        }
    }
}