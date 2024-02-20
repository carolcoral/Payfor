package com.ruoyi.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value="PAYFORT")
public class PayforEntity {
    
    @TableId(value = "id")
    private int id;

    @TableField(value = "primaryUuid")
    private String primaryUuid;
    
    @TableField(value = "chargeAccount")
    private String chargeAccount;
    
    @TableField(value = "chargeType")
    private String chargeType;
    
    @TableField(value = "chargeCardNumber")
    private String chargeCardNumber;
    
    @TableField(value = "chargeCardSecret")
    private String chargeCardSecret;
    
    @TableField(value = "createDate")
    private Date createDate;
    
}
