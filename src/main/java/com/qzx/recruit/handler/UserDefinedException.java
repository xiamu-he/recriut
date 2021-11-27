package com.qzx.recruit.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author qzx
 * @create 2021-10-22 21:12
 * @function 自定义异常
 */

//注解：@EqualsAndHashCode，lombok自动生成的equals(Object other) 和 hashCode()方法判定为相等，避免出错。
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class UserDefinedException extends RuntimeException {
    private String msg;
}