package com.ann.demo1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author longquan
 * @date 2020/4/29 10:14 上午
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private Integer id;
    private String name;
    private Integer age;
    private double salary;

    public Employee(int age){
        this.age = age;
    }

    public Employee(int id,int age){
        this.id = id;
        this.age = age;
    }
}
