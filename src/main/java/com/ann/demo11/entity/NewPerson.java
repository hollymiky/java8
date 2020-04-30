package com.ann.demo11.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Optional;

/**
 * @author longquan
 * @date 2020/4/30 5:39 下午
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewPerson {

    //  Optional没有赋值的话默认null
    private Optional<Children> children = Optional.empty();
}
