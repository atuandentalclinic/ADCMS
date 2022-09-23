package com.example.adcms_mobileapp_v2;

public class Employee {
    private String name;
    private String position;
    private String contacts;
    private String address;

    public Employee(){}
    public Employee (String name,String position, String contacts, String address)
    {
        this.name = name;
        this.position = position;
        this.contacts = contacts;
        this.address = address;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
