package com.mirror.weblog.admin.model.vo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author mirror
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@ApiModel(value = "修改用户密码 VO")
public class UpdateAdminUserPasswordReqVO {

    @NotBlank(message = "用户名不能为空")
//    @ApiModelProperty(value = "用户名")
    private String username;

    @NotBlank(message = "密码不能为空")
//    @ApiModelProperty(value = "密码")
    private String password;
}
