package com.customersscheduling.DTO;

public class BusinessDto {
    private String companyName;
    private int NIF;

    public BusinessDto(String companyName, int nif) {
        this.companyName = companyName;
        NIF = nif;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getNIF() {
        return NIF;
    }

    public void setNIF(int NIF) {
        this.NIF = NIF;
    }
}
