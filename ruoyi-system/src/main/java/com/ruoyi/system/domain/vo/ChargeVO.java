package com.ruoyi.system.domain.vo;

import lombok.Data;
import net.sf.jsqlparser.util.validation.metadata.DatabaseException;

import java.util.Date;
import java.util.List;

@Data
public class ChargeVO {
    
    private int id;
    
    private String chargeAccount;
    
    private String chargeType;
    
    private String chargeCardNumber;
    
    private String chargeCardSecret;
    
    private Date createDate;
    
    private List<String> fileList;
}
