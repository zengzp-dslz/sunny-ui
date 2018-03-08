package io.sunny.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.UUID;

public class MacAddressUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(MacAddressUtil.class);

    private MacAddressUtil() {
    }

    public static byte[] getMac(InetAddress ia) throws SocketException {
        return NetworkInterface.getByInetAddress(ia).getHardwareAddress();
    }

    public static String getMacString(InetAddress ia) throws SocketException {
        byte[] mac = getMac(ia);
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                sb.append("-");
            }
            int temp = mac[i] & 0xff;
            String str = Integer.toHexString(temp);
            if (str.length() == 1) {
                sb.append("0").append(str);
            } else {
                sb.append(str);
            }
        }
        return sb.toString().toUpperCase();
    }

    public static byte[] getLocalMac() throws SocketException, UnknownHostException {
        return getMac(InetAddress.getLocalHost());
    }

    public static String getLocalMacString() throws SocketException, UnknownHostException {
        return getMacString(InetAddress.getLocalHost());
    }

    public static String simpleMachineId(int length) {
        String id;
        try {
            id = getLocalMacString();
        } catch (Exception e) {
            id = UUID.randomUUID().toString().toUpperCase();
            LOGGER.warn("Cannot get local mac address, fallback to using random uuid {}", id);
        }
        id = StringUtils.replace(id, "-", "");
        return StringUtils.substring(id, id.length() - length);
    }
}
