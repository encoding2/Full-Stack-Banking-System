package com.example.dto;

import java.time.LocalDate;

public class CustomerDTO {

    private String name;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private String aadhaar;
    private String pan;
    private Boolean kycStatus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAadhaar() {
        return aadhaar;
    }

    public void setAadhaar(String aadhaar) {
        this.aadhaar = aadhaar;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public Boolean getKycStatus() {
        return kycStatus;
    }

    public void setKycStatus(Boolean kycStatus) {
        this.kycStatus = kycStatus;
    }
}
