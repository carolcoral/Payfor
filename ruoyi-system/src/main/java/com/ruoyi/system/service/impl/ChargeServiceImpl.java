package com.ruoyi.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.entity.FileListEntity;
import com.ruoyi.system.domain.entity.PayforEntity;
import com.ruoyi.system.domain.vo.ChargeVO;
import com.ruoyi.system.mapper.FileListMapper;
import com.ruoyi.system.mapper.PayforMapper;
import com.ruoyi.system.service.ChargeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChargeServiceImpl implements ChargeService {
    
    @Resource
    private FileListMapper fileListMapper;
    
    @Resource
    private PayforMapper payforMapper;
    
    @Override
    public int insertFile(FileListEntity fileListEntity) {
        if (null == fileListEntity) {
            return -1;
        }
        return fileListMapper.insert(fileListEntity.getFileName(), fileListEntity.getFilePath(), fileListEntity.getPrimaryUuid());
    }
    
    @Override
    public List<ChargeVO> listPayfor(String filter, int page, int pageSize, String beginTime, String endTime) {
        List<ChargeVO> chargeVOS = new ArrayList<>();
        int startIndex = (page - 1) * pageSize;
        if (StringUtils.isNotEmpty(filter)) {
            filter = "%" + filter + "%";
        }
        List<PayforEntity> payforEntities = payforMapper.selectListByFilter(filter, startIndex, pageSize, beginTime, endTime);
        // filList 组装
        for (PayforEntity payforEntity : payforEntities) {
            ChargeVO chargeVO = new ChargeVO();
            BeanUtils.copyProperties(payforEntity, chargeVO);
            chargeVO.setCreateDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, payforEntity.getCreateDate()));
            String primaryUuid = payforEntity.getPrimaryUuid();
            List<FileListEntity> listEntities = fileListMapper.selectList(primaryUuid);
            if (listEntities.isEmpty()) {
                chargeVO.setFileList(Collections.singletonList(""));
            } else {
                List<String> fileUrls = listEntities.stream().map(FileListEntity::getFilePath).collect(Collectors.toList());
                chargeVO.setFileList(fileUrls);
            }
            chargeVOS.add(chargeVO);
        }
        return chargeVOS;
    }
    
    @Override
    public Integer listTotal(String filter, String beginTime, String endTime) {
        if (StringUtils.isNotEmpty(filter)) {
            filter = "%" + filter + "%";
        }
        return payforMapper.selectCount(filter, beginTime, endTime);
    }
    
    @Override
    public String createPayfor(List<PayforEntity> payforEntities) {
        Date date = new Date();
        for (PayforEntity payforEntity : payforEntities) {
            payforEntity.setCreateDate(date);
            payforMapper.insert(payforEntity.getChargeAccount(),
                    payforEntity.getChargeType(),
                    payforEntity.getChargeCardNumber(),
                    payforEntity.getChargeCardSecret(),
                    payforEntity.getCreateDate(),
                    payforEntity.getPrimaryUuid());
        }
        return payforEntities.get(0).getPrimaryUuid();
    }
}
