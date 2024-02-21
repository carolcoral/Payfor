package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.entity.PayforEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface PayforMapper {
    
    @Select({"<script>" +
                     "select *\n" +
                     "from payfort\n" +
                     "where 1=1" +
                     "<if test='filter != null and filter != \"\"'>" +
                     " and (chargeAccount like \"%#{filter}%\" or chargeCardNumber like \"%#{filter}%\") " +
                     "</if>" +
                     "<if test='beginTime != null and endTime != null'>" +
                     " and (DATE_FORMAT(createDate, \"%Y-%m-%d\") <= #{endTime} and DATE_FORMAT(createDate, " +
                     "\"%Y-%m-%d\")" +
                     " >= #{beginTime})" +
                     "</if>" +
                     " order by id desc limit #{startIndex},#{pageSize}" +
                     "</script>"})
    List<PayforEntity> selectListByFilter(@Param("filter") String filter,
                                          @Param("startIndex") int startIndex,
                                          @Param("pageSize") int pageSize,
                                          @Param("beginTime") String beginTime,
                                          @Param("endTime") String endTime);
    
    @Select({"<script>" +
                     "select count(1)\n" +
                     "from payfort\n" +
                     "where 1=1" +
                     "<if test='filter != null and filter != \"\"'>" +
                     " and (chargeAccount like '%#{filter}%' or chargeCardNumber like '%#{filter}%') " +
                     "</if>" +
                     "<if test='beginTime != null and endTime != null'>" +
                     " and (DATE_FORMAT(createDate, '%Y-%m-%d') <= #{endTime} and DATE_FORMAT(createDate, '%Y-%m-%d')" +
                     " >= #{beginTime})" +
                     "</if>" +
                     " order by id desc" +
                     "</script>"})
    int selectCount(@Param("filter") String filter,
                    @Param("beginTime") String beginTime,
                    @Param("endTime") String endTime);
    
    @Insert({"INSERT INTO payfor_dev.payfort (chargeAccount, chargeType, chargeCardNumber, chargeCardSecret, createDate, primaryUuid) " +
                     "VALUES (#{chargeAccount}, #{chargeType}, #{chargeCardNumber}, #{chargeCardSecret}, #{createDate}, #{primaryUuid})"})
    int insert(@Param("chargeAccount") String chargeAccount,
               @Param("chargeType") String chargeType,
               @Param("chargeCardNumber") String chargeCardNumber,
               @Param("chargeCardSecret") String chargeCardSecret,
               @Param("createDate") Date createDate,
               @Param("primaryUuid") String primaryUuid);
}
