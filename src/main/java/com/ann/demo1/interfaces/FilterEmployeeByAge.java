package com.ann.demo1.interfaces;

import com.ann.demo1.entity.Employee;

/**
 * @author longquan
 * @date 2020/4/29 10:28 上午
 * 按年龄过滤
 */
public class FilterEmployeeByAge implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee employee) {
        return employee.getAge() >= 35;
    }
}
