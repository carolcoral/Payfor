package com.ruoyi.common.constant;

import lombok.Data;

@Data
public class BaseResult<T> {
    
    private int code;
    
    private String message;
    
    private T data;
    
}
