package com.ruoyi.system.service;


import com.ruoyi.system.domain.entity.FileListEntity;
import com.ruoyi.system.domain.entity.PayforEntity;
import com.ruoyi.system.domain.vo.ChargeVO;

import java.util.List;

public interface ChargeService {
    
    /**
     * 新增文件信息
     * @param fileListEntity 文件信息对象
     * @return 生成的ID，如果对象为空则返回 -1
     */
    int insertFile(FileListEntity fileListEntity);
    
    /**
     * 新增支付信息
     * @param payforEntities 支付对象
     * @return 唯一标识UUID
     */
    String createPayfor(List<PayforEntity> payforEntities);
    
    /**
     * 遍历全部支付信息结合文件信息并组装
     * @param filter 过滤条件
     * @param page 页码
     * @param pageSize 页数
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return 支付信息结果
     */
    List<ChargeVO> listPayfor(String filter, int page, int pageSize, String beginTime, String endTime);
    
    /**
     * 支付信息条数
     * @param filter 过滤条件
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return 总条数
     */
    Integer listTotal(String filter, String beginTime, String endTime);
    
    /**
     * 清空数据表并重置ID序列
     * @param tableName 表名，如果没有则清空全部数据表（fileList、payfor）
     * @return 清理结果
     */
    boolean cleanTable(String tableName);

}
