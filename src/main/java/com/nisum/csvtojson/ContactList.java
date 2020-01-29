package com.nisum.csvtojson;

import java.io.Serializable;

public class ContactList implements Serializable {
    private String  department;
    private String firstName;
    private String lastName;
    private String primaryContact;
    private String emailId;
    private String phno;
    private String role;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(String primaryContact) {
        this.primaryContact = primaryContact;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    @Override
    public String toString() {
        return "ContactList{" +
                "department='" + department + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", primaryContact='" + primaryContact + '\'' +
                ", emailId='" + emailId + '\'' +
                ", phno='" + phno + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ContactList(String department, String firstName, String lastName, String primaryContact, String emailId, String phno, String role) {
        this.department = department;
        this.firstName = firstName;
        this.lastName = lastName;
        this.primaryContact = primaryContact;
        this.emailId = emailId;
        this.phno = phno;
        this.role = role;
    }

    public ContactList() {
    }
}
