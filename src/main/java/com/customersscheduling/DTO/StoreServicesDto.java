package com.customersscheduling.DTO;

public class StoreServicesDto {
    private String staffId;
    private int storeId;
    private int serviceId;

    public StoreServicesDto(String staddId, int storeId, int serviceId) {
        this.staffId = staddId;
        this.storeId = storeId;
        this.serviceId = serviceId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

}
