package com.mini.common.utils;

import java.util.UUID;

/**
 * @author zhl
 */
public class RequestIdUtils {
    private static final ThreadLocal<UUID> REQUEST_ID_HOLDER = new ThreadLocal<>();

    private RequestIdUtils() {
    }

    public static void generateRequestId() {
        REQUEST_ID_HOLDER.set(UUID.randomUUID());
    }

    public static void generateRequestId(UUID uuid) {
        REQUEST_ID_HOLDER.set(uuid);
    }

    public static UUID getRequestId() {
        return REQUEST_ID_HOLDER.get();
    }

    public static void removeRequestId() {
        REQUEST_ID_HOLDER.remove();
    }
}