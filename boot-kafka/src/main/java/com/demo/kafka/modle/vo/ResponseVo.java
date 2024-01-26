package com.demo.kafka.modle.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一的返回对象VO
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ResponseVo", description = "统一的返回对象")
public class ResponseVo<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码")
    private String code;

    /**
     * 状态码对应描述信息
     */
    @ApiModelProperty(value = "状态码对应描述信息")
    private String msg;

    /**
     * 数据
     */
    @ApiModelProperty(value = "数据")
    private T data;
}
