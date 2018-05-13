package com.customersscheduling.DTO;

public class BookingDto {


    private int id;
    private String clientId;
    private String staffId;
    private int serviceId;
    private int storeId;

    public BookingDto(int id, String clientId, String staffId, int serviceId, int storeId) {
        this.id = id;
        this.clientId = clientId;
        this.staffId = staffId;
        this.serviceId = serviceId;
        this.storeId = storeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

}
