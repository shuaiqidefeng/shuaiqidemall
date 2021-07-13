package com.feng.shuaiqidemall.utils;

import java.util.UUID;

public class IdUtils {

    public static UUID uuid() {
        return UUID.randomUUID();
    }

    public static String uuidWithDashes() {
        return uuid().toString();
    }

    public static String uuidWithoutDashes() {
        return uuidWithDashes().replace("-", "");
    }

    public static UUID uuidFromString(String uuid) {
        return UUID.fromString(uuid);
    }

}
