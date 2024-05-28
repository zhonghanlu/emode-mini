package com.mini.common.utils;

import java.util.UUID;

public class RequestIdUtils {
    private static final ThreadLocal<UUID> requestIdHolder = new ThreadLocal<>();

    private RequestIdUtils() {
    }

    public static void generateRequestId() {
        requestIdHolder.set(UUID.randomUUID());
    }

    public static void generateRequestId(UUID uuid) {
        requestIdHolder.set(uuid);
    }

    public static UUID getRequestId() {
        return (UUID) requestIdHolder.get();
    }

    public static void removeRequestId() {
        requestIdHolder.remove();
    }
}