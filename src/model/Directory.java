package model;

import java.time.LocalDate;

public class Directory {
    private String phoneNumber;
    private String groupPhone;
    private String userName;
    private String gender;
    private String address;
    private LocalDate dob;
    private String email;
    public Directory(){}

    public Directory(String phoneNumber, String groupPhone, String userName, String gender, String address, LocalDate dob, String email) {
        this.phoneNumber = phoneNumber;
        this.groupPhone = groupPhone;
        this.userName = userName;
        this.gender = gender;
        this.address = address;
        this.dob = dob;
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGroupPhone() {
        return groupPhone;
    }

    public void setGroupPhone(String groupPhone) {
        this.groupPhone = groupPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Directory{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", groupPhone='" + groupPhone + '\'' +
                ", userName='" + userName + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", dob='" + dob + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
