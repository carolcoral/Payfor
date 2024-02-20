package com.ruoyi.common.utils;

import org.springframework.util.DigestUtils;

public class DataUtils {
    
    public static String md5(String value) {
        return DigestUtils.md5DigestAsHex(value.getBytes());
    }
    
}
