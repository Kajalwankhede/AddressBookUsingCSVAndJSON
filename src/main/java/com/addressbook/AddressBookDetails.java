package com.addressbook;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class AddressBookDetails {
    public static Scanner sc = new Scanner(System.in);
    public static List<AddressBook> list = new ArrayList<AddressBook>();

    private static final String csvFile ="AddressBookCSVData.csv";
    private static final String jsonFile ="AddressBookCSVData.json";
    public void writeData() {
        StringBuffer empBuffer = new StringBuffer();
        list.forEach(employee -> {
            String employeeDataString = employee.toString().concat("\n");
            empBuffer.append(employeeDataString);
        });
        try {
            Files.write(Paths.get("addressBook-file.txt"), empBuffer.toString().getBytes());

        } catch (IOException e) {

        }
    }

    public void readData() {
        try {
            Files.lines(new File("addressBook-file.txt").toPath()).map(line -> line.trim()).forEach(line -> System.out.println(line));

        } catch (IOException e) {

        }
    }

    public void writeDataToCSV() throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        try (Writer writer = Files.newBufferedWriter(Paths.get(csvFile));) {
            StatefulBeanToCsvBuilder<AddressBook> builder = new StatefulBeanToCsvBuilder<>(writer);
            StatefulBeanToCsv<AddressBook> beanWriter = builder.build();
            beanWriter.write(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readDataUsingCSV() throws IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFile));
             CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();) {
            String[] data;
            while ((data = csvReader.readNext()) != null) {
                System.out.println("First Name - " + data[0]);
                System.out.println("Last Name - " + data[1]);
                System.out.println("Address - " + data[2]);
                System.out.println("City - " + data[3]);
                System.out.println("State - " + data[4]);
                System.out.println("Email - " + data[5]);
                System.out.println("Phone - " + data[6]);
                System.out.println("Zip - " + data[7]);
            }
        }
    }
    public void writeDataInJSon() throws IOException {
        {
            Path filePath = Paths.get(jsonFile);
            Gson gson = new Gson();
            String json = gson.toJson(list);
            FileWriter writer = new FileWriter(String.valueOf(filePath));
            writer.write(json);
            writer.close();
        }
    }

    public void readDataFromJson() throws IOException {
        ArrayList<AddressBook> contactList = null;
        Path filePath = Paths.get(jsonFile);
        try (Reader reader = Files.newBufferedReader(filePath);) {
            Gson gson = new Gson();
            list = new ArrayList<AddressBook>(Arrays.asList(gson.fromJson(reader, AddressBook[].class)));
            for (AddressBook data : list) {
                System.out.println("Firstname : " + data.getfirstName());
                System.out.println("Lastname : " + data.getlastName());
                System.out.println("Address : " + data.getAddress());
                System.out.println("City : " + data.getcity());
                System.out.println("State : " + data.getState());
                System.out.println("Zip : " + data.getZip());
                System.out.println("Phone number : " + data.getPhoneNumber());
                System.out.println("Email : " + data.getEmail());

            }
        }
    }



    public void addDetails() {
        System.out.println("Enter the Details: ");
        int check = 0;
        String firstName = null; // For ensure no duplicate entery accepts
        while (check == 0) {
            System.out.println("Enter the First name: ");
            firstName = sc.next();
            if (checkUniqueName(firstName)) {
                System.out.println("Name already exist!!");
            } else {
                check = 1;
            }
        }
        System.out.println("Enter the Last name: ");
        String lastName = sc.next();
        System.out.println("Enter the Address: ");
        String address = sc.next();
        System.out.println("Enter the City: ");
        String city = sc.next();
        System.out.println("Enter the State: ");
        String state = sc.next();
        System.out.println("Enter the Zip code: ");
        String zip = sc.next();
        System.out.println("Enter the Phone Number: ");
        String phoneNumber = sc.next();
        System.out.println("Enter the Email: ");
        String email = sc.next();


        list.add(new AddressBook(firstName, lastName, address, city, state, zip,phoneNumber, email));

    }

    public void displayDetails() {
        if (list.isEmpty()) {
            System.out.println("Not Found!");
        } else {
            for (AddressBook contact : list) {
                System.out.println(contact);
            }
        }

    }

    public boolean updateDetails(String Name) {
        int flag = 0;
        for (AddressBook deatils : list) {
            if (deatils.getfirstName().equals(Name)) {
                System.out.println("Enter option to Update:");
                System.out.println("1.FirstName");
                System.out.println("2.LastName");
                System.out.println("3.Address");
                System.out.println("4.City");
                System.out.println("5.State");
                System.out.println("6.Zip");
                System.out.println("7.Phone Number");
                System.out.println("8.Email");

                int ch = sc.nextInt();
                switch (ch) {
                    case 1: {
                        System.out.println("Enter Updated First Name: ");
                        String firstName = sc.next();
                        deatils.setFirstName(firstName);
                        break;
                    }
                    case 2: {
                        System.out.println("Enter Updated last name: ");
                        String lastName = sc.next();
                        deatils.setLastName(lastName);
                        break;
                    }
                    case 3: {
                        System.out.println("Enter Updated Address: ");
                        String address = sc.next();
                        deatils.setAddress(address);
                        break;
                    }
                    case 4: {
                        System.out.println("Enter Updated City: ");
                        String city = sc.next();
                        deatils.setCity(city);
                        break;
                    }
                    case 5: {
                        System.out.println("Enter Updated State: ");
                        String state = sc.next();
                        deatils.setState(state);
                        break;
                    }
                    case 6: {
                        System.out.println("Enter Updated Zip : ");
                        String zip = sc.next();
                        deatils.setZip(zip);
                        break;
                    }
                    case 7: {
                        System.out.println("Enter Updated Phone Number:");
                        String phoneNumber = sc.next();
                        deatils.setPhoneNumber(phoneNumber);
                        break;
                    }
                    case 8: {
                        System.out.println("Enter Updated Email: ");
                        String email = sc.next();
                        deatils.setEmail(email);
                        break;
                    }

                }

                flag = 1;
                break;
            }
        }
        return flag == 1;
    }

    public boolean deleteDetail(String name) {
        int flag = 0;
        for (AddressBook contact : list) {
            if (contact.getfirstName().equals(name)) {
                list.remove(contact);
                flag = 1;
                break;
            }
        }
        return flag == 1;
    }

    public boolean checkUniqueName(String name) {
        int flag = 0;
        for (AddressBook contact : list) {
            if (contact.getfirstName().equals(name)) {
                flag = 1;
                break;
            }
        }
        if (flag == 1) {
            return true;
        }
        return false;
    }

    public void searchDetails() {
        System.out.println("Details search by \n1. City Name \n2. State Name");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                searchState();
                break;
            case 2:
                searchCity();
            default:
                System.out.println("--------------------");
        }
    }

    public void sortDetails() {
        System.out.println("Details search by\n 1. First Name\n2. State Name\n3. Zip \n 4.City Name");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                sortByFirstName();
                break;
            case 2:
                sortByState();
            case 3:
                sortByzip();
                break;
            case 4:
                sortByCity();
            default:
                System.out.println("--------------------------------------------------------");
        }
    }

    public void countByStateOrCity() {
        System.out.println("Details count by \n1. City Name \n2. State Name");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                countByCity();
                break;
            case 2:
                countByState();
            default:
                System.out.println("--------------------------------------------------------");
        }
    }

    public static void searchState() {
        list.stream()
                .filter(state -> state.getState().equalsIgnoreCase("Maharashtra"))
                .sorted(Comparator.comparing(AddressBook::getfirstName))
                .forEach(state -> System.out.println(state.getfirstName()));
        System.out.println(list);
    }

    public static void searchCity() {

        list.stream()
                .filter(cityName -> cityName.getcity().equalsIgnoreCase("mumbai"))
                .sorted(Comparator.comparing(AddressBook::getfirstName))
                .forEach(cityName -> System.out.println(cityName.getfirstName()));
        System.out.println(list);

    }

    public static void countByState() {

        long count = list.stream()
                .filter(stateName -> stateName.getState().equalsIgnoreCase("maharashtra"))
                .count();
        System.out.println(count);
    }

    public static void countByCity() {


        long count = list.stream()
                .filter(cityName -> cityName.getcity().equalsIgnoreCase("mumbai"))
                .count();
        System.out.println(count);
    }

    public static void sortByFirstName() {

        list.stream()
                .sorted(Comparator.comparing(AddressBook::getfirstName))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public static void sortByState() {
        list.stream()
                .sorted(Comparator.comparing(AddressBook::getState))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public static void sortByCity() {
        list.stream()
                .sorted(Comparator.comparing(AddressBook::getcity))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public static void sortByzip() {
        list.stream()
                .sorted(Comparator.comparing(AddressBook::getZip))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }



    }
