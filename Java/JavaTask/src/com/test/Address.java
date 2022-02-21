package com.test;

public class Address {

    private final String street;
    private final String city;
    private final String state;
    private final String postCode;

    public Address(
            String street,
            String city,
            String state,
            String postCode
    ) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postCode = postCode;
    }

    public String getAddress() {
        return String.join(", ",
                this.street,
                this.city,
                this.state,
                this.postCode);
    }
}
