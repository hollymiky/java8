package com.ann.demo9.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author longquan
 * @date 2020/4/30 3:18 下午
 * 交易类
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private Trader trader;
    private int year;
    private int value;
}
