package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.system.domain.entity.FileListEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileListMapper {

    @Select({"select * from filelist where primaryUuid = #{primaryUuid}"})
    List<FileListEntity> listFiles(@Param("primaryUuid") String primaryUuid);

    @Insert({"INSERT INTO payfor_dev.filelist (id, fileName, filePath, primaryUuid) VALUES (generated, #{filename}, #{filepath}, #{primaryUuid})"})
    int insert(@Param("filename") String filename, @Param("filepath") String filepath, @Param("primaryUuid") String primaryUuid);

    @Select({"select * from filelist where primaryUuid = #{primaryUuid}"})
    List<FileListEntity> selectList(@Param("primaryUuid") String primaryUuid);
}
