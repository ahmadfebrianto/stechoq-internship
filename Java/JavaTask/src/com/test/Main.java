package com.test;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        File file = new File("src/com/test/customers.json");
        JSONArray jsonArray = readFile(file);
        if (jsonArray != null) {
            ArrayList<Customer> customers = getCustomers(jsonArray);

            while (true) {
                try {
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Customer number: ");
                    int number = scanner.nextInt();
                    if (number <= customers.toArray().length) {
                        System.out.println(customers.get(number - 1).getInfo());
                    } else {
                        System.out.println("Customer not found!");
                    }
                } catch (Exception e) {
                    System.out.println("Input invalid!");
                }
            }
        }

    }

    private static JSONArray readFile(File file) {
        JSONParser parser = new JSONParser();
        try {
            FileReader fileReader = new FileReader(file);
            return (JSONArray) parser.parse(fileReader);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Customer parseCustomer(JSONObject jsonObject) {
        String name = jsonObject.get("name").toString();
        String age = jsonObject.get("age").toString();
        String balance = jsonObject.get("balance").toString();
        String email = jsonObject.get("email").toString();
        String gender = jsonObject.get("gender").toString();
        String registeredDate = jsonObject.get("registered").toString();

        JSONObject addressObject = (JSONObject) jsonObject.get("address");
        String street = addressObject.get("street").toString();
        String city = addressObject.get("city").toString();
        String state = addressObject.get("state").toString();
        String postCode = addressObject.get("postcode").toString();
        Address address = new Address(street, city, state, postCode);
        return new Customer(
                name, age, balance, gender, email, registeredDate, address.getAddress()
        );
    }

    private static ArrayList<Customer> getCustomers(JSONArray jsonArray) {
        ArrayList<Customer> customers = new ArrayList<>();
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            Customer customer = parseCustomer(jsonObject);
            customers.add(customer);
        }
        return customers;
    }
}


