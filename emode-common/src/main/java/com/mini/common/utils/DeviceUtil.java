package com.mini.common.utils;

import com.mini.common.enums.str.Device;
import com.mini.common.utils.http.ServletUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhl
 * @create 2024/5/16 14:45
 */
public class DeviceUtil {
    private DeviceUtil() {
    }

    public static Device getDevice() {
        HttpServletRequest request = ServletUtil.getRequest();
        assert request != null;
        return getDevice(request);
    }

    public static Device getDevice(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent.contains("Windows") || userAgent.contains("Mac")) {
            return Device.PC;
        } else if (userAgent.contains("miniProgram")) {
            return Device.MINI_APP;
        } else {
            return Device.OTHER;
        }
    }

}
