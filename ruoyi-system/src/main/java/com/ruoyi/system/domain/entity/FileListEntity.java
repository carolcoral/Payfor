package com.ruoyi.system.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "FILELIST")
public class FileListEntity {
    
    @TableId(value = "id")
    private int id;
    
    @TableField(value = "fileName")
    private String fileName;
    
    @TableField(value = "filePath")
    private String filePath;
    
    @TableField(value = "primaryUuid")
    private String primaryUuid;
}
