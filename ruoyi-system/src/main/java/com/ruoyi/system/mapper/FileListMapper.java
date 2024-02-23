package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.entity.FileListEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface FileListMapper {
    
    @Select({"select * from filelist where primaryUuid = #{primaryUuid}"})
    List<FileListEntity> listFiles(@Param("primaryUuid") String primaryUuid);
    
    @Insert({"INSERT INTO filelist (fileName, filePath, primaryUuid) VALUES (#{filename}, #{filepath}, #{primaryUuid})"})
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insert(@Param("filename") String filename, @Param("filepath") String filepath, @Param("primaryUuid") String primaryUuid);
    
    @Select({"select * from filelist where primaryUuid = #{primaryUuid}"})
    List<FileListEntity> selectList(@Param("primaryUuid") String primaryUuid);
    
    @Update("truncate table filelist")
    void truncateTable();
}
