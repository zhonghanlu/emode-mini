package com.mini.common.utils.webmvc;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class IDGenerator {

    private static final Pattern PATTERN_HOSTNAME = Pattern.compile("^.*\\D+([0-9]+)$");

    private static final long OFFSET = LocalDate.of(2000, 1, 1).atStartOfDay(ZoneId.of("Z")).toEpochSecond();

    private static final long MAX_NEXT = 0b11111_11111111_111L;

    private static final long SHARD_ID = getServerIdAsLong();

    private static long offset = 0;

    private static long lastEpoch = 0;

    public static long next() {
        return nextId(System.currentTimeMillis() / 1000);
    }

    public static String nextName() {
        return "用户-" + nextId(System.currentTimeMillis() / 1000);
    }

    private static synchronized long nextId(long epochSecond) {
        if (epochSecond < lastEpoch) {
            epochSecond = lastEpoch;
        }
        if (lastEpoch != epochSecond) {
            lastEpoch = epochSecond;
            reset();
        }
        offset++;
        long next = offset & MAX_NEXT;
        if (next == 0) {
            return nextId(epochSecond + 1);
        }
        return generateId(epochSecond, next, SHARD_ID);
    }

    private static void reset() {
        offset = 0;
    }

    private static long generateId(long epochSecond, long next, long shardId) {
        return ((epochSecond - OFFSET) << 21) | (next << 5) | shardId;
    }

    private static long getServerIdAsLong() {
        try {
            String hostname = InetAddress.getLocalHost().getHostName();
            Matcher matcher = PATTERN_HOSTNAME.matcher(hostname);
            if (matcher.matches()) {
                long n = Long.parseLong(matcher.group(1));
                if (n >= 0 && n < 8) {
                    return n;
                }
            }
        } catch (UnknownHostException e) {
        }
        return 0;
    }

    public static void main(String[] args) throws InterruptedException {
        Set<Long> set = new HashSet<>();
        for (int i = 0; i < 10000 * 10; i++) {
            set.add(IDGenerator.next());
            Thread.sleep(1);
        }
        log.debug(String.valueOf(set.size()));
    }

}