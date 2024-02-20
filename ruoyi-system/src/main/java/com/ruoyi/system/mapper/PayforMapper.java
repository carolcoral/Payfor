package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.system.domain.entity.PayforEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface PayforMapper {

    @Select({"select *\n" +
            "from payfort\n" +
            "where chargeAccount like '%'+#{filter}+'%'\n" +
            "   or chargeCardNumber like '%'+#{filter}+'%' order by id desc limit #{startIndex},#{pageSize}"})
    List<PayforEntity> selectListByFilter(@Param("filter") String filter,
                                  @Param("startIndex") int startIndex,
                                  @Param("pageSize") int pageSize);

    @Select({"select * from payfort order by id desc limit #{startIndex},#{pageSize}"})
    List<PayforEntity> selectList(@Param("startIndex") int startIndex,
                                  @Param("pageSize") int pageSize);

    @Select({"select count(1)\n" +
            "from payfort\n" +
            "where chargeAccount like '%'+#{filter}+'%'\n" +
            "   or chargeCardNumber like '%'+#{filter}+'%'"})
    int selectCountByFilter(@Param("filter") String filter);

    @Select({"select count(1) from payfort order by id desc"})
    int selectCount();

    @Insert({"INSERT INTO payfor_dev.payfort (chargeAccount, chargeType, chargeCardNumber, chargeCardSecret, createDate, primaryUuid) " +
            "VALUES (#{chargeAccount}, #{chargeType}, #{chargeCardNumber}, #{chargeCardSecret}, #{createDate}, #{primaryUuid})"})
    int insert(@Param("chargeAccount") String chargeAccount,
               @Param("chargeType") String chargeType,
               @Param("chargeCardNumber") String chargeCardNumber,
               @Param("chargeCardSecret") String chargeCardSecret,
               @Param("createDate") Date createDate,
               @Param("primaryUuid") String primaryUuid);
}
