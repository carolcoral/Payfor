package com.ruoyi.system.service;


import com.ruoyi.system.domain.entity.FileListEntity;
import com.ruoyi.system.domain.entity.PayforEntity;
import com.ruoyi.system.domain.vo.ChargeVO;

import java.util.List;

public interface ChargeService {

    int insertFile(FileListEntity fileListEntity);

    String createPayfor(List<PayforEntity> payforEntities);
    
    List<ChargeVO> listPayfor(String filter, int page, int pageSize);

    Integer listTotal(String filter);

}
