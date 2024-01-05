package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
  @TableName("sys_info")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("ID")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("设备名称")
      private String name;

      @ApiModelProperty("密码")
      private String password;

      @ApiModelProperty("运行状态")

      private String equstatu;

      @ApiModelProperty("唯一标识")

      private String equid;

      @ApiModelProperty("需求信息")

      private String need;


      @ApiModelProperty("位置")
      private String address;

      @ApiModelProperty("投入使用时间")
      private LocalDateTime createTime;


}
