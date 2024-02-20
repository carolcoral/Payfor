package com.ruoyi.system.domain.vo;

import com.ruoyi.system.domain.entity.FileListEntity;
import lombok.Data;

import java.util.List;

@Data
public class PayforVO {

    private String chargeAccount;

    private String chargeType;

    private String chargeCardNumber1;

    private String chargeCardSecret1;

    private String chargeCardNumber2;

    private String chargeCardSecret2;

    private String chargeCardNumber3;

    private String chargeCardSecret3;

    private String chargeCardNumber4;

    private String chargeCardSecret4;

    private String chargeCardNumber5;

    private String chargeCardSecret5;

    private String chargeCardNumber6;

    private String chargeCardSecret6;
    
    private List<FileListEntity> fileList;

}
