package com.ann.demo1.interfaces;

import com.ann.demo1.entity.Employee;

/**
 * @author longquan
 * @date 2020/4/29 11:35 上午
 * 按工资过滤
 */
public class FilterEmployeeBySalary implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() >= 5000;
    }
}
