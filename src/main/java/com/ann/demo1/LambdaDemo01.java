package com.ann.demo1;

import com.ann.demo1.entity.Employee;
import com.ann.demo1.interfaces.FilterEmployeeByAge;
import com.ann.demo1.interfaces.FilterEmployeeBySalary;
import com.ann.demo1.interfaces.MyPredicate;
import org.junit.Test;

import java.util.*;

/**
 * @author longquan
 * @date 2020/4/29 9:57 上午
 */
public class LambdaDemo01 {

    //  原本的匿名内部类
    @Test
    public void test1() {
        Comparator<Integer> com = new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(01, 02);
            }
        };

        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    //  Lambada 表达式 匿名内部类
    @Test
    public void test2() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    //  例如：
    //  需求1：列出当前企业中员工年龄大于35的员工信息
    //  需求2：列出当前企业中员工工资大于5000的员工信息
    List<Employee> employees = Arrays.asList(
            new Employee(1,"张三", 18, 9999),
            new Employee(2,"里斯", 38, 8888),
            new Employee(3,"王五", 28, 1999),
            new Employee(4,"赵六", 48, 7777),
            new Employee(5,"天启", 58, 6666)
    );

    @Test
    public void test3() {
        //  测试 旧方式
        List<Employee> ages = filterEmpAge(employees);
        for (Employee emp : ages) {
            System.out.println(emp.toString());
        }
    }

    //  1. 旧方式处理需求1
    public List<Employee> filterEmpAge(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();
        for (Employee emp : list) {
            if (emp.getAge() >= 35) {
                emps.add(emp);
            }
        }
        return emps;
    }

    //  2. 旧方式处理需求2
    public List<Employee> filterEmpSalary(List<Employee> list) {
        List<Employee> emps = new ArrayList<>();
        for (Employee emp : list) {
            if (emp.getSalary() >= 5000) {
                emps.add(emp);
            }
        }
        return emps;
    }

    //  旧方式（1、2）都过于繁琐，并且造成大量代码冗余，以往处理方式可以使用 策略设计模式-接口方式实现 MyPredicate<T>


    @Test
    public void test4() {
        List<Employee> employees = filterEmp(this.employees, new FilterEmployeeByAge());
        for (Employee employee : employees) {
            System.out.println(employee);
        }

        System.out.println("-------------------");

        List<Employee> employees1 = filterEmp(this.employees, new FilterEmployeeBySalary());
        for (Employee employee : employees1) {
            System.out.println(employee);
        }
    }

    //  1. 旧方式-优化（策略模式）
    //  但是每次有新的策略都要重新新建实现类，比较麻烦
    public List<Employee> filterEmp(List<Employee> list, MyPredicate<Employee> employeeMyPredicate) {
        List<Employee> emps = new ArrayList<>();
        for (Employee emp : list) {
            if (employeeMyPredicate.test(emp)) {
                emps.add(emp);
            }
        }
        return emps;
    }

    //  优化方式2  匿名内部类
    @Test
    public void test5() {

        List<Employee> list = filterEmp(this.employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() >= 8000;
            }
        });

        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    //  优化方式3  Lambda
    @Test
    public void test6() {
        List<Employee> list = filterEmp(employees, (e) -> e.getSalary() > 8000);
        list.forEach(System.out::println);
    }


    //  优化方式4 stream api
    @Test
    public void test7() {
        //  取工资大于5000的前两位
        employees.stream()
                .filter((e) -> e.getSalary() >= 5000)
                .limit(2)
                .forEach(System.out::println);

        System.out.println("-----------------------");

        //  打印出每个员工的姓名
        employees.stream().map(Employee::getName)
                .forEach(System.out::println);
    }
}
