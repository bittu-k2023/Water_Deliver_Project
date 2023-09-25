package com.example.water_deliver_project;

public class UserDetailsHolder {
    String Name;
    String Address;
    String City;
    String State;
    String Country;
    String Zipcode;
    String Email;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getZipcode() {
        return Zipcode;
    }

    public void setZipcode(String zipcode) {
        Zipcode = zipcode;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public UserDetailsHolder(String name, String address, String city, String state, String country, String zipcode, String email) {
        Name = name;
        Address = address;
        City = city;
        State = state;
        Country = country;
        Zipcode = zipcode;
        Email = email;
    }
}
