package org.ahivs.kotlin.basics.interop;

import java.io.IOException;

public class CustomerJava {
    String name;

    public int getAge() {
        return 20;
    }

    public static void main(String[] args) {
        CustomerKotlin customerKotlin = new CustomerKotlin("Tom");
        System.out.println(customerKotlin.title1);
        System.out.println(customerKotlin.getTitle());

        customerKotlin.getInterval(30);
        customerKotlin.getInterval1();
        customerKotlin.getInterval1(20);

        //customerKotlin.printAddress()
        customerKotlin.getAddress();

        customerKotlin.getSalary(20);
        try {
            customerKotlin.getSalary1(20);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //accessing top level funtion
        //TopLevelFunctionsKt.getAge()
        //but if we want to rename the class we can do so
        Utility.getAge();
        System.out.println(Utility.age1);

        //accessing extension Function
        CustomerKotlinKt.doSomething(customerKotlin);
    }
}
