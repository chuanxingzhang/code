package com.lp.util;

import java.util.Map;

public class Computer {
    public static String getCurrentRunningServerComputerName() {
        Map<String, String> map = System.getenv();
        // Windows
        String computerName = map.get("COMPUTERNAME");
        if (computerName == null) {
            // Linux
            String tempHostName = System.getenv().get("HOSTNAME");
            if (tempHostName != null && tempHostName.split("\\.").length > 0) {
                computerName = tempHostName.split("\\.")[0];
            } else {
                computerName = map.get("USER");
            }
        }
        return computerName;
    }
}
