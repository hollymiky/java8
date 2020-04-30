package com.ann.demo14;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * @author longquan
 * @date 2020/4/30 20:29
 *
 * java 8
 * LocalDate、 LocalTime、 LocalDateTime
 * 这三者使用方式都一样
 */
public class LocalDateTimeDemo {

    @Test
    public void test8(){
        //  指定一个时区构建一个时间
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(localDateTime);

        LocalDateTime localDateTime1 =  LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime1.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDateTime);
    }

    /**
     * ZonedDate、ZonedTime、ZonedDateTime
     */
    @Test
    public void test7(){
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);
    }

    /**
     * DateTimeFormatter : 格式化时间/日期
     */
    @Test
    public void test6(){
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime localDateTime = LocalDateTime.now();
        String s = localDateTime.format(dtf);
        System.out.println(s);

        System.out.println("---------------------------");
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String s1 = dtf2.format(localDateTime);
        System.out.println(s1);

        LocalDateTime localDateTime1 = localDateTime.parse(s1,dtf2);
        System.out.println(localDateTime1);

    }


    /**
     * TemporalAdjuster ： 时间校正器
     */
    @Test
    public void test5(){
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        LocalDateTime localDateTime1 = localDateTime.withDayOfMonth(10);
        System.out.println(localDateTime1);

        LocalDateTime localDateTime2 = localDateTime.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(localDateTime2);

        //  自定义：下一个工作日
        LocalDateTime localDateTime4 = localDateTime.with((l) -> {
           LocalDateTime localDateTime3 = (LocalDateTime) l;
           DayOfWeek dayOfWeek = localDateTime3.getDayOfWeek();
           if(dayOfWeek.equals(DayOfWeek.FRIDAY)){
               return localDateTime3.plusDays(3);
           }else if(localDateTime3.equals(DayOfWeek.SATURDAY)){
               return localDateTime3.plusDays(2);
           }else{
               return localDateTime3.plusDays(1);
           }
        });
        System.out.println(localDateTime4);
    }

    /**
     * Period：计算两个‘日期’之间的间隔
     */
    @Test
    public void test4(){
        LocalDate localDate = LocalDate.of(2015,1,1);
        LocalDate localDate1 = LocalDate.now();
        Period period = Period.between(localDate,localDate1);
        System.out.println(period);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }

    /**
     * 3. 计算时间之间的间隔
     * Duration：计算两个‘时间’之间的间隔
     * Period：计算两个‘日期’之间的间隔
     */
    @Test
    public void test3(){

        //  Duration：计算两个‘时间’之间的间隔
        Instant instant1 = Instant.now();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Instant instant2 = Instant.now();

        Duration duration = Duration.between(instant1,instant2);
        System.out.println(duration.toMillis());

        LocalTime localTime1 = LocalTime.now();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LocalTime localTime2 = LocalTime.now();

        System.out.println(Duration.between(localTime1, localTime2).toMillis());


    }

    /**
     * 机读
     * 2. Instant : 时间戳(以Unix元年：1970年1月1日 00:00:00 到某个时间之间的毫秒值)
     */
    @Test
    public void test2(){
        // 默认获取 UTC 时区
        Instant instant = Instant.now();
        System.out.println(instant);

        System.out.println("----------------------------");

        //  偏移
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        System.out.println("----------------------------");

        //  时间戳 毫秒
        System.out.println(instant.toEpochMilli());

        System.out.println("----------------------------");

        //  相较 Unix 元年加个时间运算
        Instant instant1 = Instant.ofEpochSecond(60);
        System.out.println(instant1);


    }


    /**
     * 人读
     * 1. LocalDate、 LocalTime、 LocalDateTime
     */
    @Test
    public void test(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        System.out.println("----------------------------");

        LocalDateTime ldt2 = LocalDateTime.of(2019,12,16,5,20,20);
        System.out.println(ldt2);

        System.out.println("----------------------------");

        LocalDateTime ldt3 = ldt.plusYears(2);
        System.out.println(ldt3);

        System.out.println("----------------------------");

        LocalDateTime ldt4 = ldt.minusYears(2);
        System.out.println(ldt4);

        System.out.println("----------------------------");

        System.out.println(ldt.getYear());
        System.out.println(ldt.getMonthValue());
        System.out.println(ldt.getDayOfMonth());
        System.out.println(ldt.getHour());
        System.out.println(ldt.getMinute());
        System.out.println(ldt.getSecond());

        System.out.println("----------------------------");


    }
}
