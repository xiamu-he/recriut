package com.qzx.recruit.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author qzx
 * @create 2021-10-22 21:12
 * @function 登录未授权
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class UnauthorizedExecption extends RuntimeException {
    private String msg;
}