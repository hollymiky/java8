package com.ann.demo11;

import com.ann.demo1.entity.Employee;
import com.ann.demo11.entity.Children;
import com.ann.demo11.entity.NewPerson;
import com.ann.demo11.entity.Person;
import org.junit.Test;

import java.util.Optional;

/**
 * @author longquan
 * @date 2020/4/30 5:12 下午
 * <p>
 * Optional 容器
 * **常用方法**：
 * 1. Optional.of(T t)：创建一个Optional实例
 * 2. Optional.empty()：创建一个空的Optional实例
 * 3. Optional.ofNullable(T t)：若t不为null，创建Optional实例，否则创建空实例
 * 4. isPresent()：判断是否包含值
 * 5. orElse(T t)：如果调用对象包含值，返回该值，否则返回t
 * 6. orElseGet(Supplier s)：如果调用对象包含值，返回该值，否则返回s获取的值
 * 7. map(Function f)：如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
 * 8. flatMap(Function mapper)：与map类似，要求返回值必须是Optional
 */
public class OptionalDemo01 {

    /**
     * 例子2
     */
    @Test
    public void test10(){
        // Optional<NewPerson> newPerson = Optional.ofNullable(null);
        Optional<Children> oc = Optional.ofNullable(new Children("😂"));
        Optional<NewPerson> newPerson = Optional.ofNullable(new NewPerson(oc));
        String name = getChildrenName2(newPerson);
        System.out.println(name);
    }

    /**
     * 获取某个人的孩子的名字
     */
    public String getChildrenName2(Optional<NewPerson> newPerson){
        return newPerson.orElse(new NewPerson())
                .getChildren().orElse(new Children("默认孩子名字")).getName();
    }

    /**
     * 例子1
     */
    @Test
    public void test9(){
        Person person = new Person();
        String name = getChildrenName(person);
        System.out.println(name);
    }

    /**
     * 获取某个人的孩子的名字
     */
    public String getChildrenName(Person person){
        return person.getChildren().getName();
    }

    /**
     * 8. flatMap(Function mapper)：与map类似，要求返回值必须是Optional
     */
    @Test
    public void test8() {
        Optional<Employee> op = Optional.ofNullable(new Employee(1, "miky", 5, 9999, Employee.Status.FREE));
        Optional<String> name = op.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(name.get());
    }

    /**
     * 7. map(Function f)：如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
     */
    @Test
    public void test7() {
        Optional<Employee> op = Optional.ofNullable(new Employee(1, "miky", 5, 9999, Employee.Status.FREE));
        Optional<String> name = op.map(Employee::getName);
        System.out.println(name.get());
    }

    /**
     * 6. orElseGet(Supplier s)：如果调用对象包含值，返回该值，否则返回s获取的值
     */
    @Test
    public void test6() {
        Optional<Employee> op = Optional.ofNullable(new Employee());
        Employee emp = op.orElseGet(() -> new Employee(1, "miky", 5, 9999, Employee.Status.FREE));
        System.out.println(emp);
    }

    /**
     * 5. orElse(T t)：如果调用对象包含值，返回该值，否则返回t
     */
    @Test
    public void test5() {
        Optional<Employee> op = Optional.ofNullable(null);
        Employee emp = op.orElse(new Employee(1, "miky", 5, 9999, Employee.Status.FREE));
        System.out.println(emp);
    }

    /**
     * 4. isPresent()：判断是否包含值
     */
    @Test
    public void test4() {
        Optional<Employee> op = Optional.ofNullable(new Employee());
        if (op.isPresent()) {
            System.out.println(op.get());
        }

        System.out.println("-----------------------------");

        Optional<Employee> op1 = Optional.ofNullable(null);
        if (op1.isPresent()) {
            System.out.println(op1.get());
        } else {
            System.out.println("null data");
        }
    }

    /**
     * 3. Optional.ofNullable(T t)：若t不为null，创建Optional实例，否则创建空实例
     */
    @Test
    public void test3() {
        Optional<Employee> op = Optional.ofNullable(new Employee());
        System.out.println(op.get());

        System.out.println("-----------------------------");

        Optional<Employee> op1 = Optional.ofNullable(null);
        System.out.println(op1.get());
    }

    /**
     * 2. Optional.empty()：创建一个空的Optional实例
     */
    @Test
    public void test2() {
        Optional<Employee> op = Optional.empty();
        System.out.println(op.get());
    }

    /**
     * 1. Optional.of(T t)：创建一个Optional实例
     */
    @Test
    public void test1() {

        Optional<Employee> op = Optional.of(new Employee());
        System.out.println(op.get());

        System.out.println("-----------------------------");


        Optional<Employee> op1 = Optional.of(null);
        System.out.println(op1.get());

    }
}
