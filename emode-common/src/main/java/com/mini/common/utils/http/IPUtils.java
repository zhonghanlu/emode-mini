package com.mini.common.utils.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class IPUtils {

    private IPUtils() {
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(IPUtils.class);

    public static String getIp() {
        String ip = getLocalIp();
        if (ip == null || ip.isEmpty()) {
            ip = getLocalIp2();
        }
        return ip;
    }

    private static String getLocalIp() {
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface networkInterface = en.nextElement();
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    if (!address.isLoopbackAddress() && !address.isLinkLocalAddress() && !address.isSiteLocalAddress()) {
                        String ipAddress = address.getHostAddress();
                        LOGGER.info("获取到的IP为【{}】", ipAddress);
                        return ipAddress;
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("getLocalIP1 获取IP报错", e);
        }
        return null;
    }

    public static String getLocalIp2() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            LOGGER.error("getLocalIp2 获取IP报错", e);
        }
        return null;
    }


    public static String getServerIP() {
        InetAddress[] inetAdds;
        String serverIP = null;
        try {
            inetAdds = InetAddress.getAllByName(InetAddress.getLocalHost().getHostName());
            for (int i = 0; i < inetAdds.length; i++) {
                serverIP = inetAdds[i].getHostAddress();
            }

        } catch (UnknownHostException e) {
            LOGGER.error("读取IP地址异常", e);
        }
        return serverIP;
    }


}