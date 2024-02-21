package com.ruoyi.web.controller.payfor;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.constant.BaseResult;
import com.ruoyi.system.domain.entity.FileListEntity;
import com.ruoyi.system.domain.entity.PayforEntity;
import com.ruoyi.system.domain.vo.ChargeVO;
import com.ruoyi.system.domain.vo.PayforVO;
import com.ruoyi.system.service.ChargeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping(value = "/pay/charge")
public class ChargeController {
    
    @Autowired
    private ChargeService chargeService;

    @CrossOrigin(value = "*")
    @PostMapping(value = "/create")
    public BaseResult<String> create(@RequestBody PayforVO payforVO) {
        BaseResult<String> baseResult = new BaseResult<>();
        baseResult.setCode(200);
        baseResult.setMessage("success");
        try {
            List<PayforEntity> payforEntities = new ArrayList<>();
            String chargeAccount = payforVO.getChargeAccount();
            String chargeType = payforVO.getChargeType();
            String uuid = UUID.randomUUID().toString();
            
            
            if (StringUtils.isNotEmpty(payforVO.getChargeCardNumber1())) {
                PayforEntity payforEntity = new PayforEntity();
                payforEntity.setPrimaryUuid(uuid);
                payforEntity.setChargeAccount(chargeAccount);
                payforEntity.setChargeType(chargeType);
                payforEntity.setChargeCardNumber(payforVO.getChargeCardNumber1());
                payforEntity.setChargeCardSecret(payforVO.getChargeCardSecret1());
                payforEntities.add(payforEntity);
            }
            
            if (StringUtils.isNotEmpty(payforVO.getChargeCardNumber2())) {
                PayforEntity payforEntity2 = new PayforEntity();
                payforEntity2.setPrimaryUuid(uuid);
                payforEntity2.setChargeAccount(chargeAccount);
                payforEntity2.setChargeType(chargeType);
                payforEntity2.setChargeCardNumber(payforVO.getChargeCardNumber2());
                payforEntity2.setChargeCardSecret(payforVO.getChargeCardSecret2());
                payforEntities.add(payforEntity2);
            }
            
            if (StringUtils.isNotEmpty(payforVO.getChargeCardNumber3())) {
                PayforEntity payforEntity3 = new PayforEntity();
                payforEntity3.setPrimaryUuid(uuid);
                payforEntity3.setChargeAccount(chargeAccount);
                payforEntity3.setChargeType(chargeType);
                payforEntity3.setChargeCardNumber(payforVO.getChargeCardNumber3());
                payforEntity3.setChargeCardSecret(payforVO.getChargeCardSecret3());
                payforEntities.add(payforEntity3);
            }
            
            if (StringUtils.isNotEmpty(payforVO.getChargeCardNumber4())) {
                PayforEntity payforEntity4 = new PayforEntity();
                payforEntity4.setPrimaryUuid(uuid);
                payforEntity4.setChargeAccount(chargeAccount);
                payforEntity4.setChargeType(chargeType);
                payforEntity4.setChargeCardNumber(payforVO.getChargeCardNumber4());
                payforEntity4.setChargeCardSecret(payforVO.getChargeCardSecret4());
                payforEntities.add(payforEntity4);
            }
            
            if (StringUtils.isNotEmpty(payforVO.getChargeCardNumber5())) {
                PayforEntity payforEntity5 = new PayforEntity();
                payforEntity5.setPrimaryUuid(uuid);
                payforEntity5.setChargeAccount(chargeAccount);
                payforEntity5.setChargeType(chargeType);
                payforEntity5.setChargeCardNumber(payforVO.getChargeCardNumber5());
                payforEntity5.setChargeCardSecret(payforVO.getChargeCardSecret5());
                payforEntities.add(payforEntity5);
            }
            
            if (StringUtils.isNotEmpty(payforVO.getChargeCardNumber6())) {
                PayforEntity payforEntity6 = new PayforEntity();
                payforEntity6.setPrimaryUuid(uuid);
                payforEntity6.setChargeAccount(chargeAccount);
                payforEntity6.setChargeType(chargeType);
                payforEntity6.setChargeCardNumber(payforVO.getChargeCardNumber6());
                payforEntity6.setChargeCardSecret(payforVO.getChargeCardSecret6());
                payforEntities.add(payforEntity6);
            }
            
            if (payforEntities.isEmpty() && !payforVO.getFileList().isEmpty()) {
                PayforEntity payforEntity7 = new PayforEntity();
                payforEntity7.setPrimaryUuid(uuid);
                payforEntity7.setChargeAccount(chargeAccount);
                payforEntity7.setChargeType(chargeType);
                payforEntities.add(payforEntity7);
            }
            
            String primaryUuid = chargeService.createPayfor(payforEntities);
            
            
            if (!payforVO.getFileList().isEmpty()) {
                for (FileListEntity fileListEntity : payforVO.getFileList()) {
                    fileListEntity.setPrimaryUuid(uuid);
                    chargeService.insertFile(fileListEntity);
                }
            }
            
            baseResult.setData(primaryUuid);
        } catch (Exception e) {
            log.error("新增支付数据异常", e);
            baseResult.setCode(500);
            baseResult.setMessage("failed");
        }
        return baseResult;
        
        
    }

//    @PreAuthorize("@ss.hasPermi('pay:charge:list')")
    @GetMapping(value = "/list")
    public BaseResult<HashMap> listPayfor(@RequestParam("pageNum") int pageNum,
                                          @RequestParam("pageSize") int pageSize,
                                          @RequestParam(value = "filter", required = false) String filter,
                                          @RequestParam(value = "params[beginTime]", required = false) String beginTime,
                                          @RequestParam(value = "params[endTime]", required = false) String endTime) {
        BaseResult<HashMap> baseResult = new BaseResult<>();
        baseResult.setCode(200);
        baseResult.setMessage("success");
        try {
            if (StringUtils.isEmpty(filter)) {
                filter = "";
            }
            List<ChargeVO> payforEntities = chargeService.listPayfor(filter, pageNum, pageSize, beginTime, endTime);
            Integer total = chargeService.listTotal(filter, beginTime, endTime);
            HashMap<String, Object> map = new HashMap<>();
            map.put("data", payforEntities);
            map.put("total", total);
            baseResult.setData(map);
        } catch (Exception e) {
            log.error("遍历支付数据异常", e);
            baseResult.setCode(500);
            baseResult.setMessage("failed");
        }
        return baseResult;
    }
    
}
