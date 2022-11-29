package com.addressbook;

public class AddressBook {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    private String email;

    AddressBook(String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber, String email) {//Declaring instance variable using constructor
        this.firstName = firstName;// initialize instance variable
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getfirstName() {   // For accessing the private variables
        return firstName;

    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;

    }

    public String getlastName() {
        return lastName;

    }

    public void setLastName(String lastName) {
        this.lastName = lastName;

    }


    public String getAddress() {
        return address;

    }

    public void setAddress(String address) {
        this.address = address;

    }


    public String getcity() {
        return city;

    }

    public void setCity(String city) {
        this.city = city;

    }

    public String getState() {
        return state;

    }

    public void setState(String state) {
        this.state = state;

    }

    public String getPhoneNumber() {
        return phoneNumber;

    } public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;

    }
    public String getZip() {
        return zip;

    }

    public void setZip(String zip) {
        this.zip = zip;

    }





    public String getEmail() {
        return email;

    }

    public void setEmail(String email) {
        this.email = email;

    }

    @Override
    public String toString() {// Override method
        return " First Name: " + firstName + " Last Name: " + lastName + " Address: " + address + " City: " + city + " State: " + state + " Zip: " + zip + " Phone Number: " + phoneNumber + " Email: " + email;

    }

}

