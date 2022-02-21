package com.test;

public class Customer {
    private final String name;
    private final String age;
    private final String balance;
    private final String email;
    private final String gender;
    private final String registeredDate;
    private final String address;

    public Customer(
            String name,
            String age,
            String balance,
            String email,
            String gender,
            String registeredDate,
            String address
    ) {
        this.name = name;
        this.age = age;
        this.balance = balance;
        this.email = email;
        this.gender = gender;
        this.registeredDate = registeredDate;
        this.address = address;
    }

    String getInfo() {
        String info = "Name\t\t: %s\n" +
                "Age\t\t\t: %s\n" +
                "Balance\t\t: %s\n" +
                "Email\t\t: %s\n" +
                "Gender\t\t: %s\n" +
                "Registered\t: %s\n" +
                "Address\t\t: %s\n";

        return String.format(
                info,
                this.name,
                this.age,
                this.balance,
                this.email,
                this.gender,
                this.registeredDate,
                this.address);

    }


}
